package guidetocompetitiveprogramming;

import epp.Triplet;
import epp.array.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BellmanFordSingleSourceShortestPath {
    public static void main(String[] args) {
        List<Triplet<Integer,Integer,Integer>> edges = new ArrayList<>();
        edges.add(new Triplet<>(1,2,2));
        edges.add(new Triplet<>(1,4,7));
        edges.add(new Triplet<>(1,3,3));

        edges.add(new Triplet<>(2,4,3));
        edges.add(new Triplet<>(2,5,5));

        edges.add(new Triplet<>(3,4,-2));

        edges.add(new Triplet<>(4,5,-2));
        int[] distance =  bellmanFordSingleSourceShortestPath(5,edges,1);
        ArrayUtils.printArray(distance);
        edges = new ArrayList<>();
        edges.add(new Triplet<>(1,2,3));
        edges.add(new Triplet<>(1,3,5));

        edges.add(new Triplet<>(2,3,2));
        edges.add(new Triplet<>(3,4,-7));
        edges.add(new Triplet<>(4,2,1));



        boolean negativeCycle = bellmanFordSingleDetectNegativeCycle(4, edges, 1);
        System.out.println(negativeCycle);


    }

    private static int[] bellmanFordSingleSourceShortestPath(int numNodes, List<Triplet<Integer, Integer, Integer>> edges, int source) {
        int[] distance = new int[numNodes+1];
        Arrays.fill(distance,Integer.MAX_VALUE);
        distance[source] = 0;
        boolean updated = true;
        for(int round=1;round<numNodes && updated;round++){
            updated = relaxEdges(edges, distance);
            System.out.println(updated);
        }

        return distance;
    }

    private static boolean bellmanFordSingleDetectNegativeCycle(int numNodes, List<Triplet<Integer, Integer, Integer>> edges, int source) {
        int[] distance = new int[numNodes+1];
        Arrays.fill(distance,Integer.MAX_VALUE);
        distance[source] = 0;

        for(int round=1;round<numNodes ;round++){

            boolean updated = relaxEdges(edges, distance);
            System.out.println(updated);
        }

        return relaxEdges(edges, distance);
    }

    private static boolean relaxEdges(List<Triplet<Integer, Integer, Integer>> edges, int[] distance) {
        boolean updated = false;
        for(Triplet<Integer,Integer,Integer> edge:edges){
            Integer u = edge.getFirst();
            Integer v = edge.getSecond();
            Integer weight = edge.getThird();


            if( distance[u]!=Integer.MAX_VALUE &&  distance[v] >  distance[u]+ weight){
                distance[v] = distance[u]+ weight;
                updated=true;

            }
        }
        return updated;
    }
}
