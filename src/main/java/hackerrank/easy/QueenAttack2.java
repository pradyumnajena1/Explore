package hackerrank.easy;

import epp.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class QueenAttack2 {
    static class Result {

        /*
         * Complete the 'queensAttack' function below.
         *
         * The function is expected to return an INTEGER.
         * The function accepts following parameters:
         *  1. INTEGER n
         *  2. INTEGER k
         *  3. INTEGER r_q
         *  4. INTEGER c_q
         *  5. 2D_INTEGER_ARRAY obstacles
         */

        public static int queensAttack(int n, int k, int r_q, int c_q, List<List<Integer>> obstacles) {
            // Write your code 
            Set<Pair<Integer, Integer>> obstacleSet = new HashSet<>();
            for (List<Integer> l : obstacles) {
                obstacleSet.add(new Pair<>(getRowIndex(n, l.get(0)), getColIndex(n, l.get(1))));
            }
            System.out.println(obstacleSet);
            r_q = getRowIndex(n, r_q);
            c_q = getColIndex(n, c_q);
            int count = 0;
            int rowCount = getRowCount(n, r_q, c_q, obstacleSet);
            count += rowCount;
            System.out.println(rowCount);
            int colCount = getColCount(n, r_q, c_q, obstacleSet);
            count += colCount;
            System.out.println(colCount);
            int diagonalsCount = getDiagonalsCount(n, r_q, c_q, obstacleSet);
            count+= diagonalsCount;
            System.out.println(diagonalsCount);
            return count;

        }

        private static int getDiagonalsCount(int n, int r_q, int c_q, Set<Pair<Integer, Integer>> obstacleSet) {
            int count = 0;
            for (int i = r_q - 1, j = c_q - 1; i >= 0 && j >= 0 && !obstacleSet.contains(new Pair<>(i, j)); i--, j--) {
                count++;
            }
            for (int i = r_q + 1, j = c_q + 1; i < n && j < n && !obstacleSet.contains(new Pair<>(i, j)); i++, j++) {
                count++;
            }
            for (int i = r_q + 1, j = c_q - 1; i < n && j >= 0 && !obstacleSet.contains(new Pair<>(i, j)); i++, j--) {
                count++;
            }
            for (int i = r_q - 1, j = c_q + 1; i >= 0 && j < n && !obstacleSet.contains(new Pair<>(i, j)); i--, j++) {
                count++;
            }
            return count;
        }

        private static int getColCount(int n, int r_q, int c_q, Set<Pair<Integer, Integer>> obstacleSet) {
            int count = 0;
            for (int i = r_q + 1; i < n && !obstacleSet.contains(new Pair<>(i, c_q)); i++) {
                count++;
            }
            for (int i = r_q - 1; i >= 0 && !obstacleSet.contains(new Pair<>(i, c_q)); i--) {
                count++;
            }
            return count;
        }

        private static int getRowCount(int n, int r_q, int c_q, Set<Pair<Integer, Integer>> obstacleSet) {
            int count = 0;
            for (int i = c_q + 1; i < n && !obstacleSet.contains(new Pair<>(r_q, i)); i++) {
                count++;
            }
            for (int i = c_q - 1; i >= 0 && !obstacleSet.contains(new Pair<>(r_q, i)); i--) {
                count++;
            }
            return count;
        }

        static int getRowIndex(int n, int x) {
            return (n - x);
        }

        static int getColIndex(int n, int x) {
            return x - 1;
        }

    }

    public static class Solution {
        public static void main(String[] args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

            String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            int n = Integer.parseInt(firstMultipleInput[0]);

            int k = Integer.parseInt(firstMultipleInput[1]);

            String[] secondMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            int r_q = Integer.parseInt(secondMultipleInput[0]);

            int c_q = Integer.parseInt(secondMultipleInput[1]);

            List<List<Integer>> obstacles = new ArrayList<>();

            IntStream.range(0, k).forEach(i -> {
                try {
                    obstacles.add(
                            Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                    .map(Integer::parseInt)
                                    .collect(toList())
                    );
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });

            int result = Result.queensAttack(n, k, r_q, c_q, obstacles);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();

            bufferedReader.close();
            bufferedWriter.close();
        }
    }

}
