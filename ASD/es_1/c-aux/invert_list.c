#include "invert_list.h"

#include <stdlib.h>
<<<<<<< HEAD

linked_list * invertiLista(linked_list_iterator * iter) {
  _invertiLista(iter, 0, length(iter) - 1, length(iter));
  return iter;
}

void _invertiLista(linked_list_iterator* iter, int from, int to, int length) {
  if (from >= length / 2) return;
  int tmp = iter.
}

int length(linked_list_iterator* iter){
  if (iter != NULL) return 0;
  return 1 + length(iter->next);
=======
#include <stdio.h>

linked_list * invertiLista(linked_list_iterator * iter) {
    int length = list_length(iter);
    printf("Length: %d", length);
}

int list_length(linked_list_iterator* iter) {	
    if (iter == NULL) return 0;
    return 1 + list_length(linked_list_iterator_next(iter));
>>>>>>> 16d9eed7fd18e2eba99b461efc38b7d958e6120a
}
