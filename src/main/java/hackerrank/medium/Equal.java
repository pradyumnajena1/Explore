package hackerrank.medium;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Equal {

    public static int equal(List<Integer> arr) {
        // Write your code here
        int minValue = arr.stream().min(Comparator.comparingInt(Integer::intValue)).get();
        int minOps = Integer.MAX_VALUE;
        for (int i = 0; i <= 4; i++) {

            int currentMinOp = 0;
            for (int value : arr) {
                int diff = value - minValue;
                int ops = diff / 5 + diff % 5 / 2 + diff % 5 % 2;
                currentMinOp = currentMinOp + ops;

            }

        }

        return minOps;
    }

    public static class Solution {
        public static void main(String[] args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

            int t = Integer.parseInt(bufferedReader.readLine().trim());

            for (int tItr = 0; tItr < t; tItr++) {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                String[] arrTemp = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                List<Integer> arr = new ArrayList<>();

                for (int i = 0; i < n; i++) {
                    int arrItem = Integer.parseInt(arrTemp[i]);
                    arr.add(arrItem);
                }

                int result = Equal.equal(arr);

                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            }

            bufferedReader.close();
            bufferedWriter.close();
        }
    }
}
