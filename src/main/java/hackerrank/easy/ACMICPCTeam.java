package hackerrank.easy;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class ACMICPCTeam {

    static class Result {

        /*
         * Complete the 'acmTeam' function below.
         *
         * The function is expected to return an INTEGER_ARRAY.
         * The function accepts STRING_ARRAY topic as parameter.
         */

        public static List<Integer> acmTeam(List<String> topic) {
            // Write your code here
            int max = 0;
            int maxCount=0;
            for(int i=0;i<topic.size();i++){
                for(int j=0;j<i;j++){
                    int matchCount = matchCount(topic.get(i), topic.get(j));
                    if(matchCount > max){
                        max = matchCount;
                        maxCount=1;
                    } else if (matchCount==max) {
                        maxCount++;
                    }
                }
            }
           return new ArrayList<>(List.of(max,maxCount));
        }
        static int matchCount(String a,String b){
            int count=0;
            for(int i=0;i<a.length();i++){
                if(a.charAt(i)== '1' || b.charAt(i)==  '1'){
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

            int m = Integer.parseInt(firstMultipleInput[1]);

            List<String> topic = IntStream.range(0, n).mapToObj(i -> {
                        try {
                            return bufferedReader.readLine();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    })
                    .collect(toList());

            List<Integer> result = Result.acmTeam(topic);

            bufferedWriter.write(
                    result.stream()
                            .map(Object::toString)
                            .collect(joining("\n"))
                            + "\n"
            );

            bufferedReader.close();
            bufferedWriter.close();
        }
    }

}
