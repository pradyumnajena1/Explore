package hackerrank.easy;

import java.io.*;
import java.util.stream.IntStream;

public class TaumAndBday {
    static class Result {

        /*
         * Complete the 'taumBday' function below.
         *
         * The function is expected to return a LONG_INTEGER.
         * The function accepts following parameters:
         *  1. INTEGER b
         *  2. INTEGER w
         *  3. INTEGER bc
         *  4. INTEGER wc
         *  5. INTEGER z
         */

        public static long taumBday(int b, int w, int bc, int wc, int z) {
            // Write your code here
            return taumBday2(b, w, bc, wc, z);
        }

        private static long taumBday2(long b, long w, long bc, long wc, long z) {
            if (bc + z < wc) {
                return (b + w) * bc + w * z;
            } else if (wc + z < bc) {
                return (b + w) * wc + b * z;
            } else {
                return b * bc + w * wc;
            }
        }

    }

    public static class Solution {
        public static void main(String[] args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

            int t = Integer.parseInt(bufferedReader.readLine().trim());

            IntStream.range(0, t).forEach(tItr -> {
                try {
                    String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                    int b = Integer.parseInt(firstMultipleInput[0]);

                    int w = Integer.parseInt(firstMultipleInput[1]);

                    String[] secondMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                    int bc = Integer.parseInt(secondMultipleInput[0]);

                    int wc = Integer.parseInt(secondMultipleInput[1]);

                    int z = Integer.parseInt(secondMultipleInput[2]);

                    long result = Result.taumBday(b, w, bc, wc, z);

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
