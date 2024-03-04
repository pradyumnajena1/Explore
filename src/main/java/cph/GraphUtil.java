package cph;

import java.util.ArrayList;
import java.util.List;

public class GraphUtil {
    public static List<List<Integer>> convertEdgeListToAdjList(List<List<Integer>> edges, boolean directed, int numNodes) {
       List<List<Integer>> adjList = new ArrayList<>();
        for(int i=0;i<=numNodes;i++){
            adjList.add(new ArrayList<>());
        }
        for(List<Integer> edge: edges){
            adjList.get(edge.get(0)).add(edge.get(1));
            if(!directed){
                adjList.get(edge.get(1)).add(edge.get(0));
            }
        }
        return adjList;
    }
}
