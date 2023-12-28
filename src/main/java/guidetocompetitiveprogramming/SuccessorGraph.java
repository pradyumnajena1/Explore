package guidetocompetitiveprogramming;

import epp.ListUtils;
import epp.array.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * every node has only one outgoing edge. So every node has a fixed successor.
 */
public class SuccessorGraph {
    private List<List<Integer>> adjList;
    private int[][] successorMatrix;

    public static void main(String[] args) {
        List<List<Integer>> adjList = new ArrayList<>();
        adjList.add(new ArrayList<>());
        adjList.add(new ArrayList<>(List.of(3)));
        adjList.add(new ArrayList<>(List.of(5)));
        adjList.add(new ArrayList<>(List.of(7)));
        adjList.add(new ArrayList<>(List.of(6)));
        adjList.add(new ArrayList<>(List.of(2)));
        adjList.add(new ArrayList<>(List.of(2)));
        adjList.add(new ArrayList<>(List.of(1)));
        adjList.add(new ArrayList<>(List.of(6)));
        adjList.add(new ArrayList<>(List.of(3)));
        SuccessorGraph sg = new SuccessorGraph(adjList);
        System.out.println(sg.successor(4, Integer.MAX_VALUE));


    }

    public SuccessorGraph(List<List<Integer>> adjList) {
        this.adjList = adjList;
        initSuccessorMatrix(adjList );
        ArrayUtils.print2DArray(successorMatrix);
    }

    private void initSuccessorMatrix(List<List<Integer>> adjList ) {
        successorMatrix = new int[32][adjList.size()];
        for (int i = 0; i < 32; i++) {
            for (int n = 1; n < adjList.size(); n++) {
                if (i == 0) {
                    successorMatrix[i][n] = successor(n);
                } else {
                    successorMatrix[i][n] = successorMatrix[i - 1][successorMatrix[i - 1][n]];
                }

            }
        }
    }

    public int successor(int x, int k) {
        List<Integer> powerOf2s = getPowerOf2(k);
        System.out.println(powerOf2s);
        int result = x;
        for (int p : powerOf2s) {
            result = successorMatrix[p][result];
        }
        return result;
    }

    private static List<Integer> getPowerOf2(int k) {
        List<Integer> positionalValues = new ArrayList<>();
        for (int i = 0; i < 32; i++) {
            if ((k & 1 << i) != 0) {
                positionalValues.add(i);
            }
        }
        ListUtils.reverse(positionalValues);
        return positionalValues;
    }

    private int successor(int x) {
        return adjList.get(x).get(0);
    }
}
