package hackerrank.medium;

import java.util.*;

public class ComponentsInAGraph {
    public static void main(String[] args) {
        System.out.println(componentsInGraph(new ArrayList<>(List.of(List.of(1, 6), List.of(2, 7), List.of(3, 8), List.of(4, 9),
                List.of(2, 6)))));
    }

    public static List<Integer> componentsInGraph(List<List<Integer>> gb) {
        // Write your code here
        Map<Integer,List<Integer>> adjMatrix = new HashMap<>();
        int maxNode = 0;
        for(List<Integer> edge:gb){
            int source = edge.get(0);
            int dest = edge.get(1);
            maxNode=Math.max(maxNode,source);
            maxNode=Math.max(maxNode,dest);
            List<Integer> edges = adjMatrix.getOrDefault(source, new ArrayList<>());
            edges.add(dest);
            adjMatrix.put(source,edges);
            edges = adjMatrix.getOrDefault(dest, new ArrayList<>());
            edges.add(source);
            adjMatrix.put(dest,edges);
        }
        Queue<Integer> bfsQueue = new ArrayDeque<>();
        Set<Integer> visitedSet = new HashSet<>();
        int minSize = Integer.MAX_VALUE;
        int maxSize = 0;
        for(int i=1;i<=maxNode;i++){
            if(!visitedSet.contains(i)){
                int size = doBFS(i, visitedSet, adjMatrix);
                if(size>1){
                    maxSize = Math.max(maxSize,size);
                    minSize = Math.min(minSize,size);
                }

            }
        }
        return new ArrayList<>(List.of(minSize,maxSize));
    }

    private static int doBFS(int i, Set<Integer> visitedSet, Map<Integer, List<Integer>> adjMatrix) {
        Queue<Integer> bfsQueue = new ArrayDeque<>();
        bfsQueue.add(i);
        visitedSet.add(i);
        int numNodes = 0;
        while (!bfsQueue.isEmpty()){
            Integer polled = bfsQueue.poll();
            numNodes++;
            List<Integer> neighbours = adjMatrix.get(polled);
            if(neighbours!=null){
                for(int neighbour:neighbours){
                    if(!visitedSet.contains(neighbour)){

                        visitedSet.add(neighbour);
                        bfsQueue.offer(neighbour);
                    }
                }
            }

        }
        return numNodes;
    }
}
