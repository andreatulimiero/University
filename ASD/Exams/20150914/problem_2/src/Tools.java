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

    private static NodeCount _leastCommonAncestor(NodeTree node, NodeTree u, NodeTree v){
        if (node == null) return new NodeCount(0, null);
        int count = 0;
        for (NodeTree child = node.getFirstChild(); child != null; child = child.getNextSibling()) {
            NodeCount nodeCount = _leastCommonAncestor(child, u, v);
            if (nodeCount.node != null) return nodeCount;
            count += nodeCount.count;
        }
        if (node == u || node == v) count++;
        if (count == 0) return new NodeCount(0, null);
        else if (count == 1) return new NodeCount(1, null);
        else return new NodeCount(2, node);
    }

    public static NodeTree leastCommonAncestor(NodeTree root, NodeTree u, NodeTree v) {
        NodeTree ancestor = _leastCommonAncestor(root, u, v).node;
        if (ancestor == null) ancestor = root;
        return ancestor;
    }

    private static class NodeCount {
        int count;
        NodeTree node;

        public NodeCount(int count, NodeTree node) {
            this.count = count;
            this.node = node;
        }
    }

}
