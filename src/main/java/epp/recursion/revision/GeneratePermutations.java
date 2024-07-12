package epp.recursion.revision;

import epp.array.ArrayUtils;
import java.util.*;

public class GeneratePermutations {
  public static void main(String[] args) {
    int[] value = {2, 2,3,0};

      printAllPermutations(value);
    System.out.println();
    System.out.println();
      printAllPermutations2(value);
  }

  private static void printAllPermutations2(int[] value) {

    printAllPermutations2(value, 0);
  }

  private static void printAllPermutations2(int[] value, int index) {
    if (index == value.length - 1) {
      ArrayUtils.printArray(value);
      return;
    }
    Set<Integer> seen = new HashSet<Integer>();
    for (int i = index; i < value.length; i++) {
      if (seen.contains(value[i])) {
        continue;
      }
      seen.add(value[i]);
      ArrayUtils.swap(value, index, i);
      printAllPermutations2(value, index + 1);
      ArrayUtils.swap(value, index, i);
    }
  }

  private static void printAllPermutations(int[] value) {
    Arrays.sort(value);
    int[] perm = value;
    while (perm != null) {
      System.out.println(Arrays.toString(perm));
      perm = getNextBiggerPerm(perm);
    }
  }

  private static int[] getNextBiggerPerm(int[] perm) {
    int i = perm.length - 1;
    while (i >= 1 && perm[i] <= perm[i - 1]) {
      i--;
    }
    // already sorted descending we reached the biggest perm
    if (i == 0) {
      return null;
    }
    // find the next biggest digit
    int j = i;
    while (j < perm.length && perm[j] > perm[i - 1]) {
      j++;
    }
    ArrayUtils.swap(perm, i - 1, j - 1);
    ArrayUtils.reverse(perm, i, perm.length-1);
    return perm;
  }
}
