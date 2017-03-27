all:	driver.o tree.o parse_tree.o max_level.o iter_postorder.o queue.o
	gcc -g -o driver driver.o tree.o parse_tree.o max_level.o iter_postorder.o queue.o

driver.o: tree.h parse_tree.h max_level.h iter_postorder.h tree.o parse_tree.o max_level.o iter_postorder.o queue.h queue.o driver.c
	gcc -g -c driver.c 

tree.o: tree.h tree.c
	gcc -g -c tree.c

max_level.o: queue.h max_level.h max_level.c
	gcc -g -c max_level.c 

queue.o: queue.h queue.c stack.h
	gcc -g -c queue.c

parse_tree.o: parse_tree.h parse_tree.c
	gcc -g -c parse_tree.c

iter_postorder.o: iter_postorder.h iter_postorder.c stack.h queue.o
	gcc -g -c iter_postorder.c

clean:
	-rm ./*.o 
	-rm driver
