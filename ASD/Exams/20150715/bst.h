#pragma once

typedef struct node_s {
    struct node_s* left;
    struct node_s* right;
    int key;
    int value;
} node_t;

typedef struct bst_s {
    node_t* root;
} bst_t;

bst_t* new_bst();

void insert_key(bst_t* bst, int key, int value);

void remove_key(bst_t* bst, int key);

int search_key(bst_t* bst, int key);

void free_bst(bst_t*);

void print_bst(bst_t* bst);