package hackerrank.medium;

import guidetocompetitiveprogramming.SegmentTree;

import java.io.*;
import java.util.List;
import java.util.NavigableMap;
import java.util.OptionalInt;
import java.util.TreeMap;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ArrayPairs {
    private static class Result {

        public static long solve2(List<Integer> arr) {
            // Write your code here


            long totalCount = 0;
            for (int i = 1; i < arr.size(); i++) {
                int count = 0;
                for (int j = 0; j < i; j++) {
                    OptionalInt max = arr.subList(j, i + 1).stream().mapToInt(Integer::intValue).max();
                    if (arr.get(j) * arr.get(i) <= max.getAsInt()) {
                        count++;
                    }
                }
                System.out.println(i + " " + count);
                totalCount+=count;
            }








            return totalCount;
        }
        public static long solve(List<Integer> arr) {
            // Write your code here
            TreeMap<Integer, Long> frequencyTree = new TreeMap<>();
            frequencyTree.put(arr.get(0),1L);

            long totalCount = 0;
            for (int i = 1; i < arr.size(); i++) {
                if(i==956){
                    System.out.println();
                }
                Integer a = arr.get(i);
                frequencyTree.put(a, frequencyTree.getOrDefault(a, 0L) + 1);

                Integer max = frequencyTree.lastKey();
                int b = max / a;
                NavigableMap<Integer, Long> headMap = frequencyTree.headMap(b, true);
                long pairCount = headMap.values().stream().mapToLong(Long::longValue).sum();
                if (a <= b) {

                    pairCount = pairCount - 1;
                }

                System.out.println(i +" "+ pairCount);
                totalCount += pairCount;


            }

            return totalCount;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int arrCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        long result2 = Result.solve2(arr);
        long result = Result.solve(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();
        bufferedWriter.write(String.valueOf(result2));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
