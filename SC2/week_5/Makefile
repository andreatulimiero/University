all: transactions

debug: producers_consumers.o
	gcc -g -D DEBUG=1 producers_consumers.o -o transactions -lpthread -Wall

transactions: producers_consumers.o
	gcc producers_consumers.o -o transactions -lpthread -Wall

producers_consumers.o: producers_consumers.c
	gcc -c producers_consumers.c

clean:
	rm *.o transactions