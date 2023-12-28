package guidetocompetitiveprogramming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumWaysFromSourceToDestination {
    public static void main(String[] args) {
        List<List<Integer>> adjList = new ArrayList<>();
        adjList.add(new ArrayList<>());
        adjList.add(new ArrayList<>(List.of(2, 4)));
        adjList.add(new ArrayList<>(List.of(3, 6)));
        adjList.add(new ArrayList<>(List.of(6)));
        adjList.add(new ArrayList<>(List.of(5)));
        adjList.add(new ArrayList<>(List.of(2)));
        adjList.add(new ArrayList<>());
        int n = numWaysFromSourceToDestination(adjList, 1, 6);
        System.out.println(n);
    }

    private static int numWaysFromSourceToDestination(List<List<Integer>> adjList, int source, int destination) {
        Map<Integer, List<Integer>> reverseAdjMap = getReverseAdjMap(adjList);
        System.out.println(reverseAdjMap);
        List<Integer> topologicalSort = TopologicalSort.topologicalSort(adjList);
        Map<Integer, Integer> result = new HashMap<>();
        for (int n : topologicalSort) {
            result.put(n, 0);
        }
        result.put(source, 1);
        for (int n : topologicalSort) {
            List<Integer> sources = reverseAdjMap.getOrDefault(n,new ArrayList<>());
            int numWays = result.get(n);
            for (int s : sources) {
                numWays += result.get(s);
            }
            result.put(n, numWays);
        }
        System.out.println(result);

        return result.get(destination);
    }

    private static Map<Integer, List<Integer>> getReverseAdjMap(List<List<Integer>> adjList) {
        Map<Integer, List<Integer>> sources = new HashMap<>();
        for (int i = 1; i < adjList.size(); i++) {
            for (int d : adjList.get(i)) {
                List<Integer> list = sources.getOrDefault(d, new ArrayList<>());
                list.add(i);
                sources.put(d, list);
            }
        }
        return sources;
    }
}
