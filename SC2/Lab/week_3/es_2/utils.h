#include <stdio.h>
#include <error.h>

#define GENERAL_ERROR_HANDLER (cond, errCode, msg) {\
    if (cond) {\
        fprintf(stderr, "%s:%s\n", msg, strerror(errCode);\
        exit(EXIT_FAILURE);\
    }

#define ERROR_HANDLER (errCode, msg) \
                GENERAL_ERROR_HANDLER(errCode < 0, errCode, msg)
