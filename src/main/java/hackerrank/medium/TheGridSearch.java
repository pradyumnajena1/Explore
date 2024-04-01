package hackerrank.medium;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class TheGridSearch {
    static class Result {

        /*
         * Complete the 'gridSearch' function below.
         *
         * The function is expected to return a STRING.
         * The function accepts following parameters:
         *  1. STRING_ARRAY G
         *  2. STRING_ARRAY P
         */

        public static String gridSearch(List<String> G, List<String> P) {
            // Write your code here

            int patternLength = P.get(0).length();
            List<List<Integer>> pattern_hashes = new ArrayList<>();
            for (int i = 0; i < P.size(); i++) {
                List<Integer> collect =
                        P.get(i).substring(0, patternLength).chars().mapToObj(x -> x - '0').collect(toList());
                pattern_hashes.add(collect);
            }

            List<Deque<Integer>> hashes = initHashes(G, P, patternLength, 0);

            for (int i = 0; i + P.size() <= G.size(); i++) {
                for (int j = 0; j + patternLength <= G.get(0).length(); j++) {
                    if (i == 4 && j == 11) {
                        System.out.println();
                    }
                    if (isMatching(pattern_hashes, hashes)) {
                        return "YES";
                    }
                    if (j + patternLength < G.get(0).length()) {
                        incrementHashes(G, hashes, j, patternLength, i);
                    }
                }
                if (i + P.size() < G.size()) {
                    hashes = initHashes(G, P, patternLength, i + 1);
                }
            }
            return "NO";
        }

        private static void incrementHashes(List<String> G, List<Deque<Integer>> hashes, int outgoingColumn,
                                            int patternLength,
                                            int base) {

            int incomingColumn = outgoingColumn + patternLength;
            int offset = 0;
            while (offset < hashes.size()) {
                Deque<Integer> hash = hashes.get(offset);
                String row = G.get(base + offset);
                int charAt = row.charAt(incomingColumn) - '0';
                hash.offerLast(charAt);
                hash.pollFirst();
                offset++;
            }
        }

        private static List<Deque<Integer>> initHashes(List<String> G, List<String> P, int patternLength, int base) {
            List<Deque<Integer>> hashes = new ArrayList<>();
            int offset = 0;
            while (offset < P.size()) {
                List<Integer> collect = G.get(base + offset).substring(0, patternLength).chars().mapToObj(x -> x - '0').collect(toList());
                hashes.add(new ArrayDeque(collect));
                offset++;
            }
            return hashes;
        }

        private static boolean isMatching(List<List<Integer>> patternHashes, List<Deque<Integer>> hashes) {
            int size = hashes.size();

            for (int i = 0; i < size; i++) {
                Deque<Integer> item = hashes.get(i);

                if (!patternHashes.get(i).equals(new ArrayList<>(item))) {
                    return false;
                }

            }

            return true;
        }

    }

    public static class Solution {
        public static void main(String[] args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

            int t = Integer.parseInt(bufferedReader.readLine().trim());

            IntStream.range(0, t).forEach(tItr -> {
                try {
                    String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                    int R = Integer.parseInt(firstMultipleInput[0]);

                    int C = Integer.parseInt(firstMultipleInput[1]);

                    List<String> G = IntStream.range(0, R).mapToObj(i -> {
                                try {
                                    return bufferedReader.readLine();
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                            })
                            .collect(toList());

                    String[] secondMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                    int r = Integer.parseInt(secondMultipleInput[0]);

                    int c = Integer.parseInt(secondMultipleInput[1]);

                    List<String> P = IntStream.range(0, r).mapToObj(i -> {
                                try {
                                    return bufferedReader.readLine();
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                            })
                            .collect(toList());

                    String result = Result.gridSearch(G, P);

                    bufferedWriter.write(result);
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
