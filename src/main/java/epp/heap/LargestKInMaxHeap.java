package epp.heap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class LargestKInMaxHeap {

    public static void main(String[] args) {
        int[] priorityQueue = new int[]{25,9,17,3,4,15,12,1,2,2,3,14,13,10,9};
        List<Integer> largest = getLargest(priorityQueue,5);
        System.out.println(largest);
    }

    private static class HeapNode implements Comparable<HeapNode>{
        int value;
        int index;

        public HeapNode(int value, int index) {
            this.value = value;
            this.index = index;
        }

        @Override
        public int compareTo(HeapNode o) {
            return Integer.compare(o.value,this.value);
        }
    }

    private static List<Integer> getLargest(int[] priorityQueue, int numElements) {
        List<Integer> result = new ArrayList<>();
        PriorityQueue<HeapNode> candidates = new PriorityQueue<>();
        candidates.add(new HeapNode(priorityQueue[0],0));
        while (result.size()<numElements){

            HeapNode poll = candidates.poll();
            result.add(poll.value);
            if(poll.index*2+1<priorityQueue.length){

                candidates.add(new HeapNode(priorityQueue[poll.index*2+1],poll.index*2+1));
            }
            if(poll.index*2+2<priorityQueue.length){

                candidates.add(new HeapNode(priorityQueue[poll.index*2+2],poll.index*2+2));
            }

        }
        return result;

    }
}
