package hackerrank.easy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.stream.IntStream;

public class SeparateTheNumbers {
   static class Result {

        /*
         * Complete the 'separateNumbers' function below.
         *
         * The function accepts STRING s as parameter.
         */

        public static void separateNumbers(String s) {
            // Write your code here

            if(s.startsWith("0")){
                System.out.println( "NO");
                return;
            }
            int maxLength = s.length()/2;
            boolean found = false;
            for(int i=1;i<=maxLength;i++){
                BigInteger start = new  BigInteger (s.substring(0,i));
                if(isSequenceStartsWith(start,s)){
                    System.out.println("YES "+start);
                    found = true;
                    break;
                }
            }
            if(!found){
                System.out.println( "NO");
            }

        }

       private static boolean isSequenceStartsWith(BigInteger start, String s) {
           if(s.isEmpty()){
               return true;
           }

           String prefix = String.valueOf(start);
           if(s.startsWith(prefix)){
               String suffix = s.substring(prefix.length());
               return  isSequenceStartsWith(start.add(BigInteger.ONE),
                       suffix);
           }else{
               return false;
           }

       }

   }

    public static class Solution {
        public static void main(String[] args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));

            int q = Integer.parseInt(bufferedReader.readLine().trim());

            IntStream.range(0, q).forEach(qItr -> {
                try {
                    String s = bufferedReader.readLine();

                    Result.separateNumbers(s);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });

            bufferedReader.close();
        }
    }

}
