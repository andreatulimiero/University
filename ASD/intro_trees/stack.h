#ifndef STACK_H
#define	STACK_H

#ifdef	__cplusplus
extern "C" {
#endif

#include "tree.h"

typedef void stack;
    
stack * new_stack();
void stack_push(stack * q, node_tree * n);
node_tree * stack_pop(stack * q);
int stack_size(stack * q);
void delete_stack(stack * q);

#ifdef	__cplusplus
}
#endif

#endif	/* STACK_H */