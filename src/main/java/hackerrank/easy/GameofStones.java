package hackerrank.easy;

import java.io.*;
import java.util.stream.IntStream;

public class GameofStones {
    static class Result {

        /*
         * Complete the 'gameOfStones' function below.
         *
         * The function is expected to return a STRING.
         * The function accepts INTEGER n as parameter.
         */

        public static String gameOfStones(int n) {
            // Write your code here
            boolean[] winningPositions = new boolean[n + 1];
            winningPositions[0] = false;
            winningPositions[1] = false;
            for (int i = 2; i <= n; i++) {
                winningPositions[i] = ( i>=2 && !winningPositions[i - 2]) ||
                        ( i>=3 && !winningPositions[i - 3]) ||
                        ( i>=5 && !winningPositions[i - 5]);


            }
            return winningPositions[n]?"First":"Second";

        }

    }

    public static class Solution {
        public static void main(String[] args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

            int t = Integer.parseInt(bufferedReader.readLine().trim());

            IntStream.range(0, t).forEach(tItr -> {
                try {
                    int n = Integer.parseInt(bufferedReader.readLine().trim());

                    String result = Result.gameOfStones(n);

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
