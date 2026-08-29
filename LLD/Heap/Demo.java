package LLD.Heap;

import LLD.Heap.impl.MaxHeap;
import LLD.Heap.impl.MinHeap;

public class Demo {
    public static void main(String[] args) {
        int[] values = {15, 10, 20, 8, 25, 5, 30, 3};

        System.out.println("=== Min Heap Demo ===");
        testMinHeap(values);

        System.out.println("\n=== Max Heap Demo ===");
        testMaxHeap(values);
    }

    private static void testMinHeap(int[] values) {
        Heap<Integer> minHeap = new MinHeap<>(values.length);

        System.out.println("Adding: " + format(values));
        for (int value : values) {
            minHeap.add(value);
        }

        System.out.println("Peek (smallest): " + minHeap.peek());
        System.out.println("Pop order (ascending): " + drain(minHeap));
    }

    private static void testMaxHeap(int[] values) {
        Heap<Integer> maxHeap = new MaxHeap<>(values.length);

        System.out.println("Adding: " + format(values));
        for (int value : values) {
            maxHeap.add(value);
        }

        System.out.println("Peek (largest): " + maxHeap.peek());
        System.out.println("Pop order (descending): " + drain(maxHeap));
    }

    private static String drain(Heap<Integer> heap) {
        StringBuilder result = new StringBuilder();
        while (heap.peek() != null) {
            if (!result.isEmpty()) {
                result.append(", ");
            }
            result.append(heap.peek());
            heap.pop();
        }
        return result.toString();
    }

    private static String format(int[] values) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            if (i > 0) {
                result.append(", ");
            }
            result.append(values[i]);
        }
        return result.toString();
    }
}
