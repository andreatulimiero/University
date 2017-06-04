#include <stdlib.h>
#include <stdio.h>

#include "bst.h"
#include "AVL.h"

int main(int argc, char** argv) {

    bst_t* bst = new_bst();
    int key, value;
    while(scanf("%d %d", &key, &value) != EOF) insert_key(bst, key, value);

    print_bst(bst);
    printf("[Test] Is AVL: %s\n", (is_AVL(bst) ? "true" : "false"));
    free_bst(bst);

    return 0;
}