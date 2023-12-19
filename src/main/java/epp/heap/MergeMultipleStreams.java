package epp.heap;

import java.util.*;

public class MergeMultipleStreams {
    public static void main(String[] args) {
        List<Integer> result = multiStreamMerge(Arrays.asList(Arrays.asList(1, 2), Arrays.asList(3, 4, 5), Arrays.asList(2, 3, 4)));
        System.out.println(result);
    }

    private static class HeapNode implements Comparable<HeapNode>{
        int streamIndex;
        Integer value;

        public HeapNode(int streamIndex, Integer value) {
            this.streamIndex = streamIndex;
            this.value = value;
        }

        @Override
        public int compareTo(HeapNode o) {
            return this.value.compareTo(o.value);
        }
    }

    private static class CompositeStream{


        Map<Integer,List<Integer>> streamsMap ;

        public CompositeStream(List<List<Integer>> streams) {
            streamsMap = new HashMap<>();
            for(int i=0;i<streams.size();i++){
                streamsMap.put(i, new LinkedList<>( streams.get(i)));
            }
        }

        public HeapNode getNextValue(int streamIndex){
            if(streamsMap.get(streamIndex).size()>0){
                return new HeapNode(streamIndex,streamsMap.get(streamIndex).remove(0));
            }
            return null;
        }


    }

    public static List<Integer> multiStreamMerge(List<List<Integer>> streams){
        CompositeStream compositeStream = new CompositeStream(streams);
        PriorityQueue<HeapNode> priorityQueue = new PriorityQueue<>();
        for(int i=0;i<streams.size();i++){
            HeapNode nextValue = compositeStream.getNextValue(i);
            if(nextValue!=null){
                priorityQueue.add(nextValue);
            }
        }
        List<Integer> result = new ArrayList<>();
        while (!priorityQueue.isEmpty()){
            HeapNode heapNode = priorityQueue.poll();
            result.add(heapNode.value);
            HeapNode nextValue = compositeStream.getNextValue(heapNode.streamIndex);
            if(nextValue!=null){
                priorityQueue.add(  nextValue);
            }
        }
        return result;
    }
}
