CC     = gcc
CFLAGS = -O1 -Wall -Wno-deprecated-declarations
LFLAGS = -l OpenCL

crop: main.o clut.o pgm.o crop.o
	$(CC) $(CFLAGS) main.o clut.o pgm.o crop.o \
        -o crop $(LFLAGS)

main.o: main.c clut.h crop.h pgm.h
	$(CC) $(CFLAGS) main.c -c

crop.o: crop.c crop.h clut.h
	$(CC) $(CFLAGS) crop.c -c

clut.o: clut.c clut.h
	$(CC) $(CFLAGS) clut.c -c

pgm.o: pgm.c pgm.h
	$(CC) $(CFLAGS) pgm.c -c

test:
	./crop johnny.pgm johnny-crop.pgm 120 90 80 150

.phony: clean

clean:
	rm -f *.o crop johnny-crop.pgm
