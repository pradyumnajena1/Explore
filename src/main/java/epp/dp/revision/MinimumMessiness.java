package epp.dp.revision;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinimumMessiness {
  public static void main(String[] args) {
    List<String> text =
        List.of(
            "I",
            "have",
            "inserted",
            "a",
            "large",
            "number",
            "of",
            "new",
            "examples",
            "from",
            "the",
            "papers",
            "for",
            "the",
            "Mathematical",
            "Tripos",
            "during",
            "the",
            "last",
            "twenty",
            "years,",
            "which",
            "should",
            "be",
            "useful",
            "to",
            "Cambridge",
            "students.");
    int minMessiness = computeMinimumMessiness(text, 36);
    System.out.println(minMessiness);
  }

  private static int computeMinimumMessiness(List<String> text, int lineLength) {
    Map<Integer, Integer> cache = new HashMap<>();
    int minimumMessiness = computeMinimumMessiness(text, lineLength, 0, cache);
    System.out.println(cache);
    return minimumMessiness;
  }

  private static int computeMinimumMessiness(
      List<String> text, int maxLength, int i, Map<Integer, Integer> cache) {
    if (i == text.size()) {
      return 0;
    }
    if (!cache.containsKey(i)) {
      int remainingSpaces = maxLength - text.get(i).length();
      int minMessiness = remainingSpaces * remainingSpaces;
      for (int j = i+1; j < text.size(); j++) {
        remainingSpaces = remainingSpaces -  (1 + text.get(j).length());
        if (remainingSpaces >= 0) {
          int currentMessiness =  remainingSpaces * remainingSpaces;;
          int remaining = computeMinimumMessiness(text, maxLength, j + 1, cache);
          int total = currentMessiness + remaining;
          if(total<minMessiness){
            minMessiness = total;
            System.out.println(i + " " + j + " " + currentMessiness);
          }

        } else {
          break; // we have exceeded the line length, no need to continue further.
        }
      }
      cache.put(i, minMessiness);
    }

    return cache.get(i);
  }
}
