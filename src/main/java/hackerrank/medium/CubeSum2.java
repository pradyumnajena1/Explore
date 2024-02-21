package hackerrank.medium;

import guidetocompetitiveprogramming.SegmentTreeLong;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class CubeSum2 {


    public static List<Long> cubeSum(int n, List<String> operations) {
        // Write your code here
        int mod = (int) (Math.pow(10, 9) + 7);
        SegmentTreeLong segmentTrees = new SegmentTreeLong(new long[(int) Math.pow(n, 3)], (x, y) -> (x + y), 0);

        List<Long> result = new ArrayList<>();
        for (String op : operations) {
            String[] multipleInput = op.replaceAll("\\s+$", "").split(" ");
            if (multipleInput[0].equals("UPDATE")) {
                int x = Integer.parseInt(multipleInput[1]) - 1;
                int y = Integer.parseInt(multipleInput[2]) - 1;
                int z = Integer.parseInt(multipleInput[3]) - 1;
                int value = Integer.parseInt(multipleInput[4]);
                segmentTrees.set(getIndex(n, x, y, z), value);

            } else if (multipleInput[0].equals("QUERY")) {
                int x1 = Integer.parseInt(multipleInput[1])-1;
                int y1 = Integer.parseInt(multipleInput[2])-1;
                int z1 = Integer.parseInt(multipleInput[3])-1;
                int x2 = Integer.parseInt(multipleInput[4])-1;
                int y2 = Integer.parseInt(multipleInput[5])-1;
                int z2 = Integer.parseInt(multipleInput[6])-1;

                long sum = segmentTrees.rangeResult(getIndex(n, x1, y1, z1), getIndex(n, x2, y2, z2));
                result.add(sum);
            }

        }
        return result;
    }

    private static int getIndex(int size, int x, int y, int z) {
        return (size * size) * x + (size * y) + z;
    }


    public static void main(String[] args) throws IOException {


        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(System.getenv("INPUT_PATH"))));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int T = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, T).forEach(TItr -> {
            try {
                String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                int matSize = Integer.parseInt(firstMultipleInput[0]);

                int m = Integer.parseInt(firstMultipleInput[1]);

                List<String> ops = IntStream.range(0, m).mapToObj(i -> {
                            try {
                                return bufferedReader.readLine();
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        })
                        .collect(toList());

                List<Long> res = cubeSum(matSize, ops);

                bufferedWriter.write(
                        res.stream()
                                .map(Object::toString)
                                .collect(joining("\n"))
                                + "\n"
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
