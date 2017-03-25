#include "labirinto.h"

#include <stdlib.h>
<<<<<<< HEAD
=======
#include <stdio.h>
>>>>>>> 16d9eed7fd18e2eba99b461efc38b7d958e6120a
#include <assert.h>

typedef struct {
    int n;
<<<<<<< HEAD
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
=======
    int **matrix;
    int **marcata;
} labirinto_struct ;

labirinto * labirinto_new(int n) {
    labirinto_struct* l = (labirinto_struct*) malloc(sizeof(labirinto_struct));
    l->n = n;
    l->matrix = (int**) malloc(sizeof(int*) * (n));
    l->marcata = (int**) malloc(sizeof(int*) * (n));
    int i;
    for (i = 0; i < n; i++) {
	l->matrix[i] = (int*) malloc(sizeof(int) * n);
	l->marcata[i] = (int*) malloc(sizeof(int) * n);
    }
    return l;
}

void labirinto_delete(labirinto * lab) {  
    labirinto_struct* l = (labirinto_struct*) malloc(sizeof(labirinto_struct));
    int i;
    for (i = 0; i < l->n; i++) {
	free(l->matrix[i]);
	free(l->marcata[i]);
    }
    free(l->matrix);
    free(l->marcata);
    free(l);
}

void labirinto_setpiena(labirinto * lab, int r, int c) {
    labirinto_struct* l = (labirinto_struct*) lab;
    l->matrix[r][c] = '#';
    l->marcata[r][c] = '#';
}

int labirinto_uscita(labirinto * lab, int r, int c) {
    labirinto_struct* l = (labirinto_struct*) lab;
    int n = l->n;
    return (r == n - 2 && c == n - 1) || (r == n - 1 && c == n - 2) || (r == n - 2 && c == n - 2);
}

int labirinto_percorribile(labirinto * lab, int r, int c) {
    labirinto_struct* l = (labirinto_struct*) lab;
    if (r < 0 || r>= l->n || c < 0 || c >= l->n)
	return 0;
    return l->marcata[r][c] != '#' && l->marcata[r][c] != '+';
}

int labirinto_uscitaraggiungibileda(labirinto * lab, int r, int c) {
    labirinto_struct* l = (labirinto_struct*) lab;
    if (labirinto_uscita(lab, r, c)) {
	l->marcata[l->n-1][l->n-1] = '+';
	return 1;
    }
    int i, j;
    for (i = r - 1; i <= r + 1; i++)
	for (j = c - 1; j <= c + 1; j++) {
	    if (labirinto_percorribile(l, i, j)) {
		l->marcata[i][j] = '+';
		if (!labirinto_uscitaraggiungibileda(lab, i, j)) {
		    l->marcata[i][j] = ' ';
		    return 0;
		}
		return 1;
	    }
	}
>>>>>>> 16d9eed7fd18e2eba99b461efc38b7d958e6120a
    return 0;
}

int labirinto_risolvibile(labirinto * lab) {
<<<<<<< HEAD
    return 0;
}

void labirinto_tostring(labirinto * lab, char * buffer, int buffer_size) {

=======
   return labirinto_uscitaraggiungibileda(lab, 0, 0);
}

void labirinto_tostring(labirinto * lab, char * buffer, int buffer_size) {
    int i, j;
    labirinto_struct* l = (labirinto_struct*) lab;
    printf("---------------------------\n");
    for (i = 0; i < l->n; i++) {
	    for (j = 0; j < l->n; j++)
		printf("%c", l->marcata[i][j]);
	printf("\n");
    }
    printf("---------------------------\n");
>>>>>>> 16d9eed7fd18e2eba99b461efc38b7d958e6120a
}
