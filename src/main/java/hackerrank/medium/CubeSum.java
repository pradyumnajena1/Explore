package hackerrank.medium;

import guidetocompetitiveprogramming.SegmentTree;
import guidetocompetitiveprogramming.SegmentTreeLong;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class CubeSum {


    public static List<Long> cubeSum(int n, List<String> operations) {
        // Write your code here
          int mod = (int) (Math.pow(10,9)+7);
        SegmentTreeLong[][] segmentTrees = new SegmentTreeLong[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                segmentTrees[i][j] = new SegmentTreeLong(new long[n + 1], (x,y)-> (x+y)  , 0);
            }
        }
        List<Long> result = new ArrayList<>();
        for (String op : operations) {
            String[] multipleInput = op.replaceAll("\\s+$", "").split(" ");
            if (multipleInput[0].equals("UPDATE")) {
                int x = Integer.parseInt(multipleInput[1]);
                int y = Integer.parseInt(multipleInput[2]);
                int z = Integer.parseInt(multipleInput[3]);
                int value = Integer.parseInt(multipleInput[4]);
                segmentTrees[x][y].set(z, value);
                segmentTrees[x][y].printTree();
            } else if (multipleInput[0].equals("QUERY")) {
                int x1 = Integer.parseInt(multipleInput[1]);
                int y1 = Integer.parseInt(multipleInput[2]);
                int z1 = Integer.parseInt(multipleInput[3]);
                int x2 = Integer.parseInt(multipleInput[4]);
                int y2 = Integer.parseInt(multipleInput[5]);
                int z2 = Integer.parseInt(multipleInput[6]);
                long sum = 0;
                for (int x = x1; x <= x2; x++) {
                    for (int y = y1; y <= y2; y++) {
                        sum = (sum+ segmentTrees[x][y].rangeResult(z1, z2)) ;
                    }
                }
                result.add( sum);
            }

        }
        return result;
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

                List<Long> res =  cubeSum(matSize, ops);

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
