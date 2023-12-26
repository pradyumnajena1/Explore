package guidetocompetitiveprogramming;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class CheckBipartiteness {
    public static void main(String[] args) {
        List<List<Integer>> adjList = new ArrayList<>();
        adjList.add(new ArrayList<>());
        adjList.add(new ArrayList<>(List.of(2,4)));
        adjList.add(new ArrayList<>(List.of(3,5)));
        adjList.add(new ArrayList<>(List.of(2,5)));
        adjList.add(new ArrayList<>(List.of(1,5)));
        adjList.add(new ArrayList<>(List.of(2,3,4)));
        System.out.println(checkBipartiteness(adjList, 1));
    }

    private static boolean checkBipartiteness(List<List<Integer>> adjList, int source) {
        boolean[] visitedSet = new boolean[adjList.size()];
        int[] color = new int[adjList.size()];
        visitedSet[source]=true;
        color[source] = 1;
        Queue<Integer> bfsQueue = new ArrayDeque<>();
        bfsQueue.offer(source);
        while (!bfsQueue.isEmpty()){
            Integer polled = bfsQueue.poll();
            for(int n:adjList.get(polled)){
                if(!visitedSet[n]){
                    visitedSet[n]=true;
                    color[n] = color[polled]==1?2:1;
                    bfsQueue.offer(n);
                }else{
                    if(color[n]==color[polled]){
                        return false;
                    }
                }
            }
        }
        return true;

    }
}
