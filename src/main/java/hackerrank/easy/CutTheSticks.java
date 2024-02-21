package hackerrank.easy;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class CutTheSticks {

    private static class Result {
        /*
         * Complete the 'cutTheSticks' function below.
         *
         * The function is expected to return an INTEGER_ARRAY.
         * The function accepts INTEGER_ARRAY arr as parameter.
         */

        public static List<Integer> cutTheSticks(List<Integer> arr) {
            // Write your code here
            List<Integer> result = new ArrayList<>();
            arr.sort(Comparator.naturalOrder());
            int start = 0;
            while (start < arr.size()) {
                result.add(arr.size() - start  );

                while (start+1 < arr.size() && arr.get(start+1) .equals( arr.get(start))) {
                    start++;
                }
                start++;
            }
            return result;
        }
    }


    private static class Solution {
        public static void main(String[] args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

            int n = Integer.parseInt(bufferedReader.readLine().trim());

            List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Integer::parseInt)
                    .collect(toList());

            List<Integer> result = Result.cutTheSticks(arr);

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
