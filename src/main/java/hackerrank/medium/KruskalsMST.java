package hackerrank.medium;

import epp.DisjointUnionSet;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class KruskalsMST {
    public static void main(String[] args) {
        System.out.println(kruskals(4, new ArrayList<>(List.of(1, 1, 4, 2, 3, 3)), new ArrayList<>(List.of(2, 3, 1, 4, 2, 4)), new ArrayList<>(List.of(5, 3, 6, 7, 4, 5))));
        System.out.println(kruskals(5, new ArrayList<>(List.of(1, 1, 1, 1, 2, 3, 4)), new ArrayList<>(List.of(2, 3, 4, 5, 3, 4, 5)), new ArrayList<>(List.of(20, 50, 70, 90, 30, 40, 60))));
    }

    public static int kruskals(int gNodes, List<Integer> gFrom, List<Integer> gTo, List<Integer> gWeight) {
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < gFrom.size(); i++) {
            edges.add(new Edge(gFrom.get(i), gTo.get(i), gWeight.get(i)));
        }
        DisjointUnionSet disjointUnionSet = new DisjointUnionSet(gNodes + 1);
        edges.sort(Comparator.comparingInt((Edge e) -> e.w).thenComparingInt((Edge e) -> e.u + e.v + e.w));
        List<Edge> result = new ArrayList<>();
        int totalCost = 0;
        for (int i = 0; i < edges.size(); i++) {

            Edge current = edges.get(i);
            int up = disjointUnionSet.find(current.u);
            int vp = disjointUnionSet.find(current.v);
            if (up != vp) {
                result.add(current);
                totalCost += current.w;
                disjointUnionSet.union(current.u, current.v);
            }
            if (result.size() == gNodes - 1) {
                break;
            }
        }
        return totalCost;
    }

    private static class Edge {
        int u;
        int v;
        int w;

        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }
}
