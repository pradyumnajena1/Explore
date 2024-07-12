package epp.sorting.revision;

import epp.array.ArrayUtils;

import java.util.Arrays;

public class MergeSortInPlace {
    public static void main(String[] args) {
        int[] array1 = ArrayUtils.randomSortedArray(10, 1, 30);
        int[] array2 = ArrayUtils.randomSortedArray(10, 1, 30);
        array1 = Arrays.copyOf(array1, array1.length + array2.length);
        ArrayUtils.printArray(array1);
        ArrayUtils.printArray(array2);
        mergeSortInPlace(array1,array2);
        ArrayUtils.printArray(array1);

    }

    private static void mergeSortInPlace(int[] bigger,  int[] smaller) {
         int bigStart = bigger.length - smaller.length -1;
         int smallStart = smaller.length - 1;
         int writePosition = bigger.length - 1;
         while (bigStart >=0 && smallStart>=0) {
             bigger[writePosition--] = bigger[bigStart]>=smaller[smallStart]?bigger[bigStart--]:smaller[smallStart--];
         }
         while (smallStart>=0){
             bigger[writePosition--] = smaller[smallStart--];
         }
    }
}
