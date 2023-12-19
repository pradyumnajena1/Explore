package hackerrank.medium;

import epp.DisjointUnionSet;
import epp.Pair;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class SuperMaximumCostQueries {
    public static void main2(String[] args) {
        ArrayList<List<Integer>> tree = new ArrayList<>(List.of(List.of(1, 2, 3), List.of(1, 4, 2), List.of(2, 5, 6), List.of(3, 4, 1)));
        ArrayList<List<Integer>> queries = new ArrayList<>(List.of(List.of(1, 1), List.of(1, 2), List.of(2, 3), List.of(2, 5), List.of(1, 6)));
        System.out.println(solve(tree, queries));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int q = Integer.parseInt(firstMultipleInput[1]);

        List<List<Integer>> tree = new ArrayList<>();

        IntStream.range(0, n - 1).forEach(i -> {
            try {
                tree.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

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

        List<Integer> result =  solve(tree, queries);

        bufferedWriter.write(
                result.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }

    public static List<Integer> solve(List<List<Integer>> tree, List<List<Integer>> queries) {
         return null;

    }
}
