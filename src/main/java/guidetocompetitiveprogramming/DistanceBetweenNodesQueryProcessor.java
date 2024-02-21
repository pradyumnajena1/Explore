package guidetocompetitiveprogramming;

import java.util.ArrayList;
import java.util.List;

public class DistanceBetweenNodesQueryProcessor {


    private final LCAQueryProcessor lcaQueryProcessor;

    public DistanceBetweenNodesQueryProcessor(List<List<Integer>> adjList) {
        this (adjList, 1);

    }

    public DistanceBetweenNodesQueryProcessor(List<List<Integer>> adjList,int root) {
        this.lcaQueryProcessor = new LCAQueryProcessor(adjList, root);

    }





    public int getDistance(int a, int b) {
        int lca = lcaQueryProcessor.getLowestCommonAncestor(a, b);
        return lcaQueryProcessor.getDepth(a) + lcaQueryProcessor.getDepth(b) - 2 * lcaQueryProcessor.getDepth(lca);
    }

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

        DistanceBetweenNodesQueryProcessor distanceBetweenNodesQueryProcessor = new DistanceBetweenNodesQueryProcessor(adjList);
        System.out.println(distanceBetweenNodesQueryProcessor.getDistance(5, 8));
    }
}
