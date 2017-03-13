#include "labirinto.h"

#include <stdlib.h>
#include <assert.h>

typedef struct {
    int n;
    int *matrix;
    int *marcata;
} labirinto_struct ;

labirinto * labirinto_new(int n) {
    return NULL;
}

void labirinto_delete(labirinto * lab) {
    
}

void labirinto_setpiena(labirinto * lab, int r, int c) {
    
}

int labirinto_uscita(labirinto * lab, int r, int c) {
    return 0;
}

int labirinto_percorribile(labirinto * lab, int r, int c) {
    return 0;
}

int labirinto_uscitaraggiungibileda(labirinto * lab, int r, int c) {
    return 0;
}

int labirinto_risolvibile(labirinto * lab) {
    return 0;
}

void labirinto_tostring(labirinto * lab, char * buffer, int buffer_size) {

}
