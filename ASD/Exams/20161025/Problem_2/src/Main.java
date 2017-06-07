import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {

    public static ArrayList<Integer> orderedElements(BST.Node node) {
                if (node == null) return null;
                ArrayList<Integer> elements = new ArrayList<>();
                if (isLeaf(node)) {
                    elements.add(node.getKey());
                    return elements;
                }
                ArrayList<Integer> left = orderedElements(node.getLeft());
                ArrayList<Integer> right = orderedElements(node.getRight());
                if (left != null)
                    elements.addAll(left);
                elements.add(node.getKey());
                if (right != null)
                    elements.addAll(right);
                return elements;
    }

    private static boolean isLeaf(BST.Node node) {
            return node.getLeft() == null && node.getRight() == null;
    }

    public static ArrayList<Integer> intersection(BST<String> a, BST<String> b) {
        ArrayList<Integer> a_Elements = orderedElements(a.root);
        ArrayList<Integer> b_Elements = orderedElements(b.root);

        ArrayList<Integer> small, big;
        ArrayList<Integer> intersection = new ArrayList<>();
        if (a_Elements.get(0) < b_Elements.get(0)) {
            small = a_Elements;
            big = b_Elements;
        } else {
            small = b_Elements;
            big = a_Elements;
        }

        int i = 0, j = 0;
        while (i < small.size() && j < big.size()) {
            int small_value = small.get(i);
            int big_value = big.get(j);
            if (small_value == big_value) {
                intersection.add(small.get(i));
                i++;
                j++;
                continue;
            } else if (small_value < big_value) {
                i++;
                continue;
            } else {
                j++;
                continue;
            }
        }
        return intersection;
    }

    public static void main(String[] argv) {

        BST<String> a = new BST<>(3, "2");
        a.insert(2, "2");
        a.insert(4, "4");
        a.insert(5, "5");

        BST<String> b = new BST<>(2, "2");
        b.insert(1, "1");
        b.insert(4, "4");
        b.insert(3, "3");

        a.print();
        b.print();

        System.out.println(intersection(a, b));

    }

}
