import java.util.NoSuchElementException;
import java.util.Scanner;

public class ParseTree {

    private static Scanner sc = new Scanner(System.in);

    public static String nextLine() {

        String s = null;
        try {
            s = sc.nextLine();
            //System.out.println("Got line: " + s);
        } catch(NoSuchElementException e) {
            s = null;
        }

        return s;
    }

    public static int parseInt(String s) {
        return Integer.parseInt(s);
    }

    public static NodeTree parseTree() {

        String s = nextLine();
        NodeTree root = null;
        if (s != null) {

            int depth = getDepthLine(s);

            if (depth != 0)
                return root;

            int value = parseInt(s.substring(depth));
            root = new NodeTree(value);

            s = nextLine();
            if (s != null)
                parseTreeRic(root, 0, s);
        }

        return root;
    }

    private static String parseTreeRic(NodeTree current, int current_depth, String s) {

        /*
         * ToDo: return both the string and the depth to avoid redundant calls
         *       to getDepthLine() over and over on the same string
         */

        int depth = getDepthLine(s);

        do {

            if (depth < current_depth)
                return s;

            else if (depth == current_depth) {

                int value = parseInt(s.substring(depth));
                NodeTree node = new NodeTree(value);

                current.setNextSibling(node);

                s = nextLine();
                if (s != null) {
                    s = parseTreeRic(node, depth, s);
                    depth = getDepthLine(s);
                } else
                    depth = -1; // EOF

            } else { // depth > current_depth

                int value = parseInt(s.substring(depth));
                NodeTree node = new NodeTree(value);

                current.setFirstChild(node);

                s = nextLine();
                if (s != null) {
                    s = parseTreeRic(node, depth, s);
                    depth = getDepthLine(s);
                } else
                    depth = -1; // EOF
            }

        } while(depth > 0);

        return null;
    }

    private static int getDepthLine(String s) {

        if (s == null)
            return -1;

        int depth;
        for (depth = 0; depth < s.length(); depth++)
            if (s.charAt(depth) != '-')
                break;

        return depth;
    }


}
