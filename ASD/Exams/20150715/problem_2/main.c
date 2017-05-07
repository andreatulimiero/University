#include <stdlib.h>
#include <stdio.h>

#include "bst.h"
#include "albero_bello.h"

int main(int argc, char** argv) {

    bst_t* bst = new_bst();
    int key, value;
    while(scanf("%d %d", &key, &value) != EOF) insert_key(bst, key, value);

    print_bst(bst);
    printf("[Test] Is Alberobello: %s\n", (is_albero_bello(bst) ? "true" : "false"));
    is_albero_bello(bst);
    free_bst(bst);

    return 0;
}