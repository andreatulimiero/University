all: main

main: proc_handler.o thread_handler.o main.o
	gcc -g proc_handler.o thread_handler.o main.o -o main -lpthread

proc_handler.o: proc_handler.c
	gcc -c proc_handler.c

thread_handler.o: thread_handler.c
	gcc -c thread_handler.c

main.o: main.c
	gcc -c main.c

clean:
	rm main.o thread_handler.o proc_handler.o main
