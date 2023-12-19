package hackerrank.medium;

import epp.Pair;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class TaxicabDriverProblem {
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
                junctions.add(Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" ")).map(Integer::parseInt).collect(toList()));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<List<Integer>> edges = new ArrayList<>();

        IntStream.range(0, n - 1).forEach(i -> {
            try {
                edges.add(Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" ")).map(Integer::parseInt).collect(toList()));
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
        // Write your code here
        Map<Integer, List<Integer>> adjMatrix = new HashMap<>();
        for (List<Integer> edge : edges) {
            Integer source = edge.get(0);
            Integer dest = edge.get(1);
            List<Integer> neighs = adjMatrix.getOrDefault(source, new ArrayList<>());
            neighs.add(dest);
            adjMatrix.put(source, neighs);

            neighs = adjMatrix.getOrDefault(dest, new ArrayList<>());
            neighs.add(source);
            adjMatrix.put(dest, neighs);
        }

        int invalidCount = 0;
        for (int i = 1; i <= junctions.size(); i++) {

            int invalidPairs = singleSourceShortestPath(h, v, junctions, adjMatrix, i);

            invalidCount += invalidPairs;


        }

        return invalidCount / 2;
    }


    private static int singleSourceShortestPath(long horizontal, long vertical, List<List<Integer>> junctions, Map<Integer, List<Integer>> adjmatrix, int source) {
        int numVertices = junctions.size();
        Comparator<Pair<Integer, Pair<Long, Long>>> pairComparator = Comparator.comparingLong((Pair<Integer, Pair<Long, Long>> x) -> x.getSecond().getFirst()).thenComparingLong(x -> x.getSecond().getSecond());
        PriorityQueue<Pair<Integer, Pair<Long, Long>>> priorityQueue = new PriorityQueue<>(pairComparator);

        Map<Integer, Pair<Long, Long>> distance = new HashMap<>();
        Map<Pair<Integer, Integer>, Pair<Long, Long>> numJunctionPairs = new HashMap<>();
        for (int j = 1; j <= numVertices; j++) {
            distance.put(j, new Pair<>(Long.MAX_VALUE, Long.MAX_VALUE));
        }
        distance.put(source, new Pair<>(0l, 0l));
        priorityQueue.offer(new Pair<>(source, new Pair<>(0l, 0l)));


        while (!priorityQueue.isEmpty()) {

            int u = priorityQueue.poll().getFirst();
            for (int v : adjmatrix.get(u)) {
                List<Integer> sourceJunction = junctions.get(u - 1);
                List<Integer> destinationJunction = junctions.get(v - 1);

                int xdist = (int) Math.abs(sourceJunction.get(0) - destinationJunction.get(0));
                int ydist = (int) Math.abs(sourceJunction.get(1) - destinationJunction.get(1));

                Long sourceX = distance.get(u).getFirst();
                Long destX = distance.get(v).getFirst();
                Long sourceY = distance.get(u).getSecond();
                Long destY = distance.get(v).getSecond();

                if (sourceX != Long.MAX_VALUE && destX > sourceX + xdist && sourceY != Long.MAX_VALUE && destY > sourceY + ydist) {
                    Pair<Long, Long> newDistance = new Pair<>(sourceX + xdist, sourceY + ydist);
                    distance.put(v, newDistance);
                    priorityQueue.offer(new Pair<>(v, newDistance));
                }


            }

        }
        int invalidCount = 0;
        for (Map.Entry<Integer, Pair<Long, Long>> entry : distance.entrySet()) {
            if (entry.getValue().getFirst() > horizontal || entry.getValue().getSecond() > vertical) {
                invalidCount++;
            }
        }
        return invalidCount;
    }
}
