package hackerrank.medium;

import epp.array.ArrayUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class GameOfTwoStacks {
   static class Result {

        /*
         * Complete the 'twoStacks' function below.
         *
         * The function is expected to return an INTEGER.
         * The function accepts following parameters:
         *  1. INTEGER maxSum
         *  2. INTEGER_ARRAY a
         *  3. INTEGER_ARRAY b
         */

        public static int twoStacks(int maxSum, List<Integer> a, List<Integer> b) {
            // Write your code here
           Score[][] dp = new Score[b.size()+1][a.size()+1];
           int sum = 0;
           dp[0][0] = new Score(0,0);
            int maxSteps = 0;
           for(int i=1;i<=a.size();i++){
                sum += a.get(i-1);
                dp[0][i] = sum<=maxSum ?new Score (sum,i):new Score(maxSum+1,dp[0][i-1].steps);
               maxSteps = Math.max(maxSteps,dp[0][i].steps);
            }

            sum = 0;
            for(int i=1;i<=b.size();i++){
                sum += b.get(i-1);
                dp[i][0] = sum<=maxSum ?new Score (sum,i):new Score(maxSum+1,dp[i-1][0].steps);
                maxSteps = Math.max(maxSteps, dp[i][0].steps);
            }

            for(int i=1;i<=b.size() && dp[i][0].total <= maxSum ;i++){
                for(int j=1;j<=a.size()&& dp[0][j].total <= maxSum ;j++){
                    Score maxScore =new Score(maxSum+1,0);
                    if(a.get(j-1) + dp[i][j-1].total<= maxSum){
                        maxScore = new Score(a.get(j-1) + dp[i][j-1].total,dp[i][j-1].steps+1);
                    }

                    if(b.get(i-1) + dp[i-1][j].total<= maxSum){
                        if(maxScore!=null && maxScore.steps<dp[i-1][j].steps+1){
                            maxScore = new Score(b.get(i-1) + dp[i-1][j].total,dp[i-1][j].steps+1);
                        }
                    }
                    dp[i][j] = maxScore;
                    maxSteps = Math.max(maxSteps,maxScore.steps);
                }
            }

            return maxSteps;
        }

        private static class Score{
           private int steps;
           private int total;

            public Score(int total, int steps) {
                this.steps = steps;
                this.total = total;
            }


        }


   }

    public static class Solution {
        public static void main(String[] args) throws IOException {
      BufferedReader bufferedReader =
          new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

            int g = Integer.parseInt(bufferedReader.readLine().trim());

            for (int gItr = 0; gItr < g; gItr++) {
                String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                int n = Integer.parseInt(firstMultipleInput[0]);

                int m = Integer.parseInt(firstMultipleInput[1]);

                int maxSum = Integer.parseInt(firstMultipleInput[2]);

                String[] aTemp = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                List<Integer> a = new ArrayList<>();

                for (int i = 0; i < n; i++) {
                    int aItem = Integer.parseInt(aTemp[i]);
                    a.add(aItem);
                }

                String[] bTemp = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                List<Integer> b = new ArrayList<>();

                for (int i = 0; i < m; i++) {
                    int bItem = Integer.parseInt(bTemp[i]);
                    b.add(bItem);
                }

                int result = Result.twoStacks(maxSum, a, b);

                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            }

            bufferedReader.close();
            bufferedWriter.close();
        }
    }
}
