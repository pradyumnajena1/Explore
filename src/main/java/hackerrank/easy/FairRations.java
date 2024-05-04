package hackerrank.easy;

import java.io.*;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class FairRations {
   static class Result {

        /*
         * Complete the 'fairRations' function below.
         *
         * The function is expected to return a STRING.
         * The function accepts INTEGER_ARRAY B as parameter.
         */

        public static String fairRations(List<Integer> B) {
            // Write your code here
            int count = 0;
            for(int i=0;i<B.size();i++){
                if(B.get(i)%2==1 ){

                   if(i+1<B.size()){
                       B.set(i,B.get(i)+1) ;
                       count++;
                       B.set(i+1,B.get(i+1)+1) ;
                       count++;
                   }else{
                       return "NO";
                   }

                }
            }


            return String.valueOf(count);


        }

    }

    public static class Solution {
        public static void main(String[] args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

            int N = Integer.parseInt(bufferedReader.readLine().trim());

            List<Integer> B = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Integer::parseInt)
                    .collect(toList());

            String result = Result.fairRations(B);

            bufferedWriter.write(result);
            bufferedWriter.newLine();

            bufferedReader.close();
            bufferedWriter.close();
        }
    }

}
