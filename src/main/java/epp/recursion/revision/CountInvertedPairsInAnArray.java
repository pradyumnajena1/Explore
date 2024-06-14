package epp.recursion.revision;

import epp.array.ArrayUtils;
import java.util.Arrays;

public class CountInvertedPairsInAnArray {
  public static void main(String[] args) {
    int[] values = ArrayUtils.randomArray(10, 1, 10);
    ArrayUtils.printArray(values);
    System.out.println(countInvertedPairsBrute(values, 0, values.length - 1));
    int count = mergeSort(values);
    System.out.println(count);
  }

  private static int mergeSort(int[] values) {
    if (values == null || values.length < 2) {
      return 0;
    }
    return mergeSort(values, 0, values.length - 1);
  }

  private static int mergeSort(int[] values, int start, int end) {
    if (start == end) {
      return 0;
    }
    int invertedPairs = 0;
    int mid = (start + end) / 2;
    invertedPairs += mergeSort(values, start, mid);
    invertedPairs += mergeSort(values, mid + 1, end);

    invertedPairs += mergeAndCount(values, start, mid, end);
    return invertedPairs;
  }

  private static int mergeAndCount(int[] values, int start, int mid, int end) {
    int leftIndex = start;
    int rightIndex = mid + 1;
    int accros = 0;
    int[] copy = new int[end - start + 1];
    int writeIndex = 0;
    while (leftIndex <= mid && rightIndex <= end) {
      if (values[leftIndex] <= values[rightIndex]) {
        copy[writeIndex++] = values[leftIndex++];

      } else {
        copy[writeIndex++] = values[rightIndex++];
        accros += mid - leftIndex + 1;
      }
    }
    while (leftIndex <= mid) {
      copy[writeIndex++] = values[leftIndex++];
    }
    while (rightIndex <= end) {
      copy[writeIndex++] = values[rightIndex++];
    }
    System.arraycopy(copy, 0, values, start, end - start + 1);
    return accros;
  }

  private static int countInvertedPairsBrute(int[] values, int start, int end) {
    int count = 0;
    for (int i = start; i <= end; i++) {
      for (int j = i + 1; j <= end; j++) {
        if (i < j && values[i] > values[j]) {
          count++;
        }
      }
    }
    return count;
  }
}
