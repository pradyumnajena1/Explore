package hackerrank.medium;

import epp.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class CuttingBoards {

    static class Result {

        /*
         * Complete the 'boardCutting' function below.
         *
         * The function is expected to return an INTEGER.
         * The function accepts following parameters:
         *  1. INTEGER_ARRAY cost_y
         *  2. INTEGER_ARRAY cost_x
         */

        public static int boardCutting(List<Integer> cost_y, List<Integer> cost_x) {
            // Write your code here
            List<Pair<Character, Integer>> cutsCost = new ArrayList<>();
            for (int cost : cost_y) {
                cutsCost.add(new Pair<>('y', cost));
            }
            for (int cost : cost_x) {
                cutsCost.add(new Pair<>('x', cost));
            }
            cutsCost.sort(Comparator.comparingInt((Pair<Character, Integer> x) -> x.getSecond()).reversed());
            // System.out.println(cutsCost);
            int horizontalCuts = 1;
            int verticalCuts = 1;
            int totalCost = 0;
            int modulo = (int) (Math.pow(10, 9) + 7);
            System.out.println(modulo);
            for (int i = 0; i < cutsCost.size(); i++) {
                Pair<Character, Integer> pair = cutsCost.get(i);
                if (pair.getFirst() == 'x') {
                    totalCost += (horizontalCuts * pair.getSecond());
                    verticalCuts++;

                } else {
                    totalCost += (verticalCuts * pair.getSecond());
                    horizontalCuts++;
                }
                totalCost = (totalCost % modulo);

            }

            return totalCost;
        }

    }

    public static class Solution {
        public static void main(String[] args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

            int q = Integer.parseInt(bufferedReader.readLine().trim());

            IntStream.range(0, q).forEach(qItr -> {
                try {
                    String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                    int m = Integer.parseInt(firstMultipleInput[0]);

                    int n = Integer.parseInt(firstMultipleInput[1]);

                    List<Integer> cost_y = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                            .map(Integer::parseInt)
                            .collect(toList());

                    List<Integer> cost_x = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                            .map(Integer::parseInt)
                            .collect(toList());

                    int result = Result.boardCutting(cost_y, cost_x);

                    bufferedWriter.write(String.valueOf(result));
                    bufferedWriter.newLine();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });

            bufferedReader.close();
            bufferedWriter.close();
        }
    }
}
