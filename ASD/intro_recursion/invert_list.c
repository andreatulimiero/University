#include "invert_list.h"

#include <stdlib.h>
#include <stdio.h>
#include "linked_list.h"

linked_list * invertiLista(linked_list_iterator * iter) {
    return iter;
}

int list_length(linked_list_iterator* iter) {	
    if (iter == NULL) return 0;
    return 1 + list_length(linked_list_iterator_next(iter));
}
