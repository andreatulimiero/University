all: main

main: main.c thread_handler.c
	gcc -g thread_handler.c main.c -o main -lpthread

clean:
	rm main
