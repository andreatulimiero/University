public class BST<V> {

    private int key;
    private V value;
    private BST left, right;

    public BST(int key, V value) {
        this.key = key;
        this.value = value;
    }

    public void insert(int k, V v) {
        if (k > this.key) {
            if (this.right == null)
                this.right = new BST(k, v);
            else
                this.right.insert(k, v);
        } else if (k < this.key) {
            if (this.left == null)
                this.left = new BST(k, v);
            else
                this.left.insert(k, v);
        } else
            this.value = v;
    }

    public V find(int k) {
        if (k > this.key) {
            if (this.right == null) return null;
            return this.right.find(k);
        } else if (k < this.key) {
            if (this.left == null) return null;
            return this.left.find(k);
        } else
            return this.value;
    }

    public int findMin() {
        if (this.left == null) return this.key;
        return this.left.findMin();
    }

    public void removeMin() {
        if (this.left == null) this = null;
        return this.left.remove();
    }

    private BST findMax() {
        if (this.right == null) return this;
        return this.right.findMax();
    }

    private BST predecessor() {
        if (this.left == null) return this;
        return this.left.findMax();
    }

    private BST _remove(int k) {
        if (k > this.key) {
            if (this.right == null) return null;
            return this.right._remove(k);
        } else if (k < this.key) {
            if (this.left == null) return null;
            return this.left._remove(k);
        } else {
            if (this.isLeaf()) return null;
            if (this.left.isLeaf()) return this.left;
            else if (this.right.isLeaf()) return this.right;
            else {
                BST predecessor = this.predecessor();
                this.remove(predecessor.key);
                return predecessor;
            }
        }
    }

    public void remove(int k) {
        if (k > this.key) {
            if (this.right == null) return;
            this.right = this.right._remove(k);
        } else if (k < this.key) {
            if (this.left == null) return;
            this.left = this.left._remove(k);
        } else {
            /* Deleting most ancient father, think about this */
            return;
        }
    }

    void print() {
        return;
    }

    int predecessor(int k) {
        return 0;
    }

    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }
}
