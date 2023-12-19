package hackerrank.easy;

import epp.Pair;

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
        Map<Pair<Integer, Integer>, Integer> cache = new HashMap<>();
        return twoStacks(maxSum, a, b, 0, 0, 0, cache);
    }

    private static int twoStacks(int maxSum, List<Integer> a, List<Integer> b, int runningSum, int atop, int btop, Map<Pair<Integer, Integer>, Integer> cache) {
        if (a.size() == atop && b.size() == btop) {
            return 0;
        }
        if (runningSum > maxSum) {
            return 0;
        }
        Pair<Integer, Integer> key = new Pair<>(atop, btop);
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        int numGames = 0;
        while ((atop < a.size() && a.get(atop) == 0)) {
            atop++;
            numGames++;
        }
        while ((btop < b.size() && b.get(btop) == 0)) {
            btop++;
            numGames++;
        }
        while ((atop < a.size() && a.get(atop) == 1 && btop < b.size() && b.get(btop) == 1 && maxSum- runningSum   >= 2)) {
            atop++;
            btop++;
            numGames+=2;
            runningSum+=2;
        }



        int maxGames = 0;
        if (atop < a.size() && maxSum- runningSum   >= a.get(atop) ) {

            numGames = numGames + twoStacks(maxSum, a, b, runningSum + a.get(atop), atop + 1, btop, cache);
            maxGames = Math.max(numGames, maxGames);
        }
        if (btop < b.size() && maxSum- runningSum   >= b.get(btop)) {

            numGames = numGames + twoStacks(maxSum, a, b, runningSum + b.get(btop), atop, btop + 1, cache);
            maxGames = Math.max(numGames, maxGames);
        }
        cache.put(key, maxGames);
        return maxGames;
    }
}
