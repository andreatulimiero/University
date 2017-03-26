#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <unistd.h>
#include <fcntl.h>
#include <semaphore.h>
#include <pthread.h>
#include <string.h>

#include "utils.h"

#define SEM_NAME "/semaphore"
#define FILE_NAME "out.txt"

void* handler(void* args) {
    int fatherno = *((int*) args);
    int fd = open(FILE_NAME, O_CREAT | O_APPEND | O_WRONLY, 0640);
    int digits = (fatherno / 10) + 1;
    char* buff = calloc(digits + 2, sizeof(char));
    sprintf(buff, "%d\n", fatherno);

    sem_t* sem = sem_open(SEM_NAME, 0);
    //TODO: handle error in opening semaphore

    if (sem == SEM_FAILED) {
        printf("[FATAL ERROR] Could not open the named semaphore from thread %d, the reason is: %s\n", fatherno, strerror(errno));
        exit(1);
    }

    ERROR_HANDLER(sem_wait(sem), "Could not lock the semaphore");

    //printf("Acquired resource, writing %s\n", buff, FILE_NAME);
    ERROR_HANDLER(write(fd, buff, strlen(buff)), "Error writing to file")

    ERROR_HANDLER(sem_post(sem), "[FATAL ERROR] Could not unlock the semaphore");

    ERROR_HANDLER(sem_close(sem), "Could not close the semaphore");

    char* buffer = malloc(sizeof(1024));
    sprintf(buffer, "Error closing %s", FILE_NAME);
    ERROR_HANDLER(close(fd), buffer);

    pthread_exit(0);
}
