package leetcode.hard;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UniquePathsIII {
    public static void main(String[] args) {
     Solution solution = new Solution();
        System.out.println(solution.uniquePathsIII(new int[][]{{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 2, -1}}));
        System.out.println(solution.uniquePathsIII(new int[][]{{1,0,0,0},{0,0,0,0},{0,0,0,2}}));
    }

   private static class Solution {
        public int uniquePathsIII(int[][] grid) {
            Point source = null;
            Point destination =null;
            int validPoints = 0;
            for(int i=0;i< grid.length;i++){
                for(int j=0;j<grid[0].length;j++){
                    if(grid[i][j]==1){
                        source = new Point(i,j);
                    } else if(grid[i][j]==2){
                        destination = new Point(i,j);
                    }else if(grid[i][j]==0){
                        validPoints++;
                    }
                }
            }
            validPoints+=2;
            List<List<Point>> uniquePaths = new ArrayList<>();
            dfs(grid,source,destination,new ArrayList<>(),uniquePaths,validPoints);
            System.out.println(uniquePaths);
            return uniquePaths.size();

        }

       private void dfs(int[][] grid, Point source, Point destination, ArrayList<Point> path,
                        List<List<Point>> uniquePaths, int validPoints) {

           path.add(source);
           if(source.equals(destination)){
               if(path.size()==validPoints){
                   uniquePaths.add(path);
                   return;
               }
           }

           List<Point> neighbourPoints = Point.getNeighbourPoints(source, grid.length - 1, grid[0].length - 1);
           neighbourPoints = neighbourPoints.stream().filter(x->grid[x.row][x.col]!=-1).collect(Collectors.toList());
           for(Point point:neighbourPoints){
               if(!path.contains(point)){
                   ArrayList<Point> newPath = new ArrayList<>(path);
                   dfs(grid,point,destination,newPath,uniquePaths,validPoints);
               }
           }
       }
   }
}
