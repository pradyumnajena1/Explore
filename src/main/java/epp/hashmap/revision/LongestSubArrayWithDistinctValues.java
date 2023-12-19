package epp.hashmap.revision;

import epp.array.ArrayUtils;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class LongestSubArrayWithDistinctValues {
    public static void main(String[] args) {
        int[] values = ArrayUtils.randomArray(20, 1, 8);
        ArrayUtils.printArray(values);
        Range range =  longestSubArrayWithDistinctValues(values);
        System.out.println(range);
    }

    private static Range longestSubArrayWithDistinctValues(int[] values) {
        Set<Integer> unique = new HashSet<>();
        Queue<Integer> indices = new ArrayDeque<>();
        Range maxRange = null;

        for(int i=0;i< values.length;i++){
            int value = values[i];
            if (unique.contains(value)) {
                while (indices.size() > 0 && values[indices.peek()] != value) {
                    unique.remove(values[indices.peek()]);
                    indices.poll();
                }
                if (indices.size() > 0) {
                    indices.poll();
                }
            }
            unique.add(value);
            indices.offer(i);
            if(maxRange==null|| maxRange.dist()< i-indices.peek()+1){
                maxRange = new Range(indices.peek(),i);
            }
        }
        return maxRange;
    }
}
