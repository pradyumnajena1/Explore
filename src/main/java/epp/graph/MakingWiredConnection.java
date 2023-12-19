package epp.graph;

import epp.array.ArrayUtils;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MakingWiredConnection {
    public static void main(String[] args) {
        String[] values =
                IntStream.range(0,26).mapToObj(x-> ""+Character.valueOf((char) ('a'+x))).collect(Collectors.toList()).toArray(new String[0]);
        int[][] matrix = ArrayUtils.createRandomMNMatrix(values.length,values.length,0,20);
        ArrayUtils.print2DArray(matrix);
        ArrayUtils.replaceValue(matrix, IntStream.range(2,20).boxed().collect(Collectors.toSet()),0);
        ArrayUtils.print2DArray(matrix);
        AdjacencyListGraph graph = GraphUtils.createRandomGraph(values, matrix);
        System.out.println(graph);

        boolean isFeasible = isFeasibleLeftRightPartition(graph,"a");
        System.out.println(isFeasible);
    }

    private static boolean isFeasibleLeftRightPartition(AdjacencyListGraph graph, String value) {
        GraphNode sourceNode = graph.findNode(value);
        String distance = "distance";
        sourceNode.additionalNodeData.put(distance,0);
        Queue<GraphNode> bfsQueue = new ArrayDeque<>();
        Set<GraphNode> visitedSet = new HashSet<>();

        bfsQueue.offer(sourceNode);
        visitedSet.add(sourceNode);
        while (!bfsQueue.isEmpty()){
            System.out.println(bfsQueue);
            GraphNode currentNode = bfsQueue.poll();
            int currentNodeDistance = (int) currentNode.additionalNodeData.get(distance);

            for(GraphNode node:currentNode.neighbours){
                if(!visitedSet.contains(node)){
                    node.additionalNodeData.put(distance,( currentNodeDistance +1));
                    bfsQueue.offer(node);
                    visitedSet.add(node);
                }else{
                    if(currentNodeDistance== (int) node.additionalNodeData.get(distance)){
                        return false;
                    }
                }
            }
        }

        return true;
    }

}
