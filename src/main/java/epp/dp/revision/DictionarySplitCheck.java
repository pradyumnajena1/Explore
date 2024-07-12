package epp.dp.revision;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class DictionarySplitCheck {
  public static void main(String[] args) {
    String word = "bedbathandbeyond";
    Set<String> dictionary = Set.of("bed", "bath", "beyond", "and", "hand", "bat");
    List<String> canBeSplit = checkWordSplit(word, dictionary);
    System.out.println(canBeSplit);
  }

  private static List<String> checkWordSplit(String word, Set<String> dictionary) {
    HashMap<Integer, String> cache = new HashMap<Integer, String>();
    checkWordSplit(word, dictionary, 0, cache);
    return buildSolution(word.length(), cache);
  }

  private static List<String> buildSolution(int length, HashMap<Integer, String> cache) {
    List<String> splits = new ArrayList<>();

    int start = 0;
    while (start < length && cache.containsKey(start)) {
      String prefix = cache.get(start);
      splits.add(prefix);
      start += prefix.length();
    }
    return start < length ? null : splits;
  }

  private static String checkWordSplit(
      String word, Set<String> dictionary, int i, HashMap<Integer, String> cache) {
    if (i == word.length()) {
      return "";
    }
    if (!cache.containsKey(i)) {
      cache.put(i, null);
      for (int j = i + 1; j <= word.length(); j++) {
        if (dictionary.contains(word.substring(i, j))) {
          if (checkWordSplit(word, dictionary, j, cache) != null) {
            cache.put(i, word.substring(i, j));
            break;
          }
        }
      }
    }
    return cache.get(i);
  }
}
