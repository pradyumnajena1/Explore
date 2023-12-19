package epp.graph;

import epp.array.ArrayUtils;

import java.util.*;

public class CloneGraph {
    public static void main(String[] args) {
        String[] values = new String[]{"a","b","c","d","e","f","g","h","i","j"};
        int[][] matrix = ArrayUtils.createRandomMNMatrix(10,10,0,2);
        ArrayUtils.print2DArray(matrix);
        AdjacencyListGraph graph = GraphUtils.createRandomGraph(values, matrix);
        System.out.println(graph);
        System.out.println(graph.getAsAdjMatrix());

        AdjacencyListGraph cloned =  cloneGraph(graph,"c");
        System.out.println(cloned);
    }

    private static AdjacencyListGraph cloneGraph(AdjacencyListGraph graph, String sourceValue) {
        GraphNode sourceNode = graph.findNode(sourceValue);
        Queue<GraphNode> bfsQueue = new ArrayDeque<>();
        Set<GraphNode> visitedSet = new HashSet<>();
        Map<GraphNode,GraphNode> parent = new HashMap<>();
        AdjacencyListGraph cloned  = new AdjacencyListGraph(false);
        bfsQueue.offer(sourceNode);
        visitedSet.add(sourceNode);
        while (!bfsQueue.isEmpty()){
            GraphNode currentNode = bfsQueue.poll();
            GraphNode parentNode = parent.get(currentNode);
            if(parentNode!=null){
                cloned.addEdge(parentNode,currentNode);
            }else{
                cloned.addNode(currentNode);
            }
            currentNode.neighbours.stream().filter(x->  !visitedSet.contains(x)).forEach(x->{
                bfsQueue.offer(x);
                visitedSet.add(x);
                parent.put(x,currentNode);
            });
        }
        return cloned;
    }
}
