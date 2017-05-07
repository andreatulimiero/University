public class IterPostOrder {

    public static void iterativePostOrderVisit(NodeTree node){

        Stack s = new Stack();
        boolean from_stack = false;

        while(s.size() > 0 || node != null) {

            if (node != null) {

                if (from_stack == false && node.getFirstChild() != null) {

                    s.push(node);
                    node = node.getFirstChild();

                } else {

                    System.out.println("Visiting node: " + node.getNodeInfo());
                    node = node.getNextSibling();

                }

            }

            if (node == null) {

                if (s.size() > 0) {
                    node = s.pop();
                    from_stack = true;
                }

            } else {
                from_stack = false;
            }
        }
    }

}
