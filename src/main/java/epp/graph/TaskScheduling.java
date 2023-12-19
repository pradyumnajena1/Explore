package epp.graph;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class TaskScheduling {

    public static final String TIME_TO_COMPLETE = "timeToComplete";
    public static final String TOTAL_TIME_TO_COMPLETE = "totalTimeToComplete";

    public static void main(String[] args) {
        AdjacencyListGraph graph = new AdjacencyListGraph(true);
        graph.addEdge("a","b");
        graph.addEdge("a","c");
        graph.addEdge("b","c");
        graph.addEdge("b","k");
        graph.addEdge("c","e");
        graph.addEdge("e","d");
        graph.addEdge("d","f");
        graph.addEdge("d","h");
        graph.addEdge("g","h");
        graph.addEdge("g","f");
        graph.addEdge("k","f");
        graph.addEdge("k","i");
        graph.addEdge("i","j");
        graph.addEdge("i","l");
        graph.addEdge("j","l");
        graph.addEdge("j","f");
        graph.addEdge("m","n");
        //graph.addEdge("l","i");
        System.out.println(graph);
        for(GraphNode node:graph.getAllNodes()){
            int timeToComplete = 1 + (int) (Math.random() * 5);
            node.additionalNodeData.put(TIME_TO_COMPLETE, timeToComplete);
            node.additionalNodeData.put(TOTAL_TIME_TO_COMPLETE, timeToComplete);
        }

        int minTime = getMinTime(graph);
        System.out.println(minTime);

    }

    private static int getMinTime(AdjacencyListGraph graph) {
        Set<GraphNode> visitedSet = new HashSet<>();
        Set<GraphNode> recStack = new HashSet<>();

        for(GraphNode node:graph.getAllNodes()){
            if(!visitedSet.contains(node)){
               boolean cycle =  checkCycle(node,visitedSet,recStack);
               if(cycle){
                   return -1;
               }
            }
        }
        Stack<GraphNode> stack = new Stack<>();
        visitedSet = new HashSet<>();
        for(GraphNode node:graph.getAllNodes()) {
            if (!visitedSet.contains(node)) {
                topologySort(node,visitedSet,stack);
            }
        }
        int minTime = 0;
        while (!stack.isEmpty()){
            GraphNode current = stack.pop();

            Integer currentTotalTime = (Integer) current.additionalNodeData.get(TOTAL_TIME_TO_COMPLETE);
            minTime = Math.max(minTime, currentTotalTime);
            System.out.println(current);
            for(GraphNode node:current.neighbours){
                int nodeTime = (int) node.additionalNodeData.get(TIME_TO_COMPLETE);
                int nodeTotalTime = (int) node.additionalNodeData.get(TOTAL_TIME_TO_COMPLETE);
                nodeTotalTime = Math.max(nodeTime+currentTotalTime,nodeTotalTime);

                node.additionalNodeData.put(TOTAL_TIME_TO_COMPLETE, nodeTotalTime );
            }
        }
        return minTime;
    }

    private static void topologySort(GraphNode sourceNode, Set<GraphNode> visitedSet, Stack<GraphNode> stack) {
        visitedSet.add(sourceNode);
        for(GraphNode node:sourceNode.neighbours){
            if(!visitedSet.contains(node)){
                topologySort(node,visitedSet,stack);
            }
        }
        stack.push(sourceNode);
    }

    private static boolean checkCycle(GraphNode node, Set<GraphNode> visitedSet, Set<GraphNode> recStack) {

        visitedSet.add(node);
        recStack.add(node);
        for(GraphNode neighbour:node.neighbours){
            if(!visitedSet.contains(neighbour)){
               if( checkCycle(neighbour,visitedSet,recStack)){
                   return true;
               }
            }else{
                if(recStack.contains(neighbour)){
                    return true;
                }
            }
        }
        recStack.remove(node);
        return false;
    }
}
