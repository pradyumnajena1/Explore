package leetcode.hard;

import epp.array.ArrayUtils;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class MaxSlidingWindow {
    public static void main(String[] args) {
        int[] nums = ArrayUtils.randomArray(10, 1, 20);
        ArrayUtils.printArray(nums);
        int[] maxValues = maxSlidingWindow(nums, 5);
        ArrayUtils.printArray(maxValues);
    }
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if(k>nums.length){
            throw new IllegalArgumentException("Invalid arguments k="+k);
        }
        int[] result =  new int[ nums.length -k +1];
        Deque<Integer> queue = new ArrayDeque<>();
        for(int incoming=0;incoming<=nums.length-1;incoming++){
            insertToQueue(queue, nums[incoming]);
            if(incoming==k-1){
                result[incoming-k+1] = queue.peekFirst();
            }else if(incoming>=k){
                int outgoing = incoming-k;
                if(queue.peekFirst()==nums[outgoing]){
                    queue.pollFirst();
                }
                result[incoming-k+1] = queue.peekFirst();
            }
        }
        return result;
    }

    private static void insertToQueue(Deque<Integer> queue, int num) {
        while (queue.size()>0 && queue.peekLast()< num){
            queue.pollLast();
        }
        queue.offerLast(num);
    }
}
