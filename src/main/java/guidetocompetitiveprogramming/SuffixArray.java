package guidetocompetitiveprogramming;

import epp.Pair;
import epp.array.ArrayUtils;

import java.util.Arrays;
import java.util.Comparator;

public class SuffixArray {
    public static void main(String[] args) {
        String s = "ABAACBAB";
        int[] suffixArray = buildSuffixArray(s);
        ArrayUtils.printArray(suffixArray);
    }

    public static int[] buildSuffixArray(String s) {

        int[] finalArray = new int[s.length()];
        Pair<Integer, Integer>[] initialArray = new Pair[s.length()];
        Arrays.setAll(initialArray,i->new Pair<>(s.charAt(i) - 'A' + 1, s.charAt(i) - 'A' + 1));
        populateFinalArray(initialArray, finalArray);
        for (int length = 2; length <= s.length(); length *= 2) {

            for (int i = 0; i < s.length(); i++) {
                initialArray[i] = new Pair<>(finalArray[i], i + length / 2 < s.length() ? finalArray[i + length / 2] : 0);
            }
            populateFinalArray(initialArray, finalArray);

        }
        return buildSuffixArray(finalArray);
    }

    private static int[] buildSuffixArray(int[] finalArray) {
        Integer[] temp = new Integer[finalArray.length];
        Arrays.setAll(temp, i -> i);
        Arrays.sort(temp, Comparator.comparingInt(i -> finalArray[i]));
        return Arrays.stream(temp).mapToInt(i -> i).toArray();
    }

    private static void populateFinalArray(Pair<Integer, Integer>[] initialArray, int[] finalArray) {
        Pair<Integer, Integer>[] pairs = Arrays.copyOf(initialArray, initialArray.length);
        Comparator<Pair<Integer, Integer>> comparator = Comparator.comparingInt((Pair<Integer, Integer> x) -> x.getFirst()).thenComparingInt(Pair::getSecond);
        Arrays.sort(pairs, comparator);
        //remove duplicates
        int writeIndex = 1;
        for (int i = 1; i < pairs.length; i++) {
            if (comparator.compare(pairs[i], pairs[writeIndex - 1]) > 0) {
                pairs[writeIndex++] = pairs[i];
            }
        }
        for (int i = 0; i < finalArray.length; i++) {
            int index = Arrays.binarySearch(pairs, 0, writeIndex, initialArray[i], comparator);
            finalArray[i] = index + 1;
        }
        ArrayUtils.printArray(finalArray);
    }
}
