package list;

/**
 * Created by Tuly on 3/7/2017.
 */
public class Test {

    public static void main(String[] args){

        ListFunctional<Integer> list1 = new ListFunctional<>();
        list1.cons(3);
        list1.cons(2);
        list1.cons(1);
        System.out.println("List infos");
        System.out.println("Length: " + list1.length());
        System.out.println(list1);
        System.out.println("Grabbing first: " + list1.car());
        System.out.println("Adding 4 to the end");
        System.out.println("First list: " + list1);
        ListFunctional list2 = list1.tailInsert(4);
        System.out.println("Second list: " + list2);

        ListFunctional long_list = list1.append(list2);
        System.out.println("Appending list2 to list1: " + long_list);
        System.out.println("Grabbing element in position 4 from long_list: " + long_list.getElement(4));

        ListFunctional inserted_list = long_list.insertAt(3, 8);
    }

}
