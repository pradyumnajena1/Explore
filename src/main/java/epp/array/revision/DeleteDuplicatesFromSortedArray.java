package epp.array.revision;

import epp.array.ArrayUtils;
import java.util.Arrays;

public class DeleteDuplicatesFromSortedArray {
  public static void main(String[] args) {

    ArrayUtils.printArray(deleteDuplicates(new int[] {2, 3, 5, 5, 7, 11, 11, 11, 13}));
    ArrayUtils.printArray(deleteDuplicates(new int[] {2, 3, 5, 6, 7, 11, 12, 13}));
    ArrayUtils.printArray(deleteDuplicates(new int[] {2, 2, 2, 2, 2, 2}));
    System.out.println(removeKey(new int[] {3, 2, 2, 4, 2, 7}, 2));
    System.out.println(removeKey(new int[] {2, 2, 2, 2}, 2));
    System.out.println(removeExtraValues(new int[] {2, 3, 5, 5, 7, 11, 11, 11, 13, 13, 13, 13}, 3));
  }

  /**
   * delete duplicates from sorted array, fill empty with zeros
   *
   * @param values
   * @return
   */
  public static int[] deleteDuplicates(int[] values) {
    if (values == null || values.length < 2) {
      return values;
    }
    int writeIndex = 0;
    for (int i = 1; i < values.length; i++) {
      if (values[i] > values[writeIndex]) {
        writeIndex++;
        values[writeIndex] = values[i];
      }
    }
    Arrays.fill(values, writeIndex + 1, values.length, 0);
    return values;
  }

  /**
   * remove values equals key and return number of remaining element;
   *
   * @param values
   * @param key
   * @return
   */
  public static int removeKey(int[] values, int key) {
    int writeIndex = 0;
    for (int i = 0; i < values.length; i++) {
      if (values[i] != key) {
        values[writeIndex++] = values[i];
      }
    }
    return writeIndex;
  }

  /**
   * Write a program which takes as input a sorted array A of integers and a positive integer m,and
   * updates A so that if x appears m times in A it appears exactly min(2,m) times in A. The update
   * to A should be performed in one pass, and no additional storage may be allocated
   */
  public static int removeExtraValues(int[] values, int m) {
    if (values == null) {
      return 0;
    }
    if (values.length < 2) {
      return 1;
    }
    int writeIndex = 0;
    int lastValue = values[0];
    int numOccur = 1;
    for (int i = 1; i < values.length; i++) {

      if (values[i] == lastValue) {
        numOccur++;
      } else {
        if (numOccur == m) {
          numOccur = Math.min(m, 2);
        }
        Arrays.fill(values, writeIndex, writeIndex + numOccur, lastValue);
        writeIndex += numOccur;

        lastValue = values[i];
        numOccur = 1;
      }
    }

    if (numOccur == m) {
      numOccur = Math.min(m, 2);
    }
    Arrays.fill(values, writeIndex, writeIndex + numOccur, lastValue);
    writeIndex += numOccur;

    return writeIndex;
  }
}
