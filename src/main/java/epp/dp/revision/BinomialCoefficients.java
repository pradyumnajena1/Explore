package epp.dp.revision;

import epp.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BinomialCoefficients {
  public static void main(String[] args) {
    int coeff = getBinomialCoefficients(6, 2);
    System.out.println(coeff);
  }

  private static int getBinomialCoefficients(int n, int k) {
    Map<List<Integer>, Integer> cache = new HashMap<>();
    return getBinomialCoefficientsRecurse(n, k, cache);
  }

  private static int getBinomialCoefficientsRecurse(
      int n, int k, Map<List<Integer>, Integer> cache) {
    if (k==0 || n==k) {
      return 1;
    }
    List<Integer> key = List.of(n, k); // Using List.of for Java 9 and later;
    if (!cache.containsKey(key)) {
      int result =
          getBinomialCoefficientsRecurse(n - 1, k - 1, cache)
              + getBinomialCoefficientsRecurse(n - 1, k, cache);
      cache.put(key, result);
    }

    return cache.get(key);
  }
}
