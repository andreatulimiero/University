#include <unistd.h>
#include <sys/wait.h>
#include <pthread.h>
#include <fcntl.h>
#include <semaphore.h>
#include "common.h"

#define M 4 // processes number
#define N 2 // thread numbers
#define SEMAPHORE_NAME      "/shared_sem"
#define NUM_RESOURCES 2

typedef struct thread_args_s {
    int child_id;
    int thread_id;
		sem_t* thread_sem;
		int*   shared_thread_var;
} thread_args_t;

sem_t* process_sem;

void *thread_routine(void *args) {
	
	int ret = 0;
	thread_args_t *thread_args = (thread_args_t*)args;
	printf("[CHILD%d] thread %d avviato\n", thread_args->child_id, thread_args->thread_id);
	
	/** PARTE 1; COMPLETARE QUI SOTTO:
	 *	gestione sezione critica inter-thread.
	 *	Obiettivi:
	 *	- incrementare il valore puntato da shared_thread_var
	 *	  con il seguente prodotto 'child_id+1 * thread_id+1'; i valori child_id e thread_id
	 *	  sono contenuti all'interno della struct thread_args_t
	 *	- proteggere l'operazione usando il semaforo binario thread_sem
	 *	- gestire eventuali errori di sem_wait e sem_post con l'ERROR_HELPER
	 **/

	ret = sem_wait(thread_args->thread_sem);
	ERROR_HELPER(ret, "Impossible locking the semaphore");
	*thread_args->shared_thread_var += (thread_args->child_id + 1) * (thread_args->thread_id + 1);
	printf("[CHILD%d] thread %d; valore = %d\n", thread_args->child_id, thread_args->thread_id,
												*(thread_args->shared_thread_var));
	ret = sem_post(thread_args->thread_sem);
  ERROR_HELPER(ret, "Impossible releasing the semaphore");
											
	pthread_exit(NULL);
}

void child_routine(int child_id) {
	printf("[CHILD%d] figlio avviato\n", child_id);
	
	int i = 0, ret = 0;
	
	// variabile e semaforo condivisi tra i thread
	int* shared_thread_var = malloc(sizeof(int));
	sem_t* thread_sem = malloc(sizeof(sem_t));
	
	
	/** PARTE 2; COMPLETARE QUI SOTTO:
	 *	inizializzazione semaforo e variabile condivisa dei thread.
	 *	Obiettivi:
	 *	- inizializzare il semaforo binario thread_sem e gestire eventuali errori con l'ERROR_HELPER
	 *	- inizializzare il valore puntato da shared_thread_var a 0
	 **/

	 *shared_thread_var = 0;
	 ret = sem_init(thread_sem, 0, 1);
	 ERROR_HELPER(ret, "Error initiating sem");
	
	pthread_t threads[N];
	thread_args_t threads_args[N];
	
	/** PARTE 3; COMPLETARE QUI SOTTO:
	 *	creazione di N thread e attesa dei loro risultati.
	 *	Obiettivi:
	 *	- creare in un ciclo N thread che avviano la thread_routine() prendendo come parametri
	 *	  una thread_args_t
	 *	- il thread_id deve essere un numero a partire da 0
	 *	- gestire eventuali errori di creazione del thread con il PTHREAD_ERROR_HELPER e non ERROR_HELPER!
	 *	- al termine della creazione degli N thread il processo main deve attendere esplicitamente ciascun
	 *	  thread con una join.
	 *	  
	 *	  NOTA: i cicli di creazione thread e attesa devono essere separati altrimenti i thread sarebbero
	 *	        lanciati sequenzialmente e non in concorrenza
	 */

	 for (i = 0; i < N; i++) {
		 thread_args_t thread_args;
		 thread_args.thread_id = i;
		 thread_args.child_id = child_id;
		 thread_args.thread_sem = thread_sem;
		 thread_args.shared_thread_var = shared_thread_var;
		 threads_args[i] = thread_args;
		 ret = pthread_create(&threads[i], NULL, &thread_routine, (void*) &thread_args);
		 PTHREAD_ERROR_HELPER(ret, "Impossible creating the thread");
	 }	

	 for (i = 0; i < N; i++) {
		 ret = pthread_join(threads[i], NULL);
		 PTHREAD_ERROR_HELPER(ret, "Impossible joining with the thread");
	 }
	
	/** FINE PARTE 3 - gestione multithread **/
	printf("[CHILD%d] esecuzione thread terminata. valore=%d\n", child_id, *shared_thread_var);
	
	
	/** PARTE 4; COMPLETARE QUI SOTTO:
	 *	gestione concorrenza inter-processo.
	 *	Obiettivi:
	 *	- proteggere l'istruzione di sezione critica con il semaforo process_sem
	 *	- gestire eventuali errori di sem_wait e sem_post con l'ERROR_HELPER
	 **/
	ret = sem_wait(process_sem);
	ERROR_HELPER(ret, "Impossible locking the semaphore");
	
	/* sezione critica inter-processo: simuliamo l'accesso ad una risorsa condivisa tra processi;
	 questa è l'operazione da proteggere con il semaforo */
	printf("[CHILD%d] RISORSA ACQUISITA! Valore = %d\n", child_id, *shared_thread_var);
	
	ret = sem_post(process_sem);
	ERROR_HELPER(ret, "Impossible unlocking the sem");
	
	/** FINE PARTE 4 - gestione concorrenza inter-processo **/
	
	
	/** PARTE 5; COMPLETARE QUI SOTTO:
	 *	chiusura semafori.
	 *	Obiettivi:
	 *	- chiudere il semaforo thread_sem
	 *	- chiudere il semaforo process_sem
	 *
	 *	NOTE:
	 *	      1) i due semafori usano due metodi diversi per la chiusura (uno normale, uno named)
	 *		  2) il semaforo process_sem va chiuso ma non unlinkato, altrimenti nessun altro
	 *			 processo potrà più usarlo
	 */
	 
	 ret = sem_destroy(thread_sem);
	ERROR_HELPER(ret, "Error destroing unnamed semaphore");
	
	sem_close(process_sem);
	ERROR_HELPER(ret, "Error closing semaphore");
	
	/** FINE PARTE 5 - chiusura semafori **/
	
	// rilascio risorse allocate dinamicamente all'inizio della child_routine()
	free(shared_thread_var);
	free(thread_sem);
	
}

