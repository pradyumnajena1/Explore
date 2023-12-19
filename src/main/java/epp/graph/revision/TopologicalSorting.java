package epp.graph.revision;

import epp.ListUtils;

import java.util.*;
import java.util.stream.Collectors;

public class TopologicalSorting {
    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<>(true);
        graph.addEdge(Integer.valueOf(5), Integer.valueOf(0));
        graph.addEdge(Integer.valueOf(5), Integer.valueOf(2));

        graph.addEdge(Integer.valueOf(4), Integer.valueOf(0));
        graph.addEdge(Integer.valueOf(4), Integer.valueOf(1));

        graph.addEdge(Integer.valueOf(2), Integer.valueOf(3));
        graph.addEdge(Integer.valueOf(3), Integer.valueOf(1));

       List<GraphNode<Integer>> topologicalSort =  getTopologicalSort(graph);
        System.out.println(topologicalSort.stream().map(x->x.getData()).collect(Collectors.toList()));
    }

    public static <T extends Comparable<T>> List<GraphNode<T>> getTopologicalSort(Graph<T> graph) {
        if(!graph.isDirectedGraph()){
            throw new IllegalArgumentException("graph is not directed");
        }

        Set<GraphNode<T>> visitedSet = new HashSet<>();
        List<GraphNode<T>> list = new ArrayList<>();
        for(GraphNode<T> node:graph.getAllNodes()){
            if(!visitedSet.contains(node)){
                getTopologicalSortHelper(node,graph,list,visitedSet);
            }
        }

        ListUtils.reverse(list);
        return list;
    }

    private static<T extends Comparable<T>> void getTopologicalSortHelper(GraphNode<T> node, Graph<T> graph,
                                                 List<GraphNode<T>> list, Set<GraphNode<T>> visitedSet) {
        visitedSet.add(node);
        Set<GraphNode<T>> children = graph.getChildren(node);
        for (GraphNode<T> child:children){
            if(!visitedSet.contains(child)){
                getTopologicalSortHelper(child,graph,list,visitedSet);
            }
        }
        list.add(node);
    }
}
