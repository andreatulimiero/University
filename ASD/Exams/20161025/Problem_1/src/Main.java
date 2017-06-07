/**
 * Created by tulim on 6/7/2017.
 */
public class Main {

    static int[] crea(int n) {
        int[] arr = new int[n]; // assumere new O(1)
        for(int i = 0; i < n; i++)
            arr[i] = (int)(Math.random()*n); // assumere Math.random() O(1)
        return arr;
    }

    static int processa(int[] a, int i, int j) {
        if(i == j)
            return a[i];
        int p = a[i];
        if((p >= i) && (p < j)) {
            int left = processa(a, i, p);
            int right = processa(a, p+1, j);
            int q = left - right;
            return q >= 0 ? q: -q;
        } else
            return a[j];
    }

    static int processa(int[] a) {
        return processa(a, 0, a.length-1);
    }

    static int tutto(int n) {
        return processa(crea(n));
    }

    public static void main(String[] args) {
        System.out.println(processa(new int[]{0,0,0,0}));
    }

}
