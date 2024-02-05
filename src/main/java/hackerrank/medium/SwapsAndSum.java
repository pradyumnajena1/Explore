package hackerrank.medium;

import guidetocompetitiveprogramming.SegmentTree;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class SwapsAndSum {
    private static class Result {

        /*
         * Complete the 'solve' function below.
         *
         * The function is expected to return an INTEGER_ARRAY.
         * The function accepts following parameters:
         *  1. INTEGER_ARRAY a
         *  2. 2D_INTEGER_ARRAY queries
         */

        public static List<Integer> solve(List<Integer> a, List<List<Integer>> queries) {
            // Write your code here
            List<Integer> result = new ArrayList<>();
            SegmentTree segmentTree = new SegmentTree(a, Integer::sum, 0);
            for (List<Integer> query : queries) {
                System.out.println(query);
                int l = query.get(1) - 1;
                int r = query.get(2) - 1;
                if (query.get(0).equals(1)) {

                    for (int i = l; i + 1 <= r; i += 2) {
                        int temp = segmentTree.get(i);

                        segmentTree.set(i, segmentTree.get(i + 1));
                        segmentTree.set(i + 1, temp);
                    }
                   // segmentTree.printTree();

                } else {
                    int rangeResult = segmentTree.rangeResult(l, r);
                    result.add(rangeResult);
                }
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

            int q = Integer.parseInt(firstMultipleInput[1]);

            List<Integer> a = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Integer::parseInt)
                    .collect(toList());

            List<List<Integer>> queries = new ArrayList<>();

            IntStream.range(0, q).forEach(i -> {
                try {
                    queries.add(
                            Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                    .map(Integer::parseInt)
                                    .collect(toList())
                    );
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });

            List<Integer> result = Result.solve(a, queries);

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
