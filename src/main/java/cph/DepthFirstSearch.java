package cph;

import java.util.ArrayList;
import java.util.List;

public class DepthFirstSearch {
    private final int numNodes;
    private final boolean directed;
    private List<List<Integer>> adjList;

    public DepthFirstSearch(List<List<Integer>> edges,int n,boolean directed) {
        this.numNodes = n;
        this.directed = directed;
        this.adjList= GraphUtil.convertEdgeListToAdjList(edges, this.directed,numNodes);
    }

    public   void dfs( int root){
     boolean[] visited = new boolean[numNodes+1];
     dfs(root,visited);
    }

    private void dfs(int root, boolean[] visited) {
        if(visited[root]){
            return;
        }
        visited[root] = true;
        System.out.println(root);
        for(int adjacent:adjList.get(root)){
            dfs(adjacent,visited);
        }
    }

    public static void main(String[] args) {
        ArrayList<List<Integer>> edges = new ArrayList<>();
        edges.add(new ArrayList<>(List.of(1,2)));
        edges.add(new ArrayList<>(List.of(1,4)));
        edges.add(new ArrayList<>(List.of(2,3)));
        edges.add(new ArrayList<>(List.of(2,5)));
        edges.add(new ArrayList<>(List.of(3,5)));
        DepthFirstSearch search = new DepthFirstSearch(
                edges,5,false);
        search.dfs(1);
    }
}
