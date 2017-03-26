#include <stdlib.h>

#include "tree.h"
#include "queue.h"
#include "stack.h"

typedef struct queue_node {
    node_tree * node;
    struct queue_node * prev;
    struct queue_node * next;
} queue_node;

typedef struct _queue {
	int size;
	queue_node * head;
	queue_node * tail;
} _queue;

queue * new_queue() {
	
	_queue * q = malloc(sizeof(_queue));
	q->size = 0;
	q->head = NULL;
	q->tail = NULL;

	return q;
}

void queue_enqueue(queue * q, node_tree * node) {

	//printf("adding node %d from queue %p\n", get_node_info(node), q);

	_queue * qq = q;
	qq->size++;

	// create a new queue node
	queue_node * n = malloc(sizeof(queue_node));
	n->node = node;

	// node prev is the current tail
	n->prev = qq->tail;

	// if there is a prev node, then update its next pointer
	if (n->prev != NULL)
		n->prev->next = n;

	// this node is the new tail
	n->next = NULL;
	qq->tail = n;

	if (qq->head == NULL)
		qq->head = n;
}

node_tree * queue_dequeue(queue * q) {

	_queue * qq = q;

	if (qq == NULL || qq->size <= 0)
		return NULL;

	qq->size--;

	// get head node
	queue_node * head = qq->head;
	node_tree * node = head->node;

	//printf("removing node %d from queue %p\n", get_node_info(node), q);

	// move head to second node
	qq->head = head->next;
	
	// if no head node, then fix tail
	if (qq->head == NULL)
		qq->tail = NULL;
	else
		qq->head->prev = NULL; // fix prev of new head

	free(head);
	return node;
}

int queue_size(queue * q){

	_queue * qq = q;
	if (qq == NULL)
		return -1;

	return qq->size; 
}

void delete_queue(queue * q) {

	while(queue_size(q) > 0)
		queue_dequeue(q);

	free(q);
}

stack * new_stack() {
	return new_queue();
}

void stack_push(stack * q, node_tree * node) {

	_queue * qq = q;
	qq->size++;

	// create a new queue node
	queue_node * n = malloc(sizeof(queue_node));
	n->node = node;
	n->next = NULL;
	n->prev = qq->tail;

	// if there is a prev node, then update its next pointer
	if (n->prev != NULL)
		n->prev->next = n;

	// this node is the new tail
	qq->tail = n;

	// if first node, then this is node is the new head
	if (qq->head == NULL)
		qq->head = n;
}

node_tree * stack_pop(stack * q) {

	_queue * qq = q;
	if (qq == NULL || qq->size <= 0)
		return NULL;

	qq->size--;

	// get tail node
	queue_node * tail = qq->tail;
	node_tree * node = tail->node;

	// move tail to last second node
	qq->tail = tail->prev;

	if (qq->tail != NULL)
		qq->tail->next = NULL;
	else
		qq->head = NULL;
	
	free(tail);
	return node;
}

int stack_size(stack * q) {
	return queue_size(q);
}

void delete_stack(stack * q) {
	delete_queue(q);
}