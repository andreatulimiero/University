#include "graph.h"
#include <stdlib.h>
#include <stdio.h>
#include <assert.h>

#include "linked_list.h"

typedef enum {TREE, BACK, FORWARD, CROSS} ARCH_TYPE; 

typedef struct graph_s {
	linked_list* nodes;
	linked_list* archs;
	GRAPH_TYPE type;
} graph_t;

typedef struct graph_node_s {
	void* value;
	linked_list* adjacent_nodes;
	linked_list* archs;
	unsigned int label;
	unsigned int left_label;
	short unsigned int visited;
} graph_node_t;

typedef struct arch_s {
	graph_node* from;
	graph_node* to;
	ARCH_TYPE type;
	unsigned short int explored;
} arch_t;

graph * graph_new(GRAPH_TYPE type) {
    graph_t* graph = malloc(sizeof(graph_t));
	graph->nodes = linked_list_new();
	graph->archs = linked_list_new();
	graph->type = type;
	return graph;
}

linked_list * graph_get_nodes(graph * gg) {
	return ((graph_t*) gg)->nodes;
}

linked_list * graph_get_neighbors(graph * g, graph_node * n) {
    return ((graph_node_t*) n)->adjacent_nodes;
}

graph_node * graph_add_node(graph * gg, void * value) {
	graph_node_t* graph_node = malloc(sizeof(graph_node_t));
	graph_node->value = value;
	graph_node->adjacent_nodes = linked_list_new();
	graph_node->archs = linked_list_new();
	linked_list_add(((graph_t*) gg)->nodes, graph_node);
	return graph_node;
}

void graph_add_edge(graph * gg, graph_node * from, graph_node * to) {
	graph_t* graph = (graph_t*) gg;
	arch_t* arch = malloc(sizeof(arch_t));
	arch->from = from;
	arch->to = to;
	linked_list_add(graph->archs, arch);
	linked_list_add(((graph_node_t*)from)->adjacent_nodes, to);
	linked_list_add(((graph_node_t*)from)->archs, arch);
	if (graph->type == UNDIRECTED) {
		linked_list_add(((graph_node_t*)to)->adjacent_nodes, from);
		linked_list_add(((graph_node_t*)to)->archs, arch);
	}
}

void * graph_get_node_value(graph * g, graph_node * nn) {
	return ((graph_node_t*) nn)->value;
}

void graph_delete(graph * gg) {
	graph_t* graph = (graph_t*) gg;
	for (linked_list_iterator* i = linked_list_iterator_new(graph_get_nodes(gg)); i != NULL; i = linked_list_iterator_next(i)){
		graph_node* node = (graph_node*) linked_list_iterator_getvalue(i);
		linked_list_delete(graph_get_neighbors(gg, node));
		free(node);
	}
	linked_list_delete(graph->nodes);
	linked_list_delete(graph->archs);
	graph->nodes = linked_list_new();
	graph->archs = linked_list_new();
	return;
}

static int _graph_get_path(graph_node* s, graph_node* d, linked_list* list) {
	if (s == NULL) return 0;
	graph_node_t* source = (graph_node_t*) s;
	source->visited = 1;
	graph_node_t* destination = (graph_node_t*) d;
	if (source->value == destination->value) { linked_list_add(list, source->value); return 1; };
	for (linked_list_iterator* i = linked_list_iterator_new(source->adjacent_nodes); i != NULL; i = linked_list_iterator_next(i)) {
		graph_node_t* adjacent_node = (graph_node_t*) linked_list_iterator_getvalue(i);
		if (adjacent_node->visited) continue;
		int found = _graph_get_path(adjacent_node, d, list);
		if (found) {
			linked_list_add(list, source->value);
			return 1;
		}
	}
	return 0;
}

