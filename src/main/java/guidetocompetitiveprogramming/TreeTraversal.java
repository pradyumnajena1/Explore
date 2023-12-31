package guidetocompetitiveprogramming;

import java.util.ArrayList;
import java.util.List;

/**
 * tree a  connected acyclic graph
 */
public class TreeTraversal {
    public static void main(String[] args) {
        List<List<Integer>> adjList = new ArrayList<>();
        adjList.add(new ArrayList<>());
        adjList.add(new ArrayList<>(List.of(2, 3, 4)));
        adjList.add(new ArrayList<>(List.of(1, 5, 6)));
        adjList.add(new ArrayList<>(List.of(1)));
        adjList.add(new ArrayList<>(List.of(1, 7)));
        adjList.add(new ArrayList<>(List.of(2)));
        adjList.add(new ArrayList<>(List.of(2, 8)));
        adjList.add(new ArrayList<>(List.of(4)));
        adjList.add(new ArrayList<>(List.of(6)));
        dfs(adjList, 1, 0);
        System.out.println();
        dfs(adjList, 3, 0);
        System.out.println();

        int[] counter = new int[adjList.size()];
        dfsCountNodes(adjList, 1, 0, counter);
        System.out.println(counter[1]);
    }

    private static void dfs(List<List<Integer>> adjList, int node, int parent) {
        System.out.println(node);
        for (int c : adjList.get(node)) {
            if (c != parent) dfs(adjList, c, node);
        }

    }


    private static void dfsCountNodes(List<List<Integer>> adjList, int node, int parent,int[] counter) {
        counter[node]=1;
        for (int c : adjList.get(node)) {
            if (c != parent){
                dfsCountNodes(adjList, c, node,counter);
                counter[node]+=counter[c];
            }
        }

    }
}
