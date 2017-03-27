#include <stdio.h>
#include <error.h>

#define GENERAL_ERROR_HANDLER(cond, errno, msg) \
    if (cond) {\
        fprintf(stderr, "%s: %s\n", msg, strerror(errno));\
        exit(EXIT_FAILURE);\
    }

#define ERROR_HANDLER(errno, msg) \
                GENERAL_ERROR_HANDLER(errno < 0, errno, msg)
