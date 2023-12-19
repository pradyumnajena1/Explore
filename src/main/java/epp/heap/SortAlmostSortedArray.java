package epp.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class SortAlmostSortedArray {
    public static void main(String[] args) {
        int[] array = new int[]{4,2,6,1,5,3,9,8,7};
        sortAlmostSortedArray(array,5);
        System.out.println(Arrays.toString(array));
    }

    private static void sortAlmostSortedArray(int[] array, int maxDisplacement) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for(int i=0;i<=maxDisplacement;i++){
            priorityQueue.add(array[i]);
        }

        int incoming  = maxDisplacement+1;
        int writePos = 0;
        while (!priorityQueue.isEmpty()){
            array[writePos++] = priorityQueue.poll();
            if(incoming<array.length){

                priorityQueue.offer(array[incoming++]);
            }
        }
    }
}
