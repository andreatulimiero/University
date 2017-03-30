#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <unistd.h>
#include <fcntl.h>
#include <semaphore.h>
#include <pthread.h>
#include <string.h>

#include "thread_handler.h"
#include "proc_handler.h"
#include "utils.h"


int get_line(int fd, char* line) {
    char* c = malloc(sizeof(char));
    int i;
    for (i = 0; read(fd, c, 1); i++) { 
        if (*c == '\n') return i;
        line[i] = *c;
    }
    return EOF;
}

void print_most_frequent_process(int n) {
    int i;
    int* freqs = malloc(sizeof(int) * n);
    int fd = open(FILE_NAME, O_RDONLY, 0640);
    ERROR_HANDLER(fd, "Error opening file");
    char* line = malloc(sizeof(char) * 1024);
    while (get_line(fd, line) != EOF) {
        int procno = atoi(line);
        freqs[procno]++;
    }
    int most_freq = 0;
    for (i = 1; i < n; i++) 
        if (freqs[i] > freqs[most_freq])
            most_freq = i;
        
    printf("Process with most accesses is %d with %d accesses\n", most_freq + 1, freqs[most_freq]);
}

void notify_start(sem_t* notifier) {
    printf("Notifing start\n");
    ERROR_HANDLER(sem_wait(notifier), "Error requesting start through wait")
}

void notify_stop(sem_t* notifier) {
    printf("Notifing stop\n");
    ERROR_HANDLER(sem_wait(notifier), "Error requesting stop through stop");
}

sem_t* get_sem(char* name, int resources) {
    sem_t* sem = sem_open(name, O_CREAT | O_EXCL, 0600, resources);
    if (sem == SEM_FAILED && errno == EEXIST) {
        printf("Semaphore %s already up, unlinking it\n", name);
        sem_unlink(SEM_NAME);
        sem = sem_open(name, O_CREAT | O_EXCL, 0600, resources);
        GENERAL_ERROR_HANDLER(sem == SEM_FAILED, errno, "Error opening semaphore");
    }
}

void clean_and_close() {
    ERROR_HANDLER(sem_unlink(SEM_NOT_NAME), "Error unlinking notification semaphore");
    ERROR_HANDLER(sem_unlink(SEM_NAME), "Error unlinking file write semaphore");
}

int main(int argc, char** argv) {
    if (argc < 3) {
        fprintf(stderr, "usage [<processes number> <threads number>]\n");
        exit(1);
    }

    int n = atoi(argv[1]);
    int threadsno = atoi(argv[2]);
    
    sem_t* sem = get_sem(SEM_NAME, NUM_RESOURCES);
    sem_t* notifier = get_sem(SEM_NOT_NAME, 2);
    sem_t* sync = get_sem(SEM_SYNC_NAME, 0);

    /* Cleaning output file previously used */
    int fd = open(FILE_NAME, O_CREAT | O_TRUNC, 0640);
    char* msg = malloc(sizeof(char) * 1024);
    sprintf(msg, "Error opening %s", FILE_NAME);
    ERROR_HANDLER(fd, msg);
    close(fd);

    int i;
    pid_t* pid_list = malloc(sizeof(pid_t) * n);
    for (i = 0; i < n; i++) {
        pid_list[i] = fork();
        if (pid_list[i] == 0) {
            printf("Proc %d spawned and waiting for start\n", i);
            proc(i, threadsno);
        } else if (pid_list[i] < 0) {
            fprintf(stderr, "Error spawning process\n");
            exit(1);
        }
    }

    /* Wait for all the proc to be ready */
    printf("Waiting for processes to be ready\n");
    for (i = 0; i < n; i++) sem_wait(sync);
    printf("All processes are ready\n");

    notify_start(notifier);
    sleep(WAIT_TIME);
    notify_stop(notifier);
    
    for (i = 0; i < n; i++) {
        waitpid(pid_list[i], NULL, 0);
        printf("Proc %d terminated\n", i);
    }

    print_most_frequent_process(n);

    clean_and_close(sem, notifier);
    return 0;
}