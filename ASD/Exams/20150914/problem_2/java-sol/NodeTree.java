public class NodeTree {

    private int value;
    private NodeTree firstChild = null;
    private NodeTree nextSibling = null;

    public NodeTree(int info) {
        this.value = info;
    }

    public int getNodeInfo() {
        return this.value;
    }

    public void setFirstChild (NodeTree firstChild){
        this.firstChild = firstChild;
    }

    public NodeTree getFirstChild() {
        return this.firstChild;
    }

    public void setNextSibling (NodeTree nextSibling) {
        this.nextSibling = nextSibling;
    }

    public NodeTree getNextSibling() {
        return this.nextSibling;
    }

    private static void printTreeRic(NodeTree n, int depth) {

        if (n == null)
            return;

        for (int i = 0; i < depth; i++)
            System.out.print("-");

        System.out.println(n.getNodeInfo());
        printTreeRic(n.getFirstChild(), depth + 1);
        printTreeRic(n.getNextSibling(), depth);
    }

    public void printTree() {
        printTreeRic(this, 0);
    }
}
