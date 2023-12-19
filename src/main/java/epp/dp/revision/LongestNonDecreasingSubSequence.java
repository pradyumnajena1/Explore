package epp.dp.revision;

import epp.Pair;
import epp.array.ArrayUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LongestNonDecreasingSubSequence {
    public static void main(String[] args) {
        int[] values = ArrayUtils.randomArray(10, 1, 20);
        ArrayUtils.printArray(values);
        Pair<Integer, List<Integer>> maxSequence = longestIncreasingSubSequence(values);
        System.out.println(maxSequence);

        maxSequence = longestIncreasingSubSequence2(values);
        System.out.println(maxSequence);


    }

    /**
     * nlogn complexity
     *
     * @param values
     * @return
     */
    private static Pair<Integer, List<Integer>> longestIncreasingSubSequence2(int[] values) {
        List<Integer> result = new ArrayList<>();
        result.add(values[0]);
        for (int i = 1; i < values.length; i++) {
            if (values[i] >= result.get(result.size() - 1)) {
                result.add(values[i]);
            } else {
                int index = Collections.binarySearch(result, values[i]);
                if (index < 0) {
                    index = -(index + 1);
                }
                result.set(index, values[i]);
            }
        }
        return new Pair<>(result.size(), result);
    }

    private static Pair<Integer, List<Integer>> longestIncreasingSubSequence(int[] values) {
        Pair<Integer, Integer>[] lisLength = ArrayUtils.createArray(Pair.class, values.length);
        lisLength[0] = new Pair<>(1, 0);
        int maxSequence = 0;
        for (int i = 1; i < values.length; i++) {
            lisLength[i] = new Pair<>(1, i);
            for (int j = 0; j < i; j++) {
                if (values[j] <= values[i] && lisLength[j].getFirst() + 1 > lisLength[i].getFirst()) {
                    lisLength[i] = new Pair<>(lisLength[j].getFirst() + 1, j);
                }
            }
            if (lisLength[maxSequence].getFirst() < lisLength[i].getFirst()) {
                maxSequence = i;
            }

        }

        List<Integer> sequence = new ArrayList<>();
        int current = maxSequence;
        while (true) {

            sequence.add(values[current]);
            int next = lisLength[current].getSecond();
            if (next == current) {
                break;
            }
            current = next;

        }

        return new Pair<>(lisLength[maxSequence].getFirst(), sequence);
    }
}
