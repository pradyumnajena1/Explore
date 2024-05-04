package hackerrank.easy;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;

public class ManasaAndStones {

    static class Result {

        /*
         * Complete the 'stones' function below.
         *
         * The function is expected to return an INTEGER_ARRAY.
         * The function accepts following parameters:
         *  1. INTEGER n
         *  2. INTEGER a
         *  3. INTEGER b
         */

        public static List<Integer> stones(int n, int a, int b) {
            // Write your code here
            Set<Integer> currentChoices = Set.of(0);
            for (int i = 1; i < n; i++) {
                Set<Integer> newChoices = new HashSet<>();
                for (int choice : currentChoices) {
                    newChoices.add(choice + a);
                    newChoices.add(choice + b);
                }
                currentChoices = newChoices;
            }
            ArrayList<Integer> result = new ArrayList<>(currentChoices);
            result.sort(Integer::compareTo);
            return result;
        }

    }

    public static class Solution {
        public static void main(String[] args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

            int T = Integer.parseInt(bufferedReader.readLine().trim());

            IntStream.range(0, T).forEach(TItr -> {
                try {
                    int n = Integer.parseInt(bufferedReader.readLine().trim());

                    int a = Integer.parseInt(bufferedReader.readLine().trim());

                    int b = Integer.parseInt(bufferedReader.readLine().trim());

                    List<Integer> result = Result.stones(n, a, b);

                    bufferedWriter.write(
                            result.stream()
                                    .map(Object::toString)
                                    .collect(joining(" "))
                                    + "\n"
                    );
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });

            bufferedReader.close();
            bufferedWriter.close();
        }
    }

}
