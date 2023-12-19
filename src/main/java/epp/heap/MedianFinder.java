package epp.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MedianFinder {

    PriorityQueue<Integer> maxHeapOfMins = new PriorityQueue<>(Comparator.reverseOrder());
    PriorityQueue<Integer> minHeapOfMaxs = new PriorityQueue<>(Comparator.naturalOrder());


    public void push(int value){

        if(maxHeapOfMins.size()==0){
            maxHeapOfMins.add(value);
            return;
        }
        if(maxHeapOfMins.peek()>value){
            maxHeapOfMins.add(value);
            if(maxHeapOfMins.size()>minHeapOfMaxs.size()+1){
                minHeapOfMaxs.offer(maxHeapOfMins.poll());
            }


        }else {
            minHeapOfMaxs.add(value);
            if(minHeapOfMaxs.size()>maxHeapOfMins.size()){
                maxHeapOfMins.offer(minHeapOfMaxs.poll());
            }

        }

    }

    public int getMedian(){
        if(maxHeapOfMins.size()==minHeapOfMaxs.size()){
            return (maxHeapOfMins.peek()+minHeapOfMaxs.peek())/2;
        }
        return maxHeapOfMins.peek();
    }

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.push(3);
        medianFinder.push(2);
        medianFinder.push(1);
        medianFinder.push(2);
        medianFinder.push(1);
        medianFinder.push(3);
        medianFinder.push(4);
        medianFinder.push(4);
        medianFinder.push(7);
        System.out.println(medianFinder.getMedian());
    }
}
