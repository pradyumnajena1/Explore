package epp;

import epp.array.ArrayUtils;
import java.util.*;

public class TestDriver {
  public static void main(String[] args) {
    int constructableAmount =
        smallestConstructableAmount(new ArrayList<>(List.of(1, 1, 2, 3, 5, 8, 20, 50)));
    System.out.println(constructableAmount);
    System.out.println(countSetBits(12));
    System.out.println(closestIntSameBitCount(14));
    int[] arr = {1, 2, 3, 4, 5, 6};
    evenOdd(arr);
    ArrayUtils.printArray(arr);

    arr = new int[] {1, 2, 3, 4, 5, 6};
    evenOdd2(arr);
    ArrayUtils.printArray(arr);
    System.out.println(multiply(new ArrayList<>(List.of(9, 9)), new ArrayList<>(List.of(1, 2))));
    System.out.println(longestSubArrayOfEquals(new int[] {1, 2, 3, 3, 4, 5, 6, 6, 6, 7}));
    ArrayList<Integer> values = new ArrayList<>(List.of(1, 2, 3, 4, 5));
    applyPermutation(values, new ArrayList<>(List.of(2, 1, 4, 3, 0)));
    System.out.println(values);

    System.out.println(computeRandomSubset(10000, 5));
  }

  public static int countSetBits(int value) {
    int count = 0;
    while (value > 0) {

      value &= (value - 1);
      count++;
    }
    return count;
  }

  public static int swapBits(int value, int i, int j) {
    int mask = 1 << i | 1 << j;
    value = value ^ mask;
    return value;
  }

  public static int smallestConstructableAmount(List<Integer> coins) {
    int smallestConstructableAmount = 0;
    coins.sort(Comparator.naturalOrder());
    for (int coin : coins) {
      if (coin > smallestConstructableAmount + 1) {
        break;
      }
      smallestConstructableAmount += coin;
    }
    return smallestConstructableAmount;
  }

  public static int closestIntSameBitCount(int value) {
    for (int i = 0; i < 31; i++) {
      if (((value >> i) & 1) != ((value >> (i + 1)) & 1)) {
        return swapBits(value, i, i + 1);
      }
    }
    throw new IllegalArgumentException("all bits are 0 or 1");
  }

  public static void evenOdd(int[] arr) {
    int nextEven = 0;
    int nextOdd = arr.length - 1;
    while (nextEven < nextOdd) {
      if (arr[nextEven] % 2 == 0) {
        nextEven++;
      } else {
        ArrayUtils.swap(arr, nextEven, nextOdd);
        nextOdd--;
      }
    }
  }

  public static void evenOdd2(int[] arr) {
    int nextEVen = 0;
    int nextOdd = 0;
    while (nextOdd < arr.length) {
      if (arr[nextOdd] % 2 == 0) {
        ArrayUtils.swap(arr, nextOdd, nextEVen);
        nextOdd++;
        nextEVen++;
      } else {
        nextOdd++;
      }
      ArrayUtils.printArray(arr);
    }
  }

  public static boolean canReach(List<Integer> steps) {
    int lastStep = steps.size() - 1;
    int maxReachSoFar = 0;
    for (int i = 0; i <= maxReachSoFar && i < lastStep; i++) {
      maxReachSoFar = Math.max(maxReachSoFar, maxReachSoFar + steps.get(i));
    }
    return maxReachSoFar >= lastStep;
  }

  public static void applyPermutation(List<Integer> values, List<Integer> perm) {
    for (int i = 0; i < values.size(); i++) {
      int next = i;
      while (perm.get(next) >= 0) {
        Collections.swap(values, i, perm.get(next));
        int temp = perm.get(next);
        perm.set(next, next - perm.size());
        next = temp;
      }
    }
    for (int i = 0; i < perm.size(); i++) {
      perm.set(i, perm.get(i) + perm.size());
    }
  }

  /**
   * Write a program that takes an array of integers and finds the length of a longest subarray all
   * of whose entries are equal
   *
   * @param array
   * @return
   */
  public static int longestSubArrayOfEquals(int[] array) {
    if (array.length == 0) return 0;
    int longestSubArray = 1;
    int lastSubArray = 1;
    for (int i = 1; i < array.length; i++) {
      if (array[i] == array[i - 1]) {
        lastSubArray++;
        longestSubArray = Math.max(longestSubArray, lastSubArray);
      } else {
        lastSubArray = 1;
      }
    }
    return longestSubArray;
  }

  public static List<Integer> multiply(List<Integer> a, List<Integer> b) {
    int sign = (a.get(0) < 0) ^ (b.get(0) < 0) ? -1 : 1;
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

    // remove the leading zero
    int index = 0;
    while (index < result.size() && result.get(index) == 0) {
      index++;
    }
    result = result.subList(index, result.size());
    if (result.size() == 0) {
      result.add(0);
    }
    result.set(0, result.get(0) * sign);
    return result;
  }

  public static List<Integer> computeRandomSubset(int n, int k) {

    Map<Integer, Integer> changedElements = new HashMap<Integer, Integer>();
    Random random = new Random();
    for (int i = 0; i < k; i++) {
      int nextRandom = i + random.nextInt(n - i);
      Integer ptr1 = changedElements.get(i);
      Integer ptr2 = changedElements.get(nextRandom);
      if (ptr1 == null && ptr2 == null) {
        changedElements.put(i, nextRandom);
        changedElements.put(nextRandom, i);
      } else if (ptr1 != null && ptr2 == null) {
        changedElements.put(i, nextRandom);
        changedElements.put(nextRandom, ptr1);
      } else if (ptr1 == null && ptr2 != null) {
        changedElements.put(i, ptr2);
        changedElements.put(nextRandom, i);
      } else {
        changedElements.put(i, ptr2);
        changedElements.put(nextRandom, ptr1);
      }
    }
    List<Integer> result = new ArrayList<Integer>();
    for (int i = 0; i < k; i++) {
      result.add(changedElements.get(i));
    }
    return result;
  }
}
