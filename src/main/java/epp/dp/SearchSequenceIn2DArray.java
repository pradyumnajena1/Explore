package epp.dp;

import epp.array.ArrayUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SearchSequenceIn2DArray {
    public static void main(String[] args) {
        int[][] matrix = ArrayUtils.createRandomMNMatrix(7, 7, 0, 7);
        ArrayUtils.print2DArray(matrix);
        boolean found =  findSequence(matrix,new int[]{2,3,4,5,6});
        System.out.println(found);
    }
    private static class Key{
        Position position;
        int offset;

        public Key(Position position, int offset) {
            this.position = position;
            this.offset = offset;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Key key = (Key) o;
            return offset == key.offset && Objects.equals(position, key.position);
        }

        @Override
        public int hashCode() {
            return Objects.hash(position, offset);
        }
    }

    private static boolean findSequence(int[][] matrix, int[] ints) {
        Map<Key,Boolean> cache = new HashMap<>();
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                if(matrix[i][j] == ints[0]){
                    Position startPosition = new Position(i, j);
                    boolean found =   findSequence(matrix,ints,0, startPosition,cache);
                    System.out.println(startPosition );
                  if(found){
                      return true;
                  }
                }
            }
        }
        return false;
    }

    private static boolean findSequence(int[][] matrix, int[] ints,int offset, Position startPosition,   Map<Key,Boolean> cache) {
        if(offset==ints.length){
            return true;
        }
        if(startPosition.x>=matrix.length || startPosition.x<0  || startPosition.y<0 || startPosition.y>=matrix[0].length){
            return false;
        }
        if(matrix[startPosition.x][startPosition.y]!=ints[offset]){
            return false;
        }
        Key key = new Key(startPosition, offset);
        if(cache.containsKey(key)){
            return cache.get(key);
        }
        Boolean result = false;
        Position[] offsets = new Position[]{new Position(0,1),new Position(0,-1),new Position(1,0),new Position(-1,0)};
        for(Position p:offsets){
            Position newPosition = new Position(startPosition.x + p.x, startPosition.y + p.y);
            if(findSequence(matrix,ints,offset+1, newPosition,cache)){
                result= true;
                break;
            }
        }


        cache.put(key,result);


        return result;
    }
}
