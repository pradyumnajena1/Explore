package epp.array.revision;

import epp.ListUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IncrementBigInteger {
  public static void main(String[] args) {
    List<Integer> number = new ArrayList<>(Arrays.asList(1, 1, 1));
    number = increment(number, 2);
    System.out.println(number);

    System.out.println(addBigIntegers(Arrays.asList(9, 9, 8), Arrays.asList(9, 9, 8), 10));
  }

  public static List<Integer> increment(List<Integer> number) {
    return increment(number, 10);
  }

  public static List<Integer> increment(List<Integer> number, int base) {
    number.set(number.size() - 1, number.get(number.size() - 1) + 1);

    for (int i = number.size() - 1; i > 0 && number.get(i) == base; i--) {
      number.set(i, 0);
      number.set(i - 1, number.get(i - 1) + 1);
    }
    if (number.get(0) == base) {
      number.set(0, 0);
      number.add(0, 1);
    }

    return number;
  }

  public static List<Integer> addBigIntegers(List<Integer> a, List<Integer> b) {
    return addBigIntegers(a, b, 10);
  }

  public static List<Integer> addBigIntegers(List<Integer> a, List<Integer> b, int base) {
    int carry = 0;
    int aIndex = a.size() - 1;
    int bIndex = b.size() - 1;
    List<Integer> result = new ArrayList<>();
    while (bIndex >= 0 || aIndex >= 0 || carry > 0) {
      int sum = carry;
      if (aIndex >= 0) {
        sum += a.get(aIndex);
      }
      if (bIndex >= 0) {
        sum += b.get(bIndex);
      }
      int digit = sum % base;
      carry = sum / base;
      result.add(digit);
      aIndex--;
      bIndex--;
    }
    ListUtils.reverse(result);

    return result;
  }
}
