package guidetocompetitiveprogramming;

import epp.array.ArrayUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class PathSumQueryProcessor {
    private final List<List<Integer>> adList;
    private final int[] nodeIds;
    private final int[] sizes;
    private final int[] pathSums;
    private final HashMap<Integer, Integer> indexMap;
    private final RangeUpdateSegmentTree segmentTree;

    public PathSumQueryProcessor(List<List<Integer>> adjList, int[] nodeValues, int root) {
        this.adList = adjList;
        this.nodeIds = new int[adjList.size()];
        this.sizes = new int[adjList.size()];
        this.pathSums = new int[adjList.size()];

        dfsSizes(root, 0, nodeValues, 0, new AtomicInteger(1));
        ArrayUtils.printArray(nodeIds);
        ArrayUtils.printArray(sizes);
        ArrayUtils.printArray(pathSums);
        this.indexMap = getIndexMap();
        this.segmentTree = new RangeUpdateSegmentTree(pathSums);
    }


    private HashMap<Integer, Integer> getIndexMap() {
        final HashMap<Integer, Integer> indexMap;
        indexMap = new HashMap<>();
        for (int i = 0; i < nodeIds.length; i++) {
            indexMap.put(nodeIds[i], i);
        }
        return indexMap;
    }

    private void dfsSizes(int root, int parent, int[] nodeValues, int pathSum, AtomicInteger index) {
        int currentIndex = index.get();
        this.nodeIds[currentIndex] = root;
        this.sizes[currentIndex] = 1;
        this.pathSums[currentIndex] = pathSum + nodeValues[root];
        for (int c : adList.get(root)) {
            if (c != parent) {
                int childIndex = index.incrementAndGet();
                dfsSizes(c, root, nodeValues, this.pathSums[currentIndex], index);
                sizes[currentIndex] += sizes[childIndex];
            }
        }
    }

    private void updateNode(int node, int delta) {
        Integer index = indexMap.get(node);
        segmentTree.rangeUpdate(index, index + sizes[index] - 1, delta);
    }

    private int getPathSum(int node) {
        Integer index = indexMap.get(node);
        return segmentTree.getValue(index);
    }

    public static void main(String[] args) {
        List<List<Integer>> adjList = new ArrayList<>();
        adjList.add(new ArrayList<>());
        adjList.add(new ArrayList<>(List.of(2, 3, 4, 5)));
        adjList.add(new ArrayList<>(List.of(1, 6)));
        adjList.add(new ArrayList<>(List.of(1)));
        adjList.add(new ArrayList<>(List.of(1, 7, 8, 9)));
        adjList.add(new ArrayList<>(List.of(1)));
        adjList.add(new ArrayList<>(List.of(2)));
        adjList.add(new ArrayList<>(List.of(4)));
        adjList.add(new ArrayList<>(List.of(4)));
        adjList.add(new ArrayList<>(List.of(4)));
        int[] nodeValues = new int[]{0, 4, 5, 3, 5, 2, 3, 5, 3, 1};

        int root = 1;
        PathSumQueryProcessor pathSumQueryProcessor = new PathSumQueryProcessor(adjList, nodeValues, root);
        System.out.println(pathSumQueryProcessor.getPathSum(7));
        pathSumQueryProcessor.updateNode(4, 3);
        System.out.println(pathSumQueryProcessor.getPathSum(7));
    }


}
