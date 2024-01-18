package epp.heap.revision;

import epp.array.ArrayUtils;

import java.util.Comparator;
import java.util.PriorityQueue;

public class KClosestToMedian {
    public static void main(String[] args) {
        int[] values = ArrayUtils.randomArray(11,1,30);
        ArrayUtils.printArray(values);
        int[] closestValues =  kClosestToMedian(values,5);
        ArrayUtils.printArray(closestValues);
    }

    private static int[] kClosestToMedian(int[] values, int numItems) {
        MedianCalculator calculator = new MedianCalculator();
        for(int value:values){
            calculator.add(value);
        }
        int median = calculator.getMedian().intValue();
        System.out.println(median);
        Comparator<Integer> comparator = Comparator.comparingInt((Integer x) -> Math.abs(x - median));
        PriorityQueue<Integer> priorityQueue =
                new PriorityQueue<>(comparator.reversed());
        for(int i=0;i<numItems;i++){
            priorityQueue.offer(values[i]);
        }
        for(int i=numItems;i<values.length;i++){
            if(comparator.compare(priorityQueue.peek(),values[i])>0){
                priorityQueue.poll();
                priorityQueue.offer(values[i]);
            }
        }
        int[] result = new int[numItems];
        for(int i=0;i<numItems;i++){
            result[i] = priorityQueue.poll();
        }
        return result;
    }
}
