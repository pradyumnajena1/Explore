package leetcode.hard;

import java.util.*;
import java.util.stream.Collectors;

public class SwimInWater {
    public static void main(String[] args) {
        int waitTime     = swimInWater(new int[][]{
                {0, 1, 2, 3, 4},
                {24, 23, 22, 21, 5},
                {12, 13, 14, 15, 16},
                {11, 17, 18, 19, 20},
                {10, 9, 8, 7, 6}
        });
        System.out.println(waitTime);

        waitTime     = swimInWater(new int[][]{
                {3,2},{0,1}
        });
        System.out.println(waitTime);
    }

    public static int swimInWater(int[][] grid) {
        int[][] dist = new int[grid.length][grid[0].length];
        boolean[][] sptSet  = new boolean[grid.length][grid[0].length];
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                dist[i][j] = Integer.MAX_VALUE;
                sptSet[i][j] = false;
            }
        }
        dist[0][0] = 0;
        for(int vertex = 0;vertex< grid.length* grid[0].length-1;vertex++){

            Point u = getMin(dist,sptSet);
            sptSet[u.row][u.col] = true;

            List<Point> neighbourPoints = getNeighbourPoints(u, grid.length-1, grid[0].length-1);
            for(Point neighbour:neighbourPoints){
                if(!sptSet[neighbour.row][neighbour.col]){
                    int waitTime = Math.max(0, grid[neighbour.row][neighbour.col] - grid[u.row][u.col]);
                    if(dist[u.row][u.col]!=Integer.MAX_VALUE && dist[u.row][u.col]+waitTime<dist[neighbour.row][neighbour.col] ){
                        dist[neighbour.row][neighbour.col] = dist[u.row][u.col]+waitTime;
                    }
                }
            }
        }
       return dist[grid.length-1][grid[0].length-1];

    }

    private static Point getMin(int[][] dist, boolean[][] sptSet) {
        int min = Integer.MAX_VALUE;
        Point minPoint = null;
        for(int i=0;i<dist.length;i++){
            for(int j=0;j<dist[0].length;j++){
                if(sptSet[i][j]==false ){
                    if(dist[i][j]<min){
                        minPoint = new Point(i,j);
                        min = dist[i][j];
                    }
                }
            }
        }
        return minPoint;
    }

    private static List<Point> getNeighbourPoints(Point point, int maxRow, int maxCol) {
        ArrayList<Point> points = new ArrayList<>();
        for (int i = -1; i <= 1; i++)
            for (int j = -1; j <= 1; j++) {

                if (i * j == 0 && i + j != 0) {
                    if (point.row + i >= 0 && point.row + i <= maxRow && point.col + j >= 0 && point.col + j <= maxCol) {
                        Point newPoint = new Point(point.row + i, point.col + j);
                        points.add(newPoint);
                    }
                }
            }

        return points;
    }

    private static class Point   {
        int row;
        int col;


        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Point point = (Point) o;

            if (row != point.row) return false;
            return col == point.col;
        }

        @Override
        public int hashCode() {
            int result = row;
            result = 31 * result + col;
            return result;
        }


    }
}
