package hackerrank.easy;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class NonDivisibleSubset {
    static class Result {

        /*
         * Complete the 'nonDivisibleSubset' function below.
         *
         * The function is expected to return an INTEGER.
         * The function accepts following parameters:
         *  1. INTEGER k
         *  2. INTEGER_ARRAY s
         */

        public static int nonDivisibleSubset(int k, List<Integer> s) {
            // Write your code here
            Map<Integer, Integer> moduloMap = new HashMap<>();
            for (int i : s) {
                int count = moduloMap.getOrDefault(i % k, 0);
                moduloMap.put(i % k, count + 1);
            }
            //System.out.println(moduloMap);
            int count = 0;
            for (int i = 0; i <= k / 2; i++) {
                int c = moduloMap.getOrDefault(i, 0);
                if (i == 0) {
                    if (c > 0) {
                        count++;
                    }

                } else if (2 * i == k) {
                    if (c > 0) {
                        count++;
                    }

                } else {
                    int c2 = moduloMap.getOrDefault(k - i, 0);
                    if (c > c2) {
                        count += c;
                    } else {
                        count += c2;
                    }
                }


            }
            return count;
        }

    }

    public static class Solution {
        public static void main(String[] args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

            String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            int n = Integer.parseInt(firstMultipleInput[0]);

            int k = Integer.parseInt(firstMultipleInput[1]);

            List<Integer> s = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Integer::parseInt)
                    .collect(toList());

            int result = Result.nonDivisibleSubset(k, s);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();

            bufferedReader.close();
            bufferedWriter.close();
        }
    }

}
