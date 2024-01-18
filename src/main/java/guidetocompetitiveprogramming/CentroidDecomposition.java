package guidetocompetitiveprogramming;

import epp.array.ArrayUtils;

import java.util.*;

public class CentroidDecomposition {



    private List<List<Integer>> originalTree;
    private int MaxNodes;
    private final int rootCentroid;
    private List<List<Integer>> centroidTree;
    private int[] parent;
    private boolean[] centroidMarked;

    public CentroidDecomposition(int MaxNodes, List<List<Integer>> adjList) {
       this. MaxNodes = MaxNodes;
        this.originalTree = adjList;
        this.centroidMarked = new boolean[MaxNodes];
        this.parent = new int[MaxNodes];
        this.centroidTree = new ArrayList<>();
        for(int i=0;i<adjList.size();i++){
            this.centroidTree.add(new ArrayList<>());
        }
        this.rootCentroid = decomposeTree(1);
        parent[rootCentroid] = rootCentroid;

        System.out.println(centroidTree);
        ArrayUtils.printArray(parent);
    }



    private int decomposeTree(int root) {
        int treeCentroid = getCentroid(root);

        System.out.print(treeCentroid + " ");

        for (int c : originalTree.get(treeCentroid)) {
            if (!centroidMarked[c]) {
                int subtreeCentroid = decomposeTree(c);

                centroidTree.get(treeCentroid).add(subtreeCentroid);
                centroidTree.get(subtreeCentroid).add(treeCentroid);
                parent[subtreeCentroid]=treeCentroid;
            }
        }

        return treeCentroid;
    }
    public List<List<Integer>> getCentroidTree(){
        return centroidTree;
    }
    public int getRootCentroid(){
        return rootCentroid;
    }

    private int getCentroid(int root) {

        int[] subTreeSizes = new int[MaxNodes];
        boolean[] visited = new boolean[MaxNodes];

        Arrays.fill(subTreeSizes, 0);
        Arrays.fill(visited, false);

        dfsSize(root, visited, subTreeSizes);
        int centroid = dfsCentroid(root, subTreeSizes, visited, subTreeSizes[root]);
        centroidMarked[centroid] = true;
        return centroid;

    }

    private int dfsCentroid(int root, int[] sizes, boolean[] visited, int n) {
        visited[root] = true;
        boolean iscentroid = true;
        int maxChildId = 0;
        for (int c : originalTree.get(root)) {
            if (!visited[c] && !centroidMarked[c]) {
                if (sizes[c] > n / 2) {
                    iscentroid = false;
                }
                if (maxChildId == 0 || sizes[maxChildId] < sizes[c]) {
                    maxChildId = c;
                }
            }
        }
        if (iscentroid && n - sizes[root] <= n / 2) {
            return root;
        }
        return dfsCentroid(maxChildId, sizes, visited, n);

    }
    public int getParent(int node){

        return parent[node];
    }

    private void dfsSize(int root, boolean[] visited, int[] subTreeSizes) {
        visited[root] = true;
        subTreeSizes[root] = 1;
        for (int c : originalTree.get(root)) {
            if (!visited[c] && !centroidMarked[c]) {
                dfsSize(c, visited, subTreeSizes);
                subTreeSizes[root] += subTreeSizes[c];
            }
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> adjList = new ArrayList<>();
        adjList.add(new ArrayList<>());
        adjList.add(new ArrayList<>(Set.of(2, 3, 4)));
        adjList.add(new ArrayList<>(Set.of(1, 5, 6)));
        adjList.add(new ArrayList<>(Set.of(1)));
        adjList.add(new ArrayList<>(Set.of(1, 7)));
        adjList.add(new ArrayList<>(Set.of(2)));
        adjList.add(new ArrayList<>(Set.of(2, 8)));
        adjList.add(new ArrayList<>(Set.of(4)));
        adjList.add(new ArrayList<>(Set.of(6)));
        CentroidDecomposition centroidDecomposition = new CentroidDecomposition(adjList.size(), adjList);
    }
}
