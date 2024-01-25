package hackerrank.medium;

import guidetocompetitiveprogramming.PrimeFactors;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class DownToZero {
    public static void main2(String[] args) {
        System.out.println(downToZero(4));
        System.out.println(downToZero(3));
        System.out.println(downToZero(966514));
        System.out.println(downToZero(812849));
        System.out.println(downToZero(808707));
    }

    public static int downToZero(int n) {
        // Write your code here
        Queue<Integer> bfsQueue = new ArrayDeque<>();
        Set<Integer> visitedSet = new HashSet<>();
        Map<Integer, Integer> parent = new HashMap<>();
        bfsQueue.offer(n);
        visitedSet.add(n);
        parent.put(n, -1);
        while (!bfsQueue.isEmpty()) {
            Integer polled = bfsQueue.poll();
            if (polled == 0) {
                break;
            }
            List<Integer> allFactors = new ArrayList<>(PrimeFactors.getAllFactors(polled));
            // System.out.println(allFactors);
            if (allFactors.size() > 2) {
                int left = 1;
                int right = allFactors.size() - 2;
                while (left <= right) {
                    int factor = allFactors.get(right);
                    if (!visitedSet.contains(factor)) {

                        visitedSet.add(factor);
                        bfsQueue.offer(factor);
                        parent.put(factor, polled);

                    }
                    left++;
                    right--;
                }
            }
            if (!visitedSet.contains(polled - 1)) {
                visitedSet.add(polled - 1);
                bfsQueue.offer(polled - 1);
                parent.put(polled - 1, polled);
            }


        }
       // System.out.println(parent);
        int current = 0;
        int count = 0;
        while (current != n) {
            int p = parent.get(current);
           // System.out.println(current + " " + p);
            count++;
            current = p;
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, q).forEach(qItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                int result = downToZero(n);

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
