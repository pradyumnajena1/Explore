package epp.recursion.revision;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PowerSetUtils {
  public static void main(String[] args) {
    int[] values = {1, 2, 3, 2};
    Set<Set<Integer>> result = generatePowerSets(values);
    System.out.println(result);

    result = generatePowerSets2(values);
    System.out.println(result);

    result = generatePowerSets3(values);
    System.out.println(result);

    result = generatePowerSets2(values, 2);
    System.out.println(result);
  }

  public static Set<Set<Integer>> generatePowerSets(int[] values) {
    return generatePowerSetsHelper(values, 0);
  }

  public static Set<Set<Integer>> generatePowerSetsHelper(int[] values, int i) {
    if (i == values.length) {
      HashSet<Set<Integer>> sets = new HashSet<>();
      sets.add(new HashSet<>());
      return sets;
    }
    Set<Set<Integer>> partial = generatePowerSetsHelper(values, i + 1);
    Set<Set<Integer>> result = new HashSet<>();
    // not included
    for (Set<Integer> set : partial) {
      result.add(new HashSet<>(set));
    }
    // included
    for (Set<Integer> set : partial) {
      HashSet<Integer> newSet = new HashSet<>(set);
      newSet.add(values[i]);
      result.add(newSet);
    }
    return result;
  }

  public static Set<Set<Integer>> generatePowerSets2(int[] values) {
    Set<Set<Integer>> result = new HashSet<>();
    generatePowerSetsHelper2(values, 0, new HashSet<>(), result);
    return result;
  }

  private static void generatePowerSetsHelper2(
      int[] values, int index, HashSet<Integer> current, Set<Set<Integer>> result) {
    if (index == values.length) {
      result.add(new HashSet<>(current));
      return;
    }
    // sets containing values[index]
    current.add(values[index]);
    generatePowerSetsHelper2(values, index + 1, current, result);
    current.remove(values[index]);
    // sets not containing values[index]
    generatePowerSetsHelper2(values, index + 1, current, result);
  }

  public static Set<Set<Integer>> generatePowerSets2(int[] values, int length) {
    Set<Set<Integer>> result = new HashSet<>();
    generatePowerSetsHelper2(values, length, 0, new HashSet<>(), result);
    return result;
  }

  private static void generatePowerSetsHelper2(
      int[] values,
      int length,
      int index,
      HashSet<Integer> partialCombination,
      Set<Set<Integer>> result) {
    if (partialCombination.size() == length) {
      result.add(new HashSet<>(partialCombination));
      return;
    }
    if (length - partialCombination.size() > values.length - index) {
      return;
    }
    // sets containing values[index]8
    partialCombination.add(values[index]);
    generatePowerSetsHelper2(values, length, index + 1, partialCombination, result);
    partialCombination.remove(values[index]);
    // sets not containing values[index]
    generatePowerSetsHelper2(values, length, index + 1, partialCombination, result);
  }

  public static List<List<Integer>> combinations (int n, int k) {
    List<List<Integer>> result = new ArrayList() ;
    directedCombinations (n, k, 1, new ArrayList<Integer>() , result);
    return result;
  }

  private static void directedCombinations(int n, int k, int offset, ArrayList<Integer> partialCombination,
                                           List<List<Integer>> result) {
    if(partialCombination.size()==k) {
        result.add(new ArrayList<>(partialCombination));
      return;
    }
    int numRemaining =k- partialCombination.size();
    for(int i=offset;i<=n && numRemaining<= n-i+1;i++) {
      partialCombination.add(i);
      directedCombinations(n, k, i+1, partialCombination, result);
      partialCombination.remove(partialCombination.size() - 1);
    }

  }

  /**
   * use this when values.length <= 31
   *
   * @param values
   * @return
   */
  public static Set<Set<Integer>> generatePowerSets3(int[] values) {
    Set<Set<Integer>> result = new HashSet<>();
    int maxBitSet = 1 << values.length;
    for (int intForSubset = 0; intForSubset < maxBitSet; intForSubset++) {
      int bitArray = intForSubset;
      Set<Integer> subset = new HashSet<>();
      while (bitArray != 0) {
        subset.add(values[Integer.numberOfTrailingZeros(bitArray)]);
        bitArray &= (bitArray - 1);
      }
      result.add(subset);
    }
    return result;
  }
}
