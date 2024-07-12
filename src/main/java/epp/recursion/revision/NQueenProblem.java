package epp.recursion.revision;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class NQueenProblem {
  public static void main(String[] args) {
   int  count = countNQueen(4);
    System.out.println(count);
    List<List<Integer>> result = solveNQueen(4);
    System.out.println(result);
  }

  private static List<List<Integer>> solveNQueen(int n) {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> columns = new ArrayList<>();
    solveNQueen(n, 0, result, columns);
    return result;
  }

  private static int countNQueen(int n) {
    AtomicInteger result = new AtomicInteger();
    List<Integer> columns = new ArrayList<>();
    countNQueen(n, 0, result, columns);
    return result.get();
  }

  private static void countNQueen(int n, int row, AtomicInteger result, List<Integer> columns) {
    if (row == n) {
      result.incrementAndGet();
      return;
    }
    for (int i = 0; i < n; i++) {
      columns.add(i);
      if (feasibleColumnPlacement(columns, row)) {
        countNQueen(n, row + 1, result, columns);
      }
      columns.remove(columns.size() - 1);
    }
  }

  private static void solveNQueen(
      int n, int row, List<List<Integer>> result, List<Integer> columns) {
    if (row == n) {
      result.add(new ArrayList<>(columns));
      return;
    }
    for (int i = 0; i < n; i++) {
      columns.add(i);
      if (feasibleColumnPlacement(columns, row)) {
        solveNQueen(n, row + 1, result, columns);
      }
      columns.remove(columns.size() - 1);
    }
  }

  private static boolean feasibleColumnPlacement(List<Integer> columns, int row) {
    for (int i = 0; i < row; i++) {
      int diff = Math.abs(columns.get(i) - columns.get(row));
      if (diff == 0 || diff == row - i) {
        return false;
      }
    }
    return true;
  }
}
