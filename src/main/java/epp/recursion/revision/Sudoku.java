package epp.recursion.revision;

import java.util.ArrayList;
import java.util.List;

public class Sudoku {

  public static final int EMPTY_ENTRY = 0;

  public static void main(String[] args) {


    List<List<Integer>> partialAssignment = new ArrayList<>();
    partialAssignment.add(new ArrayList<>(List.of(5, 3, 0, 0, 7, 0, 0, 0, 0)));
    partialAssignment.add(new ArrayList<>(List.of(6, 0, 0, 1, 9, 5, 0, 0, 0)));
    partialAssignment.add(new ArrayList<>(List.of(0, 9, 8, 0, 0, 0, 0, 6, 0)));
    partialAssignment.add(new ArrayList<>(List.of(8, 0, 0, 0, 6, 0, 0, 0, 3)));
    partialAssignment.add(new ArrayList<>(List.of(4, 0, 0, 8, 0, 3, 0, 0, 1)));
    partialAssignment.add(new ArrayList<>(List.of(7, 0, 0, 0, 2, 0, 0, 0, 6)));
    partialAssignment.add(new ArrayList<>(List.of(0, 6, 0, 0, 0, 0, 2, 8, 0)));
    partialAssignment.add(new ArrayList<>(List.of(0, 0, 0, 4, 1, 9, 0, 0, 5)));
    partialAssignment.add(new ArrayList<>(List.of(0, 0, 0, 0, 8, 0, 0, 7, 9)));
    boolean canSolve = solveSudoku(partialAssignment);
    System.out.println(canSolve);
  }

  private static boolean solveSudoku(List<List<Integer>> partialAssignment) {
    return solveSudoku(0, 0, partialAssignment);
  }

  private static boolean solveSudoku(int row, int column, List<List<Integer>> partialAssignment) {
    if (row == partialAssignment.size()) {
       row = 0;
       ++column;
      if (column == partialAssignment.get(row).size()) {
        return true;
      }
    }
    if (partialAssignment.get(row).get(column) != EMPTY_ENTRY) {
      return solveSudoku(row+1, column , partialAssignment);
    }
    for (int value = 1; value <= partialAssignment.size(); value++) {
      if (validToAddValue(partialAssignment, value, row, column)) {
        partialAssignment.get(row).set(column, value);
        if (solveSudoku(row+1, column , partialAssignment)) {
          return true;
        }
      }
    }
    partialAssignment.get(row).set(column, EMPTY_ENTRY);
    return false;
  }

  private static boolean validToAddValue(
      List<List<Integer>> partialAssignment, int value, int row, int column) {
    // check column constraint
    for (int i = 0; i < partialAssignment.size(); i++) {
      if (partialAssignment.get(i).get(column) == value) {
        return false;
      }
    }

    // check row constraint
    for (int i = 0; i < partialAssignment.size(); i++) {
      if (partialAssignment.get(row).get(i) == value) {
        return false;
      }
    }

    // check box constraint
    int regionSize = (int) Math.sqrt(partialAssignment.size());
    int boxRowStart = row -  row % regionSize;
    int boxColStart = column - column%  regionSize;
    for (int a = 0; a < regionSize; a++) {
      for (int b = 0; b < regionSize; b++) {
        if (partialAssignment.get(boxRowStart + a).get(boxColStart + b) == value) {
          return false;
        }
      }
    }
    return true;
  }
}
