package epp.binarySearch.revision;

import epp.array.ArrayUtils;

public class SearchInIncreasingMatrix {
    public static void main(String[] args) {
        int[][] matrix = ArrayUtils.createRandomIncreasingMNMatrix(4, 5);
        ArrayUtils.print2DArray(matrix);
        Location location =  search(matrix,14);
        System.out.println(location);
    }

    private static Location search(int[][] matrix, int value) {
        int row = 0;
        int col = matrix[0].length-1;
        while (row<matrix.length && col>=0){
            if(matrix[row][col]==value){
                return new Location(row,col);
            } else if (matrix[row][col]<value) {
                row++;
            }else{
                col--;
            }
        }
        return null;
    }

    private static class Location{
        int x,y;

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Location{");
            sb.append("x=").append(x);
            sb.append(", y=").append(y);
            sb.append('}');
            return sb.toString();
        }
    }
}
