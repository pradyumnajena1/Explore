package epp.graph.revision;

import java.util.*;

public class BellmanFordSingleSourceShortestDistance {

    public static final String DISTANCE = "distance";

    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<>(true);
        graph.addEdge(1, 2);
        graph.addEdgeProperty(1, 2, DISTANCE, 5);

        graph.addEdge(2, 3);
        graph.addEdgeProperty(2, 3, DISTANCE, 1);

        graph.addEdge(2, 4);
        graph.addEdgeProperty(2, 4, DISTANCE, 2);

        graph.addEdge(4, 6);
        graph.addEdgeProperty(4, 6, DISTANCE, 2);

        graph.addEdge(5, 4);
        graph.addEdgeProperty(5, 4, DISTANCE, 1);

        graph.addEdge(3, 5);
        graph.addEdgeProperty(3, 5, DISTANCE, 1);

        graph.addEdge(6, 5);
        graph.addEdgeProperty(6, 5, DISTANCE, -4);

        boolean isNegativeCycleExist = bellfordDetectNegativeCycle(graph);
        System.out.println(isNegativeCycleExist);


    }

    private static boolean bellfordDetectNegativeCycle(Graph<Integer> graph) {
        GraphNode<Integer> sourceNode = graph.getNode(1);
        Map<Integer, Integer> distance = new HashMap<>();
        List<GraphNode<Integer>> allNodes = new ArrayList<>(graph.getAllNodes());
        for (GraphNode<Integer> node : allNodes) {
            distance.put(node.getData(), Integer.MAX_VALUE);
        }
        distance.put(sourceNode.getData(), 0);
        for (int times = 1; times < allNodes.size(); times++) {
            Collection<GraphEdge<Integer>> allEdges = graph.getAllEdges();
            for (GraphEdge<Integer> edge : allEdges) {
                int dist = (int) edge.getProperties().get(DISTANCE);
                GraphNode<Integer> source = edge.getSource();
                GraphNode<Integer> destination = edge.getDestination();
                if (distance.get(source.getData()) != Integer.MAX_VALUE &&
                        distance.get(destination.getData()) > distance.get(source.getData()) + dist) {
                    distance.put(destination.getData(), distance.get(source.getData()) + dist);
                }
            }

        }
        boolean updated = false;

        Collection<GraphEdge<Integer>> allEdges = graph.getAllEdges();
        for (GraphEdge<Integer> edge : allEdges) {
            int dist = (int) edge.getProperties().get(DISTANCE);
            GraphNode<Integer> source = edge.getSource();
            GraphNode<Integer> destination = edge.getDestination();
            if (distance.get(source.getData()) != Integer.MAX_VALUE &&
                    distance.get(destination.getData()) > distance.get(source.getData()) + dist) {
                updated = true;
                System.out.println(edge);
                distance.put(destination.getData(), distance.get(source.getData()) + dist);
            }
        }


        return updated;
    }
}
