#ifndef QUEUE_H
#define	QUEUE_H

#ifdef	__cplusplus
extern "C" {
#endif

#include "tree.h"

typedef void queue;
    
queue * new_queue();
void queue_enqueue(queue * q, node_tree * n);
node_tree * queue_dequeue(queue * q);
int queue_size(queue * q);
void delete_queue(queue * q);

#ifdef	__cplusplus
}
#endif

#endif	/* QUEUE_H */