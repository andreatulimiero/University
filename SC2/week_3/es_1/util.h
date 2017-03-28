// intercepts CTRL+C and runs a cleanup function before quitting
void setQuitHandler(void(*f)());

#define GENERIC_ERROR_HELPER(cond, errCode, msg) \
do { \
if (cond) { \
fprintf(stderr, "%s: %s\n", \
msg, strerror(errCode)); \
exit(EXIT_FAILURE); \
} \
} while(0)

#define ERROR_HELPER(ret, msg) \
GENERIC_ERROR_HELPER((ret < 0), errno, msg)
