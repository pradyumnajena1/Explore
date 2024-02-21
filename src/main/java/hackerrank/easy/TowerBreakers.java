package hackerrank.easy;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class TowerBreakers {
    static class Result {

        /*
         * Complete the 'towerBreakers' function below.
         *
         * The function is expected to return an INTEGER.
         * The function accepts following parameters:
         *  1. INTEGER n
         *  2. INTEGER m
         */

        public static int towerBreakers(int n, int m) {
            // Write your code here
            Map<Integer, Integer> count = new HashMap<>();
            count.put(m, n);

            return isWinningPosition(count, n) ? 1 : 2;
        }

        private static boolean isWinningPosition(Map<Integer, Integer> count, int n) {

            if (count.containsKey(1) && count.get(1) == n) {
                return false;
            }
            Map<Integer, Integer> countCopy = new HashMap<>(count);
            for (int key : count.keySet()) {
                if (key != 1) {
                    if (key % 2 == 0) {
                        int newKey = key / 2;
                        moveCount(countCopy,key,newKey,1);
                        if (!isWinningPosition(countCopy, n)) {
                            return true;
                        }
                        moveCount(countCopy,newKey,key,1);
                    }
                    System.out.println("before"+ countCopy);
                    int newKey = 1;
                    int newValue = count.get(key)%2;

                    moveCount(countCopy,key,newKey,count.get(key)-newValue);
                    System.out.println("after"+countCopy);
                    if (!isWinningPosition(countCopy, n)) {
                        return true;
                    }
                }
            }
            return false;
        }

        private static void moveCount(Map<Integer, Integer> count, int fromKey,int toKey,int value) {
             incrementCount(count,toKey,value);
             decrementCount(count,fromKey,value);
        }
        private static void incrementCount(Map<Integer, Integer> count, int newKey,int value) {
            count.put(newKey, count.getOrDefault(newKey, 0) + value);
        }

        private static void decrementCount(Map<Integer, Integer> count, int key,int value) {
            Integer currentCount = count.getOrDefault(key, 0);
            if (currentCount > value) {
                count.put(key, currentCount - value);
            } else {
                count.remove(key);
            }
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

                    int n = Integer.parseInt(firstMultipleInput[0]);

                    int m = Integer.parseInt(firstMultipleInput[1]);

                    int result = Result.towerBreakers(n, m);

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
