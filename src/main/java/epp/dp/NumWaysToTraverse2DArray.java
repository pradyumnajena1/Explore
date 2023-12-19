package epp.dp;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class NumWaysToTraverse2DArray {
    private static class Position{
        int x,y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return x == position.x && y == position.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
    public static void main(String[] args) {
        int numways  = getNumWays(2,3,new Position(0,0),new Position(1,2));
        System.out.println(numways);
    }

    private static int getNumWays(int numRows, int numCols, Position start, Position end) {
        Map<Position,Integer> cache = new HashMap<>();
        return getNumWays(numRows,numCols,start,end,cache);
    }

    private static int getNumWays(int numRows, int numCols, Position start, Position end, Map<Position, Integer> cache) {
        if(start.equals(end)){
            return 1;
        }
        if (cache.containsKey(start)){
            return cache.get(start);
        }
        int num = 0;
        if(start.x<numRows-1){
            num+= getNumWays(numRows,numCols,new Position(start.x+1,start.y),end,cache);
        }
        if(start.y<numCols-1){
            num+=getNumWays(numRows,numCols,new Position(start.x,start.y+1),end,cache);
        }
        cache.put(start,num);

        return num;
    }
}
