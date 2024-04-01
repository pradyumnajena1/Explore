package amaze;

import epp.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NumberOfIslandsUsingDFS {
    public static void main(String[] args) {
       int[][] mat  = {
               {1, 1, 0, 0, 0},
            {0, 1, 0, 0, 1},
            {1, 0, 0, 1, 1},
            {0, 0, 0, 0, 0},
            {1, 0, 1, 0, 0}};
       int numIslands = findNumIslands(mat);
        System.out.println(numIslands);
    }

    private static int findNumIslands(int[][] mat) {
        int count = 0;
        Set<Pair<Integer,Integer>> visitedSet = new HashSet<>();
        for(int i=0;i<mat.length;i++){
            for(int j=0;j<mat[0].length;j++){
               Pair<Integer,Integer> currentPos = new Pair<>(i,j);
               if(mat[currentPos.getFirst()][currentPos.getSecond()]==1&& !visitedSet.contains(currentPos)){
                   count++;
                   dfs(mat,currentPos,visitedSet);
               }
            }
        }
        return count;
    }

    private static void dfs(int[][] mat, Pair<Integer, Integer> currentPos, Set<Pair<Integer, Integer>> visitedSet) {
        visitedSet.add(currentPos);
        List<Pair<Integer,Integer>> adjcents = getAdjcents(mat,currentPos);
        for(Pair<Integer,Integer> adj:adjcents){
            if(!visitedSet.contains(adj) && mat[adj.getFirst()][adj.getSecond()]==1){
                dfs(mat,adj,visitedSet);
            }
        }
    }

    private static List<Pair<Integer, Integer>> getAdjcents(int[][] mat, Pair<Integer, Integer> currentPos) {
        ArrayList<Pair<Integer, Integer>> adjcents = new ArrayList<>();
         for(int i=-1;i<=1;i++){
             for(int j=-1;j<=1;j++){
                 Pair<Integer, Integer> pair = new Pair<>(currentPos.getFirst() + i, currentPos.getSecond() + j);
                 if(pair.getFirst()>=0 && pair.getFirst()< mat.length && pair.getSecond()>=0 && pair.getSecond()<mat[0].length)
                 adjcents.add(pair);
             }
         }
        return adjcents;
    }
}
