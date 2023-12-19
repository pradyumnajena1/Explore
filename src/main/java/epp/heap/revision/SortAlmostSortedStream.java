package epp.heap.revision;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class SortAlmostSortedStream {
    public static void main(String[] args) {

        Stream<Integer> integerStream = Stream.of(3, 2, 1, 5, 6, 4, 7, 8);
        List<Integer> sorted =  sortAlmostSortedStream(integerStream,2);
        System.out.println(sorted);
    }

    private static List<Integer> sortAlmostSortedStream(Stream<Integer> stream, int maxDisplacement) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        List<Integer> result = new ArrayList<>();
        stream.forEach(x->{
            priorityQueue.offer(x);
             if(priorityQueue.size()==maxDisplacement+1){
                 Integer polled = priorityQueue.poll();
                 result.add(polled);
             }
        });
        while (!priorityQueue.isEmpty()){
            result.add(priorityQueue.poll());
        }

        return result;
    }
}
