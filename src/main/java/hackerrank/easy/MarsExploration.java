package hackerrank.easy;

import java.io.*;

public class MarsExploration {

    static class Result {


        /*
         * Complete the 'marsExploration' function below.
         *
         * The function is expected to return an INTEGER.
         * The function accepts STRING s as parameter.
         */
        public static int marsExploration(String s) {
            final String SOS = "SOS";
            // Write your code here
            int count = 0;
            for (int i = 0; i < s.length(); i += 3) {
                for (int j = 0; j < 3; j++) {
                    if (s.charAt(i + j) != SOS.charAt(j)) {
                        count++;
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

            String s = bufferedReader.readLine();

            int result = Result.marsExploration(s);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();

            bufferedReader.close();
            bufferedWriter.close();
        }
    }

}
