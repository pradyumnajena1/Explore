package hackerrank.easy;

import java.io.*;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class LisasWorkbook {

    static class Result {

        /*
         * Complete the 'workbook' function below.
         *
         * The function is expected to return an INTEGER.
         * The function accepts following parameters:
         *  1. INTEGER n
         *  2. INTEGER k
         *  3. INTEGER_ARRAY arr
         */

        public static int workbook(int n, int k, List<Integer> chapters) {
            // Write your code here
            int numMagicProblems = 0;
            int startPage = 0;
            int endPage = 0;
            for (int i = 0; i < chapters.size(); i++) {
                startPage = endPage + 1;
                endPage = endPage + chapters.get(i) / k + (chapters.get(i) % k == 0 ? 0 : 1);
                numMagicProblems += getNumMagicProblemsInChapter(chapters.get(i), startPage, endPage,k);
            }
            return numMagicProblems;
        }

        private static int getNumMagicProblemsInChapter(Integer numProblems, int startPage, int endPage, int k) {
            int count=0;
            for(int i=startPage;i<=endPage;i++){
                int startProb = (i-startPage)*k+1;
                int endProb = Math.min( startProb+k-1,numProblems);
                if(i>=startProb&&i<=endProb){
                     count++;
                 }
            }
            return count;
        }

    }

    public static class Solution {
        public static void main(String[] args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

            String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            int n = Integer.parseInt(firstMultipleInput[0]);

            int k = Integer.parseInt(firstMultipleInput[1]);

            List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Integer::parseInt)
                    .collect(toList());

            int result = Result.workbook(n, k, arr);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();

            bufferedReader.close();
            bufferedWriter.close();
        }
    }


}
