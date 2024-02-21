package hackerrank.easy;

import java.io.*;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class BigSorting2 {

   static class Result {

        /*
         * Complete the 'bigSorting' function below.
         *
         * The function is expected to return a STRING_ARRAY.
         * The function accepts STRING_ARRAY unsorted as parameter.
         */

        public static List<String> bigSorting(List<String> unsorted) {
            // Write your code here
            unsorted.sort((x,y)->compare(x,y));
            return unsorted;

        }

        public static int compare(String a,String b){
            if(a.length()!=b.length()) {
                return a.length()-b.length();

            }
            for(int i=0;i<a.length();i++){
                if(a.charAt(i)!=b.charAt(i)){
                    return a.charAt(i)-b.charAt(i);
                }
            }
            return 0;
        }

    }

    public static class Solution {
        public static void main(String[] args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

            int n = Integer.parseInt(bufferedReader.readLine().trim());

            List<String> unsorted = IntStream.range(0, n).mapToObj(i -> {
                        try {
                            return bufferedReader.readLine();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    })
                    .collect(toList());

            List<String> result = Result.bigSorting(unsorted);

            bufferedWriter.write(
                    result.stream()
                            .collect(joining("\n"))
                            + "\n"
            );

            bufferedReader.close();
            bufferedWriter.close();
        }
    }

}
