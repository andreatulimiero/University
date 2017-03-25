#include <stdlib.h>
#include <stdio.h>

#include "queue.h"
#include "max_level.h"
#include "tree.h"

int max(int a, int b) { return a > b ? a : b; }

int max_level(node_tree * n) {
    node_tree* s = n;
    int keys_sum = get_node_info(s);
    while ((s = get_next_sibling(s)) != NULL)
        keys_sum += get_node_info(s);

    node_tree* first_child = get_first_child(n);
    if (first_child == NULL) return keys_sum;
    return max(keys_sum, max_level(first_child));
}
