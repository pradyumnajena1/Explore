package guidetocompetitiveprogramming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class LCAQueryProcessor {


    private final int root;
    private final List<List<Integer>> adjList;
    private final RootedTreeAncestor rootedTreeAncestor;
    private final int[] nodeIds;
    private final int[] levels;
    private final HashMap<Integer, Integer> indexMap;


    public LCAQueryProcessor(List<List<Integer>> adjList, int root) {
        this.root = root;
        this.adjList = adjList;
        this.rootedTreeAncestor = new RootedTreeAncestor(adjList, root);
        this.nodeIds = new int[adjList.size()];
        this.levels = new int[adjList.size()];
        dfsDepths(adjList, root, 0, 0, new AtomicInteger(0));
        this.indexMap = getIndexMap();
    }

    public int getLowestCommonAncestor(int a, int b) {
        int al = levels[indexMap.get(a)];
        int bl = levels[indexMap.get(b)];
        if (al == bl) {
            return getLowestCommonAncestor(a, b, al);
        } else {
            if (al < bl) {

                int ancestor = rootedTreeAncestor.ancestor(b, bl - al);
                return getLowestCommonAncestor(a, ancestor, al);
            } else {

                int ancestor = rootedTreeAncestor.ancestor(a, al - bl);
                return getLowestCommonAncestor(ancestor, b, bl);
            }

        }
    }

    private int getLowestCommonAncestor(int a, int b, int level) {
        int low = 1;
        int high = level ;
        int lowest = 1;
        while (low <= high) {
            int mid = (low + high) / 2;

            if (rootedTreeAncestor.ancestor(a,level- mid) == rootedTreeAncestor.ancestor(b,level- mid)) {
                lowest = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return rootedTreeAncestor.ancestor(a,level- lowest);
    }

    private HashMap<Integer, Integer> getIndexMap() {
        final HashMap<Integer, Integer> indexMap;
        indexMap = new HashMap<>();
        for (int i = 0; i < nodeIds.length; i++) {
            indexMap.put(nodeIds[i], i);
        }
        return indexMap;
    }

    private void dfsDepths(List<List<Integer>> adjList, int root, int parent, int level, AtomicInteger index) {
        int currentIndex = index.incrementAndGet();
        nodeIds[currentIndex] = root;
        levels[currentIndex] = level + 1;
        for (int c : adjList.get(root)) {
            if (c != parent) {

                dfsDepths(adjList, c, root, level + 1, index);
            }

        }

    }
    public int getDepth(int a) {
        Integer index = indexMap.get(a);
        return levels[index]-1;
    }

    public static void main(String[] args) {
        List<List<Integer>> adjList = new ArrayList<>();
        adjList.add(new ArrayList<>());
        adjList.add(new ArrayList<>(List.of(2, 3, 4)));
        adjList.add(new ArrayList<>(List.of(1, 5, 6)));
        adjList.add(new ArrayList<>(List.of(1)));
        adjList.add(new ArrayList<>(List.of(1, 7)));
        adjList.add(new ArrayList<>(List.of(2)));
        adjList.add(new ArrayList<>(List.of(2, 8)));
        adjList.add(new ArrayList<>(List.of(4)));
        adjList.add(new ArrayList<>(List.of(6)));
        int root = 1;
        LCAQueryProcessor lowestCommonAncestorQueryProcessor = new LCAQueryProcessor(adjList, root);
        System.out.println(lowestCommonAncestorQueryProcessor.getLowestCommonAncestor(5, 8));
    }


}
