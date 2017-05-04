#include <stdlib.h>
#include <stdio.h>
#include "heap.h"

typedef struct heap_s {
	HEAP_TYPE type;
	int* heap_array;
	int capacity;
	int size;
} heap_t;

typedef struct heap_entry_s {
	int key;
	int value;
} heap_entry_t;

static int left_child_pos(int pos) {
	return 2*pos + 1;
}

static int right_child_pos(int pos) {
	return 2*pos + 2;
}

heap * heap_new(HEAP_TYPE is_min_heap, int capacity) {
	int* heap = malloc(sizeof(heap_t));
	heap->size = ARRAY_SIZE;
	heap->heap_array = calloc(capacity, sizeof(int));
	heap->capacity = capacity;
	heap->type = is_min_heap;
	return heap;
}

HEAP_TYPE heap_type(heap * hh) {
	return hh->type;
}

int heap_peek(heap * hh) {
	return ((heap_t*) heap)->heap_array;	
}

heap_entry * heap_add(heap * hh, int key) {
	hh[heap_size] = key;
	up_heap(hh);
}

int get_key_entry(heap_entry * ee) {
	return 0;
}

int heap_size(heap * hh) {
	return 0;
}

int heap_poll(heap * hh) {
	return 0;
}

void heap_delete(heap * hh) {
	return;
}

heap * array2heap(int * array, int size, HEAP_TYPE is_min_heap) {
	return NULL;
}

void heap_print(heap * hh) {
	heap_t* heap = (heap_t*) hh;
	int* array = heap->heap_array;
	int i;
	for (i = 0; i < heap->size && array[i] != 0; i++)
		printf("%d ", array[i]);
	printf("\n");
}

void heap_sort(int * array, int size) {
	return;
}

void heap_update_key(heap * hh, heap_entry * ee, int key) {
	return;     
}

