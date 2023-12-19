package epp.graph;

import java.util.*;

public class DegreeOfConnectedness {
    public static void main(String[] args) {
       AdjacencyListGraph graph = new AdjacencyListGraph(false);
       graph.addEdge("1","2");
       graph.addEdge("2","3");
       graph.addEdge("3","4");
       graph.addEdge("4","5");

       graph.addEdge("a","b");
       graph.addEdge("b","c");
       graph.addEdge("c","d");
       graph.addEdge("d","e");
       graph.addEdge("e","f");
       graph.addEdge("f","g");
       graph.addEdge("c","e");
        System.out.println(graph);
        GraphNode cycleNode = is2eConnectedDFS(graph);
        System.out.println(cycleNode);
        int cycleLength = getCycleLength(cycleNode);
        System.out.println(cycleLength);
        if(cycleLength==graph.getAllNodeValues().size()){
            System.out.println("2VConnected");
        }else{
            System.out.println("2eConnected");

        }

        boolean is2eConnected = is2eConnectedBFS(graph);
        System.out.println(is2eConnected);
    }

    private static GraphNode is2eConnectedDFS(AdjacencyListGraph graph) {
        Set<GraphNode> visitedSet = new HashSet<>();

        for(String nodeValue:graph.getAllNodeValues()){
            GraphNode node = graph.findNode(nodeValue);
            if(!visitedSet.contains(node)){
                GraphNode cycleNode = is2eConnectedDFS(node, null, visitedSet);
                if(cycleNode!=null){
                    return cycleNode;
                }
            }
        }
        return null;
    }

    private static GraphNode is2eConnectedDFS(GraphNode sourceNode,
                                            GraphNode parent, Set<GraphNode> visitedSet) {
        visitedSet.add(sourceNode);
        System.out.println(sourceNode.value);
        for(GraphNode node:sourceNode.neighbours){
            if(!visitedSet.contains(node)){
                GraphNode cycleNode = is2eConnectedDFS(node, sourceNode, visitedSet);
                if( cycleNode !=null){
                   return cycleNode;
               }
            }else{
                if(node!=parent){
                    return node;
                }

            }
        }
        return null;
    }

    private static int getCycleLength(GraphNode sourceNode){
        Set<GraphNode> visitedSet = new HashSet<>();
        Queue<GraphNode> bfsQueue = new ArrayDeque<>();
        Map<GraphNode,GraphNode> parent = new HashMap<>();
        bfsQueue.offer(sourceNode);
        visitedSet.add(sourceNode);
        int count=0;
        while (!bfsQueue.isEmpty()){
            GraphNode current = bfsQueue.poll();

            for(GraphNode node:current.neighbours){
                if(!visitedSet.contains(node)){
                    parent.put(node,current);
                    visitedSet.add(node);
                    bfsQueue.offer(node);
                }else{
                    if(node!=parent.get(current)){

                        while (current!=null){
                            System.out.println(current);
                            count++;
                            current = parent.get(current);
                        }
                        return count+1;
                    }
                }
            }
        }

        return count;
    }



    private static boolean is2eConnectedBFS(AdjacencyListGraph graph) {
        Set<GraphNode> visitedSet = new HashSet<>();
        Map<GraphNode,GraphNode> parent = new HashMap<>();
        for(String nodeValue:graph.getAllNodeValues()){
            GraphNode node = graph.findNode(nodeValue);
            if(!visitedSet.contains(node)){
                boolean eConnected = is2eConnectedBFS(node , visitedSet,parent);
                if(eConnected){
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean is2eConnectedBFS(GraphNode sourceNode, Set<GraphNode> visitedSet,Map<GraphNode,GraphNode> parent) {
        Queue<GraphNode> bfsQueue = new ArrayDeque<>();
        bfsQueue.offer(sourceNode);
        visitedSet.add(sourceNode);

        while (!bfsQueue.isEmpty()){
            GraphNode current = bfsQueue.poll();
            for(GraphNode neighbour:current.neighbours){
                if(!visitedSet.contains(neighbour)){
                    visitedSet.add(neighbour);
                    bfsQueue.offer(neighbour);
                    parent.put(neighbour,current);
                }else{
                    if(neighbour!=parent.get(current)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
