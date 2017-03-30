CC = gcc -Wall -g
LDFLAGS = -lpthread

all: M_proc-N_thread

M_proc-N_thread: common.h M_proc-N_thread.c
	$(CC) -o M_proc-N_thread M_proc-N_thread.c $(LDFLAGS)

.PHONY: clean
clean:
	rm -f M_proc-N_thread
