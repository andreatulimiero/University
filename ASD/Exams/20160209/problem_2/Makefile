all: AVL.o bst.o main.o
	gcc -g -Wall AVL.o bst.o main.o -o main

main.o: main.c
	gcc -g -Wall -c main.c

bst.o: bst.h bst.c
	gcc -g -Wall -c bst.c

AVL.o: AVL.h AVL.h
	gcc -g -Wall -c AVL.c

clean:
	rm *.o main
