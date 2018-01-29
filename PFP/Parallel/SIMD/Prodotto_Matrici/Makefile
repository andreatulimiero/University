all: main.o
	gcc main.o -mavx2 -o main

main.o: main.c
	gcc -c main.c

clean:
	rm *.o main