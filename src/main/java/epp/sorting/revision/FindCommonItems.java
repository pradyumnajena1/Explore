package epp.sorting.revision;

import epp.array.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FindCommonItems {
    public static void main(String[] args) {
        int[] arr1 = ArrayUtils.randomSortedArray(20, 1, 40);
        int[] arr2 = ArrayUtils.randomSortedArray(20, 1, 40);
        int[] result = intersection(arr1, arr2);
        ArrayUtils.printArray(arr1);
        ArrayUtils.printArray(arr2);
        ArrayUtils.printArray(result);

        arr1 = ArrayUtils.randomSortedArray(30, 1, 40);
        arr2 = ArrayUtils.randomSortedArray(5, 1, 40);
        result = intersection(arr1, arr2);
        ArrayUtils.printArray(arr1);
        ArrayUtils.printArray(arr2);
        ArrayUtils.printArray(result);
    }

    public static int[] intersection(int[] arr1, int[] arr2) {
        int[] bigger, smaller;
        bigger = arr1.length >= arr2.length?arr1:arr2;
        smaller = arr1.length < arr2.length?arr1:arr2;
        if (bigger.length / smaller.length >= 2) {
            return intersectionBiggerAndSmaller(bigger, smaller);
        } else {
            return intersectionEquals(bigger, smaller);
        }
    }

    private static int[] intersectionEquals(int[] arr1, int[] arr2) {
        List<Integer> result = new ArrayList<>();
        int index1 = 0;
        int index2 = 0;
        while (index1 < arr1.length && index2 < arr2.length) {
            if (arr1[index1] < arr2[index2]) {
                index1++;
            } else if (arr1[index1] > arr2[index2]) {
                index2++;
            } else {
                int value = arr1[index1];
                result.add(value);
                while (index1 < arr1.length && arr1[index1] == value) {
                    index1++;
                }

            }
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    private static int[] intersectionBiggerAndSmaller(int[] bigger, int[] smaller) {
        List<Integer> result = new ArrayList<>();
        Set<Integer> set = Arrays.stream(bigger).boxed().collect(Collectors.toSet());
        for (int i = 0; i < smaller.length; i++) {
            int value = smaller[i];
            if (set.contains(value)) {
                result.add(value);
            }
            while (i < smaller.length && smaller[i] == value) {
                i++;
            }
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}







