package hackerrank.hard;

import epp.Pair;
import epp.array.ArrayUtils;
import guidetocompetitiveprogramming.IntTriangularMatrix;
import guidetocompetitiveprogramming.LongTriangularMatrix;
import guidetocompetitiveprogramming.RangeQuerySparseTree;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class SumoftheMaximums {

    static class Result {



        public static List<Long> solve(List<Integer> a, List<List<Integer>> queries) {
            // Write your code here
            List<Long> result = new ArrayList<>();
            RangeQuerySparseTree tree = new RangeQuerySparseTree(a, Integer::max, Integer.MIN_VALUE);
            List<Long> cumSum = new ArrayList<>() ;
            cumSum.add(Long.valueOf(a.get(0)));
            for (int i = 1; i < a.size(); i++) {
                long sum = cumSum.get(i-1);
                for (int j = 0; j <=i  ; j++) {
                    sum+=tree.rangeQuery(j, i);

                }
                cumSum.add(sum);
            }

            for (List<Integer> query : queries) {
                int l = query.get(0) - 1;
                int r = query.get(1) - 1;
                long sum =cumSum.get(r) - cumSum.get(l);
                result.add(sum);
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

            int m = Integer.parseInt(firstMultipleInput[1]);

            List<Integer> a = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Integer::parseInt)
                    .collect(toList());

            List<List<Integer>> queries = new ArrayList<>();

            IntStream.range(0, m).forEach(i -> {
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

            List<Long> result = Result.solve(a, queries);

            bufferedWriter.write(
                    result.stream()
                            .map(Object::toString)
                            .collect(joining("\n"))
                    // + "\n"
            );

            bufferedReader.close();
            bufferedWriter.close();
        }
    }
}
