package list;

/**
 * Created by Tuly on 3/7/2017.
 */
public class ListSideEffect<T>{

    private Node<T> head;

    public ListSideEffect() {
        head = null;
    }

    public boolean estVuota(){
        return head == null;
    }

    public void cons(T o){
        Node<T> node = new Node<>();
        node.info = o;
        node.next = head;
    }

    public T car(){
        if (head == null) throw new RuntimeException("List is empty");

        return head.info;
    }

    public void cdr(){
        if (head == null) throw new RuntimeException("List is empty");

        head = head.next;
    }

    @Override
    public Object clone() {
        try {
            ListSideEffect<T> clonedList = (ListSideEffect<T>) super.clone();
            if (!estVuota()) {
                Node<T> iterator = head;
                Node<T> nodeClone = new Node<>();
                clonedList.head = nodeClone;
                while (iterator != null) {
                    nodeClone.info = iterator.info;
                    iterator = iterator.next;
                    if (iterator != null) {
                        nodeClone.next = new Node<>();
                        nodeClone = nodeClone.next;
                    } else {
                        nodeClone.next = null;
                    }
                }
            }
            return clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e.toString());
        }
    }
}
