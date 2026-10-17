package LLD.Heap.impl;

import LLD.Heap.Heap;

public class MinHeap<T extends Comparable<T>> extends Heap<T> {

    public MinHeap(int capacity) {
        super(capacity);
    }

    @Override
    public boolean compare(T first, T second) {
        return first.compareTo(second) < 0;
    }
}
