package epp.honours;

import epp.array.ArrayUtils;
import java.util.Arrays;

public class CalculateBonus {
  public static void main(String[] args) {
    int[] linesOfCode = ArrayUtils.randomArray(10, 100, 500);
    ArrayUtils.printArray(linesOfCode);
    int[] bonuses = getBonusAmounts(linesOfCode);
    ArrayUtils.printArray(bonuses);
  }

  private static int[] getBonusAmounts(int[] linesOfCode) {
    int[] bonusAmounts = new int[linesOfCode.length];
    Arrays.fill(bonusAmounts, 1);
    for (int i = 1; i < bonusAmounts.length; i++) {
      if (linesOfCode[i] > linesOfCode[i - 1]) {
        bonusAmounts[i] = Math.max(bonusAmounts[i], bonusAmounts[i - 1] + 1);
      }
    }

    for (int i = bonusAmounts.length - 2; i >= 0; i--) {
      if (linesOfCode[i] > linesOfCode[i + 1]) {
        bonusAmounts[i] = Math.max(bonusAmounts[i], bonusAmounts[i + 1] + 1);
      }
    }
    return bonusAmounts;
  }
}
