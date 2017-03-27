#ifndef TREE_H
#define	TREE_H

#ifdef	__cplusplus
extern "C" {
#endif
    
typedef void node_tree;

typedef struct {
    int info;
    struct node_t* sibling;
    struct node_t* child;
} node_t;

node_tree * new_node_tree(int info);

int get_node_info(node_tree * node);

void set_first_child(node_tree * node, node_tree * firstChild);
node_tree * get_first_child(node_tree * node_tree);

void set_next_sibling(node_tree * node, node_tree * set_next_sibling);
node_tree * get_next_sibling(node_tree * node_tree);

void print_depth(int depth);

void print_tree(node_tree * node_tree);

void delete_tree(node_tree * node_tree);

#ifdef	__cplusplus
}
#endif

#endif	/* TREE_H */

