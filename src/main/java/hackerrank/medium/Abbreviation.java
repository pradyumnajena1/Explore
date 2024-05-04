package hackerrank.medium;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class Abbreviation {
    static class Result {

        /*
         * Complete the 'abbreviation' function below.
         *
         * The function is expected to return a STRING.
         * The function accepts following parameters:
         *  1. STRING a
         *  2. STRING b
         */

        public static String abbreviation(String a, String b) {
            // Write your code here
            return isAbbreviation(a, b) ? "YES" : "NO";
        }

        private static boolean isAbbreviation(String a, String b) {
            Map<List<Integer>, Boolean> cache = new HashMap<>();
            return isAbbreviation(a, b, 0, 0, cache);
        }

        private static boolean isAbbreviation(String a, String b, int i, int j, Map<List<Integer>, Boolean> cache) {
            if (a.length() == i && b.length() == j) {
                return true;
            } else if (a.length() == i && j < b.length()) {
                return false;
            } else if (i < a.length() && b.length() == j) {
                return a.substring(i).equals(a.substring(i).toLowerCase());
            }
            List<Integer> key = List.of(i, j);
            if (cache.containsKey(key)) {
                return cache.get(key);
            }
            boolean result = false;

            if (a.charAt(i) != b.charAt(j) && Character.isUpperCase(a.charAt(i))) {
                result = false;
            } else if (a.charAt(i) == b.charAt(j)) {
                result = isAbbreviation(a, b, i + 1, j + 1, cache);
            } else if (Character.toUpperCase(a.charAt(i)) == b.charAt(j)) {
                result = isAbbreviation(a, b, i + 1, j + 1, cache) || isAbbreviation(a, b, i + 1, j, cache);
            } else {
                result = isAbbreviation(a, b, i + 1, j, cache);
            }
            cache.put(key, result);
            return result;
        }

    }

    public static class Solution {
        public static void main(String[] args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

            int q = Integer.parseInt(bufferedReader.readLine().trim());

            IntStream.range(0, q).forEach(qItr -> {
                try {
                    String a = bufferedReader.readLine();

                    String b = bufferedReader.readLine();

                    String result = Result.abbreviation(a, b);

                    bufferedWriter.write(result);
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
