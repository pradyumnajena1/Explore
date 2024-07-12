package epp.list.revision;

import java.util.Arrays;

public class SalaryCap {
  public static void main(String[] args) {
    int[] salaries = {20, 30, 40, 90, 100};
    System.out.println(salaryCap(salaries, 210));
  }

  private static double salaryCap(int[] salaries, int maxBudget) {
    Arrays.sort(salaries);
    double unAdjustedSum = 0;
    for (int i = 0; i < salaries.length; i++) {
      double adjustedSum = salaries[i] * (salaries.length - i);
      if (unAdjustedSum + adjustedSum >= maxBudget) {
        return (maxBudget - unAdjustedSum) / (salaries.length - i);
      }
      unAdjustedSum += salaries[i];
    }
    return -1.0;
  }
}
