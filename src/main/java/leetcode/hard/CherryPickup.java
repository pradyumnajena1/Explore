package leetcode.hard;

import epp.array.ArrayUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class CherryPickup {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int cherryPickup = solution.cherryPickup(new int[][]{
                {0, 1, -1},
                {1, 0, -1},
                {1, 1, 1}
        });
        System.out.println(cherryPickup);
        cherryPickup = solution.cherryPickup(new int[][]{
                {1,1,-1},
        {1,-1,1},
            {-1,1,1}
        });
        System.out.println(cherryPickup);
    }

   private static class Solution {
        public int cherryPickup(int[][] grid) {
            ArrayUtils.print2DArray(grid);
          Point source = new Point(0,0);
          Point destination = new Point(grid.length-1,grid[0].length-1);
            List<Point> maxPath = getMaxPath(grid, source, destination );
            if(maxPath==null){
                return 0;
            }
            int pathSum = getPathSum(maxPath, grid);
            for(Point point:maxPath){
                grid[point.row][point.col] = 0;
            }
            ArrayUtils.print2DArray(grid);

            List<Point> maxPath2 = getMaxPathReturn(grid, destination, source );
            if(maxPath2==null){
                return 0;
            }

            int pathSumReturn = getPathSum(maxPath2, grid);
            return pathSum + pathSumReturn;

        }

       private List<Point> getMaxPath(int[][] grid, Point source, Point destination ) {
            if(source.equals(destination)){
                if(grid[source.row][source.col]>=0){
                    ArrayList<Point> points = new ArrayList<>();
                    points.add(source);
                    return points;
                }else{
                    return null;
                }

            }
           List<Point> rightPath = null;
           List<Point> downPath = null;
            if(source.col<grid[0].length-1  ){

                Point right = new Point(source.row,source.col+1);
                if(grid[right.row][right.col]>=0  ){
                      rightPath = getMaxPath(grid, right, destination );
                }
            }
            if(source.row<grid.length-1){
                Point down = new Point(source.row+1,source.col);
                if(grid[down.row][down.col]>=0  ){
                    downPath = getMaxPath(grid, down, destination );
                }
            }
            if(rightPath==null&&downPath==null){
                return  null;
            }
           List<Point> maxPath = null;
            if(getPathSum(rightPath,grid)>getPathSum(downPath,grid)){
                maxPath = rightPath;
            }else{
                maxPath = downPath;
            }
            maxPath.add(0,source);
           return maxPath;
       }

       private List<Point> getMaxPathReturn(int[][] grid, Point source, Point destination ) {
           if(source.equals(destination)){
               if(grid[source.row][source.col]>=0){
                   ArrayList<Point> points = new ArrayList<>();
                   points.add(source);
                   return points;
               }else{
                   return null;
               }

           }
           List<Point> leftPath = null;
           List<Point> upPath = null;
           if(source.col>0   ){

               Point left = new Point(source.row,source.col-1);
               if(grid[left.row][left.col]>=0  ){
                   leftPath = getMaxPathReturn(grid, left, destination );
               }
           }
           if(source.row>0 ){
               Point up = new Point(source.row-1,source.col);
               if(grid[up.row][up.col]>=0  ){
                   upPath = getMaxPathReturn(grid, up, destination );
               }
           }
           if(leftPath==null&&upPath==null){
               return  null;
           }
           List<Point> maxPath = null;
           if(leftPath==null){
               maxPath = upPath;
           }else if(upPath==null){
               maxPath = leftPath;
           }
           else if( getPathSum(leftPath,grid)>getPathSum(upPath,grid)){
               maxPath = leftPath;
           }else{
               maxPath = upPath;
           }
           maxPath.add(0,source);
           return maxPath;
       }

       private int getPathSum(List<Point> path, int[][] grid) {
            if(path==null){
                return 0;
            }
            int sum =0;
            for(Point aPoint:path){
                sum+=grid[aPoint.row][aPoint.col];
            }
           return sum;
       }


   }
}
