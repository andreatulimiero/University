package officina;

import javax.naming.OperationNotSupportedException;
import java.util.Iterator;
import java.util.function.Consumer;

/**
 * Created by tulim on 3/8/2017.
 */
public class ProtectedIterator<E> implements Iterator<E> {

    Iterator<E> iterator;

    public ProtectedIterator(Iterator<E> iterator) {
        this.iterator = iterator;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public E next() {
        return iterator.next();
    }

    @Override
    public void remove() { }

    @Override
    public void forEachRemaining(Consumer<? super E> action) {
        iterator.forEachRemaining(action);
    }

}
