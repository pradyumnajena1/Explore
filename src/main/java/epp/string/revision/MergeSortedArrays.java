package epp.string.revision;

import epp.array.ArrayUtils;

public class MergeSortedArrays {

  public static void main(String[] args) {
    int[] B = new int[] {1, 2, 3, 4, 5};
    int[] A = new int[] {6,6,7, 0, 0, 0, 0, 0};
    mergeSortedArrays(A, B);
    ArrayUtils.printArray(A);
  }

  /**
   * a has enough space
   *
   * @param a
   * @param b
   */
  public static void mergeSortedArrays(int[] a, int[] b) {
    int aIndex = a.length - b.length - 1;
    int bIndex = b.length - 1;
    int writeIndex = a.length - 1;
    while (aIndex >= 0 && bIndex >= 0) {
      if (a[aIndex] >= b[bIndex]) {
        a[writeIndex--] = a[aIndex--];
      } else {
        a[writeIndex--] = b[bIndex--];
      }
    }
    //copy remaining
    while (bIndex >= 0) {
      a[writeIndex--] = b[bIndex--];
    }
  }
}
