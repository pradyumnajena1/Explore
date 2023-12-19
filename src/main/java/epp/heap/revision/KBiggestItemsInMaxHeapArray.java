package epp.heap.revision;

import epp.array.ArrayUtils;
import epp.heap.HeapUtils;

import java.util.*;

public class KBiggestItemsInMaxHeapArray {
    public static void main(String[] args) {
        Integer[] values = Arrays.stream(ArrayUtils.randomArray(10, 1, 30))
                .boxed().toArray(Integer[]::new);
        HeapUtils.createMaxHeap(values);
        ArrayUtils.printArray(values);
        List<Integer> topValues =  getKBiggestItemsInMaxHeapArray(values,5);
        System.out.println(topValues);
    }

    private static <T extends Comparable<T>> List<T>
    getKBiggestItemsInMaxHeapArray(T[] maxHeap, int numItems) {
        PriorityQueue<Record<T>> priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());
        priorityQueue.offer(new Record(maxHeap[0],0));
        List<T> result = new ArrayList<>();
        while (result.size()<numItems){
            Record<T> polled = priorityQueue.poll();
            result.add(polled.value);
            int left = HeapUtils.getLeft(polled.index);
            if(left <maxHeap.length){
                priorityQueue.offer(new Record<>(maxHeap[left],left));
            }
            int right = HeapUtils.getRight(polled.index);
            if(right <maxHeap.length){
                priorityQueue.offer(new Record<>(maxHeap[right],right));
            }
        }

        return result;
    }
    private static class Record<T extends Comparable<T>> implements Comparable<Record<T>>{
        T value;
        int index;

        public Record(T value, int index) {
            this.value = value;
            this.index = index;
        }

        @Override
        public int compareTo(Record<T> o) {
            return  this.value.compareTo(o.value);
        }
    }
}
