package epp.array.revision;

import epp.array.ArrayUtils;
import java.util.Arrays;
import java.util.List;

public class ApplyPermutationArray {
  public static void main(String[] args) {
    int[] values = {'3', '4', '2', '5', '1'};
    Character[] array =
        Arrays.stream(values).mapToObj(x -> Character.valueOf((char) x)).toArray(Character[]::new);
    int[] permutation = {2, 1, 3, 0, 4};
    applyPermutation(array, permutation);
    ArrayUtils.printArray(array);
  }

  public static <T> void applyPermutation(List<T> values, int[] permutation) {
    applyPermutation(values.toArray(), permutation);
  }

  public static <T> void applyPermutation(T[] values, int[] permutation) {

    for (int i = 0; i < values.length; i++) {
      int current = i;
      while (permutation[current] >= 0) {
        int next = permutation[current];
        ArrayUtils.swap(values, i, next);
        permutation[current] = permutation[current] - permutation.length;
        current = next;
      }
    }
    // restore perm
    for (int i = 0; i < permutation.length; i++) {
      permutation[i] = permutation[i] + permutation.length;
    }
  }
}
