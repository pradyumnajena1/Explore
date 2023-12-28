package guidetocompetitiveprogramming;

import epp.DisjointUnionSet;
import epp.Triplet;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class KruskalMinSpanningTree {
    public static void main(String[] args) {
        int numNodes = 6;
        List<Triplet<Integer,Integer,Integer>> edges = new ArrayList<>();
        edges.add(new Triplet<>(5,6,2));
        edges.add(new Triplet<>(1,2,3));
        edges.add(new Triplet<>(3,6,3));
        edges.add(new Triplet<>(1,5,5));
        edges.add(new Triplet<>(2,3,5));
        edges.add(new Triplet<>(2,5,6));
        edges.add(new Triplet<>(4,6,7));
        edges.add(new Triplet<>(3,4,9));
       List<Triplet<Integer, Integer, Integer>> min = kruskalMinSpanningTree(6,edges) ;
        System.out.println(min);
        System.out.println(min.stream().mapToInt(Triplet::getThird).sum());
    }

    private static List<Triplet<Integer,Integer,Integer>> kruskalMinSpanningTree(int numNodes, List<Triplet<Integer, Integer, Integer>> edges) {
        DisjointUnionSet disjointUnionSet = new DisjointUnionSet(numNodes+1);
        edges.sort(Comparator.comparingInt(Triplet::getThird));
        List<Triplet<Integer,Integer,Integer>> minSpanningTree = new ArrayList<>();
        for(Triplet<Integer,Integer,Integer> edge:edges){
            int pu = disjointUnionSet.find(edge.getFirst());
            int pv = disjointUnionSet.find(edge.getSecond());
            if(pu!=pv){
                disjointUnionSet.union(pu,pv);
                minSpanningTree.add(edge);
            }
        }
        return minSpanningTree;
    }
}
