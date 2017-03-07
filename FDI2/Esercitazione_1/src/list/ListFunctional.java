package list;

/**
 * Created by Tuly on 3/7/2017.
 */
public class ListFunctional<T> {

    private Node<T> head;

    public ListFunctional() {
        head = null;
    }

    private ListFunctional(Node<T> node) {
        head = node;
    }

    public boolean estVuota() {
        return head == null;
    }

    public ListFunctional<T> cons(T o) {
        Node<T> node = new Node<>();
        node.info = o;
        node.next = head;
        head = node;

        return new ListFunctional<>(node);
    }

    public T car() {
        if (head == null) throw new RuntimeException("List is empty");

        return head.info;
    }

    public ListFunctional<T> cdr() {
        if (head == null) throw new RuntimeException("List is empty");

        return new ListFunctional<>(head.next);
    }

    public int length() {
        if (estVuota()) return 0;

        return length(head);
    }

    private int length(Node<T> node) {
        if (node == null) return 0;

        return 1 + length(node.next);
    }

    public ListFunctional<T> tailInsert(T o) {
        if (estVuota()) return new ListFunctional<>(new Node<T>(o, null));

        return new ListFunctional<>(tailInsert(o, head));
    }

    private Node<T> tailInsert(T o, Node<T> node) {
        if (node.next == null)
            return new Node<>(node.info, new Node<>(o, null));

        return new Node<>(node.info, tailInsert(o, node.next));
    }

    private Node<T> lastElement(){
        if (head == null) return null;
        Node node;
        for (node = head; node.next != null; node = node.next)
            continue;
        return node;
    }

    public ListFunctional<T> append(ListFunctional<T> list) {
        ListFunctional<T> long_list = new ListFunctional<>(head);
        long_list.lastElement().next = list.head;
        return long_list;
    }

    public T getElement(int i){
        return getElement(head, i);
    }

    private T getElement(Node<T> node, int i){
        if (node == null) throw new RuntimeException("Out of range element");
        if (i == 0) return node.info;
        return getElement(node.next, i - 1);
    }

    private Node<T> getNode(Node<T> node, int i){
        if (node == null) throw new RuntimeException("Out of range element");
        if (i == 0) return node;
        return getNode(node.next, i - 1);
    }

    public ListFunctional<T> insertAt(int i, T o){
        if (i >= length()) throw new RuntimeException("List length is " + length());

        Node<T> node = new Node<>(o, getNode(head, i));
        getNode(head, i).next = node;
        return new ListFunctional<>()
    }

    private String prettifyList(Node<T> node) {
        if (node == null) return "";
        return String.valueOf(node.info) + (node.next == null ? "" : ", ") + prettifyList(node.next);
    }

    @Override
    public String toString() {
        return "[" + prettifyList(head) + "]";
    }
}
