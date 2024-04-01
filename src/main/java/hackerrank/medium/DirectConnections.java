package hackerrank.medium;

import java.io.*;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class DirectConnections {
    private static final int mod = 1000000007;

    public static long solve(int n, List<Integer> cordinates, List<Integer> population) {
        // Write your code here
        List<Integer> sortedPopulation = IntStream.range(0, population.size()).boxed().sorted(Comparator.comparingInt(population::get)).collect(toList());
        Set<Integer> set = new HashSet<>();
        long totalWire = 0;
        for (int index : sortedPopulation) {
            Integer currentPop = population.get(index);
            Integer currentLoc = cordinates.get(index);

            for (int loc : set) {
                long cont;
                if (loc < currentLoc) {
                    cont = ((currentLoc - loc) * (long) currentPop) % mod;
                } else {
                    cont = ((loc - currentLoc) * (long) currentPop) % mod;
                }
                totalWire = (totalWire + cont) % mod;

            }
            set.add(currentLoc);
        }
        return totalWire;
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
                long result = solve(arrCount, cords, populations);

                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }

    static class TreeNode implements Comparable<TreeNode> {
        int data;
        int leftSum;
        int leftCount;
        TreeNode left, right;

        public TreeNode(int data) {
            this.data = data;
        }

        public TreeNode(int data, TreeNode left, TreeNode right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(TreeNode o) {
            return Integer.compare(data, o.data);
        }
    }


}
