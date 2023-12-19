package hackerrank.medium;

import java.util.*;

public class CutTheTree {
    public static void main(String[] args) {
        System.out.println(cutTheTree(new ArrayList<>(List.of(100, 200, 100, 500, 100, 600)),
                new ArrayList<>(List.of(new ArrayList<>(List.of(1, 2)), new ArrayList<>(List.of(2, 3)),
                        new ArrayList<>(List.of(2, 5)), new ArrayList<>(List.of(4, 5))
                        , new ArrayList<>(List.of(5,
                                6))))
        ));
    }

    public static int cutTheTree(List<Integer> data, List<List<Integer>> edges) {
        // Write your code here
        List<GraphNode> graphNodes = new ArrayList<>();
        Map<Integer, List<Integer>> edgeMap = new HashMap<>();
        graphNodes.add(null);
        for (int i = 0; i < data.size(); i++) {
            graphNodes.add(new GraphNode(i + 1, data.get(i)));
        }
        for (List<Integer> edge : edges) {
            List<Integer> descendants = edgeMap.getOrDefault(edge.get(0), new ArrayList<>());
            descendants.add(edge.get(1));
            edgeMap.put(edge.get(0), descendants);

            descendants = edgeMap.getOrDefault(edge.get(1), new ArrayList<>());
            descendants.add(edge.get(0));
            edgeMap.put(edge.get(1), descendants);
        }

        Set<Integer> visitedSet = new HashSet<>();
        setSum(null, graphNodes.get(1), graphNodes, edgeMap, visitedSet);

        visitedSet = new HashSet<>();
        int minDiff = getMinDiff(null, graphNodes.get(1), graphNodes, edgeMap,visitedSet);

        return minDiff;
    }

    private static int getMinDiff(GraphNode parent, GraphNode graphNode, List<GraphNode> graphNodes, Map<Integer,
            List<Integer>> edgeMap, Set<Integer> visitedSet) {
        List<Integer> descendants = edgeMap.get(graphNode.nodeId);
        if (parent != null && descendants.size() == 1) {

            return Integer.MAX_VALUE;
        }
        int minDiff = Integer.MAX_VALUE;
        for (Integer i : descendants) {
            if( (parent==null||parent.nodeId!=i) && !visitedSet.contains(i))
            minDiff = Math.min(minDiff, getMinDiff(graphNode, graphNodes.get(i), graphNodes, edgeMap,visitedSet));
        }
        for (Integer i : descendants) {
            if((parent==null||parent.nodeId!=i)){

                minDiff = Math.min(minDiff,
                        Math.abs( (graphNodes.get(1).sum -graphNodes.get(i).sum) -  graphNodes.get(i).sum));
            }
        }
         visitedSet.add(graphNode.nodeId);
        return minDiff;
    }

    private static void setSum(GraphNode parent, GraphNode graphNode, List<GraphNode> graphNodes, Map<Integer,
            List<Integer>> edges, Set<Integer> visitedSet) {
        if (visitedSet.contains(graphNode.nodeId)) {
            return;
        }

        List<Integer> descendants = edges.get(graphNode.nodeId);
        if (parent != null && descendants.size() == 1) {
            graphNode.sum = graphNode.data;
            visitedSet.add(graphNode.nodeId);

            return;
        }
        for (Integer i : descendants) {
            if ((parent == null || i != parent.nodeId) && !visitedSet.contains(graphNodes.get(i).nodeId)) {

                setSum(graphNode, graphNodes.get(i), graphNodes, edges, visitedSet);
            }
        }
        int total = graphNode.data;
        for (Integer i : descendants) {
            total += graphNodes.get(i).sum;
        }
        graphNode.sum = total;

        visitedSet.add(graphNode.nodeId);
    }

    private static class GraphNode {
        int nodeId;
        int data;
        int sum;


        public GraphNode(int nodeId, int data) {
            this.nodeId = nodeId;
            this.data = data;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", GraphNode.class.getSimpleName() + "[", "]")
                    .add("nodeId=" + nodeId)
                    .add("data=" + data)
                    .add("sum=" + sum)
                    .toString();
        }
    }
}
