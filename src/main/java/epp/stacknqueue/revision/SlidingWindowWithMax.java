package epp.stacknqueue.revision;

import java.util.ArrayList;
import java.util.List;

public class SlidingWindowWithMax<T extends Comparable<T>> {
    private static   class Pair<T extends Comparable<T>>{
        Long timeStamp;
        T value;

        public Pair(Long timeStamp, T value) {
            this.timeStamp = timeStamp;
            this.value = value;
        }
    }
    private List<Pair<T>> values;
    private Long windowSize;

    public SlidingWindowWithMax(List<Pair<T>> values, Long windowSize) {
        this.values = values;
        this.windowSize = windowSize;
    }
    public List<T> getSlidingWindowMax(){
        List<T> result = new ArrayList<>();
        QueueWithMax<T> queueWithMax = new QueueWithMax<>();
        int index = 0;
        while (values.get(index).timeStamp - values.get(0).timeStamp<=windowSize){
            queueWithMax.enqueue(values.get(index).value);
            index++;
        }
        result.add( queueWithMax.max());
        int start=0;
        System.out.println(start+" "+(index-1));
        int end = index;
        for(;end<values.size();end++){
            queueWithMax.enqueue(values.get(end).value);
            if(values.get(end).timeStamp-values.get(start).timeStamp>windowSize){
                queueWithMax.dequeue();
                start++;
            }
            result.add( queueWithMax.max());
            System.out.println(start+" "+end);
        }
        return result;
    }

    public static void main(String[] args) {
        List<Pair<Integer>> values  = new ArrayList<>();
        values.add(new Pair<>(1L,2));
        values.add(new Pair<>(2L,5));
        values.add(new Pair<>(3L,3));
        values.add(new Pair<>(4L,1));
        values.add(new Pair<>(5L,7));
        values.add(new Pair<>(6L,3));
        values.add(new Pair<>(7L,3));
        values.add(new Pair<>(8L,1));
        SlidingWindowWithMax<Integer> slidingWindowWithMax = new SlidingWindowWithMax<>(values,2L);
        System.out.println(slidingWindowWithMax.getSlidingWindowMax());
    }

}
