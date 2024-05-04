package hackerrank.easy;

import java.io.*;

public class StrangeCounter {

    static class Result {

        /*
         * Complete the 'strangeCounter' function below.
         *
         * The function is expected to return a LONG_INTEGER.
         * The function accepts LONG_INTEGER t as parameter.
         */

        public static long strangeCounter(long t) {
            // Write your code here

            long start = 0;
            long nextStart = 3;
            long totalSecsElapsed = 0;
            while (true) {
                nextStart = start == 0 ? 3 : start * 2;
                if (totalSecsElapsed + nextStart >= t) {
                    break;
                }
                totalSecsElapsed += nextStart;
                start = nextStart;
            }
            return nextStart - (t - totalSecsElapsed-1);


        }

    }

    public static class Solution {
        public static void main(String[] args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

            long t = Long.parseLong(bufferedReader.readLine().trim());

            long result = Result.strangeCounter(t);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();

            bufferedReader.close();
            bufferedWriter.close();
        }
    }

}
