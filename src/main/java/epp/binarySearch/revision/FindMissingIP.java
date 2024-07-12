package epp.binarySearch.revision;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.BitSet;

public class FindMissingIP {
  public static final int NUM_BUCKETS = 1 << 16;

  public static void main(String[] args) throws IOException {

    int x =
        getMissingIP(
            new File(
                    "C:\\Users\\Pradyumna\\IdeaProjects\\Explore\\src\\main\\java\\epp"
                        + "\\binarySearch"
                        + "\\revision\\ips.txt")
                .toPath());
    System.out.println(x);
  }

  private static int getMissingIP(Path path) throws IOException {
    int[] counter = getRangeCounters(path);
    BitSet bitSet = null;
    for (int i = 0; i < counter.length; i++) {
      if (counter[i] < NUM_BUCKETS) {
        bitSet = new BitSet(1 << 16);
        BufferedReader reader = Files.newBufferedReader(path, Charset.defaultCharset());
        String line;
        while ((line = reader.readLine()) != null) {
          int value = Integer.parseInt(line);
          int bucketNum = value >> 16;
          if (bucketNum == i) {
            bitSet.set(value & (1 << 16) - 1);
          }
        }
        reader.close();
        for (int j = 0; j < (1 << 16); j++) {
          if (!bitSet.get(j)) {
            return j;
          }
        }
      }
    }
    return -1;
  }

  private static int[] getRangeCounters(Path path) throws IOException {

    BufferedReader reader = Files.newBufferedReader(path, Charset.defaultCharset());
    String line;
    int[] counter = new int[NUM_BUCKETS];
    while ((line = reader.readLine()) != null) {
      int value = Integer.parseInt(line);
      counter[value >> 16]++;
    }
    reader.close();
    return counter;
  }
}
