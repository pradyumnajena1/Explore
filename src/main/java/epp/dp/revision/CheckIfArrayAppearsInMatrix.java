package epp.dp.revision;

import epp.array.ArrayUtils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CheckIfArrayAppearsInMatrix {
  public static void main(String[] args) {
    int[][] matrix = {{5, 1, 5, 4, 1}, {4, 4, 4, 1, 3}, {2, 1, 5, 5, 5}};
    int[] array = {1, 2, 4, 5};
    ArrayUtils.print2DArray(matrix);
    ArrayUtils.printArray(array);
    System.out.println(checkIfArrayAppears(matrix, array));
    System.out.println(checkIfArrayAppearsNoRepeat(matrix, array));
    enumerateArrayAppearsNoRepeat(matrix, array);
  }

  public static boolean checkIfArrayAppears(int[][] matrix, int[] array) {
    Set<List<Integer>> previousAttempts = new HashSet<>();
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[0].length; j++) {
        if (checkIfArrayAppears(matrix, array, i, j, 0, previousAttempts)) {
          return true;
        }
      }
    }
    return false;
  }

  private static boolean checkIfArrayAppears(
      int[][] matrix, int[] array, int i, int j, int offset, Set<List<Integer>> previousAttempts) {
    if (offset == array.length) {
      return true;
    }
    if (i >= matrix.length
        || i < 0
        || j < 0
        || j >= matrix[0].length
        || previousAttempts.contains(List.of(i, j, offset))) {
      return false;
    }

    if (matrix[i][j] == array[offset]) {
      boolean remaining =
          checkIfArrayAppears(matrix, array, i - 1, j, offset + 1, previousAttempts)
              || checkIfArrayAppears(matrix, array, i + 1, j, offset + 1, previousAttempts)
              || checkIfArrayAppears(matrix, array, i, j - 1, offset + 1, previousAttempts)
              || checkIfArrayAppears(matrix, array, i, j + 1, offset + 1, previousAttempts);
      if (remaining) {
        return true;
      }
    }

    previousAttempts.add(List.of(i, j, offset));
    return false;
  }

  public static boolean checkIfArrayAppearsNoRepeat(int[][] matrix, int[] array) {
    Set<List<Integer>> previousAttempts = new HashSet<>();
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[0].length; j++) {
        if (checkIfArrayAppearsNoRepeat(
            matrix, array, i, j, 0, previousAttempts, new ArrayList<>())) {
          return true;
        }
      }
    }
    return false;
  }

  private static boolean checkIfArrayAppearsNoRepeat(
      int[][] matrix,
      int[] array,
      int i,
      int j,
      int offset,
      Set<List<Integer>> previousAttempts,
      List<List<Integer>> path) {
    if (offset == array.length) {
      return true;
    }
    if (i >= matrix.length
        || i < 0
        || j < 0
        || j >= matrix[0].length
        || previousAttempts.contains(List.of(i, j, offset))
        || path.contains(List.of(i, j))) {
      return false;
    }

    if (matrix[i][j] == array[offset]) {
      path.add(List.of(i, j));
      boolean remaining =
          checkIfArrayAppearsNoRepeat(matrix, array, i - 1, j, offset + 1, previousAttempts, path)
              || checkIfArrayAppearsNoRepeat(
                  matrix, array, i + 1, j, offset + 1, previousAttempts, path)
              || checkIfArrayAppearsNoRepeat(
                  matrix, array, i, j - 1, offset + 1, previousAttempts, path)
              || checkIfArrayAppearsNoRepeat(
                  matrix, array, i, j + 1, offset + 1, previousAttempts, path);
      path.remove(path.size() - 1);
      if (remaining) {
        return true;
      }
    }

    previousAttempts.add(List.of(i, j, offset));
    return false;
  }

  public static void enumerateArrayAppearsNoRepeat(int[][] matrix, int[] array) {
    Set<List<Integer>> previousAttempts = new HashSet<>();
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[0].length; j++) {
        enumerateArrayAppearsNoRepeat(matrix, array, i, j, 0, previousAttempts, new ArrayList<>());
      }
    }
  }

  private static boolean enumerateArrayAppearsNoRepeat(
      int[][] matrix,
      int[] array,
      int i,
      int j,
      int offset,
      Set<List<Integer>> previousAttempts,
      List<List<Integer>> path) {
    if (offset == array.length) {
      System.out.println(path);
      return true;
    }
    if (i >= matrix.length
        || i < 0
        || j < 0
        || j >= matrix[0].length
        || previousAttempts.contains(List.of(i, j, offset))
        || path.contains(List.of(i, j))) {
      return false;
    }

    if (matrix[i][j] == array[offset]) {
      path.add(List.of(i, j));

      boolean result =
          enumerateArrayAppearsNoRepeat(matrix, array, i - 1, j, offset + 1, previousAttempts, path)
              || enumerateArrayAppearsNoRepeat(
                  matrix, array, i + 1, j, offset + 1, previousAttempts, path)
              || enumerateArrayAppearsNoRepeat(
                  matrix, array, i, j - 1, offset + 1, previousAttempts, path)
              || enumerateArrayAppearsNoRepeat(
                  matrix, array, i, j + 1, offset + 1, previousAttempts, path);

      path.remove(path.size() - 1);
      if (result) {
        return true;
      }
    }
    previousAttempts.add(List.of(i, j, offset));
    return false;
  }
}
