package hackerrank.medium;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

/**
 * Consider an undirected graph where each edge weighs 6 units.
 * Each of the nodes is labeled consecutively from 1 to n.
 * <p>
 * You will be given a number of queries. For each query, you will be given a list of edges
 * describing an undirected graph. After you create a representation of the graph, you must
 * determine and report the shortest distance to each of the other nodes from a given starting
 * position using the breadth-first search algorithm (BFS). Return an array of distances from the
 * start node in node number order. If a node is unreachable, return  for that node.
 */
public class BFS {
    public static void main(String[] args) throws IOException {
        ArrayList<List<Integer>> edges = new ArrayList<>();
        edges.add(new ArrayList<>(List.of(1, 2)));
        edges.add(new ArrayList<>(List.of(1, 3)));
       // edges.add(new ArrayList<>(List.of(4, 2)));
        System.out.println(bfs(4, 2, edges, 1));
        main2(null);
    }

    public static void main2(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, q).forEach(qItr -> {
            try {
                String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                int n = Integer.parseInt(firstMultipleInput[0]);

                int m = Integer.parseInt(firstMultipleInput[1]);

                List<List<Integer>> edges = new ArrayList<>();

                IntStream.range(0, m).forEach(i -> {
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

                int s = Integer.parseInt(bufferedReader.readLine().trim());

                List<Integer> result =  bfs(n, m, edges, s);

                bufferedWriter.write(
                        result.stream()
                                .map(Object::toString)
                                .collect(joining(" "))
                                + "\n"
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }

    public static List<Integer> bfs(int n, int m, List<List<Integer>> edges, int s) {
        // Write your code here

        Queue<Integer> bfsQueue = new ArrayDeque<>();
        Set<Integer> visitedSet = new HashSet<>();
        Map<Integer, Integer> parentmap = new HashMap<>();

        bfsQueue.offer(s);
        visitedSet.add(s);
        parentmap.put(s, null);
        while (!bfsQueue.isEmpty()) {
            Integer polled = bfsQueue.poll();
            List<Integer> neighbours = getNeighbours(edges, polled);
            for (int neighbour : neighbours) {
                if (!visitedSet.contains(neighbour)) {
                    visitedSet.add(neighbour);
                    bfsQueue.offer(neighbour);
                    parentmap.put(neighbour, polled);
                }
            }
        }
        List<Integer> path = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i != s) {
                Integer pathLength = getPathLength(parentmap, i, s);
                if (pathLength != -1) {

                    path.add(6 * pathLength);
                } else {
                    path.add(-1);
                }
            }
        }
        return path;

    }

    private static Integer getPathLength(Map<Integer, Integer> parentmap, int i, int s) {
        Integer current = i;
        int pathLength = 0;
        while (current!=null && current != s   ) {
            current = parentmap.get(current);

            pathLength++;
        }
        return  current==null?-1: pathLength;
    }

    private static List<Integer> getNeighbours(List<List<Integer>> edges, Integer polled) {
        List<Integer> neighbours = new ArrayList<>();
        for (List<Integer> edge : edges) {
            if (edge.get(0).intValue() == polled.intValue()) {
                neighbours.add(edge.get(1));
            } else if (edge.get(1).intValue() == polled.intValue()) {
                neighbours.add(edge.get(0));
            }
        }
        return neighbours;
    }
}
