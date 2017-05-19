all: main

main: albero_bello.o bst.o main.o
	gcc -Wall albero_bello.o bst.o main.o -o main

main.o: main.c
	gcc -c main.c

bst.o: bst.c
	gcc -c bst.c

albero_bello.o: albero_bello.c
	gcc -g -c albero_bello.c

clean:
	rm *.o main