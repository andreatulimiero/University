#include <pthread.h>
#include <semaphore.h>
#include "common.h"

#define BUFFER_LENGTH 50
#define THREAD_COUNT 10
#define ITERATION_COUNT 50
#define PROD_ROLE 0
#define CONS_ROLE 1

typedef struct thread_args_s {
    int idx;
    int role;
} thread_args_t;

int buffer[BUFFER_LENGTH];
int write_index, read_index;

/**
 * COMPLETARE QUI
 *
 * Obiettivi:
 * - dichiarare i semafori necessari
 *
 */

 sem_t* fill_sem;
 sem_t* empty_sem;
 sem_t* write_sem;


void enqueue(int x) {

    /**
     * COMPLETARE QUI
     *
     * Obiettivi:
     * - completare l'implementazione secondo il paradigma
     *   multi-prod/single-cons
     * - gestire gli errori
     *
     */
     int ret;

     ret = sem_wait(empty_sem);
     ERROR_HELPER(ret, "Error waiting empty_sem");
     
     ret = sem_wait(write_sem);
     ERROR_HELPER(ret, "Error waiting write_sem");
     buffer[write_index % BUFFER_LENGTH] = x;
     write_index++;
     
     ret = sem_post(write_sem);
     ERROR_HELPER(ret, "Error posting to write_sem");
     
     ret = sem_post(fill_sem);
     ERROR_HELPER(ret, "Error posting to fill_sem");

}

int dequeue() {

    /**
     * COMPLETARE QUI
     *
     * Obiettivi:
     * - completare l'implementazione secondo il paradigma
     *   multi-prod/single-cons
     * - gestire gli errori
     *
     */
     int ret;
     ret = sem_wait(fill_sem);
     ERROR_HELPER(ret, "Error waiting fill_sem");

     int value = buffer[read_index % BUFFER_LENGTH];
     ret = sem_post(empty_sem);
     
     ERROR_HELPER(ret, "Error postin to empty_sem");
     read_index++;
     return value;
}

void *thread_routine(void *args) {

    thread_args_t *thread_args = (thread_args_t*)args;

    int i = 0;
    for (; i < ITERATION_COUNT; i++) {
        if (thread_args->role == PROD_ROLE) {
            int value = thread_args->idx * i;
            enqueue(value);
            printf("[Thread #%d] inserito %d nel buffer\n", thread_args->idx, value);
        } else if (thread_args->role == CONS_ROLE) {
            int value = dequeue();
            printf("[Thread #%d] letto %d dal buffer\n", thread_args->idx, value);
        } else {
            printf("[Thread #%d] ruolo sconosciuto: %d\n", thread_args->idx, thread_args->role);
        }
    }

    free(thread_args);

    pthread_exit(NULL);
}

int main(int argc, char* argv[]) {

    int ret;

    // inizializzazioni
    read_index = write_index = 0;

    /**
     * COMPLETARE QUI
     *
     * Obiettivi
     * - inizializzare i semafori dichiarati
     * - gestire gli errori
     */

     fill_sem = malloc(sizeof(sem_t));
     ret = sem_init(fill_sem, 0, 0);
     ERROR_HELPER(ret, "Error creating fill_sem");
     empty_sem = malloc(sizeof(sem_t));
     ret = sem_init(empty_sem, 0, BUFFER_LENGTH);
     ERROR_HELPER(ret, "Error creating empty_sem");
     write_sem = malloc(sizeof(sem_t));
     ret = sem_init(write_sem, 0, 1);
     ERROR_HELPER(ret, "Error creating write_sem");

    /**
     * COMPLETARE QUI
     *
     * Obiettivi: lanciare THREAD_COUNT thread
     * - nota bene: successivamente bisogna attendere il termine di ogni
     *   thread
     * - per ogni thread, preparare prima i parametri da passargli
     *  (struttura thread_args_t)
     * - l'idx deve essere diverso per ogni thread
     * - il role può assumere come valori PROD_ROLE o CONS_ROLE, ci deve
     *   essere un solo consumatore, gli altri devono essere dei
     *   produttori
     * - ogni thread lanciato deve eseguire la funzione thread_routine()
     * - una volta lanciato un thread, gestire gli errori
     */

    // lancio thread
    pthread_t threads[THREAD_COUNT];
    int i;
    for (i = 0; i < THREAD_COUNT; i++) {
        thread_args_t* thread_arg = malloc(sizeof(thread_args_t));
        thread_arg->idx = i;
        thread_arg->role = i == 0 ? CONS_ROLE : PROD_ROLE;
        ret = pthread_create(&threads[i], NULL, thread_routine, (void*) thread_arg);
        PTHREAD_ERROR_HELPER(ret, "Error creating thread");
    }

    /**
     * COMPLETARE QUI
     *
     * Obiettivi: attendere il termine dei thread
     * - attendere il termine di ognuno dei THREAD_COUNT thread lanciati
     *   in precedenza
     * - gestire gli errori
     */

    for (i = 0; i < THREAD_COUNT; i++) {
        ret = pthread_join(threads[i], NULL);
        PTHREAD_ERROR_HELPER(ret, "Error waiting for thread");
    }



    /**
     * COMPLETARE QUI
     *
     * Obiettivi: rilasciare i semafori inizializzati in precedenza
     * - per ogni semaforo rilasciato, gestire gli errori
     */

     ret = sem_destroy(fill_sem);
     ERROR_HELPER(ret, "Error closing fill_sem");
     ret = sem_destroy(empty_sem);
     ERROR_HELPER(ret, "Error closing empty_sem");
     ret = sem_destroy(write_sem);
     ERROR_HELPER(ret, "Error closing write sem");

     free(fill_sem);
     free(empty_sem);
     free(write_sem);

    printf("[Main Thread] operazioni completate\n");

    return EXIT_SUCCESS;
}
