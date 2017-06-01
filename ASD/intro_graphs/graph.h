#ifndef GRAPH_H
#define	GRAPH_H

#include "linked_list.h"

#ifdef	__cplusplus
extern "C" {
#endif

typedef void graph;
typedef void graph_node;
typedef void graph_arch;
typedef enum {UNDIRECTED, DIRECTED} GRAPH_TYPE;

graph * graph_new(GRAPH_TYPE type);
linked_list * graph_get_nodes(graph * g);
linked_list * graph_get_neighbors(graph * g, graph_node * n);
graph_node * graph_add_node(graph * g, void * value);
void graph_add_edge(graph * g, graph_node * from, graph_node * to);
void * graph_get_node_value(graph * g, graph_node * n);
void graph_delete(graph * h);

linked_list * graph_get_path(graph * g, graph_node * s, graph_node * t);

void sweep(graph * g, char * format_string);

#ifdef	__cplusplus
}
#endif

#endif	/* GRAPH_H */

