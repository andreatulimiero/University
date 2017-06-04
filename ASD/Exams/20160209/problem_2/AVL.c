#include <stdlib.h>

#include "AVL.h"

#define max(a, b) (a > b ? a : b)

int isLeaf(node_t* node){
    return node->left == NULL && node->right == NULL;
}

int _is_AVL(node_t* node) {
    if (node == NULL) return 0;
    if (isLeaf(node)) return 1;
    int left_height = _is_AVL(node->left);
    int right_height = _is_AVL(node->right);
    if (left_height == -1 || right_height == -1) return -1;
    if (abs(left_height - right_height) > 1) return -1;
    return 1 + max(left_height, right_height);
}

int is_AVL(bst_t* bst) {
    int isAVL = _is_AVL(bst->root);
    switch (isAVL) {
        case -1:
        case 0:
            return 0;
        default:
            return 1;
    }
}