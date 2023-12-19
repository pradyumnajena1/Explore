package epp.graph;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeSet;

public class ShortestPathWithMinEdges {

    public static final String DISTANCE = "distance";
    public static final String LENGTH = "length";

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

      int[] result =   findShortestPathWithMinEdges(graph,"a","h");
        System.out.println(Arrays.toString(result));
    }

    private static int[] findShortestPathWithMinEdges(AdjacencyListGraphWithEdge graph, String sourceValue, String targetValue) {
        GraphNodeWithEdge sourceNode = graph.findNode(sourceValue);
        GraphNodeWithEdge targetNode = graph.findNode(targetValue);
        Comparator<GraphNodeWithEdge> comparator = getGraphNodeWithEdgeComparator();
        TreeSet<GraphNodeWithEdge> tree = new TreeSet<>(comparator);

        sourceNode.additionalNodeData.put(DISTANCE,0);
        sourceNode.additionalNodeData.put(LENGTH,0);
        tree.add(sourceNode);

        for(GraphNodeWithEdge node:graph.getAllNodes()){
            if(!node.equals(sourceNode)){
                node.additionalNodeData.put(DISTANCE,Integer.MAX_VALUE);
                node.additionalNodeData.put(LENGTH,Integer.MAX_VALUE);
                tree.add(node);
            }

        }

        while (!tree.isEmpty()){
            GraphNodeWithEdge minNode = tree.iterator().next();
            tree.remove(minNode);

            if(minNode.equals(targetNode)){
                break;
            }
            //relax neighbours
            for(Edge neighbour:minNode.neighbours){

                GraphNodeWithEdge node = neighbour.targetNode;
                Map<String, Object> additionalEdgeData = neighbour.additionalEdgeData;

                int existingDistance = (int) node.additionalNodeData.get(DISTANCE);
                int newDistance = (int) minNode.additionalNodeData.get(DISTANCE) + (int) additionalEdgeData.get(DISTANCE);
                int existingLength  = (int) node.additionalNodeData.get(LENGTH);
                int newLength  =
                        (int) minNode.additionalNodeData.get(LENGTH) + 1;
                if( existingDistance > newDistance || existingDistance==newDistance&&existingLength>newLength){
                    tree.remove(node);
                    node.additionalNodeData.put(DISTANCE,newDistance);
                    node.additionalNodeData.put(LENGTH,newLength);
                    tree.add(node);
                }


            }
        }

        return new int[]{ (int)targetNode.additionalNodeData.get(DISTANCE),
                (int)targetNode.additionalNodeData.get(LENGTH)};
    }

    private static Comparator<GraphNodeWithEdge> getGraphNodeWithEdgeComparator() {
        Comparator<GraphNodeWithEdge> comparator =
                Comparator.comparingInt((GraphNodeWithEdge o) -> (Integer) o.additionalNodeData.get(DISTANCE))
                .thenComparingInt(o -> (Integer) o.additionalNodeData.get(LENGTH)).thenComparing((GraphNodeWithEdge o) ->  o.value);
        return comparator;
    }
}
