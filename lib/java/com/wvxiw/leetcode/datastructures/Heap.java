/**
 * @Author  wvxiw
 * @Title   The heap data structure implementation for leetcode
 */
package com.wvxiw.leetcode.datastructures;

public class Heap {
    private int[] h; // Heap
    private int size; // Current heap size

    private final boolean isMinHeap;

    public Heap(int[] a) {
        this(a, false);
    }

    public Heap(int[] a, boolean isMinHeap) {
        h = new int[a.length];
        this.isMinHeap = isMinHeap;
        build(a);
    }

    static public int getParent(int i) {
        return (i - 1) / 2;
    }

    static public int getL(int i) {
        return 2 * i + 1;
    }

    static public int getR(int i) {
        return 2 * i + 2;
    }

    public int[] getHeap(){
        return h;
    }
    public void build(int[] a) {
//        h = buildWilliams(a);
        h = buildFloyd(a);
        size = h.length;
    }

    /**
     * Williams' method of building heap.
     * https://en.wikipedia.org/wiki/Binary_heap#Building_a_heap
     */
    private int[] buildWilliams(int[] a) {
        int[] b = new int[a.length];
        // Insert nodes one by one and to the end of heap and bubble-up, if needed, to restore the heap property
        for (int i = 0; i < a.length; i++) {
            b[i] = a[i];
            int j = i;
            while (j > 0
                    && (!isMinHeap && b[j] > b[Heap.getParent(j)] || isMinHeap && b[j] < b[Heap.getParent(j)])) {
                int temp = b[j];
                b[j] = b[Heap.getParent(j)];
                b[Heap.getParent(j)] = temp;

                j = Heap.getParent(j);
            }
        }
        return b;
    }

    /**
     * Floyd's method of building heap (faster).
     * https://en.wikipedia.org/wiki/Heapsort#Floyd's_heap_construction
     */
    // todo this method is not working with #912
    private int[] buildFloyd(int[] a) {
        for (int i = a.length/2; i >= 0 ; i--)
//        for (int i = 0; i < (a.length/4) *3; i++)
            heapify(i);
        return a;
    }

    /** Restore heap property at i-th node */
    public void heapify(int i) {
        while (true) {
            int largest;
            int l = getL(i);
            int r = getR(i);

            if (l < size && (!isMinHeap && h[l] > h[i] || isMinHeap && h[l] < h[i]))
                largest = l;
            else
                largest = i;

            if (r < size && (!isMinHeap && h[r] > h[largest] || isMinHeap && h[r] < h[largest]))
                largest = r;

            if (largest == i)
                break;

            int temp = h[i];
            h[i] = h[largest];
            h[largest] = temp;

            i = largest;
        }
    }

    /** Extract from the top (max or min) */
    public int extractTop() {
        int top = h[0];
        h[0] = h[size-1];
        size--;
        heapify(0);
        return top;
    }
}
