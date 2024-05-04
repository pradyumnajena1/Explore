package hackerrank.easy;

import guidetocompetitiveprogramming.SegmentTree;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class ServiceLane {

    static class Result {

        /*
         * Complete the 'serviceLane' function below.
         *
         * The function is expected to return an INTEGER_ARRAY.
         * The function accepts following parameters:
         *  1. INTEGER n
         *  2. 2D_INTEGER_ARRAY cases
         */

        public static List<Integer> serviceLane(int n, List<Integer> width, List<List<Integer>> cases) {
            // Write your code here
            SegmentTree segmentTree = new SegmentTree(width, Integer::min, Integer.MAX_VALUE);
            List<Integer> result = new ArrayList<>();
            for (List<Integer> aCase : cases) {
                int minWidth = segmentTree.rangeResult(aCase.get(0), aCase.get(1));
                result.add(minWidth);
            }
            return result;
        }

    }

    public static class Solution {
        public static void main(String[] args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

            String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            int n = Integer.parseInt(firstMultipleInput[0]);

            int t = Integer.parseInt(firstMultipleInput[1]);

            List<Integer> width = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Integer::parseInt)
                    .collect(toList());

            List<List<Integer>> cases = new ArrayList<>();

            IntStream.range(0, t).forEach(i -> {
                try {
                    cases.add(
                            Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                    .map(Integer::parseInt)
                                    .collect(toList())
                    );
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });

            List<Integer> result = Result.serviceLane(n, width, cases);

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
