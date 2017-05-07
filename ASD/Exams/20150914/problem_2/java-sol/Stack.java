
public class Stack {

    private java.util.Stack<NodeTree> s;

    public Stack() {
        this.s = new java.util.Stack<NodeTree>();
    }

    public void push(NodeTree n) {
        s.push(n);
    }

    public NodeTree pop() {
        return s.pop();
    }

    public int size() {
        return s.size();
    }

}
