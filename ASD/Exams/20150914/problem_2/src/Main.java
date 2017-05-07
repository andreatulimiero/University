/**
 * Created by Tuly on 5/7/2017.
 */
public class Main {

    static NodeTree d, e;

    public static void main(String[] args) {

        NodeTree root = buildTwo();
        root.printTree();
        NodeTree leastCommonAncestor = Tools.leastCommonAncestor(root, d, e);
        System.out.println("Least Common Ancestor of " + d + " and " + e + " is " + leastCommonAncestor);
    }

    public static NodeTree buildOne() {

        NodeTree root = new NodeTree(10);
        NodeTree a = new NodeTree(6);
        NodeTree b = new NodeTree(5);
        NodeTree c = new NodeTree(9);

        d = new NodeTree(2);
        e = new NodeTree(8);

        root.setFirstChild(a);
        a.setNextSibling(b);
        b.setNextSibling(c);

        a.setFirstChild(d);
        d.setNextSibling(e);

        return root;
    }

    public static NodeTree buildTwo() {

        NodeTree root = new NodeTree(10);
        NodeTree a = new NodeTree(6);
        NodeTree b = new NodeTree(5);
        NodeTree c = new NodeTree(9);

        d = new NodeTree(2);
        e = new NodeTree(8);

        NodeTree f = new NodeTree(3);
        NodeTree g = new NodeTree(11);

        root.setFirstChild(a);
        a.setNextSibling(b);
        b.setNextSibling(c);

        a.setFirstChild(d);
        d.setNextSibling(e);

        c.setFirstChild(f);
        f.setNextSibling(g);

        return root;
    }

}
