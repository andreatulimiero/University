#include <stdio.h>
#include <stdlib.h>

#include "parse_tree.h"
#include "tree.h"

char * next_line(char * buffer, int size) {
    size_t s = size;
    ssize_t res = getline(&buffer, &s, stdin);
    if (res >= 0)
    	return buffer;
    else
    	return NULL;
}

int parse_int(char * buffer) {
    if (*buffer != '-') return atoi(buffer);
    return parse_int(buffer + 1);
}

int parse_depth(char* buffer) {
    if (*buffer != '-') return 0;
    return 1 + parse_depth(buffer + 1);
}

node_tree* parse(node_tree* node, int depth, char* line) {
    if (line == NULL) return;
    node_tree* last_node = NULL;
    node_tree* first_child = NULL;

    do {
        int node_depth = parse_depth(line);
        int info = parse_int(line);

        if (node_depth > depth) {
           set_first_child(last_node, parse(node, depth + 1, line)); 
           if (line == NULL) return first_child;
           node_depth = parse_depth(line);
           if (node_depth != depth) continue;
           info = parse_int(line);
        }
        else if (node_depth < depth) return first_child;

        node_tree* nnode = new_node_tree(info);
        if (first_child == NULL){
            last_node = nnode;
            first_child = last_node;
        }
        else {
            set_next_sibling(last_node, nnode);
            last_node = nnode;
        }
    } while ((line = next_line(line, 1024)) != NULL);

    return first_child;
}

node_tree * parse_tree() {
    char* line = malloc(sizeof(char) * 1024);
    node_tree* node = new_node_tree(parse_int(next_line(line, 1024)));
    int depth = 1;
    set_first_child(node, parse(node, depth, next_line(line, 1024))); 
    return node;
}

