#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "linked_list.h"
#include "graph.h"

void graph_print(graph * g) {

    linked_list * nodes = graph_get_nodes(g);
    linked_list_iterator * lli = linked_list_iterator_new(nodes);
    while (lli != NULL) {
        
        graph_node * node = (graph_node *) linked_list_iterator_getvalue(lli);
        printf("%s : ", (char *) graph_get_node_value(g, node));

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

void print_help() {
    printf("Richiesto argomento: {graph, path, sweep}\n");
}

int main(int argc, char** argv) {

    if (argc != 2) {
        print_help();
        return EXIT_FAILURE;
    }

    if (strcmp(argv[1], "graph") == 0) {
        
        graph * graph = graph_new(UNDIRECTED);

        graph_node * a = graph_add_node(graph, "a");
        graph_node * b = graph_add_node(graph, "b");
        graph_node * c = graph_add_node(graph, "c");
        graph_node * d = graph_add_node(graph, "d");
        graph_node * e = graph_add_node(graph, "e");
        graph_node * f = graph_add_node(graph, "f");
        graph_node * g = graph_add_node(graph, "g");
        graph_node * h = graph_add_node(graph, "h");

        graph_add_edge(graph, a, b);
        graph_add_edge(graph, a, f);
        graph_add_edge(graph, b, c);
        graph_add_edge(graph, b, f);
        graph_add_edge(graph, c, d);
        graph_add_edge(graph, d, b);
        graph_add_edge(graph, e, f);
        graph_add_edge(graph, f, c);
        graph_add_edge(graph, g, h);

        printf("Grafo UNDIRECTED\n\n");
        graph_print(graph);
        printf("\n");

        graph_delete(graph);

        graph = graph_new(DIRECTED);

        a = graph_add_node(graph, "a");
        b = graph_add_node(graph, "b");
        c = graph_add_node(graph, "c");
        d = graph_add_node(graph, "d");
        e = graph_add_node(graph, "e");
        f = graph_add_node(graph, "f");
        g = graph_add_node(graph, "g");
        h = graph_add_node(graph, "h");

        graph_add_edge(graph, a, b);
        graph_add_edge(graph, a, f);
        graph_add_edge(graph, b, c);
        graph_add_edge(graph, b, f);
        graph_add_edge(graph, c, d);
        graph_add_edge(graph, d, b);
        graph_add_edge(graph, e, f);
        graph_add_edge(graph, f, c);
        graph_add_edge(graph, g, h);

        printf("Grafo DIRECTED\n\n");
        graph_print(graph);
        printf("\n");

        graph_delete(graph);
        
    } else if (strcmp(argv[1], "path") == 0) {
        
        graph * graph = graph_new(UNDIRECTED);

        graph_node * a = graph_add_node(graph, "a");
        graph_node * b = graph_add_node(graph, "b");
        graph_node * c = graph_add_node(graph, "c");
        graph_node * d = graph_add_node(graph, "d");
        graph_node * e = graph_add_node(graph, "e");
        graph_node * f = graph_add_node(graph, "f");
        graph_node * g = graph_add_node(graph, "g");
        graph_node * h = graph_add_node(graph, "h");

        graph_add_edge(graph, a, b);
        graph_add_edge(graph, a, f);
        graph_add_edge(graph, b, c);
        graph_add_edge(graph, b, f);
        graph_add_edge(graph, c, d);
        graph_add_edge(graph, d, b);
        graph_add_edge(graph, e, f);
        graph_add_edge(graph, f, c);
        graph_add_edge(graph, g, h);

        printf("Grafo UNDIRECTED\n\n");
        graph_print(graph);
        printf("\n");

        linked_list * path = graph_get_path(graph, a, f);
        linked_list_iterator * i = linked_list_iterator_new(path);
        while (i != NULL) {
            char * n = (char *) linked_list_iterator_getvalue(i);
            printf("%s", n);
            i = linked_list_iterator_next(i);
            if (i != NULL)
                printf(" -> ");
        }
        printf("\n");

        linked_list_delete(path);

        graph_delete(graph);

    } else if (strcmp(argv[1], "sweep") == 0) {

        graph * graph = graph_new(DIRECTED);

        graph_node * a = graph_add_node(graph, "a");
        graph_node * b = graph_add_node(graph, "b");
        graph_node * c = graph_add_node(graph, "c");
        graph_node * d = graph_add_node(graph, "d");
        graph_node * e = graph_add_node(graph, "e");
        graph_node * f = graph_add_node(graph, "f");
        graph_node * g = graph_add_node(graph, "g");
        graph_node * h = graph_add_node(graph, "h");

        graph_add_edge(graph, a, b);
        graph_add_edge(graph, a, f);
        graph_add_edge(graph, b, c);
        graph_add_edge(graph, b, f);
        graph_add_edge(graph, c, d);
        graph_add_edge(graph, d, b);
        graph_add_edge(graph, e, f);
        graph_add_edge(graph, f, c);
        graph_add_edge(graph, g, h);

        printf("Grafo DIRECTED\n\n");
        graph_print(graph);
        printf("\n");

        sweep(graph, "%s");

        graph_delete(graph);

    } else {

        print_help();
        return EXIT_FAILURE;
    }
    
    return EXIT_SUCCESS;
}