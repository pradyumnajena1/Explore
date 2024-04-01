package hackerrank.medium;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ConstructTheArray {
    static class Result {
        static final int mod = (int) (Math.pow(10, 9) + 7);

        /*
         * Complete the 'countArray' function below.
         *
         * The function is expected to return a LONG_INTEGER.
         * The function accepts following parameters:
         *  1. INTEGER n
         *  2. INTEGER k
         *  3. INTEGER x
         */

        public static long countArray(int n, int k, int x) {
            // Return the number of ways to fill in the array.
            Map<Key, Long> cache = new HashMap<>();
            return countArray(n, 1, x, k, cache);
        }

        private static long countArray(int n, int first, int last, int k, Map<Key, Long> cache) {

            if (n == 2) {
                if (first == last) {
                    return 0;
                }
                return 1;
            }
            if (n == 3) {
                if (first == last) {
                    return k - 1;
                }
                return k - 2;
            }
            Key key = new Key(n, first==last);
            if (cache.containsKey(key)) {
                return cache.get(key);
            }
            long count = 0;


            for (int i = 1; i <= k; i++) {
                long left = countArray(n/2+1, first, i, k, cache);
                long right = countArray( n - (n/2+1)+1, i, last, k, cache);

                count += left * right;
                count = count%mod;
            }
            cache.put(key, count);
            return count;
        }

        private static class Key {
            int n;

            boolean same;


            public Key(int n, boolean same) {
                this.n = n;
                this.same = same;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;

                Key key = (Key) o;

                if (n != key.n) return false;
                return same == key.same;
            }

            @Override
            public int hashCode() {
                int result = n;
                result = 31 * result + (same ? 1 : 0);
                return result;
            }
        }


    }

    public static class Solution {
        public static void main(String[] args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

            String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            int n = Integer.parseInt(firstMultipleInput[0]);

            int k = Integer.parseInt(firstMultipleInput[1]);

            int x = Integer.parseInt(firstMultipleInput[2]);

            long answer = Result.countArray(n, k, x);

            bufferedWriter.write(String.valueOf(answer));
            bufferedWriter.newLine();

            bufferedReader.close();
            bufferedWriter.close();
        }
    }

}
