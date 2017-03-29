#include <stdio.h>
#include <error.h>


#define NUM_RESOURCES 1
#define WAIT_TIME 2
#define SEM_NAME "/semaphore"
#define SEM_NOT_NAME "/semaphore-notification"
#define SEM_SYNC_NAME "/semaphore-sync"
#define FILE_NAME "out.txt"

#define GENERAL_ERROR_HANDLER(cond, errno, msg) \
    if (cond) {\
        fprintf(stderr, "%s: %s\n", msg, strerror(errno));\
        exit(EXIT_FAILURE);\
    }

#define ERROR_HANDLER(errno, msg) \
                GENERAL_ERROR_HANDLER(errno < 0, errno, msg)
