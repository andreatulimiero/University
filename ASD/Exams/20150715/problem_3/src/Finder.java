import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

/**
 * Created by Tuly on 5/7/2017.
 */
public class Finder {

    private static int getPivot(ArrayList<Integer> set) {
        return new Random().nextInt(set.size());
    }

    /**
     * @param set The source set
     * @param nth The nth element to find
     * @param i The relative index of the set respect to the initial set
    */
    private static Integer _findNth(ArrayList<Integer> set, int nth, int i) {
        ArrayList<Integer> lower = new ArrayList<>();
        ArrayList<Integer> equal = new ArrayList<>();
        ArrayList<Integer> greater = new ArrayList<>();
        Integer pivot = set.get(getPivot(set));

        for (Integer e : set) {
            if (e < pivot) lower.add(e);
            else if (e > pivot) greater.add(e);
            else equal.add(e);
        }

        if (lower.size() + i == nth) return equal.get(0);
        if (nth > lower.size() + i) return _findNth(greater, nth, lower.size() + i + 1);
        else return _findNth(lower, nth, i);
    }

    public static Integer findNth(ArrayList<Integer> set, int nth) {
        if (nth >= set.size()) return null;
        return _findNth(set, nth, 0);
    }

}
