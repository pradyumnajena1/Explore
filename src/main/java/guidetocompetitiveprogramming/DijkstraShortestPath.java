package guidetocompetitiveprogramming;

import epp.Pair;
import epp.Triplet;
import epp.array.ArrayUtils;

import java.util.*;

public class DijkstraShortestPath {
    public static void main(String[] args) {
        int numNodes = 9;
        List<List<Pair<Integer, Integer>>> adjList = new ArrayList<>();
        for (int i = 0; i < numNodes; i++) {
            adjList.add(new ArrayList<>());
        }

        addEdge(adjList, new Triplet<>(0, 1, 4));
        addEdge(adjList, new Triplet<>(0, 7, 8));
        addEdge(adjList, new Triplet<>(1, 2, 8));
        addEdge(adjList, new Triplet<>(1, 7, 11));
        addEdge(adjList, new Triplet<>(2, 3, 7));
        addEdge(adjList, new Triplet<>(2, 8, 2));
        addEdge(adjList, new Triplet<>(2, 5, 4));
        addEdge(adjList, new Triplet<>(3, 4, 9));
        addEdge(adjList, new Triplet<>(3, 5, 14));
        addEdge(adjList, new Triplet<>(4, 5, 10));
        addEdge(adjList, new Triplet<>(5, 6, 2));
        addEdge(adjList, new Triplet<>(6, 7, 1));
        addEdge(adjList, new Triplet<>(6, 8, 6));
        addEdge(adjList, new Triplet<>(7, 8, 7));
        int[] distance = singleSourceShortestPath(adjList, 0);
        ArrayUtils.printArray(distance);

    }

    private static int[] singleSourceShortestPath(List<List<Pair<Integer, Integer>>> adjList, int source) {
        int[] distance = new int[adjList.size()];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;
        PriorityQueue<Pair<Integer, Integer>> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(Pair::getFirst));
        Set<Integer> processed = new HashSet<>();
        priorityQueue.offer(new Pair<>(0, source));
        while (!priorityQueue.isEmpty()) {
            Pair<Integer, Integer> min = priorityQueue.poll();

            if (processed.contains(min.getSecond())) {
                continue;
            }
            processed.add(min.getSecond());

            int u = min.getSecond();
            for (Pair<Integer, Integer> edge : adjList.get(min.getSecond())) {
                int v = edge.getFirst();
                int w = edge.getSecond();
                if (distance[u] != Integer.MAX_VALUE && distance[u] + w < distance[v]) {
                    distance[v] = distance[u] + w;
                    priorityQueue.offer(new Pair<>(distance[v], v));
                }
            }

        }
        return distance;
    }

    private static void addEdge(List<List<Pair<Integer, Integer>>> adjList, Triplet<Integer, Integer, Integer> edge) {
        Integer u = edge.getFirst();
        Integer v = edge.getSecond();
        Integer w = edge.getThird();
        adjList.get(u).add(new Pair<>(v, w));
        adjList.get(v).add(new Pair<>(u, w));
    }
}
