package hackerrank.easy;

import java.io.*;

public class SumvsXOR {
   static class Result {

        /*
         * Complete the 'sumXor' function below.
         *
         * The function is expected to return a LONG_INTEGER.
         * The function accepts LONG_INTEGER n as parameter.
         */

        public static long sumXor(long n) {
            // Write your code here
            long l = Long.highestOneBit(n);
            int bitCount = 0;
            while (l>0){
                bitCount++;
                l = l/2;
            }
            int setbitCount = 0;
             while (n>0){
                 setbitCount++;
                 n = n&(n-1);

             }


            return (long) Math.pow(2,bitCount-setbitCount);
        }

    }

    public static class Solution {
        public static void main(String[] args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

            long n = Long.parseLong(bufferedReader.readLine().trim());

            long result = Result.sumXor(n);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();

            bufferedReader.close();
            bufferedWriter.close();
        }
    }

}
