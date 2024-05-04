package hackerrank.easy;

import epp.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class CavityMap {

    static class Result {

        /*
         * Complete the 'cavityMap' function below.
         *
         * The function is expected to return a STRING_ARRAY.
         * The function accepts STRING_ARRAY grid as parameter.
         */

        public static List<String> cavityMap(List<String> grid) {
            // Write your code here
            char[][] charGrid = new char[grid.size()][grid.get(0).length()];
            for (int i = 0; i < grid.size(); i++) {
                charGrid[i] = grid.get(i).toCharArray();
            }
            List<List<Integer>> valleys = new ArrayList<>();
            for (int i = 1; i < charGrid.length - 1; i++) {
                for (int j = 1; j < charGrid[0].length - 1; j++) {

                    if (charGrid[i][j] > charGrid[i - 1][j] && charGrid[i][j] > charGrid[i + 1][j]
                            && charGrid[i][j] > charGrid[i][j - 1] && charGrid[i][j] > charGrid[i][j + 1]) {
                        valleys.add(List.of(i, j));
                    }
                }
            }
            for (List<Integer> valley : valleys) {
                charGrid[valley.get (0)][valley.get (1)] = 'X';
            }
            List<String> result = new ArrayList<>();
            for (int i = 0; i < charGrid.length; i++) {
                result.add(new String(charGrid[i]));
            }
            return result;
        }

    }


    public static class Solution {
        public static void main(String[] args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

            int n = Integer.parseInt(bufferedReader.readLine().trim());

            List<String> grid = IntStream.range(0, n).mapToObj(i -> {
                        try {
                            return bufferedReader.readLine();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    })
                    .collect(toList());

            List<String> result = Result.cavityMap(grid);

            bufferedWriter.write(
                    result.stream()
                            .collect(joining("\n"))
                            + "\n"
            );

            bufferedReader.close();
            bufferedWriter.close();
        }
    }

}
