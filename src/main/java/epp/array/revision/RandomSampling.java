package epp.array.revision;

import epp.array.ArrayUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class RandomSampling {
  public static void main(String[] args) {
    int[] values = ArrayUtils.randomUniqueArray(10, 1, 20);
    ArrayUtils.printArray(values);
    getRandomSample(values, 5);
    ArrayUtils.printArray(values);
  }

  public static void getRandomSample(int[] values, int numItems) {
    Random random = new Random();
    for (int i = 0; i < numItems; i++) {
      int next = i + random.nextInt(values.length - i);
      ArrayUtils.swap(values, i, next);
    }
  }

  public static List<Integer> getRandomOnlineSample(Iterator<Integer> stream, int k) {
    List<Integer> result = new ArrayList<>();
    for (int i = 0; i < k && stream.hasNext(); i++) {
      result.add(stream.next());
    }

    Random random = new Random();
    int numSeenSoFar = k;
    while (stream.hasNext()) {
      numSeenSoFar++;
      int nextValue = stream.next();
      int idToReplace = random.nextInt(numSeenSoFar);
      if (idToReplace < k) {
        result.set(idToReplace, nextValue);
      }
    }

    return result;
  }
}
