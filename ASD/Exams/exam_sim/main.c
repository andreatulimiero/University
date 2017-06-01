/* versione C */

/*
    dato un albero binario di tipo binTree, determinare se esso è completo
*/


#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include "queue.h"
#include <time.h>

typedef struct binTree {
    int key;
    struct binTree *left, *right;
} binTree;

#ifndef _BOOLEAN
typedef unsigned char boolean;
#define TRUE (unsigned char)1
#define FALSE (unsigned char)0
#define _BOOLEAN
#endif

/* restituisce TRUE se completo, FALSE altrimenti */
boolean isComplete(binTree *t);
/* restituisce un albero completo di n livelli */
binTree *genCompleteBinTree(int);

binTree *newBinTree(int);
binTree *setLeftChild(binTree*, int);
binTree *setRightChild(binTree*, int);
binTree *buildNonCompleteTree1();
binTree *buildNonCompleteTree2();
binTree *buildNonCompleteTree3();
binTree *buildNonCompleteTree4();
binTree *buildNonCompleteTree5();
binTree *buildNonCompleteTree6();
binTree *buildCompleteTree1();
binTree *buildCompleteTree2();



binTree *newBinTree(int k) {
    binTree *t = malloc(sizeof(binTree));
    t->key = k;
    t->left = t->right = NULL;
    return t;
}

static int _isComplete(binTree* t) {
    if (t == NULL) return 0;
    int left = _isComplete(t->left);
    int right = _isComplete(t->right);
    if (left == -1 || right == -1) return -1;
    if (left != right) return -1;
    return 1 + left;
}

boolean isComplete(binTree *t) {
    return _isComplete(t) == -1 ? FALSE : TRUE;
}

binTree *genCompleteBinTree(int nLev) {
    queue* current = newQueue();
    queue* next = newQueue();
    if (nLev <= 0) return NULL;
    int val = 0;
    binTree* tree = newBinTree(-1);
    enqueue(current, tree);
    while(nLev > 0) {
        binTree* t = (binTree*) dequeue(current);
        t->key = ++val;
        if (nLev > 0) {
            binTree* left = setLeftChild(t, -1);
            binTree* right = setRightChild(t, -1);
            enqueue(next, left);
            enqueue(next, right);
        }
        if (isEmptyQueue(current)) {
            current = next;
            next = newQueue();
            nLev--;
        }
    }
    return tree;
}

binTree *setLeftChild(binTree *t, int k) {
    if(t==NULL) return NULL;
    if(t->left != NULL) return NULL;
    return t->left = newBinTree(k);
}

binTree *setRightChild(binTree *t, int k) {
    if(t==NULL) return NULL;
    if(t->right != NULL) return NULL;
    return t->right = newBinTree(k);
}

binTree *buildNonCompleteTree1() {
    int k = 0;
    binTree *t = newBinTree(k++);
    binTree *a = setRightChild(t, k++);
    binTree *b = setLeftChild(a, k++);
    binTree *c = setLeftChild(t, k++);
    return t;
}

binTree *buildNonCompleteTree2() {
    int k = 0;
    binTree *t = newBinTree(k++);
    binTree *a = setLeftChild(t, k++);
    binTree *b = setRightChild(a, k++);
    binTree *c = setRightChild(t, k++);
    binTree *d = setLeftChild(c, k++);
    binTree *e = setRightChild(c, k++);
    return t;
}

binTree *buildNonCompleteTree3() {
    int k = 0;
    binTree *t = newBinTree(k++);
    binTree *a = setRightChild(t, k++);
    return t;
}

binTree *buildNonCompleteTree4() {
    int k = 0;
    binTree *t = newBinTree(k++);
    binTree *a = setLeftChild(t, k++);
    return t;
}

binTree *buildNonCompleteTree5() {
    int k = 0;
    binTree *t = newBinTree(k++);
    binTree *a = setRightChild(t, k++);
    binTree *b = setLeftChild(a, k++);
    return t;
}

binTree *buildNonCompleteTree6() {
    int k = 0;
    binTree *t = newBinTree(k++);
    binTree *a = setLeftChild(t, k++);
    binTree *b = setRightChild(t, k++);
    binTree *c = setLeftChild(a, k++);
    binTree *d = setRightChild(a, k++);
    binTree *e = setLeftChild(b, k++);
    binTree *f = setRightChild(b, k++);
    binTree *g = setLeftChild(d, k++);
    binTree *h = setRightChild(d, k++);
    binTree *i = setLeftChild(f, k++);
    binTree *j = setRightChild(f, k++);
    return t;
}

binTree *buildNonCompleteTree7() {
    int k = 0;
    binTree *t = newBinTree(k++);
    binTree *a = setRightChild(t, k++);
    binTree *b = setLeftChild(a, k++);
    binTree *c = setRightChild(a, k++);
    return t;
}

binTree *buildCompleteTree1() {
    int k = 0;
    binTree *t = newBinTree(k++);
    return t;
}

binTree *buildCompleteTree2() {
    int k = 0;
    binTree *t = newBinTree(k++);
    binTree *a = setLeftChild(t, k++);
    binTree *b = setRightChild(t, k++);
    binTree *c = setLeftChild(a, k++);
    binTree *d = setRightChild(a, k++);
    binTree *e = setLeftChild(b, k++);
    binTree *f = setRightChild(b, k++);
    return t;
}

int main() {
    binTree *nc_t1, *nc_t2, *nc_t3, *nc_t4, *nc_t5, *nc_t6, *nc_t7;
    binTree *c_t1, *c_t2;
    binTree *c1, *c2;

    nc_t1 = buildNonCompleteTree1();
    nc_t2 = buildNonCompleteTree2();
    nc_t3 = buildNonCompleteTree3();
    nc_t4 = buildNonCompleteTree4();
    nc_t5 = buildNonCompleteTree5();
    nc_t6 = buildNonCompleteTree6();
    nc_t7 = buildNonCompleteTree7();

    c_t1 = buildCompleteTree1();
    c_t2 = buildCompleteTree2();

    c1 = genCompleteBinTree(6);
    c2 = genCompleteBinTree(24);


    printf("Completo nc_t1? %u\n", isComplete(nc_t1));
    printf("Completo nc_t2? %u\n", isComplete(nc_t2));
    printf("Completo nc_t3? %u\n", isComplete(nc_t3));
    printf("Completo nc_t4? %u\n", isComplete(nc_t4));
    printf("Completo nc_t5? %u\n", isComplete(nc_t5));
    printf("Completo nc_t6? %u\n", isComplete(nc_t6));
    printf("Completo nc_t7? %u\n", isComplete(nc_t7));


    printf("Completo c_t1? %u\n", isComplete(c_t1));
    printf("Completo c_t2? %u\n", isComplete(c_t2));

    printf("Completo c1? %u\n", isComplete(c1));
    printf("Completo c2? %u\n", isComplete(c2));

    return 0;
}