package epp.dp;

import epp.array.ArrayUtils;

import java.util.ArrayDeque;

public class MaximumSubarrayInCircularArray {
    public static void main(String[] args) {
         int[] array =new int[]{904,40,523,12,-335,-385,-124,481,-31};//  ArrayUtils.randomArray(10,-20,20);
         ArrayUtils.printArray(array);
         int[] maxsum = getMaxSumSubarray(array);
         ArrayUtils.printArray(maxsum);
    }
    private static class Pair{
        int value;
        int index;

        public Pair(int value, int index) {
            this.value = value;
            this.index = index;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Pair{");
            sb.append("value=").append(value);
            sb.append(", index=").append(index);
            sb.append('}');
            return sb.toString();
        }
    }

    private static int[] getMaxSumSubarray(int[] array) {
        int[] copy = new int[array.length+array.length-1];
        System.arraycopy(array,0,copy,0,array.length);
        System.arraycopy(array,0,copy,array.length,array.length-1);
        ArrayUtils.printArray(copy);
       copy =  ArrayUtils.cumulativeSum(copy);
       ArrayUtils.printArray(copy);
        ArrayDeque<Pair> queue = new ArrayDeque<>();
        int[] result = new int[2];
        int sum=0;
        int maxSum =0;
        for(int i=0;i<copy.length;i++){
            System.out.println(i);
             while (!queue.isEmpty() && queue.peekLast().value>copy[i]){
                 queue.pollLast();
             }
             queue.offerLast(new Pair(copy[i],i));
             if(i>=array.length-1){
                 sum = copy[i] - queue.peek().value;
                 if(sum>maxSum){
                     maxSum = sum;
                     result = new int[]{ (queue.peekFirst().index+1) % array.length, (i+1)%array.length};
                 }
                 if(i>=array.length && queue.peekFirst().index==i-array.length){

                     queue.pollFirst();
                 }
             }
            System.out.println(queue);

        }

        return result;
    }
}
