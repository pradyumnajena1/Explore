package guidetocompetitiveprogramming;

import java.util.*;

public class NearestRedNodeQueryProcessor {
    private final DistanceBetweenNodesQueryProcessor distanceBetweenNodesQueryProcessor;
    private final CentroidDecomposition centroidDecomposition;
    private int[] ans;

    public NearestRedNodeQueryProcessor(List<List<Integer>> adjList) {
        this.distanceBetweenNodesQueryProcessor= new DistanceBetweenNodesQueryProcessor(adjList);
        this. centroidDecomposition = new CentroidDecomposition(adjList);
        ans = new int[adjList.size()];
        Arrays.fill(ans,Integer.MAX_VALUE);
    }
    public void update(int node){

        int x=node;
        while (true){
            ans[x] = Math.min(ans[x],distanceBetweenNodesQueryProcessor.getDistance(node,x));
            int parent  = centroidDecomposition.getParent(x);
            if(parent==x){
                break;
            }
            x=parent;
        }


    }
    public int getNearestRedNode(int node){
        int x=node;
        int result = Integer.MAX_VALUE;
        while (true){
            result = Math.min(result,ans[x]<Integer.MAX_VALUE? ans[x]+ distanceBetweenNodesQueryProcessor.getDistance(node,x):Integer.MAX_VALUE);
            int parent  = centroidDecomposition.getParent(x);
            if(parent==x){
                break;
            }
            x=parent;
        }
        return result;
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

        NearestRedNodeQueryProcessor nearestRedNodeQueryProcessor = new NearestRedNodeQueryProcessor(adjList);
        nearestRedNodeQueryProcessor.update(1);
        System.out.println(nearestRedNodeQueryProcessor.getNearestRedNode(3));
        System.out.println(nearestRedNodeQueryProcessor.getNearestRedNode(8));
        nearestRedNodeQueryProcessor.update(5);
        System.out.println(nearestRedNodeQueryProcessor.getNearestRedNode(8));
    }
}
