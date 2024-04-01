package hackerrank.medium;

import epp.Pair;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class SherlockAndCost {
    static class Result {

        /*
         * Complete the 'cost' function below.
         *
         * The function is expected to return an INTEGER.
         * The function accepts INTEGER_ARRAY B as parameter.
         */

        public static int cost(List<Integer> B) {
            // Write your code here

            Map<Pair<Integer, Integer>, ResultHolder> cache = new HashMap<>();


            for(int i= B.size()-1;i>=0;i--){
                if(i>0){
                    cost(B,i,B.get(i-1),cache);
                    cost(B,i,1,cache);
                }else{
                    cost(B,i,0,cache);
                }

            }

            return cache.get(new Pair<>(0,0)).max;

        }

        private static ResultHolder cost(List<Integer> b, int startIndex, int prev, Map<Pair<Integer,
                Integer>, ResultHolder> cache) {

            if (startIndex == b.size() - 1) {
                if ( prev==1) {
                    return new ResultHolder( b.get(startIndex)-1 , b.get(startIndex));
                } else {
                    return new ResultHolder( prev-1, 1);
                }

            }
            Pair<Integer, Integer> key = new Pair<>(startIndex, prev);
            if (cache.containsKey(key)) {
                return cache.get(key);
            }

            ResultHolder resultHolder = null;

            ResultHolder chosenOne = cost(b, startIndex + 1, 1 , cache);
            ResultHolder chosenN = cost(b, startIndex + 1, b.get(startIndex),  cache);
            if(startIndex>0){
                if(chosenOne.max+ prev-1 > chosenN.max+ b.get(startIndex)-prev){
                    resultHolder = new ResultHolder(chosenOne.max+ prev-1,1);
                }else{
                    resultHolder = new ResultHolder(chosenN.max+ b.get(startIndex)-prev,b.get(startIndex));;
                }
            }else{
                if(chosenOne.max  > chosenN.max ){
                    resultHolder = new ResultHolder(chosenOne.max ,1);
                }else{
                    resultHolder = new ResultHolder(chosenN.max ,b.get(startIndex));;
                }
            }

            cache.put(key, resultHolder);
            return resultHolder;
        }

        static class ResultHolder {
            int max;
            int lastValue;

            public ResultHolder(int max, int lastValue) {
                this.max = max;
                this.lastValue = lastValue;
            }

            @Override
            public String toString() {
                return new StringJoiner(", ", ResultHolder.class.getSimpleName() + "[", "]")
                        .add("max=" + max)
                        .add("lastValue=" + lastValue)
                        .toString();
            }
        }


    }

    public static class Solution {
        public static void main(String[] args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

            int t = Integer.parseInt(bufferedReader.readLine().trim());

            IntStream.range(0, t).forEach(tItr -> {
                try {
                    int n = Integer.parseInt(bufferedReader.readLine().trim());

                    List<Integer> B = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                            .map(Integer::parseInt)
                            .collect(toList());

                    int result = Result.cost(B);

                    bufferedWriter.write(String.valueOf(result));
                    bufferedWriter.newLine();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });

            bufferedReader.close();
            bufferedWriter.close();
        }
    }

}
