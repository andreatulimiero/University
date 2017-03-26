#include <stdlib.h>
#include <stdio.h>

#include "tree.h"

node_tree * new_node_tree(int info) {
    node_t* node = malloc(sizeof(node_t));
    node->info = info;
    node->sibling = NULL;
    node->child = NULL;
    return node;
}

int get_node_info(node_tree * n) {
    return ((node_t*) n)->info;
}

void set_first_child(node_tree * n, node_tree * first_child) {
    ((node_t*) n)->child = first_child;
}

node_tree * get_first_child(node_tree * n) {
    return ((node_t*) n)->child;
}

void set_next_sibling(node_tree * n, node_tree * next_sibling) {
    ((node_t*) n)->sibling = next_sibling;
}

node_tree * get_next_sibling(node_tree * n) {
    return ((node_t*) n)->sibling;
}

void delete_tree(node_tree * nt) {
    return;
    if (((node_t*) nt)->child == NULL) {
        if (((node_t*) nt)->sibling == NULL)
            free(nt);
        else
            delete_tree(((node_t*)nt)->sibling);
    } else {
        delete_tree(((node_t*)nt)->child);
        if (((node_t*)nt)->sibling == NULL)
          free(nt);
        else
           delete_tree(((node_t*)nt)->sibling);
    }
}

void print_depth(int depth) {
    int i;
    for (i = 0; i < depth; i++) printf("-");
}

void _print_tree(node_tree* node, int depth) {
    if (node == NULL) return;
    node_t* n = (node_t*) node;
    print_depth(depth);
    printf("%d\n", n->info);
    _print_tree(n->child, depth+1);
    _print_tree(n->sibling, depth);
}

void print_tree(node_tree * node) {
    _print_tree(node, 0);
}
