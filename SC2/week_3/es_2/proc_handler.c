#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <unistd.h>
#include <fcntl.h>
#include <semaphore.h>
#include <pthread.h>
#include <string.h>

#include "utils.h"
#include "thread_handler.h"

void proc(int procno, int m) {
    int i;
    sem_t* notifier = sem_open(SEM_NOT_NAME, 0);
    GENERAL_ERROR_HANDLER(notifier == SEM_FAILED, errno, "Error opening notifier semaphore");
    sem_t* sync = sem_open(SEM_SYNC_NAME, 0);
    GENERAL_ERROR_HANDLER(sync == SEM_FAILED, errno, "Error opening sync semaphore");
    sem_post(sync);
    pthread_t* threads = malloc(sizeof(pthread_t) * m);
    int notif_val; sem_getvalue(notifier, &notif_val);
    while (notif_val != 1) sem_getvalue(notifier, &notif_val);
    printf("Start notification received from proc %d\n", procno);
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

    ERROR_HANDLER(sem_close(notifier), "Impossible closing the SEM_NOT");
    _exit(0);
}