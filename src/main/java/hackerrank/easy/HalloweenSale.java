package hackerrank.easy;

import java.io.*;

public class HalloweenSale {
    static class Result {

        /*
         * Complete the 'howManyGames' function below.
         *
         * The function is expected to return an INTEGER.
         * The function accepts following parameters:
         *  1. INTEGER p
         *  2. INTEGER d
         *  3. INTEGER m
         *  4. INTEGER s
         */

        public static int howManyGames(int p, int d, int m, int s) {
            // Return the number of games you can buy
            int remainingAmount = s;
            int currentItemPrice = p;
            int numItemsBought = 0;
            while (remainingAmount >= currentItemPrice) {

                numItemsBought++;
                remainingAmount = remainingAmount - currentItemPrice;
                currentItemPrice = Math.max(m, currentItemPrice - d);


            }
           return numItemsBought;

        }

    }

    public static class Solution {
        public static void main(String[] args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

            String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            int p = Integer.parseInt(firstMultipleInput[0]);

            int d = Integer.parseInt(firstMultipleInput[1]);

            int m = Integer.parseInt(firstMultipleInput[2]);

            int s = Integer.parseInt(firstMultipleInput[3]);

            int answer = Result.howManyGames(p, d, m, s);

            bufferedWriter.write(String.valueOf(answer));
            bufferedWriter.newLine();

            bufferedReader.close();
            bufferedWriter.close();
        }
    }

}
