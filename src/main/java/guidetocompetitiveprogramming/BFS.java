package guidetocompetitiveprogramming;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BFS {
    public static void main(String[] args) {
        List<List<Integer>> adjList = new ArrayList<>();
        adjList.add(new ArrayList<>(List.of(1, 3)));
        adjList.add(new ArrayList<>(List.of(0,2,4)));
        adjList.add(new ArrayList<>(List.of(1,5)));
        adjList.add(new ArrayList<>(List.of(0)));
        adjList.add(new ArrayList<>(List.of(1,5)));
        adjList.add(new ArrayList<>(List.of(2,4)));

        System.out.println(adjList);
        breadthFirstSearch(adjList, 0);
    }

    private static void breadthFirstSearch(List<List<Integer>> adjList, int source) {
        Queue<Integer> bfsQueue = new ArrayDeque<>();
        boolean[] visited = new boolean[adjList.size()];
        bfsQueue.offer(source);
        visited[source] = true;
        while (!bfsQueue.isEmpty()){
            Integer polled = bfsQueue.poll();
            System.out.println(polled);

            for(int n:adjList.get(polled)){
                if(!visited[n]){
                    visited[n] = true;
                    bfsQueue.offer(n);
                }
            }
        }
    }
}
