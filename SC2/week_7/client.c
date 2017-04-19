#include <errno.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>  // htons() and inet_addr()
#include <netinet/in.h> // struct sockaddr_in
#include <sys/socket.h>

#include "common.h"

int main(int argc, char* argv[]) {
    int ret;

    // variables for handling a socket
    int socket_desc;
    struct sockaddr_in server_addr = {0}; // some fields are required to be filled with 0

    // create a socket
    socket_desc = socket(AF_INET, SOCK_STREAM, 0);
    ERROR_HELPER(socket_desc, "Could not create socket");

    // set up parameters for the connection
    server_addr.sin_addr.s_addr = inet_addr(SERVER_ADDRESS);
    server_addr.sin_family      = AF_INET;
    server_addr.sin_port        = htons(SERVER_PORT); // don't forget about network byte order!

    // initiate a connection on the socket
    ret = connect(socket_desc, (struct sockaddr*) &server_addr, sizeof(struct sockaddr_in));
    ERROR_HELPER(ret, "Could not create connection");

    if (DEBUG) fprintf(stderr, "Connection established!\n");

    char buf[1024];
    size_t buf_len = sizeof(buf);
    size_t msg_len;

    // display welcome message from server
    while ( (msg_len = recv(socket_desc, buf, buf_len - 1, 0)) < 0 ) {
        if (errno == EINTR) continue;
        ERROR_HELPER(-1, "Cannot read from socket");
    }
    buf[msg_len] = '\0';
    printf("%s", buf);

    // main loop
    while (1) {
        char* quit_command = SERVER_COMMAND;
        size_t quit_command_len = strlen(quit_command);

        printf("Insert your message: ");

        /* Read a line from stdin
         *
         * fgets() reads up to sizeof(buf)-1 bytes and on success
         * returns the first argument passed to it. */
        if (fgets(buf, sizeof(buf), stdin) != (char*)buf) {
            fprintf(stderr, "Error while reading from stdin, exiting...\n");
            exit(EXIT_FAILURE);
        }

        msg_len = strlen(buf);
        buf[--msg_len] = '\0'; // remove '\n' from the end of the message

        /** INSERT CODE TO SEND THE MESSAGE TO THE SERVER
         *
         * Suggestions:
         * - send() with flags = 0 is equivalent to write() to a descriptor
         * - the number of bytes to send is msg_len as we have discarded '\n'
         * - don't deal with partially sent messages
         */

         ret = send(socket_desc, buf, msg_len, 0);

        /* After a quit command we won't receive any more data from
         * the server, thus we must exit the main loop. */

        /** INSERT CODE HERE TO IMPLEMENT THE OPERATION DESCRIBED ABOVE
         *
         * Suggestions:
         * - compare the number of bytes sent with the length of the
         *   quit command that tells the server to close the connection
         *   (see SERVER_COMMAND macro in common.h)
         * - perform a byte-to-byte comparsion when required using
         *   memcmp(const void *ptr1, const void *ptr2, size_t num)
         * - exit from the cycle when there is nothing left to receive
         */
         
         if (strlen(buf) == strlen(SERVER_COMMAND) &&
                 !memcmp(buf, SERVER_COMMAND, strlen(SERVER_COMMAND))) {
             break;
         }

        // read message from server
        /** INSERT CODE TO RECEIVE THE REPLY FROM THE SERVER
         *
         * Suggestions:
         * - recv() with flags = 0 is equivalent to read() from a descriptor
         * - for sockets, we get a 0 return value only when the peer closes
         *   the connection: if there are no bytes to read and we invoke
         *   recv() we will get stuck, because the call is blocking!
         */

         ret = recv(socket_desc, buf, buf_len, 0);

        printf("Server response: %s\n", buf); // no need to insert '\0'
    }


    // close the socket
    ret = close(socket_desc);
    ERROR_HELPER(ret, "Cannot close socket");

    if (DEBUG) fprintf(stderr, "Exiting...\n");

    exit(EXIT_SUCCESS);
}