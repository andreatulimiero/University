import java.util.*;

/**
 * Created by Tuly on 5/7/2017.
 */
public class Main {

    private static final int ELEMENTS = 5;
    private static final int MAX_VALUE = 1000;

    public static void main(String[] args) {

        ArrayList<Integer> set = new ArrayList<>();
        for (int i = 0; i < ELEMENTS; i++) {
            set.add(new Random().nextInt(MAX_VALUE));
        }
        System.out.println("Set: " + set.toString());
        System.out.print("Nth to find: ");
        int nth = new Scanner(System.in).nextInt();

        System.out.println(nth + "-nth element: " + Finder.findNth(set, nth));
    }

}
