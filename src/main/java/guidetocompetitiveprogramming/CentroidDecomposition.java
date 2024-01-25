package guidetocompetitiveprogramming;

import epp.array.ArrayUtils;

import java.util.*;

public class CentroidDecomposition {
    private final List<List<Integer>> originalTree;
    private final int MaxNodes;
    private final int rootCentroid;
    private final List<List<Integer>> centroidTree;
    private final int[] parent;


    public CentroidDecomposition(int MaxNodes, List<List<Integer>> adjList) {
       this. MaxNodes = MaxNodes;
        this.originalTree = adjList;
        this.centroidTree = new ArrayList<>();
        this.parent = new int[MaxNodes+1];
        for(int i=0;i<=MaxNodes ;i++){
            this.centroidTree.add(new ArrayList<>());
        }
        boolean[] centroidMarked = new boolean[MaxNodes+1];
        this.rootCentroid = decomposeTree(1,centroidMarked);
        parent[rootCentroid] = rootCentroid;
      //  System.out.println(centroidTree);

    }





    private int decomposeTree(int root, boolean[] centroidMarked) {
        int treeCentroid = getCentroid(root,centroidMarked);
        for (int c : originalTree.get(treeCentroid)) {
            if (!centroidMarked[c]) {
                int subtreeCentroid = decomposeTree(c, centroidMarked);

                centroidTree.get(treeCentroid).add(subtreeCentroid);
                centroidTree.get(subtreeCentroid).add(treeCentroid);
                parent[subtreeCentroid]=treeCentroid;
            }
        }

        return treeCentroid;
    }
    private int getCentroid(int root, boolean[] centroidMarked) {

        int[] subTreeSizes = new int[MaxNodes+1];
        boolean[] visited = new boolean[MaxNodes+1];
        Arrays.fill(subTreeSizes, 0);
        Arrays.fill(visited, false);

        dfsSize(root, visited, subTreeSizes,centroidMarked);
        Arrays.fill(visited, false);
        int centroid = dfsCentroid(root, subTreeSizes, visited, subTreeSizes[root],centroidMarked);
        centroidMarked[centroid] = true;
        return centroid;

    }

    private int dfsCentroid(int root, int[] sizes, boolean[] visited, int n, boolean[] centroidMarked) {
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
        return dfsCentroid(maxChildId, sizes, visited, n, centroidMarked);

    }
    public int getParent(int node){

        return parent[node];
    }

    private void dfsSize(int root, boolean[] visited, int[] subTreeSizes, boolean[] centroidMarked) {
        visited[root] = true;
        subTreeSizes[root] = 1;
        for (int c : originalTree.get(root)) {
            if (!visited[c] && !centroidMarked[c]) {
                dfsSize(c, visited, subTreeSizes, centroidMarked);
                subTreeSizes[root] += subTreeSizes[c];
            }
        }
    }
    public List<List<Integer>> getCentroidTree(){
        return centroidTree;
    }
    public int getRootCentroid(){
        return rootCentroid;
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
