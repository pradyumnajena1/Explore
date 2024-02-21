package hackerrank.easy;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class SequenceEquation {
  static   class Result {

        /*
         * Complete the 'permutationEquation' function below.
         *
         * The function is expected to return an INTEGER_ARRAY.
         * The function accepts INTEGER_ARRAY p as parameter.
         */

        public static List<Integer> permutationEquation(List<Integer> p) {
            // Write your code here
            p.add(0,0);
            List<Integer> p2 = new ArrayList<>();
            for(int i=0;i<p.size();i++){
                p2.add(p.get(p.get(i)));
            }
            Map<Integer,Integer> map = new HashMap<>();
            for(int i=0;i<p2.size();i++){
                map.put(p2.get(i),i);
            }
            System.out.println(map);
            List<Integer> result = new ArrayList<>();
            for(int i=1;i<p2.size();i++){
                result.add(map.get(i));
            }
            return result;
        }

    }

    public static class Solution {
        public static void main(String[] args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

            int n = Integer.parseInt(bufferedReader.readLine().trim());

            List<Integer> p = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Integer::parseInt)
                    .collect(toList());

            List<Integer> result = Result.permutationEquation(p);

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
