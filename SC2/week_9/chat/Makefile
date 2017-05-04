CC = gcc -Wall -g
LDFLAGS = -lpthread

all: chat-socket

chat-socket: chat-socket.c
	$(CC) -o chat-socket chat-socket.c $(LDFLAGS)

.PHONY: clean

clean:
	rm -f chat-socket
