package epp.graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BellmanFordShortestPath {
    public static final String DISTANCE = "distance";
    public static void main(String[] args) {
        AdjacencyListGraphWithEdge graph = new AdjacencyListGraphWithEdge(true);
        //bottom part of graph
        graph.addEdge("a","b", Map.of(DISTANCE,3));
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

        System.out.println(graph);

      Map<String, Integer> distances =   findSingleSourceAllNodeShortestPath(graph,"a");
        System.out.println(distances);

    }

    private static Map<String, Integer> findSingleSourceAllNodeShortestPath(AdjacencyListGraphWithEdge graph, String sourceNodeValue) {
        GraphNodeWithEdge sourceNode = graph.findNode(sourceNodeValue);

        for(GraphNodeWithEdge node: graph.getAllNodes()){
            node.additionalNodeData.put(DISTANCE,Integer.MAX_VALUE);
        }
        sourceNode.additionalNodeData.put(DISTANCE,0);

        Set<Edge> allEdges = graph.getAllEdges();
        for(int i = 0; i<graph.getAllNodes().size()-1; i++){
            for(Edge edge: allEdges){

                GraphNodeWithEdge v = edge.targetNode;
                GraphNodeWithEdge u = edge.sourceNode;
                int vDist = (int) v.additionalNodeData.get(DISTANCE);
                int uDist = (int) u.additionalNodeData.get(DISTANCE);
                int edgeWeight = (int) edge.additionalEdgeData.get(DISTANCE);
                int newDistance = uDist!=Integer.MAX_VALUE?uDist + edgeWeight:Integer.MAX_VALUE;
                if(vDist > newDistance){
                    v.additionalNodeData.put(DISTANCE, newDistance);
                }

            }

        }
        //check negative weight cycle
        for(Edge edge: allEdges){

            GraphNodeWithEdge v = edge.targetNode;
            GraphNodeWithEdge u = edge.sourceNode;
            int vDist = (int) v.additionalNodeData.get(DISTANCE);
            int uDist = (int) u.additionalNodeData.get(DISTANCE);
            int edgeWeight = (int) edge.additionalEdgeData.get(DISTANCE);
            int newDistance = uDist!=Integer.MAX_VALUE?uDist + edgeWeight:Integer.MAX_VALUE;

            if(vDist > newDistance){
                System.out.println("presence of negative weight cycle");
                return new HashMap<String,Integer>();
            }

        }
        Map<String,Integer> result = new HashMap<>();
        for(GraphNodeWithEdge node:graph.getAllNodes()){
            result.put(node.value, (Integer) node.additionalNodeData.get(DISTANCE));
        }



        return result;
    }
}
