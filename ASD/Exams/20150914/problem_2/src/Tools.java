/**
 * Created by Tuly on 5/7/2017.
 */
public class Tools {

    private static boolean containsNode(NodeTree root, NodeTree node) {
        if (root == null) return false;
        if (root == node) return true;
        NodeTree child = root.getFirstChild();
        while (child != null) {
            if (containsNode(child, node)) return true;
            child = child.getNextSibling();
        }
        return false;
    }

    private static NodeTree _leastCommonAncestor(NodeTree node, NodeTree u, NodeTree v){
        if (!containsNode(node, u) || !containsNode(node, v)) return null;
        NodeTree child = node.getFirstChild();
        while (child != null) {
            if (_leastCommonAncestor(child, u, v) != null) return _leastCommonAncestor(child, u, v);
            child = child.getNextSibling();
        }
        return node;
    }

    public static NodeTree leastCommonAncestor(NodeTree root, NodeTree u, NodeTree v) {
        NodeTree ancestor = _leastCommonAncestor(root, u, v);
        if (ancestor == null) ancestor = root;
        return ancestor;
    }

}
