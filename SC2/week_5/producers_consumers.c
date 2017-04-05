#include <string.h>
#include <semaphore.h>
#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <time.h>       // nanosleep()

#include "common.h"		// macros for error handling

#define BUFFER_SIZE         128
#define INITIAL_DEPOSIT     0
#define MAX_TRANSACTION     1000
#define CONSUMERS           2
#define PRODUCERS           2

struct timespec pause_interval; // used by nanosleep()

typedef struct thread_args_s {
    sem_t* fill_count;
    sem_t* empty_count;
    sem_t* job_sem;
} thread_args_t;

unsigned int read_idx;
unsigned int write_idx;
int transactions[BUFFER_SIZE];
int balance;

/** Auxiliary method to simulate a random non-zero transaction **/
static inline int performRandomTransaction() {
    nanosleep(&pause_interval, NULL);

    int amount = rand() % (2 * MAX_TRANSACTION); // {0, ..., 2*MAX_TRANSACTION - 1}
    if (amount++ >= MAX_TRANSACTION) {
        // now amount is in {MAX_TRANSACTION + 1, ..., 2*MAX_TRANSACTION}
        return MAX_TRANSACTION - amount; // {-MAX_TRANSACTION, ..., -1}
    } else {
        // now amount is a number between 1 and MAX_TRANSACTION
        return amount;
    }

}

void* performTransaction(void* thread_args) {
    thread_args_t* args = (thread_args_t*) thread_args;
    int ret;
    while(1) {
        int transaction = performRandomTransaction();
        ret = sem_wait(args->empty_count);
        ERROR_HELPER(ret, "Locking empty count sempahore");
        ret = sem_wait(args->job_sem);
        ERROR_HELPER(ret, "Locking writers sempahore");
        transactions[write_idx] = transaction;
        write_idx = BUFFER_SIZE % (++write_idx);
        ret = sem_post(args->job_sem);
        ret = sem_post(args->fill_count);
        ERROR_HELPER(ret, "Posting writers sempahore");
        ERROR_HELPER(ret, "Posting fill count sempahore");
    }
}

void* processTransaction(void* thread_args) {
    thread_args_t* args = (thread_args_t*) thread_args;
    int ret;
    LOG("Processing transactions ...");
    while(1) {
        ret = sem_wait(args->fill_count);
        ERROR_HELPER(ret, "Locking fill count semaphore");
        ret = sem_wait(args->job_sem);
        ERROR_HELPER(ret, "Locking readers sempahore");
        balance += transactions[read_idx];
        printf("Balance is now: %d$\n", balance);
        read_idx = BUFFER_SIZE % (++read_idx);
        ret = sem_post(args->job_sem);
        ERROR_HELPER(ret, "Posting readers sempahore");
        ret = sem_post(args->empty_count);
        ERROR_HELPER(ret, "Posting empty count sempahore");
    }
}

int main(int argc, char** argv) {
    int ret;

    printf("Welcome! This program simulates financial transactions on a deposit.\n");
    printf("\nThe maximum amount of a single transaction is %d (negative or positive).\n", MAX_TRANSACTION);
    printf("\nInitial balance is %d. Press CTRL+C to quit.\n\n", INITIAL_DEPOSIT);

    pause_interval.tv_sec = 1; // Interval between a transaction and another

    pthread_t* producers = calloc(PRODUCERS, sizeof(pthread_t));
    pthread_t* consumers = calloc(CONSUMERS, sizeof(pthread_t));

    sem_t* fill_count = malloc(sizeof(sem_t));
    sem_t* empty_count = malloc(sizeof(sem_t));
    sem_t* reader_sem = malloc(sizeof(sem_t));
    sem_t* writer_sem = malloc(sizeof(sem_t));
    sem_init(fill_count, 0, 0);
    sem_init(empty_count, 0, BUFFER_SIZE);
    sem_init(reader_sem, 0, 1);
    sem_init(writer_sem, 0, 1);

    unsigned int i;
    for (i = 0; i < PRODUCERS; i++) {
        thread_args_t *args = malloc(sizeof(thread_args_t));
        args->empty_count = empty_count;
        args->fill_count = fill_count;
        args->job_sem = writer_sem;
        ret = pthread_create(&producers[i], NULL, &performTransaction, args);
        PTHREAD_ERROR_HELPER(ret, "Could not create producer thread");
        pthread_detach(producers[i]);
    }

    for (i = 0; i < CONSUMERS; i++) {
        thread_args_t *args = malloc(sizeof(thread_args_t));
        args->empty_count = empty_count;
        args->fill_count = fill_count;
        args->job_sem = reader_sem;
        ret = pthread_create(&consumers[i], NULL, &processTransaction, args);
        PTHREAD_ERROR_HELPER(ret, "Could not create consumer thread");
        pthread_detach(consumers[i]);
    }

    free(producers);
    free(consumers);
    pthread_exit(NULL);
}