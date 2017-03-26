#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <unistd.h>
#include <fcntl.h>
#include <semaphore.h>
#include <pthread.h>
#include <string.h>

#include "thread_handler.h"

void proc(int procno, int m, sem_t* sem, sem_t* notifier) {
    int i;
    pthread_t* threads = malloc(sizeof(pthread_t) * m);
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