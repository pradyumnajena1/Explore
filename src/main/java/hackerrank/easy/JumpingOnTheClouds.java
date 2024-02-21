package hackerrank.easy;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class JumpingOnTheClouds {
    static class Result {

        /*
         * Complete the 'jumpingOnClouds' function below.
         *
         * The function is expected to return an INTEGER.
         * The function accepts INTEGER_ARRAY c as parameter.
         */

        public static int jumpingOnClouds(List<Integer> c) {
            // Write your code here
            Map<Integer,Integer> cache = new HashMap<>();
            return jumpingOnClouds(c,0,c.size()-1,cache);

        }

        private static int jumpingOnClouds(List<Integer> c, int start, int end, Map<Integer, Integer> cache) {
            if(start==end){
                return 0;
            }
            if(cache.containsKey(start)){
                return cache.get(start);
            }
            int oneStep = Integer.MAX_VALUE;
            if(start+1<=end && c.get(start+1)!=1 ){
                oneStep = jumpingOnClouds(c,start+1,end, cache);
            }
            int twoStep = Integer.MAX_VALUE;
            if(start+2<=end && c.get(start+2)!=1 ){
                twoStep = jumpingOnClouds(c,start+2,end, cache);
            }
            int min = Math.min(oneStep, twoStep)+1;
            cache.put(start,min);
            return min;
        }

    }

    public static class Solution {
        public static void main(String[] args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

            int n = Integer.parseInt(bufferedReader.readLine().trim());

            List<Integer> c = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Integer::parseInt)
                    .collect(toList());

            int result = Result.jumpingOnClouds(c);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();

            bufferedReader.close();
            bufferedWriter.close();
        }
    }

}