int main() {

	int i = 0, ret = 0;
	
	/** PARTE 6; COMPLETARE QUI SOTTO:
	 *	apertura semaforo named.
	 *	Obiettivi:
	 *	- aprire il semaforo named con nome SEMAPHORE_NAME per gestire NUM_RESOURCES processi in parallelo
	 *	- in caso di fallimento fare unlink e riprovare
	 *	- gestire eventuali errori con l'ERROR_HELPER
	 **/
	
	process_sem = sem_open(SEMAPHORE_NAME, O_CREAT | O_EXCL, 0600, NUM_RESOURCES);
	if (process_sem == SEM_FAILED && errno == EEXIST) {
		printf("Semaphore already up, unlinking it");
		sem_unlink(SEMAPHORE_NAME);
		
		process_sem = sem_open(SEMAPHORE_NAME, O_CREAT | O_EXCL, 0600, NUM_RESOURCES);
		ERROR_HELPER(process_sem, "Impossible to open the semaphore");
	}
	
	/** FINE PARTE 6 - apertura semaforo named **/
	
	
	pid_t pid;
	/** PARTE 7; COMPLETARE QUI SOTTO:
	 *	ciclo creazione M processi figlio.
	 *	Obiettivi:
	 *	- creare M processi figlio che lavorano in parallelo
	 *	- distinguere in base al pid padre e figlio
	 *	- il figlio deve lanciare child_routine(i+1) e poi smettere di ciclare per non creare
	 *	  altri processi figlio
	 *	- gestire eventuali errori di fork() con l'ERROR_HELPER
	 **/
		
		for (i = 0; i < M; i++) {
			pid = fork();
			ERROR_HELPER(pid, "Impossible forking the child");
			
			if (pid == 0) {
				child_routine(i + 1);
				exit(EXIT_SUCCESS);
			}

		}
		
	/** FINE PARTE 7 - distinzione padre figlio **/
	
	
	/** PARTE 8; COMPLETARE QUI:
	 *	attesa figli e unlink semaforo named
	 *	Obiettivi:
	 *	- attendere tutti gli M figli
	 *	- unlinkare il semaforo named
	 *	- gestire eventuali errori con l'ERROR_HELPER
	 *
	 *	NOTA: l'unlink del semaforo named potevo farlo prima? No, solo perchè qui ho la certezza
	 *		  che il semaforo non serva più poiché sono terminati tutti i figli.
	 **/
		
	for (i = 0; i < M; i++) {
		wait(NULL);
	}	

	ret = sem_unlink(SEMAPHORE_NAME);
	ERROR_HELPER(ret, "Impossible closing the semaphore");
		
	/** FINE PARTE 8 - attesa figli e unlink semaforo **/
		
	
	return EXIT_SUCCESS;
}	