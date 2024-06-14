package epp.array.revision;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MultiplyBigInteger {

  public static void main(String[] args) {
    System.out.println(multiply(new ArrayList<>(List.of(1, 8)), new ArrayList<>(List.of(1, 9))));
  }

  private static List<Integer> multiply(List<Integer> a, List<Integer> b) {
    int sign = a.get(0) < 0 ^ b.get(0) < 0 ? -1 : 1;
    a.set(0, Math.abs(a.get(0)));
    b.set(0, Math.abs(b.get(0)));
    List<Integer> result = new ArrayList<>(Collections.nCopies(a.size() + b.size(), 0));
    for (int i = a.size() - 1; i >= 0; i--) {
      for (int j = b.size() - 1; j >= 0; j--) {
        int sum = result.get(i + j + 1) + a.get(i) * b.get(j);
        result.set(i + j + 1, sum % 10);
        result.set(i + j, result.get(i + j) + sum / 10);
      }
    }
    int firstNonZero = 0;
    while (firstNonZero < result.size() && result.get(firstNonZero) == 0) {
      firstNonZero++;
    }
    result = result.subList(firstNonZero, result.size());
    if (result.size() == 0) {
      return new ArrayList<>(List.of(0));
    }
    result.set(0, result.get(0) * sign);
    return result;
  }
}
