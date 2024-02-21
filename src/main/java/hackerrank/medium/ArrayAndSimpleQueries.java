package hackerrank.medium;

import epp.array.ArrayUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class ArrayAndSimpleQueries {


    public static void main(String[] args) throws IOException {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */

        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        List<Integer> multipleParams = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());
        int n = multipleParams.get(0);
        int q = multipleParams.get(1);

        int[] values = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();


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

        long t = System.currentTimeMillis();
        int result = solve(values, queries);
        System.out.println(System.currentTimeMillis()-t);
        bufferedWriter.write(result + "\n");
        bufferedWriter.write(
                Arrays.stream(values)
                        .mapToObj(String::valueOf)
                        .collect(joining(" "))
                        + "\n");
        bufferedReader.close();
        bufferedWriter.close();
    }

    public static int solve(int[] values, List<List<Integer>> queries) {


        for (List<Integer> query : queries) {

            int type = query.get(0);
            int a = query.get(1) - 1;
            int b = query.get(2) - 1;
            if (type == 1) {

                reverse(values, 0, a - 1);
                reverse(values, a, b);
                reverse(values, 0, b);

            } else {
                reverse(values, b + 1, values.length - 1);
                reverse(values, a, b);
                reverse(values, a, values.length - 1);

            }
        }
        return Math.abs(Math.abs(values[0]) - Math.abs(values[values.length - 1]));
    }

    public static void reverse(int[] array, int left, int right) {
        while (left < right) {
            swap(array, left++, right--);
        }
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
