package epp.honours;

import java.util.*;
import java.util.stream.Collectors;

/**
 * You are reading a sequence of strings separated by whitespace. You are allowed to read the
 * sequence twice. Devise an algorithm that uses 0(k) memory to identify the words that occur more
 * than n/k times, where n is the length of the sequence.
 */
public class SearchFrequentItems {
  public static void main(String[] args) {
    List<String> words =
        new ArrayList<String>(
            List.of(
                "banana",
                "orange",
                "apple",
                "mango",
                "strawberry",
                "orange",
                "apple",
                "mango",
                "mango",
                "mango",
                "banana",
                "orange",
                "apple",
                "mango",
                "strawberry",
                "orange",
                "apple",
                "apple",
                "apple",
                "apple",
                "mango",
                "strawberry"));
    List<String> frequentWords = searchFrequentItems(words, 5);
    System.out.println(frequentWords);
  }

  private static List<String> searchFrequentItems(List<String> list, int k) {
    Map<String, Integer> candidates = new HashMap<String, Integer>();
    Iterator<String> iterator = list.iterator();
    int n = 0;
    while (iterator.hasNext()) {
      n++;
      String word = iterator.next();
      candidates.put(word, candidates.getOrDefault(word, 0) + 1);
      if (candidates.size() == k) {
        Set<String> keySet =new HashSet<>( candidates.keySet());
        for (String key : keySet) {
          Integer count = candidates.get(key);
          if (count == 1) {
            candidates.remove(key);
          } else {
            candidates.put(key, count - 1);
          }
        }
      }
    }

    // Resets hash for the following counting.
    for (String it : candidates.keySet()) {
      candidates.put(it, 0);
    }
    iterator = list.iterator();
    while (iterator.hasNext()) {
      String word = iterator.next();
      if (candidates.containsKey(word)) {
        candidates.put(word, candidates.get(word) + 1);
      }
    }
    int size = n;
    List<String> result =
        candidates.keySet().stream()
            .filter(x -> candidates.get(x) >= size / k)
            .collect(Collectors.toList());

    return result;
  }
}
