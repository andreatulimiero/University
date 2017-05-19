#include <stdlib.h>

#include "albero_bello.h"
#include "bst.h"

static inline int is_terminal(node_t* node) {
  if (node == NULL) return 1;
  return node->left == NULL && node->right == NULL;
}

static int w(node_t* node) {
  if (node == NULL) return 0;
  return node->key + w(node->left) + w(node->right);
}

int is_albero_bello(bst_t* bst) {
  node_t* root = bst->root;

  if (is_terminal(root) || (is_terminal(root->left) && is_terminal(root->right))) {
    int left_sum = (root->left == NULL ? 0 : root->left->key);
    int right_sum = (root->right == NULL ? 0 : root->right->key);
    return root->key >= abs(left_sum + right_sum) ? root->key + left_sum + right_sum : 0;
  } 

  bst_t* left_subtree = malloc(sizeof(bst_t));
  left_subtree->root = root->left;
  bst_t* right_subtree = malloc(sizeof(bst_t));
  right_subtree->root = root->right;

  int left_sum = is_albero_bello(left_subtree);
  int right_sum = is_albero_bello(right_subtree);
  
  if (left_sum == 0 || right_sum == 0) return 0;
  return root->key >= abs(left_sum - right_sum) ? root->key + left_sum + right_sum : 0;
}