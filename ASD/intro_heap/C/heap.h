#ifndef HEAP_H
#define	HEAP_H

#ifdef	__cplusplus
extern "C" {
#endif
    
typedef int HEAP_TYPE;
#define MIN_HEAP 1
#define MAX_HEAP 0
#define ARRAY_SIZE 64

typedef void heap;
typedef void heap_entry;

heap * heap_new(HEAP_TYPE type, int capacity);
HEAP_TYPE heap_type(heap * h);
int heap_peek(heap * h);
int heap_poll(heap * h);
heap_entry * heap_add(heap * h, int key);
int get_key_entry(heap_entry * e);
int heap_size(heap * h);
void heap_delete(heap * h);
void heap_print(heap * h);

heap * array2heap(int * array, int size, HEAP_TYPE type);

void heap_sort(int * array, int size);

void heap_update_key(heap * h, heap_entry * e, int key);

#ifdef	__cplusplus
}
#endif

#endif	/* HEAP_H */

