#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <unistd.h>
#include <fcntl.h>
#include <semaphore.h>
#include <pthread.h>
#include <string.h>

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

    if (sem_wait(sem)) {
        printf("[FATAL ERROR] Could not lock the semaphore from thread %d, the reason is: %s\n", fatherno, strerror(errno));
        exit(1);
    }

    printf("Acquired resource, writing %s\n", buff, FILE_NAME);
    write(fd, buff, strlen(buff));

    if (sem_post(sem)) {
        printf("[FATAL ERROR] Could not unlock the semaphore from thread %d, the reason is: %s\n", fatherno, strerror(errno));
        exit(1);
    }
    sem_close(sem);
    if (close(fd)) {
	fprintf(stderr, "Error closing %s: %s\n", FILE_NAME, strerror(errno));
	exit(1);
    }

    pthread_exit(0);
}
