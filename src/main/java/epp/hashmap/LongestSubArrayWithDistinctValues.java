package epp.hashmap;

import epp.array.ArrayUtils;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class LongestSubArrayWithDistinctValues {
    public static void main(String[] args) {
        int[] values = new int[]{5,7,5,11,13,2,11,19,2,11};
        int[] subarray = getLongestSubarrayWithUniqueValues(values);
        ArrayUtils.printArray(subarray);
    }
    private static class Tuple{
        int value;
        int index;

        public Tuple(int value, int index) {
            this.value = value;
            this.index = index;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Tuple{");
            sb.append("value=").append(value);
            sb.append(", index=").append(index);
            sb.append('}');
            return sb.toString();
        }
    }
    private static int[] getLongestSubarrayWithUniqueValues(int[] values) {
        Map<Integer,Integer> positionMap = new HashMap<>();
        Queue<Tuple> queue= new ArrayDeque<>();
        int maxlength = Integer.MIN_VALUE;
        int maxPosition = -1;
        for(int i=0;i<values.length;i++){
            Integer previousPosition = positionMap.get(values[i]);


            int length = 0;
            if(previousPosition!=null){

                  while (queue.peek().index<=previousPosition){
                      Tuple poll = queue.poll();
                      positionMap.remove(poll.value);
                  }

            }
            positionMap.put(values[i],i);
            queue.offer(new Tuple(values[i],i));
            System.out.println(positionMap);
            System.out.println(queue);
            length = positionMap.size();
            if(length>maxlength){
                maxlength = length;
                maxPosition = i;
            }

        }
        int[] result = new int[maxlength];
        System.arraycopy(values,maxPosition-maxlength+1,result,0,maxlength);
        return result;
    }
}
