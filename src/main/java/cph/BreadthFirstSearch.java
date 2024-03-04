package cph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearch {
    private final int numNodes;
    private final boolean directed;
    private List<List<Integer>> adjList;

    public BreadthFirstSearch(List<List<Integer>> edges,int n,boolean directed) {
        this.numNodes = n;
        this.directed = directed;
        this.adjList= GraphUtil.convertEdgeListToAdjList(edges, this.directed,numNodes);
    }
    public void bfs(int root){
        boolean[] visited = new boolean[numNodes+1];
        Queue<Integer> bfsQueue = new ArrayDeque<>();
        bfsQueue.offer(root);
        visited[root] = true;
        while (!bfsQueue.isEmpty()){
            Integer polled = bfsQueue.poll();
            System.out.println(polled);
            for(int adjacent:adjList.get(polled)){
                if(!visited[adjacent]){
                    visited[adjacent]=true;
                    bfsQueue.offer(adjacent);
                }
            }
        }
    }

    public static void main(String[] args) {
        ArrayList<List<Integer>> edges = new ArrayList<>();
        edges.add(new ArrayList<>(List.of(1,2)));
        edges.add(new ArrayList<>(List.of(1,4)));
        edges.add(new ArrayList<>(List.of(2,3)));
        edges.add(new ArrayList<>(List.of(2,5)));
        edges.add(new ArrayList<>(List.of(3,6)));
        edges.add(new ArrayList<>(List.of(5,6)));

        BreadthFirstSearch search = new BreadthFirstSearch(edges,6,false);
        search.bfs(1);
    }
}
