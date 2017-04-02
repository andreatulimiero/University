#include <stdlib.h>
#include <stdio.h>

#include "bst.h"

int main(int argc, char** argv) {

    bst_t* bst = new_bst();
    int key, value;
    while(scanf("%d %d", &key, &value) != EOF) insert_key(bst, key, value);

    print_bst(bst);
    printf("---------------\n");
    printf("Remove 528\n");
    remove_key(bst, 528);
    print_bst(bst);
    printf("---------------\n");
    printf("Remove 77\n");
    remove_key(bst, 77);
    print_bst(bst);
    printf("---------------\n");
    printf("Remove 622\n");
    remove_key(bst, 622);
    print_bst(bst);
    printf("---------------\n");
    printf("Key: 980, Value: %d\n", search_key(bst, 980));
    free_bst(bst);

    return 0;
}