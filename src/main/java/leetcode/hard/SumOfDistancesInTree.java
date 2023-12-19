package leetcode.hard;

import epp.array.ArrayUtils;

import java.util.*;
import java.util.stream.Collectors;

public class SumOfDistancesInTree {
    public static void main(String[] args) {
       Solution solution = new Solution();
        int[] sumOfDistancesInTree = solution.sumOfDistancesInTree(6, new int[][]{{0, 1}, {0, 2}, {2, 3}, {2, 4}, {2, 5}});
        ArrayUtils.printArray(sumOfDistancesInTree);
    }
    private static class Solution {
        public int[] sumOfDistancesInTree(int n, int[][] edges) {
             int[][] dist = new int[n][n];
            Map<Integer, List<Integer>> edgeMap = getEdgeMap(edges);



            for(int i=0;i<n;i++){
                 for(int j=0;j<n;j++){
                     dist[i][j]=-1;
                 }
             }
            return getResult(n, dist, edgeMap);
        }

        private int[] getResult(int n, int[][] dist, Map<Integer, List<Integer>> edgeMap) {
            int[] result = new int[n];
            for(int i = 0; i< n; i++){
                bfs(i, edgeMap, dist);
                ArrayUtils.print2DArray(dist);

                int sum =0;
                for(int j = 0; j< n; j++){

                    sum+= dist[i][j];
                }
                result[i] = sum;
            }
            return result;
        }

        private static Map<Integer, List<Integer>> getEdgeMap(int[][] edges) {
            Map<Integer, List<Integer>> edgeMap = new HashMap<>();
            for(int[] edge: edges){
                List<Integer> list = edgeMap.getOrDefault(edge[0], new ArrayList<>());
                list.add(edge[1]);
                edgeMap.put(edge[0],list);

                list = edgeMap.getOrDefault(edge[1], new ArrayList<>());
                list.add(edge[0]);
                edgeMap.put(edge[1],list);

            }
            return edgeMap;
        }

        private void bfs(int source, Map<Integer, List<Integer>> edgeMap, int[][] dist) {
            System.out.println("bfs "+source);
            Queue<Integer> bfsQueue = new ArrayDeque<>();
            Set<Integer> visitedSet = new HashSet<>();
            bfsQueue.offer(source);
            visitedSet.add(source);
            dist[source][source] = 0;

            while (!bfsQueue.isEmpty()){
                Integer poll = bfsQueue.poll();

                List<Integer> edges = edgeMap.get(poll);
                if(edges!=null)
                for(int  neighbour:edges){
                    if(  !visitedSet.contains(neighbour)){
                        bfsQueue.offer(neighbour);
                        visitedSet.add(neighbour);

                        dist[source][neighbour] = dist[source][poll]+1;
                        dist[neighbour][source] = dist[poll][source]+1;
                    }
                }
            }
        }
    }
}
