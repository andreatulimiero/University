#include <stdlib.h>
#include <stdio.h>

#include "iter_postorder.h"
#include "stack.h"
#include "tree.h"

void _iterative_postorder_visit(node_tree* node, int depth) {
    node_t* n;
    for (n = (node_t*) node; n != NULL; n = (node_t*) n->sibling) {
        _iterative_postorder_visit(n->child, depth + 1);
        print_depth(depth);
        printf("%d\n", n->info);
    }
}

void iterative_postorder_visit(node_tree * node) {
    _iterative_postorder_visit(node, 0);
}