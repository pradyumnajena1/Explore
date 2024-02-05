package hackerrank.medium;

import epp.Pair;
import epp.binaryTree.BinaryTreeNode;
import epp.binaryTree.BinaryTreeUtils;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;

public class MedianUpdates2 {


    public static List<String> medianCalculator(List<Pair<String, Long>> commands) {
        List<String> result = new ArrayList<>();
        MedianCalculator medianCalculator = new MedianCalculator();
        for (Pair<String, Long> command : commands) {
             System.out.println(command);
            if (command.getFirst().equals("a")) {
                medianCalculator.add(command.getSecond());
                Double medianValue = medianCalculator.getMedian();
                String median = getMedian(medianValue);
                result.add(median);
            } else if (command.getFirst().equals("r")) {
                boolean success = medianCalculator.remove(command.getSecond());
                if (!success || medianCalculator.size() == 0) {
                    result.add("Wrong!");
                } else {
                    Double medianValue = medianCalculator.getMedian();
                    String median = getMedian(medianValue);
                    result.add(median);
                }
            }
        }
        return result;
    }


    private static String getMedian(Double value) {
        if (Math.floor(value) == value) {
            return String.valueOf(value.intValue());
        } else {
            return String.format("%.1f", value);
        }
    }


    public static void main(String[] args) throws IOException {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */

        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

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


        bufferedWriter.write(result.stream().map(Object::toString).collect(joining(System.lineSeparator()))

        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}



