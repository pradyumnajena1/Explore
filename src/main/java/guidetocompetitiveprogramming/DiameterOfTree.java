package guidetocompetitiveprogramming;

import epp.array.ArrayUtils;

import java.util.*;

/**
 * tree here a connected acyclic graph
 */
public class DiameterOfTree {
    public static void main(String[] args) {
        List<List<Integer>> adjList = new ArrayList<>();
        adjList.add(new ArrayList<>());
        adjList.add(new ArrayList<>(List.of(2, 3, 4)));
        adjList.add(new ArrayList<>(List.of(1, 5, 6)));
        adjList.add(new ArrayList<>(List.of(1)));
        adjList.add(new ArrayList<>(List.of(1, 7)));
        adjList.add(new ArrayList<>(List.of(2)));
        adjList.add(new ArrayList<>(List.of(2)));
        adjList.add(new ArrayList<>(List.of(4)));

        System.out.println(diameterOfTree(adjList, 1, 0));
    }

    private static int diameterOfTree(List<List<Integer>> adjList, int root, int parent) {
        int[] maxPath = new int[adjList.size()];
        int[] toLeaf = new int[adjList.size()];
        toLeaf(adjList, root, parent, toLeaf);
        ArrayUtils.printArray(toLeaf);
        maxPathLength(adjList, root, parent, maxPath, toLeaf);
        ArrayUtils.printArray(maxPath);
       return Arrays.stream(maxPath).max().getAsInt();
    }

    private static void toLeaf(List<List<Integer>> adjList, int root, int parent, int[] toLeaf) {
        toLeaf[root] = 0;
        for (int c : adjList.get(root)) {
            if (c != parent) {
                toLeaf(adjList, c, root, toLeaf);
                toLeaf[root] = Math.max(toLeaf[root], toLeaf[c] + 1);
            }
        }
    }

    private static void maxPathLength(List<List<Integer>> adjList, int root, int parent, int[] maxPath, int[] toLeaf) {
        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;
        for (int c : adjList.get(root)) {
            if (c != parent) {
                maxPathLength(adjList, c, root, maxPath, toLeaf);

                if (largest < toLeaf[c]) {
                    secondLargest = largest;
                    largest = toLeaf[c];
                } else if (secondLargest < toLeaf[c]) {
                    secondLargest = toLeaf[c];
                }

            }
        }
        if(largest!=Integer.MIN_VALUE&&secondLargest!=Integer.MIN_VALUE){
            maxPath[root] = largest+secondLargest+2;
        }else{
            maxPath[root] = 0;
        }

    }
}
