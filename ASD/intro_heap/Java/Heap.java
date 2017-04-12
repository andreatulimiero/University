import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

public class Heap {

    private HEAP_TYPE type;
    private int elements;
    private ArrayList<HeapEntry> heap;

    public enum HEAP_TYPE {MAX_HEAP, MIN_HEAP};

    public static class HeapEntry {
        int key;

        public HeapEntry(int key) {
            this.key = key;
        }

        @Override
        public String toString() {
            return String.valueOf(key);
        }

    }

    public Heap(HEAP_TYPE type, int capacity) {
        this.type = type;
        heap = new ArrayList<>(capacity);
        heap.add(null);
    }

    public HEAP_TYPE getType() {
        return type;
    }

    public int peek() {
        if (elements == 0) return -1;
        return heap.get(1).key;
    }

    private boolean isViolated(HeapEntry father, HeapEntry children) {
        return type == HEAP_TYPE.MAX_HEAP ? children.key > father.key : children.key < father.key;
    }

    private void upHeap(int pos) {
        HeapEntry node;
        while (pos > 1) {
            node = heap.get(pos);
            int father_pos = (pos % 2) == 0 ? pos / 2 : (pos - 1) / 2;
            HeapEntry father = heap.get(father_pos);
            if (isViolated(father, node)) {
                heap.remove(pos);
                heap.add(pos, father);
                heap.remove(father_pos);
                heap.add(father_pos, node);
            }
            pos = father_pos;
        }
    }

    private void downHeap() {
        heap.add(1, heap.remove(elements));
        downHeap(1);
    }

    private void downHeap(int pos) {
        HeapEntry node;
        while (pos <= elements) {
            node = heap.get(pos);
            int left = 2*pos;
            int right = 2*pos + 1;
            print();
            System.out.println("On: " + node + " left: " + (left <= elements ? heap.get(left) : null) + " right: " + (right <= elements ? heap.get(right) : null) + "\n");
            int fittest = pos;
            if (left <= elements && isViolated(node, heap.get(left))) fittest = left;
            if (right <= elements) {
                if (type == HEAP_TYPE.MAX_HEAP) {
                    if (heap.get(right).key > heap.get(fittest).key) fittest = right;
                } else {
                    if (heap.get(right).key < heap.get(fittest).key) fittest = right;
                }
            }
            System.out.println("Fittest " + heap.get(fittest));
            if (fittest != pos) {
                HeapEntry fittestNode = heap.get(fittest);
                heap.remove(pos); 
                heap.add(pos, fittestNode);
                heap.remove(fittest);
                heap.add(fittest, node);
                print();
                downHeap(fittest);
            }
            return;
        }
    }

    public HeapEntry add(int key) {
        HeapEntry entry = new HeapEntry(key);
        heap.add(++elements, entry);
        upHeap(elements);
        return entry;
    }

    public int getEntryKey(HeapEntry e) {
        for (int i = 1; i < elements; i++) {
            if (e == heap.get(i)) return heap.get(i).key;
        }
        return -1;
    }   

    public int size() {
        return elements;
    }

    public int poll() {
        HeapEntry top = heap.remove(1);
        elements--;
        if (elements > 0)
            downHeap();
        return top.key;
    }

    private static void heapify(int[] array, int i) {
        if (i >= array.length) return;
        int left = 2*i + 1;
        int right = 2*i + 2;
        int largest = i;
        if (left < array.length && array[left] > array[i]) largest = left;
        if (right < array.length && array[right] > array[largest]) largest = right;
        if (largest != i) {
            int tmp = array[i]; 
            array[i] = array[largest];
            array[largest] = tmp;
            heapify(array, largest);
        }
    }

    public static Heap array2heap(int[] array, HEAP_TYPE type) {
        Heap heap = new Heap(HEAP_TYPE.MAX_HEAP, array.length);
        for (int i = array.length / 2; i >= 0; i--) {
            heapify(array, i);
        }
        for (int i = 0; i < array.length; i++) heap.heap.add(i + 1, new HeapEntry(array[i]));
        heap.elements = array.length;
        return heap;
    }

    public void print() {
        for (int i = 1; i <= elements; i++) {
            System.out.print(heap.get(i).key + " ");
        }
        System.out.println();
    }

    public static void heapSort(int[] array) {
        Heap heap = array2heap(array, HEAP_TYPE.MIN_HEAP);
        for (int i = 0; heap.size() > 0; i++) {
            // array[array.length - (i + 1)] = heap.poll();
            System.out.print(heap.poll() + " ");
            heap.print();
        }
    }

    public void updateEntryKey(HeapEntry e, int key) {
        for (int i = 1; i <= elements; i++) {
            if (heap.get(i) == e) {
                heap.remove(i);
                heap.add(i, new Heap.HeapEntry(key));
                if (type == HEAP_TYPE.MAX_HEAP) {
                    if (key > e.key) {
                        upHeap(i);
                    } else if (key < e.key) {
                        downHeap(i);
                    }
                } else {
                    if (key > e.key) {
                        downHeap(i);
                    } else if (key < e.key) {
                        upHeap(i);
                    }
                }
            }
        }
    }

}
