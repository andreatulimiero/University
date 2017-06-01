#include "linked_list.h"
#include "stdlib.h"
#include "stdio.h"

typedef struct _linked_list_node {
    void * value;
    struct _linked_list_node * next;
} linked_list_node;
    
typedef struct {
    linked_list_node *head;
    linked_list_node *tail;
    int size;
} linked_list_struct;

linked_list * linked_list_new() {
    linked_list_struct * ptr = (linked_list_struct *) malloc(sizeof(linked_list_struct));
    ptr->head = NULL;
    ptr->tail = NULL;
    ptr->size = 0;
    return (linked_list *) ptr;
}

void * linked_list_get(linked_list * ll, int index) {

    linked_list_struct * ptr = (linked_list_struct *) ll;
    if (index < 0 || index >= ptr->size)
        return NULL;
    
    int i;
    linked_list_node *current = ptr->head;
    for (i = 0; i < index && current != NULL; i++) {
        current = current->next;
    }
    if (current == NULL)
        return NULL;

    return current->value;
}

void linked_list_add(linked_list * ll, void * value) {

    linked_list_struct * ptr = (linked_list_struct *) ll;
    linked_list_node * added = (linked_list_node *) malloc(sizeof(linked_list_node));
    added->next = NULL;
    added->value = value;

    if (ptr->tail != NULL)
        ptr->tail->next = added;
    
    if (ptr->head == NULL)
        ptr->head = added;
    
    ptr->tail = added;
    ptr->size++;

    /*
    printf("added node: %p - size: %d - head: %p - tail: %p\n", value, ptr->size,
        ptr->head != NULL ? ptr->head->value : NULL, 
        ptr->tail != NULL ? ptr->tail->value : NULL);
    */
}

void linked_list_remove_last(linked_list *ll) {

    linked_list_struct * ptr = (linked_list_struct *) ll;

    if (ptr->tail == NULL)
        return;

    linked_list_node * current = ptr->head;
    linked_list_node * previous = NULL;

    // inefficient: use a double linked list!
    while(current->next != NULL) {
        previous = current;
        current = current->next;
    }

    ptr->tail = previous;
    
    if (previous == NULL)
        ptr->head = current->next;
    else
        previous->next = current->next;
    
    ptr->size--;
    /*
    printf("removed node: %p - size: %d - head: %p - tail: %p\n", current->value, ptr->size, 
        ptr->head != NULL ? ptr->head->value : NULL, 
        ptr->tail != NULL ? ptr->tail->value : NULL);
    */

    free(current);
    return;
}

void linked_list_delete(linked_list *ll) {

    if (ll == NULL) {
        return;
    }

    linked_list_struct *ptr = (linked_list_struct *) ll;
    int i;
    for (i = 0; ptr->size > 0; i++) {
        linked_list_remove_last(ll);
    }

    free(ll);
    return;
}

linked_list_iterator * linked_list_iterator_new(linked_list *ll) {
    linked_list_struct *ptr = (linked_list_struct *) ll;
    return (linked_list_iterator *) ptr->head;
}

linked_list_iterator * linked_list_iterator_next(linked_list_iterator * iter) {
    linked_list_node *ptr = (linked_list_node *) iter;
    return ptr->next;
}

void * linked_list_iterator_getvalue(linked_list_iterator *iter) {
    linked_list_node *ptr = (linked_list_node *) iter;
    return ptr->value;
}

int linked_list_size(linked_list *ll) {
    linked_list_struct *ptr = (linked_list_struct *) ll;
    return ptr->size;
}