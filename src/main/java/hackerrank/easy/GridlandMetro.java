package hackerrank.easy;

import epp.Pair;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class GridlandMetro {
    static class Result {

        /*
         * Complete the 'gridlandMetro' function below.
         *
         * The function is expected to return an INTEGER.
         * The function accepts following parameters:
         *  1. INTEGER n
         *  2. INTEGER m
         *  3. INTEGER k
         *  4. 2D_INTEGER_ARRAY track
         */

        public static long gridlandMetro(int n, int m, int k, List<List<Integer>> tracks) {
            // Write your code here
            System.out.println(System.currentTimeMillis());
            Map<Integer, List<Pair<Integer, Integer>>> trackMap = new HashMap<>();
            for (List<Integer> track : tracks) {
                Integer row = track.get(0);
                List<Pair<Integer, Integer>> list = trackMap.getOrDefault(row, new ArrayList<>());
                list.add(new Pair<>(track.get(1), track.get(2)));
                trackMap.put(row, list);
            }
            System.out.println(System.currentTimeMillis());
            long count = 0;
            for (int row:trackMap.keySet()) {
                List<Pair<Integer, Integer>> trackList = trackMap.getOrDefault(row, new ArrayList<>());
                long sum = 0;
                if (trackList.size() > 0) {
                    trackList.sort(Comparator.comparingInt(x -> x.getFirst()));
                    List<Pair<Integer, Integer>> trackList2 = new ArrayList<>();
                    trackList2.add(trackList.get(0));
                    for (int j = 1; j < trackList.size(); j++) {
                        Pair<Integer, Integer> current = trackList.get(j);
                        Pair<Integer, Integer> last = trackList2.get(trackList2.size() - 1);
                        if (current.getFirst() <= last.getSecond()) {
                            last.setSecond(Math.max(last.getSecond(), current.getSecond()));
                        } else {
                            trackList2.add(current);
                        }
                    }
                     sum = trackList2.stream().mapToLong(x -> x.getSecond() - x.getFirst() + 1).sum();
                }
                count += m - sum;
            }
            count+= (long) m * (n- trackMap.keySet().size());
            System.out.println(System.currentTimeMillis());
            return count;
        }


    }

    public static class Solution {
        public static void main(String[] args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

            String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            int n = Integer.parseInt(firstMultipleInput[0]);

            int m = Integer.parseInt(firstMultipleInput[1]);

            int k = Integer.parseInt(firstMultipleInput[2]);

            List<List<Integer>> track = new ArrayList<>();

            IntStream.range(0, k).forEach(i -> {
                try {
                    track.add(
                            Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                    .map(Integer::parseInt)
                                    .collect(toList())
                    );
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });

            long result = Result.gridlandMetro(n, m, k, track);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();

            bufferedReader.close();
            bufferedWriter.close();
        }
    }

}
