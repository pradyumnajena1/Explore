package guidetocompetitiveprogramming;

import epp.array.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

public class RootedTreeAncestor {
    private final int[][] anscestor;
    private final List<List<Integer>> adjList;
    private final int root;

    public RootedTreeAncestor(List<List<Integer>> adjList, int root) {
        this.adjList = adjList;
        this.root = root;
        int numNodes = adjList.size();
        int highestPower = 31-  Integer.numberOfLeadingZeros(numNodes - 1) ;
        anscestor = new int[highestPower+1][numNodes];
        dfsAncestor(adjList, root, 0, anscestor[0]);
        for (int row = 1; row < anscestor.length; row++) {
            for (int col = 1; col < anscestor[0].length; col++) {
                anscestor[row][col] = anscestor[row - 1][anscestor[row - 1][col]];
            }
        }
        ArrayUtils.print2DArray(anscestor);

    }

    public int ancestor(int node, int k) {
        int result = node;
        while (k > 0) {
            int row = 31 - Integer.numberOfLeadingZeros(k);
            result = anscestor[row][result];
            k = k - Integer.highestOneBit(k);

        }
        return result;
    }

    private void dfsAncestor(List<List<Integer>> adjList, int root, int parent, int[] ancestor) {
        ancestor[root] = parent;
        for (int n : adjList.get(root)) {
            if (n != parent) {
                dfsAncestor(adjList, n, root, ancestor);
            }
        }

    }


    public static void main(String[] args) {
        List<List<Integer>> adjList = new ArrayList<>();
        adjList.add(new ArrayList<>());
        adjList.add(new ArrayList<>(List.of(2)));
        adjList.add(new ArrayList<>(List.of(1, 3)));
        adjList.add(new ArrayList<>(List.of(2)));

        int root = 3;
        RootedTreeAncestor rootedTreeAncestor = new RootedTreeAncestor(adjList, root);
        System.out.println(rootedTreeAncestor.ancestor(1, 1));
        System.out.println(rootedTreeAncestor.ancestor(1, 2));
        System.out.println(rootedTreeAncestor.ancestor(2, 1));
        System.out.println(rootedTreeAncestor.ancestor(2, 2));
        System.out.println(rootedTreeAncestor.ancestor(3, 1));



    }
}
