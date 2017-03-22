package list;

//classe ausiliaria per l'utilizzo della pila
//si noti NON E' public!!!

class Node<T> {
    T info;
    Node<T> next;

    public Node() {
    }

    public Node(T info, Node<T> next) {
        this.info = info;
        this.next = next;
    }
}
