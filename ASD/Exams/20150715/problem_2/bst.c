#include <stdlib.h>
#include <stdio.h>

#include "bst.h"

bst_t* new_bst(){
    bst_t* bst = malloc(sizeof(bst_t));
    bst->root = NULL;
    return bst;
}

node_t* _insert_node(node_t* node, int key, int value) {
    if (node == NULL) {
        node_t* nnode = malloc(sizeof(node_t));
        nnode->key = key;
        nnode->value = value;
        return nnode;
    }
    if (key > node->key) node->right = _insert_node(node->right, key, value);
    else if (key < node->key) node->left = _insert_node(node->left, key, value);
    else node->value = value;
    return node;
}

void insert_key(bst_t* bst, int key, int value) {
    bst->root = _insert_node(bst->root, key, value);
}

node_t* _search_node(node_t* node, int key) {
    if (node == NULL) return NULL;
    if (key > node->key) return _search_node(node->right, key);
    else if (key < node->key) return _search_node(node->left, key);
    return node;
}

int search_key(bst_t* bst, int key) {
    node_t* node = _search_node(bst->root, key);
    if (node == NULL) return -1;
    return node->value;
}

node_t* get_predecessor(node_t* node) {
    if (node == NULL) return NULL;
    if (node->right == NULL) return node;
    return get_predecessor(node->right);
}

node_t* _remove_node(node_t* node, int key) {
    if (node == NULL) return NULL;
    if (key > node->key) node->right = _remove_node(node->right, key);
    else if (key < node->key) node->left = _remove_node(node->left, key);
    else {
        if (node->left == NULL && node->right == NULL) {
            free(node);
            return NULL;
        } else if (node->left != NULL && node->right != NULL) {
            node_t* pre = get_predecessor(node->left);
            node->key = pre->key;
            node->value = pre->value;
            _remove_node(node->left, pre->key);
        } else {
            if (node->left != NULL) node = _remove_node(node->left, key);
            else if (node->right != NULL) node = _remove_node(node->right, key);
        }
    }
    return node;
}

void remove_key(bst_t* bst, int key) {
    bst->root = _remove_node(bst->root, key);
}

void _print_bst(node_t* node) {
    if (node == NULL) return;
    printf("[%d]<-%d->[%d]\n", (node->left == NULL ? -1 : node->left->key), node->key, (node->right == NULL ? -1 : node->right->key));
    _print_bst(node->left);
    _print_bst(node->right);
}

void print_bst(bst_t* bst) {
    _print_bst(bst->root);
}

void _free_bst(node_t* node) {
    if (node == NULL) return;
    _free_bst(node->left);
    _free_bst(node->right);
    free(node);
}

void free_bst(bst_t* bst) {
    _free_bst(bst->root);
    free(bst);
}