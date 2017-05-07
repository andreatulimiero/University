import java.util.LinkedList;

public class Queue {

    private LinkedList<NodeTree> q;

    public Queue() {
        this.q = new LinkedList<NodeTree>();
    }

    public void enqueue(NodeTree n) {
        q.add(n);
    }

    public NodeTree dequeue() {
        return q.poll();
    }

    public int size() {
        return q.size();
    }

}
