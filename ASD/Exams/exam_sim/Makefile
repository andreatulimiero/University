all: main.o queue.o
	gcc -g -Wall queue.o main.o -o main

main.o: main.c
	gcc -g -c main.c

queue.o: queue.h queue.o
	gcc -g -Wall -c queue.c

clean:
	rm *.o main
