import org.junit.Before;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by Tuly on 5/7/2017.
 */
public class FinderTest {

    private static final int ELEMENTS = 5000;
    private static final int MAX_VALUE = 1000;

    private Set<Integer> set = new HashSet<>();

    @Before
    public void setUp() throws Exception {
        for (int i = 0; i < ELEMENTS; i++) {
            set.add(new Random().nextInt(MAX_VALUE));
        }
    }

    @org.junit.Test
    public void findNth() throws Exception {
        ArrayList<Integer> arraySet = new ArrayList<>(set);
        int nth = new Random().nextInt(set.size());

        Integer nthElement = Finder.findNth(arraySet, nth);
        Collections.sort(arraySet);
        assertEquals(arraySet.get(nth), nthElement);
    }

}