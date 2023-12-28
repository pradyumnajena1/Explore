package guidetocompetitiveprogramming;

import epp.ListUtils;

import java.util.ArrayList;
import java.util.List;

public class TopologicalSort {
    public static void main(String[] args) {
        List<List<Integer>> adjList = new ArrayList<>();
        adjList.add(new ArrayList<>());
        adjList.add(new ArrayList<>(List.of(2)));
        adjList.add(new ArrayList<>(List.of(3)));
        adjList.add(new ArrayList<>(List.of(6)));
        adjList.add(new ArrayList<>(List.of(1, 5)));
        adjList.add(new ArrayList<>(List.of(2, 3)));
        adjList.add(new ArrayList<>());
        List<Integer> sort = topologicalSort(adjList);
        System.out.println(sort);

        //cycle present shd throw exception
        adjList = new ArrayList<>();
        adjList.add(new ArrayList<>());
        adjList.add(new ArrayList<>(List.of(2)));
        adjList.add(new ArrayList<>(List.of(3)));
        adjList.add(new ArrayList<>(List.of(5, 6)));
        adjList.add(new ArrayList<>(List.of(1, 5)));
        adjList.add(new ArrayList<>(List.of(2)));
        adjList.add(new ArrayList<>());
        try {
            sort = topologicalSort(adjList);
            System.out.println(sort);
        } catch (Exception e) {
            System.out.println(e.getMessage());;
        }
    }

    public static List<Integer> topologicalSort(List<List<Integer>> adjList) {
        byte[] color = new byte[adjList.size()];
        List<Integer> topology = new ArrayList<>();
        for (int i = 1; i < adjList.size(); i++) {
            if (color[i] == 0) {
                topologicalSort(adjList, i, color, topology);
            }
        }
        ListUtils.reverse(topology);
        return topology;
    }

    private static void topologicalSort(List<List<Integer>> adjList, int source, byte[] color, List<Integer> topology) {

        if (color[source] == 2) {
            return;
        }
        if (color[source] == 1) {
            throw new RuntimeException("cycle present ");
        }

        color[source] = 1;
        for (Integer n : adjList.get(source)) {
            topologicalSort(adjList, n, color, topology);
        }
        color[source] = 2;
        topology.add(source);

    }

}
