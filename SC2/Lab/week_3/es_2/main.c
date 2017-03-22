#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <unistd.h>
#include <semaphore.h>
#include <pthread.h>

#include "utils.h"

#define WAIT_TIME 1

void proc(int procno, int m) {
    int i;
    pthread_t* threads = malloc(sizeof(pthread_t) * m);
    for (i = 0; i < m; i++) {
        pthread_create(&threads[i], NULL, handler, NULL);
    }
}

int main(int argc, char** argv) {
    if (argc < 3) {
        fprintf(stderr, "usage <process number> <threads number>\n");
        exit(1);
    }

    int n = atoi(argv[1]);
    int m = atoi(argv[2]);
    
    sem_t* sem = sem_open(SEM_NAME, O_CREAT | O_EXCL, 0600, 0);
    if (sem == SEM_FAILED && errno == EEXIST) {
        printf("Semaphore already up, unlinking it\n");
        sem_unlink(SEM_NAME);
        sem = sem_open(SEM_NAME, O_CREAT | O_EXCL, 0600, 0);
        ERROR_HANDLER(errno, "Error creating semaphore\n");
    }

    int i;
    pid_t* pid_list = malloc(sizeof(pid_t) * n);
    for (i = 0; i < n; i++) {
        proc_list[i] = fork();
        if (pid_list[i] == 0)
            exec_proc(i);
        else if (pid_list < 0) {
            fprintf(stderr, "Error spawning process\n");
            exit(1);
        }
    }
    sleep(WAIT_TIME);

    return 0;
}
