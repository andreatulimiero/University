CC     = gcc
CFLAGS = -O1 -Wall -Wno-deprecated-declarations
LFLAGS = -l OpenCL

reflect: main.o clut.o pgm.o reflect.o
	$(CC) $(CFLAGS) main.o clut.o pgm.o reflect.o \
        -o reflect $(LFLAGS)

main.o: main.c clut.h reflect.h pgm.h
	$(CC) $(CFLAGS) main.c -c

reflect.o: reflect.c reflect.h clut.h
	$(CC) $(CFLAGS) reflect.c -c

clut.o: clut.c clut.h
	$(CC) $(CFLAGS) clut.c -c

pgm.o: pgm.c pgm.h
	$(CC) $(CFLAGS) pgm.c -c

test:
	./reflect johnny.pgm johnny-reflect.pgm

.phony: clean

clean:
	rm -f *.o reflect johnny-reflect.pgm
