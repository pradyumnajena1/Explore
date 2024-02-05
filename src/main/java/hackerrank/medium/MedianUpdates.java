package hackerrank.medium;

import epp.Pair;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class MedianUpdates {

    public static List<String> medianCalculator(List<Pair<String, Long>> commands) {
        List<String> result = new ArrayList<>();
        TreeSet<Pair<Long, Integer>> tree = new TreeSet<>(Comparator.comparingLong((Pair<Long, Integer> x) -> x.getFirst()).thenComparingInt(Pair::getSecond));
        for (int i = 0; i < commands.size(); i++) {
            Pair<String, Long> command = commands.get(i);

            if (command.getFirst().equals("a")) {
                tree.add(new Pair<>(command.getSecond(), i));
                String median = getMedian(tree);
                result.add(median);

            } else if (command.getFirst().equals("r")) {

                SortedSet<Pair<Long, Integer>> sortedSet = tree.subSet(new Pair<>(command.getSecond(), 0), new Pair<>(command.getSecond(), i));
                if (!sortedSet.isEmpty()) {
                    sortedSet.remove(sortedSet.first());
                    if (!tree.isEmpty()) {
                        String median = getMedian(tree);
                        result.add(median);
                    } else {
                        result.add("Wrong!");
                    }
                } else {
                    result.add("Wrong!");
                }


            }

        }
        return result;
    }

    private static String getMedian(TreeSet<Pair<Long, Integer>> tree) {
        int size = tree.size();
        Iterator<Pair<Long, Integer>> iterator = tree.iterator();
        int n = 1;
        long sum = 0;
        if (size % 2 == 0) {
            while (iterator.hasNext()) {
                Pair<Long, Integer> next = iterator.next();
                if (n == size / 2) {
                    sum += next.getFirst();
                }
                if (n == size / 2 + 1) {
                    sum += next.getFirst();
                    break;
                }
                n++;
            }
            if (sum % 2 == 0) {
                return String.valueOf(sum / 2);
            } else {
                double d = ((double) sum) / 2;
                return String.format("%.1f", d);
            }
        } else {

            while (iterator.hasNext()) {
                Pair<Long, Integer> next = iterator.next();
                if (n == size / 2 + 1) {
                    sum += next.getFirst();
                    break;
                }
                n++;
            }
            return String.valueOf(sum);
        }

    }

    public static void main(String[] args) throws IOException {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */

        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);
        List<Pair<String, Long>> commands = new ArrayList<>();
        IntStream.range(0, n).forEach(iter -> {
                    try {
                        String[] s = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
                        Pair<String, Long> pair = new Pair<String, Long>(s[0], Long.parseLong(s[1]));
                        commands.add(pair);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }

        );


        List<String> result = medianCalculator(commands);


        bufferedWriter.write(
                result.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}



