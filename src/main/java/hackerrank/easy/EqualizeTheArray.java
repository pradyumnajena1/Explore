package hackerrank.easy;

import java.io.*;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class EqualizeTheArray {

    static class Result {

        /*
         * Complete the 'equalizeArray' function below.
         *
         * The function is expected to return an INTEGER.
         * The function accepts INTEGER_ARRAY arr as parameter.
         */

        public static int equalizeArray(List<Integer> arr) {
            // Write your code here
            Map<Integer, Long> frequencyMap = arr.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

            Long max = frequencyMap.entrySet().stream().map(x -> x.getValue()).max(Comparator.comparingLong(x -> x)).orElse(0L);
           return (int) (arr.size()-max);
        }

    }

    public static class Solution {
        public static void main(String[] args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

            int n = Integer.parseInt(bufferedReader.readLine().trim());

            List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Integer::parseInt)
                    .collect(toList());

            int result = Result.equalizeArray(arr);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();

            bufferedReader.close();
            bufferedWriter.close();
        }
    }

}
