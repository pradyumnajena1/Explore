package hackerrank.medium;

import java.util.*;

public class PrimsSubTree {
    public static void main(String[] args) {
        int prims = prims(5, new ArrayList<>(List.of(List.of(1, 2, 3), List.of(1, 3, 4), List.of(4, 2, 6), List.of(5, 2, 2), List.of(2, 3, 5), List.of(3, 5, 7))), 1);
        System.out.println(prims);
    }

    public static int prims(int n, List<List<Integer>> edges, int start) {
        // Write your code here

        int[][] graph = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= n; j++) {
                graph[i][j] = -1;
            }
        }
        for (List<Integer> edge : edges) {
            graph[edge.get(0)][edge.get(1)] = edge.get(2);
            graph[edge.get(1)][edge.get(0)] = edge.get(2);
        }
        Map<Integer, Integer> keys = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            keys.put(i, Integer.MAX_VALUE);
        }
        Set<Integer> mst = new HashSet<>();

        keys.put(start, 0);
        int totalEdgeCost = 0;
        for (int i = 1; i <= n; i++) {
            int u = findMinNode(n, keys, mst);
            //System.out.println(u);
            totalEdgeCost += keys.get(u);
            mst.add(u);
            for (int v = 1; v <= n; v++) {
                if (graph[u][v] != -1 && !mst.contains(v)) {
                    if (graph[u][v] < keys.get(v)) {
                        keys.put(v, graph[u][v]);
                    }
                }
            }

        }
        return totalEdgeCost;

    }

    private static int findMinNode(int n, Map<Integer, Integer> keys, Set<Integer> mst) {
        int minValue = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int i = 1; i <= n; i++) {
            if (!mst.contains(i) && keys.get(i) < minValue) {
                minValue = keys.get(i);
                minIndex = i;
            }
        }
        return minIndex;
    }
}
