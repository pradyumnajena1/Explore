package epp.dp;

import epp.array.ArrayUtils;

import java.util.*;

public class FisherMansPath{
    public static void main(String[] args) {
        int numRows = 4;
        int numCols = 5;

        int[][] fishMatrix = ArrayUtils.createRandomMNMatrix(numRows, numCols, 0,5);
        ArrayUtils.print2DArray(fishMatrix);

        int maxValue = getMaxValue(fishMatrix,new Position(0,0),new Position(numRows-1,numCols-1));
        System.out.println(maxValue);
    }

    private static int getMaxValue(int[][] fishMatrix, Position start, Position end) {
        Map<Position,Integer> cache = new HashMap<>();
        return getMaxValue(fishMatrix,start,end,cache);
    }

    private static int getMaxValue(int[][] fishMatrix, Position start, Position end, Map<Position, Integer> cache) {
        if(start.equals(end)){
            return fishMatrix[start.x][start.y];
        }
        if(cache.containsKey(start)){
            return cache.get(start);
        }
        int right=0,down=0;
        if(start.x<fishMatrix.length-1){
            down =   fishMatrix[start.x][start.y]+ getMaxValue(fishMatrix,new Position(start.x+1, start.y),end,cache);
        }
        if(start.y<fishMatrix[0].length-1){
            right = fishMatrix[start.x][start.y] + getMaxValue(fishMatrix,new Position(start.x, start.y+1),end,cache);
        }
        int value = Math.max(down,right);
        cache.put(start,value);
        return value;
    }

}
