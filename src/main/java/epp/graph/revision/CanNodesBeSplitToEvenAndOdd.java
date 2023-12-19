package epp.graph.revision;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/**
 * can nodes be split into odd even, no link between odd to even
 */
public class CanNodesBeSplitToEvenAndOdd {

    public static final String DISTANCE = "distance";

    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<>(true);
        graph.addEdge(Integer.valueOf(1),Integer.valueOf(2));
        graph.addEdge(Integer.valueOf(2),Integer.valueOf(3));
        graph.addEdge(Integer.valueOf(3),Integer.valueOf(4));
        graph.addEdge(Integer.valueOf(4),Integer.valueOf(1));

        graph.addEdge(Integer.valueOf(1),Integer.valueOf(5));

        graph.addEdge(Integer.valueOf(5),Integer.valueOf(6));
        graph.addEdge(Integer.valueOf(6),Integer.valueOf(7));
        graph.addEdge(Integer.valueOf(7),Integer.valueOf(8));
        graph.addEdge(Integer.valueOf(8),Integer.valueOf(5));

        GraphNode<Integer> root = graph.getNode(Integer.valueOf(1));
        System.out.println(root);
        boolean success =  canNodesBiSplit(root,graph);
        System.out.println(success);

    }

    private static boolean canNodesBiSplit(GraphNode<Integer> root, Graph<Integer> graph) {
        Queue<GraphNode<Integer>> bfsQueue = new ArrayDeque<>();
        Set<GraphNode> visitedSet = new HashSet<>();
        root.addProperty(DISTANCE,0);
        bfsQueue.offer(root);
        visitedSet.add(root);

        while (!bfsQueue.isEmpty()){
            GraphNode<Integer> polled = bfsQueue.poll();
            int parentDistance = (int) polled.getProperty(DISTANCE);
            Set<GraphNode<Integer>> children = graph.getChildren(polled);
           // System.out.println(children);
            for(GraphNode<Integer> child:children){

                if(!visitedSet.contains(child)){
                    child.addProperty(DISTANCE,   parentDistance +1);
                    visitedSet.add(child);
                    bfsQueue.offer(child);
                }else{
                    int childDistance = (int) child.getProperty(DISTANCE);
                    if( childDistance ==  parentDistance){
                       return false;
                   }
                }
            }
        }
        return true;
    }
}
