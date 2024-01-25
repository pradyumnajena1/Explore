package hackerrank.hard;

import guidetocompetitiveprogramming.RangeQuerySparseTree;
import guidetocompetitiveprogramming.SegmentTree;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class MaxTransform {

    public static int solve(List<Integer> A) {
        // Return the sum of S(S(A
        List<Integer> max = maxTransform(A);
        System.out.println(max);
        RangeQuerySparseTree rangeQuerySparseTree = new RangeQuerySparseTree(max, Integer::max, Integer.MIN_VALUE);
        int result=0;
        for (int k = 0; k < max.size(); k++) {
            for (int i = 0; i < max.size() - k; i++) {
                int j = i + k;
                result = (result+ (rangeQuerySparseTree.rangeQuery(i, j)))%1000000007;
            }
        }
        return result;
    }

    private static List<Integer> maxTransform(List<Integer> a) {
        SegmentTree segmentTree = new SegmentTree(a, Integer::max, Integer.MIN_VALUE);
        List<Integer> result = new ArrayList<>();
        for (int k = 0; k < a.size(); k++) {
            for (int i = 0; i < a.size() - k; i++) {
                int j = i + k;
                result.add(segmentTree.rangeResult(i, j));
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> A = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int result = solve(A);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }

}
