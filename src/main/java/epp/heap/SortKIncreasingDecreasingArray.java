package epp.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class SortKIncreasingDecreasingArray {
    private static class Segment{
        int start;
        int end;
        boolean increasing;
        int current;

        public Segment(int start, boolean increasing) {
            this.start = start;

            this.increasing = increasing;


        }
        public void setEnd(int end){
            this.end = end;
            current = increasing?start:end;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Segment{");
            sb.append("start=").append(start);
            sb.append(", end=").append(end);
            sb.append(", increasing=").append(increasing);
            sb.append(", current=").append(current);
            sb.append('}');
            return sb.toString();
        }

        Integer getNextValue(int[] array){
            if(current<start || current>end){
                return null;
            }
            int value = array[current];
            if(increasing){
                current++;
            }else{
                current--;
            }
            return value;
        }
    }
    private static class HeapNode<T extends Comparable<T>> implements Comparable<HeapNode<T>>{
        public int segmentId;
        public T value;

        public HeapNode(int segmentId, T value) {
            this.segmentId = segmentId;
            this.value = value;
        }

        @Override
        public int compareTo(HeapNode<T> o) {
            return this.value.compareTo(o.value);
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("HeapNode{");
            sb.append("segmentId=").append(segmentId);
            sb.append(", value=").append(value);
            sb.append('}');
            return sb.toString();
        }
    }
    public static void main(String[] args) {
        int[] array = new int[]{57,131,493,294,221,339,418,452,442,190};
        int[] result = new int[array.length];
          sort(array,result);

    }

    private static void sort(int[] array, int[] result) {
        List<Segment> segments = getSegments(array);
        System.out.println(segments);
        PriorityQueue<HeapNode<Integer>> heap = new PriorityQueue<>();
        for(int i=0;i<segments.size();i++){
            Integer nextValue = segments.get(i).getNextValue(array);
            HeapNode<Integer> node = new HeapNode<>(i,nextValue);
            heap.add(node);
        }
        System.out.println(heap);
        int writePos = 0;
        while (!heap.isEmpty()){
            HeapNode<Integer> min = heap.poll();
            result[writePos++] = min.value;
            Integer nextValue = segments.get(min.segmentId).getNextValue(array);
            if(nextValue!=null){

                heap.offer(new HeapNode<>(min.segmentId,nextValue));
            }

        }
        System.out.println(segments);
        System.out.println(Arrays.toString(result));



    }

    private static List<Segment> getSegments(int[] array) {
        List<Segment> segments = new ArrayList<>();
        Segment currentSegment = new Segment(0,true);


        for(int i = 1; i< array.length-1; i++){
            if(array[i-1]< array[i] && array[i]> array[i+1] || array[i-1]> array[i] && array[i]< array[i+1]){
                currentSegment.setEnd( i);
                segments.add(currentSegment);
                Segment newSegment = new Segment(i+1,!currentSegment.increasing);

                currentSegment = newSegment;
            }
        }
        currentSegment.setEnd( array.length-1);
        segments.add(currentSegment);
        return segments;
    }
}
