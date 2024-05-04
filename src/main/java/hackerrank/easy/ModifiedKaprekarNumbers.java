package hackerrank.easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ModifiedKaprekarNumbers {
   static class Result {

        /*
         * Complete the 'kaprekarNumbers' function below.
         *
         * The function accepts following parameters:
         *  1. INTEGER p
         *  2. INTEGER q
         */

        public static void kaprekarNumbers(int p, int q) {
            // Write your code here
            boolean found = false;
            for(int num=p;num<=q;num++){
                if(iskaprekarNumber(num)){
                    System.out.print(num+" ");
                    found=true;
                }
            }
            if(!found){
                System.out.println( "INVALID RANGE");
            }

        }

       private static boolean iskaprekarNumber(int num) {

            int d = String.valueOf(num).length();
            long square = ((long) num) * num;
           String s = String.valueOf(square);
           String leftString = s.substring(0, s.length() - d);
           int left = leftString.isEmpty()?0: Integer.parseInt(leftString);
           String rightString = s.substring(s.length() -d, s.length());
           int startIndex = 0;
           while (startIndex<rightString.length() && rightString.charAt(startIndex)=='0'){
               startIndex++;
           }
           rightString = rightString.substring(startIndex);
           int right =rightString.isEmpty()?0: Integer.parseInt(rightString);
           return num == left+right;
       }

   }

    public static class Solution {
        public static void main(String[] args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            int p = Integer.parseInt(bufferedReader.readLine().trim());

            int q = Integer.parseInt(bufferedReader.readLine().trim());

            Result.kaprekarNumbers(p, q);

            bufferedReader.close();
        }
    }

}
