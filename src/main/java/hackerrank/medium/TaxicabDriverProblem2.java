package hackerrank.medium;

import epp.Pair;
import epp.array.ArrayUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class TaxicabDriverProblem2 {
    public static void main2(String[] args) {
        ArrayList<List<Integer>> junctions = new ArrayList<>(List.of(List.of(0, 0), List.of(1, 1), List.of(2, 0)));
        ArrayList<List<Integer>> edges = new ArrayList<>(List.of(List.of(1, 2), List.of(2, 3)));
        System.out.println(solve(2, 1, junctions, edges));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        long h = Long.parseLong(firstMultipleInput[1]);

        long v = Long.parseLong(firstMultipleInput[2]);

        List<List<Integer>> junctions = new ArrayList<>();

        IntStream.range(0, n).forEach(i -> {
            try {
                junctions.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<List<Integer>> edges = new ArrayList<>();

        IntStream.range(0, n - 1).forEach(i -> {
            try {
                edges.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int result = solve(h, v, junctions, edges);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }



    public static int solve(long h, long v, List<List<Integer>> junctions, List<List<Integer>> edges) {
        int numVertices = junctions.size();

        Pair<Long, Long>[][] dist = new Pair[numVertices + 1][numVertices + 1];
        for (int i = 1; i <= numVertices; i++) {
            for (int j = 1; j <= numVertices; j++) {
                if(i==j){
                    dist[i][j] = new Pair<>(0l, 0l);
                }else{

                    dist[i][j] = new Pair<>(Long.MAX_VALUE, Long.MAX_VALUE);
                }
            }
        }
        for (List<Integer> edge : edges) {
            Integer source = edge.get(0);
            Integer dest = edge.get(1);
            List<Integer> sourceJunction = junctions.get(source - 1);
            List<Integer> destJunction = junctions.get(dest - 1);
            Integer sourceX = sourceJunction.get(0);
            Integer destX = destJunction.get(0);
            Integer sourceY = sourceJunction.get(1);
            Integer destY = destJunction.get(1);
            Pair<Long, Long> distance = new Pair<>((long) Math.abs(sourceX - destX), (long) Math.abs(sourceY - destY));
            dist[source][dest] = distance;
            dist[dest][source] = distance;
        }
        //ArrayUtils.print2DArray(dist);
        for (int k = 1; k <= numVertices; k++) {
            System.out.println(k);
            for (int i = 1; i <= numVertices; i++) {
                for (int j = 1; j <= numVertices; j++) {

                    if ((dist[i][k].getFirst() != Long.MAX_VALUE && dist[k][j].getFirst() != Long.MAX_VALUE && dist[i][k].getFirst() + dist[k][j].getFirst() < dist[i][j].getFirst()) && (dist[i][k].getSecond() != Long.MAX_VALUE && dist[k][j].getSecond() != Long.MAX_VALUE && dist[i][k].getSecond() + dist[k][j].getSecond() < dist[i][j].getSecond()))
                        dist[i][j] = new Pair<>(dist[i][k].getFirst() + dist[k][j].getFirst(), dist[i][k].getSecond() + dist[k][j].getSecond());
                }
            }
        }
       // ArrayUtils.print2DArray(dist);
        int numInvalid = 0;
        for (int i = 1; i <= numVertices; i++) {
            for (int j = 1; j <= numVertices; j++) {
                if (dist[i][j].getFirst() > h  || dist[i][j].getSecond() > v) {
                    numInvalid++;
                }
            }
        }
        return numInvalid/2;
    }
}
