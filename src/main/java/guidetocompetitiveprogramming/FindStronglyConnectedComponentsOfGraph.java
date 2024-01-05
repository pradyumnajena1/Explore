package guidetocompetitiveprogramming;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindStronglyConnectedComponentsOfGraph {
    public static void main(String[] args) {
        List<List<Integer>> adjList = new ArrayList<>();
        adjList.add(new ArrayList<>());
        adjList.add(new ArrayList<>(List.of(2, 4)));
        adjList.add(new ArrayList<>(List.of(1, 5)));
        adjList.add(new ArrayList<>(List.of(2, 7)));
        adjList.add(new ArrayList<>());
        adjList.add(new ArrayList<>(List.of(4)));
        adjList.add(new ArrayList<>(List.of(3, 5)));
        adjList.add(new ArrayList<>(List.of(6)));

        List<Set<Integer>> components = getStronglyConnectedComponents(adjList);
        System.out.println(components);
    }

    private static List<Set<Integer>> getStronglyConnectedComponents(List<List<Integer>> adjList) {
        List<Integer> nodeList = getNodesInCompletedProcessingOrder(adjList);
        System.out.println(nodeList);
        List<List<Integer>> revAdjList =  reverseEdges(adjList);
        System.out.println(revAdjList);
        List<Set<Integer>> components =   dfsForComponents(revAdjList,nodeList);

        return components;
    }

    private static List<Set<Integer>> dfsForComponents(List<List<Integer>> revAdjList, List<Integer> nodeList) {
        List<Set<Integer>> result = new ArrayList<>();
        boolean[] visited = new boolean[revAdjList.size()];
        for(int i=nodeList.size()-1;i>=0;i--){
            Integer node = nodeList.get(i);
            if(!visited[node]){
                Set<Integer> component = new HashSet<>();
                dfsForComponents(revAdjList, node,component,visited);
                result.add(component);
            }
        }
        return result;
    }

    private static void dfsForComponents(List<List<Integer>> revAdjList, int source, Set<Integer> component,boolean[] visited) {
        if(visited[source]){
            return;
        }
        visited[source] = true;
        component.add(source);
        for(int c:revAdjList.get(source)){
            dfsForComponents(revAdjList,c,component,visited);
        }
    }

    private static List<List<Integer>> reverseEdges(List<List<Integer>> adjList) {
        List<List<Integer>> revAdjList = new ArrayList<>();
        for(int i=0;i< adjList.size();i++){
            revAdjList.add(new ArrayList<>());
        }
        for(int i=1;i< adjList.size();i++){
            for(int c:adjList.get(i)){
                revAdjList.get(c).add(i);
            }
        }
        return revAdjList;
    }

    private static List<Integer> getNodesInCompletedProcessingOrder(List<List<Integer>> adjList) {
        boolean[] visited = new boolean[adjList.size()];
        List<Integer> nodeList = new ArrayList<>();
        for (int i = 1; i < adjList.size(); i++) {
            if (!visited[i])
                dfsForProcessingTime(adjList, i, visited, nodeList);
        }
        return nodeList;
    }

    private static void dfsForProcessingTime(List<List<Integer>> adjList, int source, boolean[] visited, List<Integer> nodeList) {
        if (visited[source]) {
            return;
        }
        visited[source] = true;
        for (int c : adjList.get(source)) {
            dfsForProcessingTime(adjList, c, visited, nodeList);
        }
        nodeList.add(source);
    }
}
