package epp.dp.revision;

import epp.Pair;
import epp.array.ArrayUtils;

import java.util.StringJoiner;

public class Maximum2DSubArray {
    public static void main(String[] args) {
        int[][] matrix =   ArrayUtils.createRandomMNMatrix(10, 15, 0, 2);
        ArrayUtils.print2DArray(matrix);
        Pair<Integer, Integer> result = findMaximum2DSubArray(matrix,true);
        System.out.println(result);
    }

    private static Pair<Integer, Integer> findMaximum2DSubArray(int[][] matrix,boolean onlySquare) {
        HeightWidth[][] heightWidth = new HeightWidth[matrix.length][matrix[0].length];

        for (int row = 0; row < heightWidth.length; row++) {
            for (int col = 0; col < heightWidth[0].length; col++) {
                heightWidth[row][col] = matrix[row][col] == 1 ?
                        new HeightWidth(1 + (row>0? heightWidth[row - 1][col].height:0),
                                1 + (col>0? heightWidth[row][col - 1].width:0)) :
                        new HeightWidth(0, 0);
            }
        }
       // ArrayUtils.print2DArray(heightWidth);
        Pair<Integer, Integer> maxAreaLocation = null;
        Integer maxAreaValue = 0;
        for (int row = 0; row < heightWidth.length; row++) {
            for (int col = 0; col < heightWidth[0].length; col++) {

                int width = Integer.MAX_VALUE;
                int area = 0;
                for (int r = row; r > row - heightWidth[row][col].height; r--) {
                    width = Math.min(width, heightWidth[r][col].width);
                    int height = row - r + 1;
                    if(onlySquare  ){
                        int side = Math.min(width,height);
                        area = Math.max(area,  side * side);
                    }else{
                        area = Math.max(area,  width * height);
                    }
                }
                if(area>maxAreaValue){
                    maxAreaValue = area;
                    maxAreaLocation= new Pair<>(row,col);
                }
            }
        }

        return maxAreaLocation;
    }

    private static class HeightWidth {
        int height;
        int width;

        public HeightWidth(int height, int width) {
            this.height = height;
            this.width = width;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", HeightWidth.class.getSimpleName() + "[", "]")
                    .add("height=" + height)
                    .add("width=" + width)
                    .toString();
        }
    }
}
