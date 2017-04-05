all: main

main: one_producer_one_consumer.o
	gcc one_producer_one_consumer.o -o main -lpthread

one_producer_one_consumer.o: one_producer_one_consumer.c
	gcc -c one_producer_one_consumer.c