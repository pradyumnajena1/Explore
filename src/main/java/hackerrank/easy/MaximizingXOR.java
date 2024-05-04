package hackerrank.easy;

import java.io.*;

public class MaximizingXOR {
   static class Result {

        /*
         * Complete the 'maximizingXor' function below.
         *
         * The function is expected to return an INTEGER.
         * The function accepts following parameters:
         *  1. INTEGER l
         *  2. INTEGER r
         */

        public static int maximizingXor(int l, int r) {
            // Write your code here
            int max = 0;
               for(int i=l;i<=r;i++){
                   for(int j=l;j<=r;j++){
                       max = Math.max(max,i^j);
                   }
               }
               return max;
        }

    }

    public static class Solution {
        public static void main(String[] args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

            int l = Integer.parseInt(bufferedReader.readLine().trim());

            int r = Integer.parseInt(bufferedReader.readLine().trim());

            int result = Result.maximizingXor(l, r);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();

            bufferedReader.close();
            bufferedWriter.close();
        }
    }

}
