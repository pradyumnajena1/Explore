package hackerrank.easy;

import java.io.*;

public class AppendAndDelete {

    static class Result {

        /*
         * Complete the 'appendAndDelete' function below.
         *
         * The function is expected to return a STRING.
         * The function accepts following parameters:
         *  1. STRING s
         *  2. STRING t
         *  3. INTEGER k
         */

        public static String appendAndDelete(String s, String t, int k) {
            // Write your code here
            int prefixLength =s.length();
           while (!t.startsWith(s.substring(0,prefixLength))){
               prefixLength--;
           }
           int minSteps = s.length() - prefixLength +  (t.length()-prefixLength);
           int maxSteps = s.length() + t.length() ;
           if(k>=maxSteps){
               return "Yes";
           }
           if(k>=minSteps && k< maxSteps && (k-minSteps)%2==0){
               return "Yes";
           }
           return "No";

        }

    }

    public static class Solution {
        public static void main(String[] args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

            String s = bufferedReader.readLine();

            String t = bufferedReader.readLine();

            int k = Integer.parseInt(bufferedReader.readLine().trim());

            String result = Result.appendAndDelete(s, t, k);

            bufferedWriter.write(result);
            bufferedWriter.newLine();

            bufferedReader.close();
            bufferedWriter.close();
        }
    }
}
