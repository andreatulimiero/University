#include "invert_list.h"

#include <stdlib.h>

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
}
