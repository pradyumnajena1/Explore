package leetcode.hard;

import epp.array.ArrayUtils;

import java.util.*;

public class SmallestRangeCoveringKSets {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] range = solution.smallestRange(List.of(List.of(4, 10, 15, 24, 26), List.of(0, 9, 12, 20), List.of(5, 18, 22, 30)));
        ArrayUtils.printArray(range);

        range = solution.smallestRange(List.of(List.of(1, 2, 3), List.of(1, 2, 3), List.of(1, 2, 3)));
        ArrayUtils.printArray(range);

        range = solution.smallestRange(List.of(List.of(10,10), List.of(11,11) ));
        ArrayUtils.printArray(range);
    }

    private static class Solution {
        public int[] smallestRange(List<List<Integer>> nums) {
            PriorityQueue<PriorityQueueNode> priorityQueue = new PriorityQueue<>();
            int min = Integer.MAX_VALUE;
            Deque<Integer> max = new ArrayDeque<>();
            Map<Integer, Integer> pointers = new HashMap<>();
            for (int i = 0; i < nums.size(); i++) {
                pointers.put(i, 0);
                Integer nextValue = nums.get(i).get(0);
                priorityQueue.offer(new PriorityQueueNode(nextValue, i));
                min = Math.min(min, nextValue);
                while (max.size() > 0 && max.peekLast() < nextValue) {
                    max.pollLast();
                }
                max.offerLast(nextValue);
            }


            Range result = new Range(min, max.peekFirst());
            System.out.println(result);
            Integer incomingValue = null;
            while (!priorityQueue.isEmpty()) {


                PriorityQueueNode outgoingNode = priorityQueue.poll();
                int outgoingValue = outgoingNode.value;
                int outgoingIndex = outgoingNode.listIndex;


                if (max.size() > 0 && max.peekFirst() == outgoingValue) {
                    max.pollFirst();
                }

                if (pointers.get(outgoingIndex) < nums.get(outgoingIndex).size() - 1) {
                    int nextValueIndex = pointers.get(outgoingIndex) + 1;
                    pointers.put(outgoingIndex, nextValueIndex);
                    incomingValue = nums.get(outgoingIndex).get(nextValueIndex);
                    priorityQueue.offer(new PriorityQueueNode(incomingValue, outgoingIndex));

                    while (max.size() > 0 && max.peekLast() < incomingValue) {
                        max.pollLast();
                    }
                    max.offerLast(incomingValue);
                    if(nums.size()<=pointers.size()){
                        Range range = new Range(priorityQueue.peek().value, max.peekFirst());
                        System.out.println(range);
                        if (result == null) {
                            result = range;
                        } else if (result.compareTo(range) > 0) {
                            result = range;
                        }
                    }


                } else {
                    pointers.remove(outgoingIndex);

                }


            }
            return new int[]{result.a, result.b};
        }


        private static class Range implements Comparable<Range> {
            int a;
            int b;

            public Range(int a, int b) {
                if (a > b) throw new IllegalArgumentException("invalid params a=" + a + " b=" + b);
                this.a = a;
                this.b = b;
            }

            @Override
            public int compareTo(Range o) {
                int compare = Integer.compare(this.b - this.a, o.b - o.a);
                if (compare != 0) {
                    return compare;
                }
                return Integer.compare(this.a, o.a);
            }

            @Override
            public String toString() {
                final StringBuffer sb = new StringBuffer("Range{");
                sb.append("a=").append(a);
                sb.append(", b=").append(b);
                sb.append('}');
                return sb.toString();
            }
        }


        private static class PriorityQueueNode implements Comparable<PriorityQueueNode> {
            int value;
            int listIndex;

            public PriorityQueueNode(int value, int listIndex) {
                this.value = value;
                this.listIndex = listIndex;
            }

            @Override
            public int compareTo(PriorityQueueNode o) {
                return Integer.compare(this.value, o.value);
            }
        }
    }
}
