#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <unistd.h>
#include <fcntl.h>
#include <semaphore.h>
#include <pthread.h>
#include <string.h>

#include "thread_handler.h"

#define NUM_RESOURCES 1
#define WAIT_TIME 1
#define SEM_NAME "/semaphore"
#define SEM_NOT_NAME "/semaphore-notification"
#define FILE_NAME "out.txt"

void proc(int procno, int m, sem_t* sem, sem_t* notifier) {
    int i;
    pthread_t* threads = malloc(sizeof(pthread_t) * m);
    //TODO: replace should_stop with a var set by the main proc in the notify function
    int notif_val; sem_getvalue(notifier, &notif_val);
    while (notif_val != 1) sem_getvalue(notifier, &notif_val);
    do {
	for (i = 0; i < m; i++) {
	    pthread_create(&threads[i], NULL, &handler, (void*) &procno);
	}
	for (i = 0; i < m; i++) {
	    pthread_join(threads[i], NULL);
	    //printf("Thread %d in proc %d finished\n", i, procno);
	}
	sem_getvalue(notifier, &notif_val);
    } while (notif_val);

    _exit(0);
}

int get_line(int fd, char* line) {
    char* c = malloc(sizeof(char));
    int i;
    for (i = 0; read(fd, c, 1); i++) { 
	if (*c == '\n') return i;
	line[i] = *c;
    }
    return EOF;
}

int get_most_frequent_process(int n) {
    int i;
    int* freqs = malloc(sizeof(int) * n);
    int fd = open(FILE_NAME, O_RDONLY, 0640);
    char* line = malloc(sizeof(char) * 1024);
    while (get_line(fd, line) != EOF) {
	int procno = atoi(line);
	freqs[procno]++;
    }
    int most_freq = 0;
    for (i = 1; i < n; i++) 
	if (freqs[i] > freqs[most_freq])
	    most_freq = i;
    return most_freq;
}

void notify_start(sem_t* notifier) {
    printf("Notifing start\n");
    sem_wait(notifier);
}

void notify_stop(sem_t* notifier) {
    printf("Notifing stop\n");
    sem_wait(notifier);
}

void clean() {
    sem_unlink(SEM_NOT_NAME);
    sem_unlink(SEM_NAME);
}

int main(int argc, char** argv) {
    if (argc < 3) {
        fprintf(stderr, "usage [<processes number> <threads number>]\n");
        exit(1);
    }

    int n = atoi(argv[1]);
    int threadsno = atoi(argv[2]);
    
    sem_t* sem = sem_open(SEM_NAME, O_CREAT | O_EXCL, 0600, NUM_RESOURCES);
    if (sem == SEM_FAILED && errno == EEXIST) {
        printf("Semaphore already up, unlinking it\n");
        sem_unlink(SEM_NAME);
        sem = sem_open(SEM_NAME, O_CREAT | O_EXCL, 0600, NUM_RESOURCES);
        //ERROR_HANDLER(errno, "Error creating semaphore\n");
    }

    sem_t* notifier = sem_open(SEM_NOT_NAME, O_CREAT | O_EXCL, 0600, 2);
    if (notifier == SEM_FAILED && errno == EEXIST) {
        printf("Notification semaphore already up, unlinking it\n");
        sem_unlink(SEM_NOT_NAME);
        notifier = sem_open(SEM_NOT_NAME, O_CREAT | O_EXCL, 0600, 2);

        //ERROR_HANDLER(errno, "Error creating semaphore\n");
    }
    if (notifier == SEM_FAILED) {
	printf("Impossible to create the semaphore since %s\n", strerror(errno));
    }

    /* Cleaning output file previously used */
    int fd = open(FILE_NAME, O_CREAT | O_TRUNC, 0640);
    close(fd);

    int i;
    pid_t* pid_list = malloc(sizeof(pid_t) * n);
    for (i = 0; i < n; i++) {
        pid_list[i] = fork();
        if (pid_list[i] == 0) {
	    printf("Proc %d spawned and waiting for start\n", i);
            proc(i, threadsno, sem, notifier);
	} else if (pid_list[i] < 0) {
            fprintf(stderr, "Error spawning process\n");
            exit(1);
        }
    }
    notify_start(notifier);
    sleep(WAIT_TIME);
    notify_stop(notifier);
    for (i = 0; i < n; i++) {
	waitpid(pid_list[i], NULL, 0);
	printf("Proc %d terminated\n", i);
    }
    printf("Process with most accesses is %d\n", get_most_frequent_process(n));

    clean(sem, notifier);
    return 0;
}
