#include <stdlib.h>

#include "albero_bello.h"
#include "bst.h"

static int w(node_t* node) {
  if (node == NULL) return 0;
  return node->key + w(node->left) + w(node->right);
}

int is_albero_bello(bst_t* bst) {
  node_t* root = bst->root;
  return root->key >= abs(w(root->left) - w(root->right));
}