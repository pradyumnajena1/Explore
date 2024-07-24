package epp.honours;

import java.util.ArrayList;
import java.util.List;

public class MaxRectangularSubMatrix {

  public static void main(String[] args) {
    List<List<Boolean>> matrix = new ArrayList<>();
    matrix.add(List.of(true, false, true, true));
    matrix.add(List.of(false, false, true, false));
    matrix.add(List.of(true, true, false, true));
    matrix.add(List.of(false, true, true, true));
    matrix.add(List.of(false, true, true, true));
    System.out.println(maxRectangleArea(matrix));
    System.out.println(maxSquareArea(matrix));
  }

  /**
   * time complexity O(m^2n)
   *
   * @param matrix
   * @return
   */
  public static int maxRectangleArea(List<List<Boolean>> matrix) {
    HW[][] table = getHws(matrix);

    int maxRectArea = 0;
    for (int i = 0; i < matrix.size(); i++) {
      for (int j = 0; j < matrix.get(i).size(); j++) {
        if (matrix.get(i).get(j)) {
          int minWidth = Integer.MAX_VALUE;
          // one O(m) complexity here
          for (int a = 0; a < table[i][j].h; a++) {
            minWidth = Math.min(minWidth, table[i - a][j].w);
            maxRectArea = Math.max(maxRectArea, minWidth * (a + 1));
          }
        }
      }
    }

    return maxRectArea;
  }

  public static int maxSquareArea(List<List<Boolean>> matrix) {
    HW[][] table = getHws(matrix);
    int[][] sides = new int[matrix.size()][matrix.get(0).size()];
    int maxSquareArea = 0;
    for (int i = 0; i < matrix.size(); i++) {
      for (int j = 0; j < matrix.get(i).size(); j++) {
        if (matrix.get(i).get(j)) {
          int side = Math.min(table[i][j].h, table[i][j].w);
          if (i - 1 >= 0 && j - 1 >= 0) {
            side = Math.min(sides[i - 1][j - 1] + 1, side);
          }
          sides[i][j] = side;
          maxSquareArea = Math.max(maxSquareArea, side * side);
        }
      }
    }

    return maxSquareArea;
  }

  private static HW[][] getHws(List<List<Boolean>> matrix) {
    HW[][] table = new HW[matrix.size()][matrix.get(0).size()];
    for (int i = 0; i < matrix.size(); i++) {
      for (int j = 0; j < matrix.get(i).size(); j++) {
        if (matrix.get(i).get(j)) {
          table[i][j] =
              new HW(i == 0 ? 1 : table[i - 1][j].h + 1, j == 0 ? 1 : table[i][j - 1].w + 1);
        } else {
          table[i][j] = new HW(0, 0);
        }
      }
    }
    return table;
  }

  private static class HW {
    int h;
    int w;

    public HW(int h, int w) {
      this.h = h;
      this.w = w;
    }
  }
}