static void graph_print(graph * g) {

    linked_list * nodes = graph_get_nodes(g);
    linked_list_iterator * lli = linked_list_iterator_new(nodes);
    while (lli != NULL) {
        
        graph_node * node = (graph_node *) linked_list_iterator_getvalue(lli);
        printf("[%u/%u] %s : ", ((graph_node_t*)node)->label, ((graph_node_t*)node)->left_label, (char *) graph_get_node_value(g, node));

        linked_list * neighbors = graph_get_neighbors(g, node);
        linked_list_iterator * inner_lli = linked_list_iterator_new(neighbors);
        while (inner_lli != NULL) {
            graph_node * n = (graph_node *) linked_list_iterator_getvalue(inner_lli);
            printf("%s ", (char *) graph_get_node_value(g, n));
            inner_lli = linked_list_iterator_next(inner_lli);
        }
        printf("\n");

        lli = linked_list_iterator_next(lli);
    }
}


linked_list * graph_get_path(graph * gg, graph_node * s, graph_node * t) {
	linked_list* list = linked_list_new();
	_graph_get_path(s, t, list);
	return list;
}

static graph_node_t* get_opposite(graph_node_t* node, arch_t* arch) {
	if (arch->from == node) return arch->to;
	return arch->from;
}

static void _sweep(graph_t* graph, graph_node_t* node, unsigned int* label, unsigned int* left_label) {
	node->visited = 1;
	node->label = ++(*label);
	node->left_label = 0;
	if (linked_list_size(node->archs))
		for (linked_list_iterator* i = linked_list_iterator_new((node)->archs); i != NULL; i = linked_list_iterator_next(i)) {
			arch_t* arch = linked_list_iterator_getvalue(i);
			if (arch->explored) continue;
			if (graph->type == DIRECTED && arch->from != node) continue;
			graph_node_t* w = get_opposite(node, arch);
			if (!w->visited) { arch->type = TREE; _sweep(graph, w, label, left_label); }
			else if (w->left_label == 0) arch->type = BACK;
			else if (node->label < w->label) arch->type = FORWARD;
			else arch->type = CROSS;
		}
	node->left_label = ++(*left_label);
}

static char* get_arch_type_string(arch_t* arch) {
	switch (arch->type) {
		case TREE:
			return "TREE";
		case BACK:
			return "BACK";
		case FORWARD:
			return "FORWARD";
		case CROSS:
			return "CROSS";
	}
	return "WTF";
}

void sweep(graph * g, char * format_string) {
	unsigned int label = 0;
	unsigned int left_label = 0;
	graph_t* graph = (graph_t*) g;

	// Setup
	for (linked_list_iterator* i = linked_list_iterator_new(graph_get_nodes(g)); i != NULL; i = linked_list_iterator_next(i)) {
		graph_node_t* node = (graph_node_t*) linked_list_iterator_getvalue(i);
		node->visited = 0;
		node->label = 0;
		node->left_label = -1;
	}
	for (linked_list_iterator* i = linked_list_iterator_new(graph->archs); i != NULL; i = linked_list_iterator_next(i)) {
		arch_t* arch = (arch_t*) linked_list_iterator_getvalue(i);
		arch->explored = 0;
	}

	// DFS Driver
	for (linked_list_iterator* i = linked_list_iterator_new(graph_get_nodes(g)); i != NULL; i = linked_list_iterator_next(i)) {
		graph_node_t* node = (graph_node_t*) linked_list_iterator_getvalue(i);
		if (!node->visited) _sweep(((graph_t*)g), node, &label, &left_label);
	}

	//Results
	graph_print(g);
	for (linked_list_iterator* i = linked_list_iterator_new(graph->archs); i != NULL; i = linked_list_iterator_next(i)) {
		arch_t* arch = (arch_t*) linked_list_iterator_getvalue(i);
		graph_node_t* from = arch->from;
		graph_node_t* to = arch->to;
		char arrow_left = ' ';
		char arrow_right = ' ';
		if (((graph_t*)g)->type == DIRECTED) { arrow_left = '<'; arrow_right = '>'; }
		printf("[%s] %c %c-%c %c\n", get_arch_type_string(arch), *((char*)(from->value)), arrow_left, arrow_right, *((char*)(to->value)));
	} 
}
