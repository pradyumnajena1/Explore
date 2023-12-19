package epp.graph;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class TeamPhotoOrLongestPathProblem {
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
        System.out.println(graph);

        int longestPath = getLongestPathLength(graph);
        System.out.println(longestPath);


    }

    private static int getLongestPathLength(AdjacencyListGraph graph) {
        Set<GraphNode> visitedSet = new HashSet<>();
        Stack<GraphNode> stack = new Stack<>();
        for(GraphNode node:graph.getAllNodes()){
            if(!visitedSet.contains(node)){

                dfs(node,visitedSet,stack);
            }
        }
        System.out.println(stack);
        int maxLength = 0;

        while (stack.size()>0){
            GraphNode current = stack.pop();
            Integer length = (Integer) current.additionalNodeData.getOrDefault("length", 0);

            if(length>maxLength){
                maxLength = length;
                System.out.println(current.value);
            }
            current.neighbours.forEach(x->x.additionalNodeData.put("length",
                    (int) current.additionalNodeData.getOrDefault("length",0)+1));

        }
        return maxLength;
    }

    private static void dfs(GraphNode node, Set<GraphNode> visitedSet, Stack<GraphNode> stack) {
        visitedSet.add(node);
        for(GraphNode neighbour:node.neighbours){
            if(!visitedSet.contains(neighbour)){
                dfs(neighbour,visitedSet,stack);
            }
        }
        stack.push(node);
    }
}
