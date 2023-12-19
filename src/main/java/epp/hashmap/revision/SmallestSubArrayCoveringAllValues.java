package epp.hashmap.revision;

import epp.array.ArrayUtils;
import epp.DoubleLinkedList;
import epp.DoubleLinkedListNode;

import java.util.*;
import java.util.stream.Collectors;

public class SmallestSubArrayCoveringAllValues {
    public static void main(String[] args) {
        int[] values = ArrayUtils.randomArray(20, 1, 20);
        int[] randomIndices = ArrayUtils.randomArray(5, 0, 10);
        Set<Integer> randomValues = new HashSet<>();
        for (int i = 0; i < randomIndices.length; i++) {
            randomValues.add(values[randomIndices[i]]);
        }
        int[] requiredArray = randomValues.stream().mapToInt(x -> x.intValue()).toArray();
        ArrayUtils.printArray(values);
        ArrayUtils.printArray(requiredArray);
        Range range = getSmallestSubArrayCover(values, requiredArray);
        System.out.println(range);

        range = getSmallestSubArrayCover2(values, requiredArray);
        System.out.println(range);

        range = getSmallestSubArrayCover3(values, requiredArray);
        System.out.println(range);

    }

    private static Range getSmallestSubArrayCover(int[] values, int[] requiredArray) {
        Map<Integer, Integer> value_to_location = new HashMap<>();
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        Set<Integer> requiredSet = Arrays.stream(requiredArray).boxed().collect(Collectors.toSet());
        Range minRange = null;
        for (int i = 0; i < values.length; i++) {
            if (requiredSet.contains(values[i])) {
                Integer oldIndex = value_to_location.put(values[i], i);
                if (minHeap.size() > 0 && minHeap.peek().equals(oldIndex)) {
                    minHeap.poll();
                }
                minHeap.offer(i);
                if (value_to_location.size() == requiredSet.size()) {
                    Range range = new Range(minHeap.peek(), i);
                    if (minRange == null || range.dist() < minRange.dist()) {
                        minRange = range;
                    }
                }

            }
        }

        return minRange;
    }

    private static Range getSmallestSubArrayCover2(int[] values, int[] requiredArray) {
        Set<Integer> requiredSet = Arrays.stream(requiredArray).boxed().collect(Collectors.toSet());
        int left = 0;
        int right = 0;
        Range minRange = null;
        Map<Integer, Integer> q_set_freq = new HashMap<>();
        while (right < values.length) {
            while (right < values.length && q_set_freq.size() < requiredSet.size()) {
                if (requiredSet.contains(values[right])) {
                    q_set_freq.put(values[right], q_set_freq.getOrDefault(values[right], 0) + 1);
                }
                right++;
            }

            if (q_set_freq.size() == requiredSet.size()) {
                if (minRange == null || minRange.dist() > right - left) {
                    minRange = new Range(left, right - 1);
                }
            }

            while (left < right && q_set_freq.size() == requiredSet.size()) {

                if (requiredSet.contains(values[left])) {

                    q_set_freq.put(values[left], q_set_freq.get(values[left]) - 1);
                    if (q_set_freq.get(values[left]) == 0) {
                        q_set_freq.remove(values[left]);
                        if (minRange == null || minRange.dist() > right - left) {
                            minRange = new Range(left, right - 1);
                        }
                    }

                }
                left++;
            }

        }
        return minRange;
    }

    public static Range getSmallestSubArrayCover3(int[] values, int[] requiredArray) {
        HashMap<Integer, DoubleLinkedListNode<Integer>> valueIndexMap = new HashMap<>();
        DoubleLinkedList<Integer> queue = new DoubleLinkedList<>();
        Set<Integer> requiredSet = Arrays.stream(requiredArray).boxed().collect(Collectors.toSet());

        Range minRange = null;
        for (int i = 0; i < values.length; i++) {
            int value = values[i];
            if (requiredSet.contains(value)) {

                if (valueIndexMap.containsKey(value)) {
                    DoubleLinkedListNode<Integer> node = valueIndexMap.get(value);
                    queue.removeNode(node);
                }
                DoubleLinkedListNode<Integer> newNode = new DoubleLinkedListNode<>(i);
                queue.addAtEnd(newNode);
                valueIndexMap.put(value, newNode);


                if (valueIndexMap.size() == requiredArray.length) {
                    Integer first = queue.getFirst();
                    int newDist = i - first + 1;
                    if (minRange == null || minRange.dist() > newDist) {
                        minRange = new Range(first, i);
                    }
                }
            }
        }

        return minRange;

    }

}
