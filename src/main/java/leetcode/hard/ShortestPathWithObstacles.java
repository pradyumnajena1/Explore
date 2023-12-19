package leetcode.hard;

import epp.array.ArrayUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ShortestPathWithObstacles {
    public static void main(String[] args) {
        Solution solution = new Solution();
       /*  solution.shortestPath(new int[][]{{0, 0, 0},
                {1, 1, 0},
                {0, 0, 0},
                {0, 1, 1},
                {0, 0, 0}}, 1);*/
        solution.shortestPath(new int[][]{{0, 1, 1}, {1, 1, 1}, {1, 0, 0}}, 3);
    }

    private static class Solution {

        public int shortestPath(int[][] grid, int k) {


            int shortestPath = getShortestPath(grid,k);
            System.out.println(shortestPath);

            return shortestPath;
        }



        private static int getShortestPath(int[][] grid, int k) {
            int[][] dist = new int[grid.length][grid[0].length];
            boolean[][] sptSet = new boolean[grid.length][grid[0].length];
            int blockedCount = 0;
            int unblockedCount = 0;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == 0) {
                        dist[i][j] = Integer.MAX_VALUE;
                        sptSet[i][j] = false;
                    } else {
                        blockedCount++;
                    }
                }
            }
            dist[0][0] = 0;
            for (int i = 0; i < grid.length * grid[0].length - 1 - (blockedCount-unblockedCount) ; i++) {
                Point u = getMin(grid, dist, sptSet);
                if(u==null){
                    break;
                }
                sptSet[u.row][u.col] = true;
                System.out.println(u);

                List<Point> neighbourPoints = getNeighbourPoints(u, grid.length - 1, grid[0].length - 1);
                List<Point> unblockedNeighbourPoints =
                        neighbourPoints.stream().filter(x -> grid[x.row][x.col] != 1).collect(Collectors.toList());
                if(neighbourPoints.size()>unblockedNeighbourPoints.size() &&   unblockedCount< k){
                    Point unblockedPoint =
                            neighbourPoints.stream().filter(x -> grid[x.row][x.col] == 1).findFirst().get();
                    unblockedNeighbourPoints.add(unblockedPoint);
                    dist[unblockedPoint.row][unblockedPoint.col]=Integer.MAX_VALUE;
                    grid[unblockedPoint.row][unblockedPoint.col]=0;
                    unblockedCount++;
                }
                if(unblockedNeighbourPoints.isEmpty()){
                    break;
                }
                for (Point v : unblockedNeighbourPoints) {

                    if (sptSet[v.row][v.col] == false && dist[u.row][u.col] != Integer.MAX_VALUE) {
                        if (dist[u.row][u.col] + 1 < dist[v.row][v.col]) {
                            dist[v.row][v.col] = dist[u.row][u.col] + 1;
                        }
                    }
                }
                ArrayUtils.print2DArray(dist);
            }
            return dist[grid.length - 1][grid[0].length - 1] == Integer.MAX_VALUE ? -1 : dist[grid.length - 1][grid[0].length - 1];
        }


    }

    private static Point getMin(int[][] grid, int[][] dist, boolean[][] sptSet) {
        int min = Integer.MAX_VALUE;
        Point minPoint = null;
        for (int i = 0; i < dist.length; i++) {
            for (int j = 0; j < dist[0].length; j++) {
                if (grid[i][j] == 0 && sptSet[i][j] == false) {
                    if (dist[i][j] < min) {
                        minPoint = new Point(i, j);
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

    private static class Point {
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

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Point{");
            sb.append("row=").append(row);
            sb.append(", col=").append(col);
            sb.append('}');
            return sb.toString();
        }
    }
}
