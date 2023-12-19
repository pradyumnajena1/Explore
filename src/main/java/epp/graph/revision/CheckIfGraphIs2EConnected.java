package epp.graph.revision;

import java.util.HashSet;
import java.util.Set;

/**
 * check if there exist an edge , if broken makes the graph disconnected.
 */
public class CheckIfGraphIs2EConnected {

    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<>(false);
        graph.addEdge(Integer.valueOf(1), Integer.valueOf(2));
        graph.addEdge(Integer.valueOf(2), Integer.valueOf(3));
        graph.addEdge(Integer.valueOf(3), Integer.valueOf(4));
        graph.addEdge(Integer.valueOf(4), Integer.valueOf(1));

        graph.addEdge(Integer.valueOf(1), Integer.valueOf(5));

        graph.addEdge(Integer.valueOf(5), Integer.valueOf(6));
        graph.addEdge(Integer.valueOf(6), Integer.valueOf(7));
        graph.addEdge(Integer.valueOf(7), Integer.valueOf(8));
        graph.addEdge(Integer.valueOf(8), Integer.valueOf(5));
          GraphEdge<Integer> edge = checkIfGraphIs2EConnected(graph);
       // Set<GraphNode<Integer>> cyclePresent = graph.isCyclePresent(null);
         System.out.println(edge);
    }

    private static GraphEdge<Integer> checkIfGraphIs2EConnected(Graph<Integer> graph) {

        boolean isConnected = graph.checkIfGraphIsConnected();
        if (!isConnected) {
           return null;
        }

        Set<GraphNode<Integer>> cycleNodes = graph.isCyclePresent(null);
        if(cycleNodes!=null){
            System.out.println(cycleNodes);
            Set<GraphNode<Integer>> secondCycleNodes = graph.isCyclePresent(cycleNodes);
            if(secondCycleNodes!=null){
                System.out.println(secondCycleNodes);
                HashSet<GraphNode<Integer>> excludingSet = new HashSet<>();
                excludingSet.addAll(cycleNodes);
                excludingSet.addAll(secondCycleNodes);
                Set<GraphNode<Integer>> thirdCycle = graph.isCyclePresent(excludingSet);
                System.out.println(thirdCycle);
                if(thirdCycle==null){
                    for(GraphNode<Integer> node:cycleNodes){
                        for(GraphNode<Integer> child: graph.getChildren(node)){
                            if(secondCycleNodes.contains(child)){
                                return new GraphEdge<>(node,child);
                            }
                        }
                    }
                    for(GraphNode<Integer> node:secondCycleNodes){
                        for(GraphNode<Integer> child: graph.getChildren(node)){
                            if(cycleNodes.contains(child)){
                                return new GraphEdge<>(node,child);
                            }
                        }
                    }
                }
            }
        }


        return null;
    }


}
