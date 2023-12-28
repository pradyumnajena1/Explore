package guidetocompetitiveprogramming;

import epp.Pair;
import epp.Triplet;
import epp.array.ArrayUtils;

import java.util.*;

public class PrimsMinSpanningTree {
    public static void main(String[] args) {
        int numNodes = 6;
        List<Triplet<Integer, Integer, Integer>> edges = new ArrayList<>();
        edges.add(new Triplet<>(5, 6, 2));
        edges.add(new Triplet<>(1, 2, 3));
        edges.add(new Triplet<>(3, 6, 3));
        edges.add(new Triplet<>(1, 5, 5));
        edges.add(new Triplet<>(2, 3, 5));
        edges.add(new Triplet<>(2, 5, 6));
        edges.add(new Triplet<>(4, 6, 7));
        edges.add(new Triplet<>(3, 4, 9));
        List<Triplet<Integer, Integer, Integer>> min = primsMinSpanningTree(6, edges);
        System.out.println(min);
        System.out.println(min.stream().mapToInt(Triplet::getThird).sum());
    }

    private static List<Triplet<Integer, Integer, Integer>> primsMinSpanningTree(int numNodes, List<Triplet<Integer, Integer, Integer>> edges) {

        List<List<Pair<Integer, Integer>>> adjList = getAdjList(numNodes, edges);
        int[] cost = new int[numNodes + 1];
        int[] parent = new int[numNodes + 1];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[1] = 0;
        Arrays.fill(parent, -1);
        PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>(Comparator.comparingInt(Pair::getSecond));
        pq.add(new Pair<>(1, 0));
        Set<Integer> mst = new HashSet<>();
        while (!pq.isEmpty()) {
            Pair<Integer, Integer> min = pq.poll();
            Integer u = min.getFirst();
            if (mst.contains(u)) {
                continue;
            }
            mst.add(u);
            if(mst.size()==numNodes){
                break;
            }
            for (Pair<Integer, Integer> pair : adjList.get(u)) {
                Integer v = pair.getFirst();
                Integer weight = pair.getSecond();
                if (!mst.contains(v) && weight < cost[v]) {
                    cost[v] = weight;
                    pq.add(new Pair<>(v, cost[v]));
                    parent[v] = u;
                }

            }
        }

        return buildMST(numNodes, adjList, parent);
    }

    private static List<Triplet<Integer, Integer, Integer>> buildMST(int numNodes, List<List<Pair<Integer, Integer>>> adjList, int[] parent) {
        List<Triplet<Integer, Integer, Integer>> selectedEdges = new ArrayList<>();
        for (int i = 2; i <= numNodes; i++) {
            int v = i;
            Pair<Integer, Integer> pair = adjList.get(parent[i]).stream().filter(x -> x.getFirst().equals(v)).findFirst().get();
            selectedEdges.add(new Triplet<>(parent[i], i, pair.getSecond()));
        }
        return selectedEdges;
    }

    private static List<List<Pair<Integer, Integer>>> getAdjList(int numNodes, List<Triplet<Integer, Integer, Integer>> edges) {
        List<List<Pair<Integer, Integer>>> adjList = new ArrayList<>();
        for (int i = 0; i <= numNodes; i++) {
            adjList.add(new ArrayList<>());
        }
        for (Triplet<Integer, Integer, Integer> edge : edges) {
            adjList.get(edge.getFirst()).add(new Pair<>(edge.getSecond(), edge.getThird()));
            adjList.get(edge.getSecond()).add(new Pair<>(edge.getFirst(), edge.getThird()));

        }
        return adjList;
    }
}
