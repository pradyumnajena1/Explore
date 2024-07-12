package epp.hashmap.revision;

import epp.array.ArrayUtils;

import java.util.*;

public class SmallestSubArrayCoveringAllValuesSequentially {
    public static void main(String[] args) {
        int[] values = ArrayUtils.randomArray(20, 1, 20);
        int[] randomIndices = ArrayUtils.randomSortedUniqueArray(5, 0, 10);
        Set<Integer> unique = new HashSet<>();
        List<Integer> uniqueInOrder = new ArrayList<>();
        for (int i = 0; i < randomIndices.length; i++) {
            if(!unique.contains(values[randomIndices[i]])){
                uniqueInOrder.add(values[randomIndices[i]]);
            }
            unique.add( values[randomIndices[i]]);
        }
        int[] requiredArray = uniqueInOrder.stream().mapToInt(x->x.intValue()).toArray();
        ArrayUtils.printArray(values);
        ArrayUtils.printArray(requiredArray);

        Range range = smallestSubArrayCoveringAllValuesSequentially(values, requiredArray);
        System.out.println(range);
    }

    private static Range smallestSubArrayCoveringAllValuesSequentially(int[] values, int[] requiredArray) {
        Map<Integer, Integer> keysToIndexMap = new HashMap<>();
        for (int i = 0; i < requiredArray.length; i++) {
            keysToIndexMap.put(requiredArray[i], i);
        }
        int[] locations = new int[requiredArray.length];
        int[] distance = new int[requiredArray.length];
        Arrays.fill(locations, -1);
        Arrays.fill(distance, Integer.MAX_VALUE);
        int shortestDistance = Integer.MAX_VALUE;
        Range minRange = new Range(-1, -1);

        for (int i = 0; i < values.length; i++) {
            int value = values[i];
            if (keysToIndexMap.containsKey(value)) {
                System.out.println("processing value "+value);
                int index = keysToIndexMap.get(value);
                locations[index] = i;
                if (index == 0) {
                    distance[index] = 1;
                } else {
                    int distanceToPrevKeyWord = i - locations[index - 1];
                    int previousDistance = distance[index - 1];
                    if (previousDistance != Integer.MAX_VALUE) {
                        distance[index] = previousDistance + distanceToPrevKeyWord;
                    }
                }
                if (index == requiredArray.length - 1) {
                    if (shortestDistance > distance[index]) {

                        shortestDistance = distance[index];
                        minRange = new Range(i - distance[index]+1, i);
                    }
                }
                ArrayUtils.printArray("requiredArray", requiredArray);
                ArrayUtils.printArray("locations", locations);
                ArrayUtils.printArray("distance", distance);
            }
        }

        return minRange;
    }
}
