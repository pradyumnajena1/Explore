package amaze;

import java.util.*;

public class DetectCycleInGraph {
    public static void main(String[] args) {
        List<List<Integer>> edges = new ArrayList<>();
        edges.add(new ArrayList<>(List.of(0, 1)));
        edges.add(new ArrayList<>(List.of(0, 2)));
        edges.add(new ArrayList<>(List.of(1, 2)));
        edges.add(new ArrayList<>(List.of(2, 0)));
        edges.add(new ArrayList<>(List.of(2, 3)));
        edges.add(new ArrayList<>(List.of(3, 3)));

        boolean isCyclic = checkCycle(edges, 3);
    }

    private static boolean checkCycle(List<List<Integer>> edges, int maxNode) {
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i <= maxNode; i++) {
            adjList.add(new ArrayList<>());
        }
        for (List<Integer> edge : edges) {
            adjList.get(edge.get(0)).add(edge.get(1));
        }
        Set<Integer> visitedSet = new HashSet<>();
        Map<Integer, Boolean> path = new HashMap<>();
        for (int i = 0; i <= maxNode; i++) {
            if (!visitedSet.contains(i)) {
                if (checkCycle(adjList, i, visitedSet, path)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean checkCycle(List<List<Integer>> adjList, int root, Set<Integer> visitedSet, Map<Integer, Boolean> path) {
        if (path.get(root)) {
            return true;
        }
        if (visitedSet.contains(root)) {
            return false;
        }
        visitedSet.add(root);
        path.put(root, true);
        for (int node : adjList.get(root)) {
            if (checkCycle(adjList, node, visitedSet, path)) {
                return true;
            }
        }
        path.put(root, false);
        return false;
    }
}
