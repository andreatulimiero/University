all: main

main: bst.o main.o
	gcc -g -Wall bst.o main.o -o main

main.o: main.c
	gcc -c main.c

bst.o: bst.c
	gcc -c bst.c

clean:
	rm *.o main