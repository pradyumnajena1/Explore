package hackerrank.easy;

import epp.Triplet;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class GameofTwoStacks {
    public static void main(String[] args) throws IOException {
        /* System.out.println(twoStacks(19, new ArrayList<>(List.of(14, 0, 15, 12, 15, 6, 15, 0, 18, 19, 16, 1, 3, 9, 5,
                         19, 0, 10, 10, 2, 14, 12, 1, 4, 6, 6, 10, 16, 7, 2, 14, 2)),
                 new ArrayList<>(List.of(2, 2, 6, 9, 0, 1, 1, 18, 12, 17, 11, 16, 18, 8, 7, 18, 19, 17, 13, 13, 2, 14, 10, 8, 0, 0, 4, 0, 2, 11, 2, 16))));
     */
        main2(null);
    }

    public static void main2(String[] args) throws IOException {
        System.out.println(System.getenv());
        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int g = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, g).forEach(gItr -> {
            try {
                String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                int n = Integer.parseInt(firstMultipleInput[0]);

                int m = Integer.parseInt(firstMultipleInput[1]);

                int maxSum = Integer.parseInt(firstMultipleInput[2]);

                List<Integer> a = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" ")).map(Integer::parseInt).collect(toList());

                List<Integer> b = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" ")).map(Integer::parseInt).collect(toList());

                int result = twoStacks(maxSum, a, b);

                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }

    public static int twoStacks(int maxSum, List<Integer> a, List<Integer> b) {
        // Write your code here
        Map<Triplet<Integer, Integer, Integer>, Integer> cache = new HashMap<>();
        return twoStacks(maxSum, a, b, 0, 0, cache);
    }

    private static int twoStacks(int maxSum, List<Integer> a, List<Integer> b, int i, int j, Map<Triplet<Integer, Integer, Integer>, Integer> cache) {
        if (i == a.size() && j == b.size()) {
            return 0;
        }
        if (maxSum < 0) {
            return 0;
        }
        Triplet<Integer, Integer, Integer> key = new Triplet<>(maxSum, i, j);
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        int maxResult = 0;
        int count=0;
        while (i < a.size() && a.get(i)==0){
            i++;
            count++;
        }
        while (j < b.size() && b.get(j)==0){
            j++;
            count++;
        }

        if (i < a.size() && maxSum >= a.get(i)) {
            int aSelect = twoStacks(maxSum - a.get(i), a, b, i + 1, j, cache);
            maxResult = Math.max(maxResult,  aSelect+1);
        }
        if (j < b.size() && maxSum >= b.get(j)) {
            int bSelect = twoStacks(maxSum - b.get(j), a, b, i, j + 1, cache);
            maxResult = Math.max(maxResult, bSelect+1);
        }
           maxResult+=count;
        cache.put(key, maxResult );
        return maxResult;
    }


}
