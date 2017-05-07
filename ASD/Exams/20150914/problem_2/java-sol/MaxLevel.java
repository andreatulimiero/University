public class MaxLevel {

    public static int maxLevel(NodeTree n) {

        if (n == null)
            return -1;

        Queue q = new Queue();
        Queue next_q = new Queue();
        NodeTree node = n;

        int depth = 0;
        int max_level = 0;
        int max_sum = 0;

        int sum = 0;

        while(node != null) {

            sum += node.getNodeInfo();

            if (node.getFirstChild() != null)
                next_q.enqueue(node.getFirstChild());

            if (node.getNextSibling() != null)
                node = node.getNextSibling();
            else
                node = null;

            if (node == null && q.size() > 0)
                node = q.dequeue();

            if (node == null) {

                System.out.println("Visited all nodes at level " + depth + " - sum: " + sum);
                if (sum > max_sum) {
                    max_level = depth;
                    max_sum = sum;
                }

                depth += 1;

                sum = 0;

                Queue temp = q;
                q = next_q;
                next_q = temp;

                node = q.dequeue();
            }
        }

        return max_level;
    }

}
