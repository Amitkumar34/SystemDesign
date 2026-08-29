package LLD.Heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Heap<T extends Comparable<T>> {
    protected final List<T> heapArray;
    protected final int capacity;

    public Heap(int capacity) {
        this.capacity = capacity;
        this.heapArray = new ArrayList<>(capacity);
    }

    public T peek() {
        return heapArray.isEmpty() ? null : heapArray.getFirst();
    }

    public void pop() {
        heapArray.set(0, heapArray.getLast());
        heapArray.removeLast();
        heapifyTopToBottom();
    }

    public void add(T element) {
        if (heapArray.size() >= capacity) throw new RuntimeException("Capacity limit reached");
        heapArray.add(element);
        heapifyBottomToTop();
    }

    protected void heapifyTopToBottom() {
        int curIdx = 0;
        while (2 * curIdx + 1 < heapArray.size()) {
            int leftIdx = 2 * curIdx + 1;
            int rightIdx = 2 * curIdx + 2;
            int childMin = leftIdx;
            if (rightIdx < heapArray.size() && compare(heapArray.get(rightIdx), heapArray.get(leftIdx))) {
                childMin = rightIdx;
            }
            if (compare(heapArray.get(childMin), heapArray.get(curIdx))) {
                Collections.swap(heapArray, curIdx, childMin);
                curIdx = childMin;
            } else break;
        }
    }

    protected void heapifyBottomToTop() {
        int childIdx = heapArray.size() - 1;
        while (childIdx > 0) {
            int parentIdx = (childIdx - 1) / 2;
            if (compare(heapArray.get(childIdx), heapArray.get(parentIdx))) {
                Collections.swap(heapArray, childIdx, parentIdx);
                childIdx = parentIdx;
            } else break;
        }
    }

    /**
     * @return true if first should be at top of heap in comparison to second
     */
    public abstract boolean compare(T first, T second);
}
