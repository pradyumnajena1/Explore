package leetcode.hard;

import epp.array.ArrayUtils;

import java.util.Comparator;
import java.util.PriorityQueue;

public class SlidingWindowMedian {
    public static void main(String[] args) {
        Solution solution = new Solution();
        ArrayUtils.printArray(solution.medianSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 4));
        ArrayUtils.printArray(solution.medianSlidingWindow(new int[]{1,2,3,4,2,3,1,4,2}, 3));
    }

    private static class Solution {
        public double[] medianSlidingWindow(int[] nums, int k) {
            double[] result = new double[nums.length - k + 1];
            PriorityQueue<Integer> heapOfMinimumNumbers = new PriorityQueue<>(Comparator.reverseOrder());
            PriorityQueue<Integer> heapOfMaximumNumbers = new PriorityQueue<>(Comparator.naturalOrder());

            for (int i = 0; i < nums.length; i++) {

                if (heapOfMinimumNumbers.size() == 0) {
                    heapOfMinimumNumbers.offer(nums[i]);
                }else {
                    if (heapOfMinimumNumbers.peek() > nums[i]) {
                        heapOfMinimumNumbers.offer(nums[i]);

                        if (k%2==1 && heapOfMinimumNumbers.size() > heapOfMaximumNumbers.size()+1  ) {
                            heapOfMaximumNumbers.offer(heapOfMinimumNumbers.poll());
                        }else if(k%2==0 && heapOfMinimumNumbers.size() > heapOfMaximumNumbers.size()){
                            heapOfMaximumNumbers.offer(heapOfMinimumNumbers.poll());
                        }

                    } else {
                        heapOfMaximumNumbers.offer(nums[i]);
                        if ( k%2==1 &&  heapOfMaximumNumbers.size() == heapOfMinimumNumbers.size() ) {
                            heapOfMinimumNumbers.offer(heapOfMaximumNumbers.poll());
                        }else if ( k%2==0 &&  heapOfMaximumNumbers.size() > heapOfMinimumNumbers.size()) {
                            heapOfMinimumNumbers.offer(heapOfMaximumNumbers.poll());
                        }
                    }
                    double median;
                    if (i == k - 1) {
                        median = getMedian(heapOfMinimumNumbers, heapOfMaximumNumbers);
                        result[i - k + 1] = median;
                    } else if (i >= k) {
                        if (nums[i - k] <= heapOfMinimumNumbers.peek()) {
                            boolean remove = heapOfMinimumNumbers.remove(nums[i - k]);

                            if (k%2==1 &&  heapOfMaximumNumbers.size() == heapOfMinimumNumbers.size()) {
                                heapOfMinimumNumbers.offer(heapOfMaximumNumbers.poll());
                            }else if (k%2==0 &&  heapOfMaximumNumbers.size() > heapOfMinimumNumbers.size()) {
                                heapOfMinimumNumbers.offer(heapOfMaximumNumbers.poll());
                            }

                        } else {
                            boolean remove = heapOfMaximumNumbers.remove(nums[i - k]);

                            if ( k%2==1 && heapOfMinimumNumbers.size() > heapOfMaximumNumbers.size()+1) {
                                heapOfMaximumNumbers.offer(heapOfMinimumNumbers.poll());
                            }else if ( k%2==0 && heapOfMinimumNumbers.size() > heapOfMaximumNumbers.size()) {
                                heapOfMaximumNumbers.offer(heapOfMinimumNumbers.poll());
                            }
                        }

                        median = getMedian(heapOfMinimumNumbers, heapOfMaximumNumbers);
                        result[i - k + 1] = median;
                    }
                }

                System.out.println("heapOfMinimumNumbers" + heapOfMinimumNumbers);
                System.out.println("heapOfMaximumNumbers" + heapOfMaximumNumbers);
                System.out.println();
            }
            return result;
        }

        private static double getMedian(PriorityQueue<Integer> heapOfMinimumNumbers,
                                        PriorityQueue<Integer> heapOfMaximumNumbers ) {
            double median;
            if (heapOfMinimumNumbers.size() == heapOfMaximumNumbers.size()) {

                median = ((double) heapOfMaximumNumbers.peek() + heapOfMinimumNumbers.peek()) / 2;
            } else {
                median = (double) heapOfMinimumNumbers.peek();
            }
            return median;
        }
    }
}
