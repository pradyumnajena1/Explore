package leetcode.hard;

import java.util.*;
import java.util.stream.Collectors;

public class LargestIsland {
    public static void main(String[] args) {
      Solution solution = new Solution();
        int largestIsland;
         /* largestIsland = solution.largestIsland(new int[][]{{1,0},{0,1}});
        System.out.println(largestIsland);

        largestIsland = solution.largestIsland(new int[][]{{1,1},{1,0}});
        System.out.println(largestIsland);

        largestIsland = solution.largestIsland(new int[][]{{1,1},{1,1}});
        System.out.println(largestIsland);

        largestIsland = solution.largestIsland(new int[][]{{0,0},{0,0}});
        System.out.println(largestIsland);*/

        largestIsland = solution.largestIsland(new int[][]{
                {0,0,0,0,0,0,0},
                {0,1,1,1,1,0,0},
                {0,1,0,0,1,0,0},
                {1,0,1,0,1,0,0},
                {0,1,0,0,1,0,0},
                {0,1,0,0,1,0,0},
                {0,1,1,1,1,0,0}
        });
        System.out.println(largestIsland);
    }
   private static class Solution {
        public int largestIsland(int[][] grid) {
            int maxArea = 0;

            for(int i=0;i<grid.length;i++){
                for(int j=0;j<grid[0].length;j++){
                    Point source = new Point(i, j);
                    if(grid[source.row][source.col] == 1  ){
                        int area =   getLargestArea(grid, source, 0,1);
                      maxArea = Math.max(maxArea,area);
                    }else if(grid[source.row][source.col] == 0  ){
                        grid[source.row][source.col] = 1;
                        int area =   getLargestArea(grid, source, 1,1);
                        maxArea = Math.max(maxArea,area);
                        grid[source.row][source.col] = 0;

                    }
                }
            }
            return maxArea;


        }

       private int getLargestArea(int[][] grid, Point source,  int changedCount,
                                  int maxChangeAllowed) {
           Queue<Point> bfsQueue = new ArrayDeque<>();
           Set<Point> visitedSet = new HashSet<>();
           bfsQueue.offer(source);
           visitedSet.add(source);
           int count = 0;
           List<Point> changedPoints = new ArrayList<>();

           while (!bfsQueue.isEmpty()){
               Point currentPoint = bfsQueue.poll();
               count++;
            List<Point> neighbours = Point. getNeighbourPoints(currentPoint,grid.length-1,grid[0].length-1);
            neighbours = neighbours.stream().filter(x->!visitedSet.contains(x)).collect(Collectors.toList());
            List<Point> validNeighbours =
                    neighbours.stream().filter(x->!visitedSet.contains(x) && grid[x.row][x.col]==1).collect(Collectors.toList());
            if(validNeighbours.size()==0 && changedCount<maxChangeAllowed){
                neighbours =   neighbours.stream().filter(x->grid[x.row][x.col]==0).collect(Collectors.toList());
                if(neighbours.size()>0){
                   Point changedPoint =   neighbours.remove(0);
                   changedPoints.add(changedPoint);
                    grid[changedPoint.row][changedPoint.col]=1;
                    validNeighbours.add(changedPoint);
                    changedCount++;
                }

            }
            for(Point point:validNeighbours){
                bfsQueue.offer(point);
                visitedSet.add(point);
            }

           }
           for(Point changedPoint: changedPoints  ){
               visitedSet.remove(changedPoint);
               grid[changedPoint.row][changedPoint.col]=0;
           }
           return count;
       }

   }

}
