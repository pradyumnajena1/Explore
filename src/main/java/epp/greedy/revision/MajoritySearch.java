package epp.greedy.revision;

import epp.ListUtils;

import java.util.*;

public class MajoritySearch {
  public static void main(String[] args) {
    List<Integer> values = ListUtils.randomList(10, 1, 20);
    List<Integer> majority = new ArrayList<>();
    for (int i = 0; i < 11; i++) {
      majority.add(23);
    }
    values.addAll(majority);
    int majorityElement = findMajorityElement(values);
    System.out.println(majorityElement);
    System.out.println(confirmMajority(values, majorityElement, 2));

      values = new ArrayList<>(List.of(1, 1, 2, 2, 3, 3, 4, 4, 4));
    majorityElement = findMajorityElement(values,4);
    System.out.println(majorityElement);
    System.out.println(confirmMajority(values, majorityElement, 4));
  }

  /**
   * majority guaranteed
   *
   * @param values
   * @return
   */
  public static int findMajorityElement(List<Integer> values) {

    int majority = values.get(0);
    int count = 1;
    for (int i = 1; i < values.size(); i++) {
      if (count == 0) {
        majority = values.get(i);
        count = 1;
      } else if (majority == values.get(i)) {
        count++;
      } else {
        count--;
      }
    }
    return majority;
  }

  public static int findMajorityElement(List<Integer> values, int k) {

    Map<Integer, Integer> frequency = new HashMap<Integer, Integer>();

    for (int i = 0; i < values.size(); i++) {
      frequency.put(values.get(i), frequency.getOrDefault(values.get(i), 0) + 1);
      if (frequency.size() >= k) {
        Set<Integer> keys = new HashSet<>( frequency.keySet());
        for (int key : keys ) {
          int newFrequency = frequency.get(key) - 1;
          if (newFrequency == 0) {
            frequency.remove(key);
          } else {
            frequency.put(key, newFrequency);
          }

        }
      }
    }
    return frequency.keySet().iterator().next();
  }

  public static boolean confirmMajority(List<Integer> values, int majorityCandidate, int k) {
    long count = values.stream().filter(x -> x.equals(majorityCandidate)).count();
    return count > values.size() / k;
  }
}
