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
        anscestor = new int[highestPower][numNodes];
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
        adjList.add(new ArrayList<>(List.of(2, 4, 5)));
        adjList.add(new ArrayList<>(List.of(1, 6)));
        adjList.add(new ArrayList<>(List.of(4)));
        adjList.add(new ArrayList<>(List.of(1, 3, 7)));
        adjList.add(new ArrayList<>(List.of(1)));
        adjList.add(new ArrayList<>(List.of(2)));
        adjList.add(new ArrayList<>(List.of(4, 8)));
        adjList.add(new ArrayList<>(List.of(7)));
        int root = 1;
        RootedTreeAncestor rootedTreeAncestor = new RootedTreeAncestor(adjList, root);
        System.out.println(rootedTreeAncestor.ancestor(8, 1));
        System.out.println(rootedTreeAncestor.ancestor(8, 2));
        System.out.println(rootedTreeAncestor.ancestor(8, 3));
        System.out.println(rootedTreeAncestor.ancestor(8, 4));


    }
}
