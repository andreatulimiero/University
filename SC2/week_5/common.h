#include <errno.h>
#include <stdio.h>
#include <string.h>

#define GENERIC_ERROR_HELPER(cond, errCode, msg) do {               \
        if (cond) {                                                 \
            fprintf(stderr, "[ERROR] %s: %s\n", msg, strerror(errCode));    \
            exit(EXIT_FAILURE);                                     \
        }                                                           \
    } while(0)

#define ERROR_HELPER(ret, msg)          GENERIC_ERROR_HELPER((ret < 0), errno, msg)
#define PTHREAD_ERROR_HELPER(ret, msg)  GENERIC_ERROR_HELPER((ret != 0), ret, msg)

/* LOGGING */
#define DEBUG 1

#define LOG(msg) do { \
            if (DEBUG) printf("[LOG] %s\n", msg); \
        } while(0)
