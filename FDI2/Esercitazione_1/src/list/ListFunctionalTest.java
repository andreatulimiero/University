package list;

import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.*;

/**
 * Created by tulim on 3/7/2017.
 */
public class ListFunctionalTest {

    @org.junit.Test
    public void estVuota() throws Exception {
        assertEquals(new ListFunctional<Integer>().estVuota(), true);
    }

    @org.junit.Test
    public void cons() throws Exception {
        ListFunctional l = new ListFunctional<Integer>().cons(1);
        assertEquals(l.getElement(0), 1);
    }

    @org.junit.Test
    public void car() throws Exception {
        ListFunctional<Integer> list = new ListFunctional<Integer>()
                .cons(3)
                .cons(2)
                .cons(1);

        assertEquals(list.car(), new Integer(1));
    }

    @org.junit.Test
    public void cdr() throws Exception {


    }

    @org.junit.Test
    public void length() throws Exception {
        ListFunctional<Integer> list = new ListFunctional<Integer>()
                .cons(3)
                .cons(2)
                .cons(1)
                .tailInsert(4);

        assertEquals(4, list.length());
    }

    @org.junit.Test
    public void tailInsert() throws Exception {
        ListFunctional<Integer> list = new ListFunctional<>();
        list.cons(3);
        list.cons(2);
        list.cons(1);
        ListFunctional list_tail = list.tailInsert(4);

        assertEquals(new Integer(4), list_tail.getElement(list_tail.length() - 1));
    }

    @org.junit.Test
    public void append() throws Exception {

    }

    @org.junit.Test
    public void getElement() throws Exception {
        ListFunctional<Integer> list = new ListFunctional<Integer>()
                .cons(3)
                .cons(2)
                .cons(1)
                .tailInsert(4);

        assertEquals(new Integer(4), list.getElement(list.length() - 1));
    }

    @org.junit.Test
    public void insertAt() throws Exception {
        ListFunctional<Integer> list = new ListFunctional<Integer>()
                .cons(3)
                .cons(2)
                .cons(1)
                .tailInsert(5)
                .insertAt(3, 4);

        assertEquals(new Integer(4), list.getElement(3));
    }

}