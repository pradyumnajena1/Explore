package guidetocompetitiveprogramming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MergeDataStructureQueryProcessor {
    private final List<List<Integer>> adjList;
    private final int root;
    private final Map<Integer,Integer>[] frequencies;

    public MergeDataStructureQueryProcessor(List<List<Integer>> adjList, int[] nodeValues, int root) {
        this.adjList = adjList;
        this.root = root;

        this. frequencies = new Map[adjList.size()];
        dfs(adjList,nodeValues, root,0,frequencies);
    }
    public int getCount(int root,int value){
        return frequencies[root].get(value);
    }

    private void dfs(List<List<Integer>> adjList, int[] nodeValues, int root, int parent, Map<Integer, Integer>[] frequencies) {
        frequencies[root] = new HashMap<>();
        frequencies[root].put(nodeValues[root],1);
        for(int c:adjList.get(root)){
            if(c!=parent){
                dfs(adjList,nodeValues,c,root,frequencies);
                merge(frequencies,root,c) ;

            }
        }
    }

    private void merge(Map<Integer, Integer>[] frequencies, int a,  int b) {
        for(Map.Entry<Integer,Integer> e:frequencies[b].entrySet()){
            frequencies[a].merge(e.getKey(),e.getValue(), Integer::sum);
        }

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

        MergeDataStructureQueryProcessor queryProcessor = new MergeDataStructureQueryProcessor(adjList, nodeValues,1);
        System.out.println(queryProcessor.getCount(1, 3));
    }
}
