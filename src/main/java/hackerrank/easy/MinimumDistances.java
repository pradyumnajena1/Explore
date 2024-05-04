package hackerrank.easy;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class MinimumDistances {
   static class Result {

        /*
         * Complete the 'minimumDistances' function below.
         *
         * The function is expected to return an INTEGER.
         * The function accepts INTEGER_ARRAY a as parameter.
         */

        public static int minimumDistances(List<Integer> a) {
            // Write your code here
            Map<Integer,Integer> indexMap = new HashMap<>();
            int minDistance = Integer.MAX_VALUE;
            for(int i=0;i<a.size();i++){
                if(indexMap.containsKey(a.get(i))){
                     minDistance = Math.min(minDistance,i-indexMap.get(a.get(i)));
                }
                indexMap.put(a.get(i),i);
            }
            return minDistance==Integer.MAX_VALUE?-1:minDistance;
        }

    }

    public static class Solution {
        public static void main(String[] args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

            int n = Integer.parseInt(bufferedReader.readLine().trim());

            List<Integer> a = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Integer::parseInt)
                    .collect(toList());

            int result = Result.minimumDistances(a);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();

            bufferedReader.close();
            bufferedWriter.close();
        }
    }

}
