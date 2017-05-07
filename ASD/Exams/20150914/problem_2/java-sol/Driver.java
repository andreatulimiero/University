import javax.xml.soap.Node;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Driver {

    public static void main(String[] argv) {

        if (argv.length < 1) {
            System.out.println("Richiesto argomento: {tree, parse, max, postorder}");
            return;
        }

        if (argv[0].equals("tree")) {

            NodeTree root = buildOne();
            root.printTree();

        } else if (argv[0].equals("parse")) {

            System.out.println("Reading tree description from stdin...");
            NodeTree root = ParseTree.parseTree();
            System.out.println("\nPrinting tree...");
            root.printTree();

        } else if (argv[0].equals("max")) {

            NodeTree root = buildTwo();

            System.out.println("Printing tree...\n");
            root.printTree();

            System.out.println("Level with max sum is: " + MaxLevel.maxLevel(root));

        } else if (argv[0].equals("postorder")) {

            NodeTree root = buildOne();

            System.out.println("\nPrinting tree...");
            root.printTree();

            System.out.println("PostOrder visit...");
            IterPostOrder.iterativePostOrderVisit(root);

        } else {
            System.out.println("Richiesto argomento: {tree, parse, max, postorder}");
        }

    }


    public static NodeTree buildOne() {

        NodeTree root = new NodeTree(10);
        NodeTree a = new NodeTree(6);
        NodeTree b = new NodeTree(5);
        NodeTree c = new NodeTree(9);

        NodeTree d = new NodeTree(2);
        NodeTree e = new NodeTree(8);

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

        NodeTree d = new NodeTree(2);
        NodeTree e = new NodeTree(8);

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
