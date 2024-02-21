package hackerrank.easy;

import java.io.*;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class MaximumSubarraySumModulo {
    static class Result {

        /*
         * Complete the 'maximumSum' function below.
         *
         * The function is expected to return a LONG_INTEGER.
         * The function accepts following parameters:
         *  1. LONG_INTEGER_ARRAY a
         *  2. LONG_INTEGER m
         */

        public static long maximumSum(List<Long> a, long m) {
            // Write your code here
            long sum = 0;
            long maxMod = 0;
            TreeSet<Long> mods = new TreeSet<>();
            for (int i = 0; i < a.size(); i++) {
                long currentMaxMod = a.get(i) % m;
                if (currentMaxMod > maxMod) {
                    maxMod = currentMaxMod;
                }
                sum += a.get(i);
                currentMaxMod = sum % m;
                System.out.println(currentMaxMod);
                if (currentMaxMod > maxMod) {
                    maxMod = currentMaxMod;
                }
                SortedSet<Long> tailSet = mods.tailSet(  currentMaxMod + 1, true);
               // System.out.println(tailSet);
                if (!tailSet.isEmpty()) {
                    currentMaxMod = currentMaxMod + m- tailSet.first();
                    if (currentMaxMod > maxMod) {
                        maxMod = currentMaxMod;
                    }
                }
                mods.add(sum % m);
            }


            return maxMod;
        }

    }

    public static class Solution {
        public static void main(String[] args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

            int q = Integer.parseInt(bufferedReader.readLine().trim());

            IntStream.range(0, q).forEach(qItr -> {
                try {
                    String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                    int n = Integer.parseInt(firstMultipleInput[0]);

                    long m = Long.parseLong(firstMultipleInput[1]);

                    List<Long> a = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                            .map(Long::parseLong)
                            .collect(toList());

                    long result = Result.maximumSum(a, m);

                    bufferedWriter.write(String.valueOf(result));
                    bufferedWriter.newLine();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });

            bufferedReader.close();
            bufferedWriter.close();
        }
    }

}
