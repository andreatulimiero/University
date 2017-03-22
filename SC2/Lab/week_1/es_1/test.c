#include<stdio.h>
#include<stdlib.h>
#include<errno.h>
#include<pthread.h>

int* values;
int cycles;

void* calculate(void* arg){
    int thread_space = (int) arg;
    values[thread_space] = 0;
    for (int i = 0; i < cycles; i++){
        values[thread_space]++;
    }
    pthread_exit(0);
}

int main(int argc, char** argv){
    if (argc < 3){
        fprintf(stderr, "usage <threads_num> <thread_cycles>\n");
        exit(1);
    }
    int thread_num = atoi(argv[1]);
    values = (int*) malloc(sizeof(int) * thread_num);
    cycles = atoi(argv[2]);
    pthread_t* threads = (pthread_t*) malloc(sizeof(pthread_t) * thread_num);

    for (int i = 0; i < thread_num; i++){
        if (pthread_create(&threads[i], NULL, calculate, (void*) i) != 0){
            perror("Error: ");
        }
    }
    int sum = 0;
    for (int i = 0; i < thread_num; i++){
        pthread_join(threads[i], NULL);
        sum += values[i];
    }

    printf("Sum: %d\n", sum);

    return 0;
}
