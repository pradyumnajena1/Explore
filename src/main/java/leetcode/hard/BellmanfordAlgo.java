package leetcode.hard;

import epp.graph.Edge;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BellmanfordAlgo {
    private static final String DISTANCE = "Distance";

    public static void main(String[] args) {
        Graph graph = new Graph();
        //bottom part of graph
        graph.addEdge( "a","b", Map.of(DISTANCE,3));
        graph.addEdge("b","a", Map.of(DISTANCE,4));
        graph.addEdge("b","k", Map.of(DISTANCE,1));
        graph.addEdge("k","i", Map.of(DISTANCE,1));
        graph.addEdge("i","j", Map.of(DISTANCE,6));
        graph.addEdge("j","l", Map.of(DISTANCE,7));
        graph.addEdge("l","i", Map.of(DISTANCE,9));
        graph.addEdge("j","f", Map.of(DISTANCE,1));
        graph.addEdge("f","g", Map.of(DISTANCE,6));
        graph.addEdge("g","f", Map.of(DISTANCE,7));
        graph.addEdge("g","h", Map.of(DISTANCE,4));
        //top part
        graph.addEdge("a","c", Map.of(DISTANCE,2));
        graph.addEdge("c","e", Map.of(DISTANCE,8));
        graph.addEdge("e","d", Map.of(DISTANCE,7));
        graph.addEdge("d","c", Map.of(DISTANCE,5));
        graph.addEdge("d","h", Map.of(DISTANCE,5));

        graph.addEdge("m","n", Map.of(DISTANCE,5));
        graph.addEdge("n","m", Map.of(DISTANCE,12));

        Map<String,Integer> singleSourceShortestDistance = getSingleSourceShortestDistance(graph,"a");
        System.out.println(singleSourceShortestDistance);
    }

    private static Map<String, Integer> getSingleSourceShortestDistance(Graph graph, String source) {
        Map<String, Integer> result = new HashMap<>();
        Optional<GraphNode> sourceNode = graph.getNode(source);
        if(!sourceNode.isPresent()){
            return result;
        }
        for(GraphNode node: graph.getNodes()){
            node.setNodeAttribute(DISTANCE,Integer.MAX_VALUE);
        }
        sourceNode.get().setNodeAttribute(DISTANCE,0);
        System.out.println(graph);

        for(int i=0;i<graph.getNodes().size()-1;i++){
            for(GraphEdge edge:graph.getEdges()){
                GraphNode source_node = edge.getSource();
                GraphNode dest_node = edge.getDestination();

                int newDistance =
                        (int)source_node.getNodeAttribute(DISTANCE) == Integer.MAX_VALUE ? Integer.MAX_VALUE:
                                (int)source_node.getNodeAttribute(DISTANCE)+(int)edge.getAttribute(DISTANCE);
                if( (int) dest_node.getNodeAttribute(DISTANCE) > newDistance){
                    dest_node.setNodeAttribute(DISTANCE,newDistance);
                }
            }
        }

        for(GraphEdge edge:graph.getEdges()){
            GraphNode source_node = edge.getSource();
            GraphNode dest_node = edge.getSource();
            int newDistance =
                    (int)source_node.getNodeAttribute(DISTANCE) == Integer.MAX_VALUE ? Integer.MAX_VALUE:
                            (int)source_node.getNodeAttribute(DISTANCE)+(int)edge.getAttribute(DISTANCE);
            if( (int) dest_node.getNodeAttribute(DISTANCE) > newDistance){
                return result;
            }
        }
        for(GraphNode node:graph.getNodes()){
            result.put(node.getName(), (Integer) node.getNodeAttribute(DISTANCE));
        }

        return result;
    }
}
