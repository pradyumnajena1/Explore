package hackerrank.medium;

import epp.Triplet;
import epp.array.ArrayUtils;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class DirectConnections {
    private static final int mod = 1000000007;

    public static int solve(int n, List<Integer> cordinates, List<Integer> population) {
        // Write your code here
        List<Triplet<Integer, Integer, Integer>> list = new ArrayList<>();
        int total = 0;
        for (int i = 0; i < n; i++) {
            Triplet<Integer, Integer, Integer> triplet = new Triplet<>(population.get(i), cordinates.get(i), i);
            list.add(triplet);
        }
        list.sort(Comparator.comparingInt(Triplet::getSecond));

        int[] cables = new int[n];
        mergeSort(list, cables);


        for (int i = 0; i < cables.length; i++) {
            total = (total + cables[i]) % mod;
        }
        return total;
    }


    private static List<Triplet<Integer, Integer, Integer>> mergeSort(List<Triplet<Integer, Integer, Integer>> list, int[] cables) {
        if (list.size() <= 1) {
            return list;
        }

        int mid = list.size() / 2;
        List<Triplet<Integer, Integer, Integer>> left = list.subList(0, mid);
        List<Triplet<Integer, Integer, Integer>> right = list.subList(mid, list.size());
        left = mergeSort(left, cables);
        right = mergeSort(right, cables);
        return merge(left, right, cables);
    }

    private static List<Triplet<Integer, Integer, Integer>> merge(List<Triplet<Integer, Integer, Integer>> left, List<Triplet<Integer, Integer, Integer>> right, int[] cables) {
        List<Triplet<Integer, Integer, Integer>> result = new ArrayList<>(Collections.nCopies(left.size() + right.size(), null));

        List<Integer> cumLeft = getCumlativeSum(left);
        List<Integer> cumRight = getCumlativeSum(right);

        int leftIndex = left.size() - 1;
        int rightIndex = right.size() - 1;
        int writeIndex = result.size() - 1;
        while (leftIndex >= 0 && rightIndex >= 0) {

            if (left.get(leftIndex).getFirst() >= right.get(rightIndex).getFirst()) {
                Triplet<Integer, Integer, Integer> selectedLeft = left.get(leftIndex--);

                int distance = modulo(Math.abs(modulo(cumRight.get(rightIndex) - modulo ((rightIndex + 1) * selectedLeft.getSecond()))));
                cables[selectedLeft.getThird()] = modulo(cables[selectedLeft.getThird()] + modulo(selectedLeft.getFirst() * distance));

                result.set(writeIndex--, selectedLeft);

            } else {

                Triplet<Integer, Integer, Integer> selectedRight = right.get(rightIndex--);

                int distance = modulo(Math.abs(modulo((leftIndex + 1) * selectedRight.getSecond()) - cumLeft.get(leftIndex)));
                cables[selectedRight.getThird()] = modulo(cables[selectedRight.getThird()] + modulo(selectedRight.getFirst() * distance));

                result.set(writeIndex--, selectedRight);

            }
        }
        while (leftIndex >= 0) {
            result.set(writeIndex--, left.get(leftIndex--));
        }
        while (rightIndex >= 0) {
            result.set(writeIndex--, right.get(rightIndex--));
        }


        return result;
    }

    private static List<Integer> getCumlativeSum(List<Triplet<Integer, Integer, Integer>> list) {
        List<Integer> cumulative = new ArrayList<>();
        cumulative.add(list.get(0).getSecond());
        for (int i = 1; i < list.size(); i++) {
            int value = modulo(cumulative.get(i - 1) + list.get(i).getSecond());
            cumulative.add(value);
        }
        return cumulative;
    }

    static int modulo(int value) {
        return value % mod;
    }


    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int arrCount = Integer.parseInt(bufferedReader.readLine().trim());

                List<Integer> cords = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList());
                List<Integer> populations = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList());
                int result = solve(arrCount, cords, populations);

                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }

    private static void bruteforce(List<Triplet<Integer, Integer, Integer>> list, int[] cables) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).getFirst() >= list.get(j).getFirst()) {
                    cables[list.get(i).getThird()] = modulo(cables[list.get(i).getThird()] + modulo(Math.abs(list.get(i).getSecond() - list.get(j).getSecond()) * list.get(i).getFirst()));
                } else {
                    cables[list.get(j).getThird()] = modulo(cables[list.get(j).getThird()] + modulo(Math.abs(list.get(j).getSecond() - list.get(i).getSecond()) * list.get(j).getFirst()));
                }
            }
        }
    }
}
