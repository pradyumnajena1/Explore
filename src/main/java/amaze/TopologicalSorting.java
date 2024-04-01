package amaze;

import epp.ListUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TopologicalSorting {
    public static void main(String[] args) {
        List<List<Integer>> edges = new ArrayList<>();
        edges.add(new ArrayList<>(List.of(5,0)));
        edges.add(new ArrayList<>(List.of(5,2)));
        edges.add(new ArrayList<>(List.of(4,0)));
        edges.add(new ArrayList<>(List.of(4,1)));
        edges.add(new ArrayList<>(List.of(2,3)));
        edges.add(new ArrayList<>(List.of(3,1)));


        List<Integer> topologicalSort = getTopoSort(5,edges);
        System.out.println(topologicalSort);
    }

    private static List<Integer> getTopoSort(int n, List<List<Integer>> edges) {
        List<List<Integer>> adjList = new ArrayList<>();

        for(int i=0;i<= n;i++){
            adjList.add(new ArrayList<>());

        }

        for(List<Integer> edge:edges){
            adjList.get(edge.get(0)).add(edge.get(1));
        }

        ArrayList<Integer> result = new ArrayList<>();
        Set<Integer> visitedSet = new HashSet<>();
        for(int i=0;i<=n;i++){
            if(!visitedSet.contains(i)){
                getTopoSort(adjList,i,visitedSet, result);
            }
        }
        ListUtils.reverse(result);
        return result;
    }

    private static void getTopoSort(List<List<Integer>> adjList, Integer source, Set<Integer> visitedSet, ArrayList<Integer> result) {
        if(visitedSet.contains(source)){
            return;
        }
        visitedSet.add(source);
        for(Integer node:adjList.get(source)){
           if(!visitedSet.contains(node)){

               getTopoSort(adjList,node, visitedSet, result);
           }
       }
       result.add(source);
    }
}
