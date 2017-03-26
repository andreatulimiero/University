all: main

main: proc_handler.c thread_handler.c main.c
	gcc -g proc_handler.c thread_handler.c main.c -o main -lpthread

clean:
	rm main
