package epp.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class Location {
    int x;
    int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void setValue(int[][] matrix){
        setValue(matrix,1);
    }
    public void resetValue(int[][] matrix){
        setValue(matrix,0);
    }

    public void setValue(int[][] matrix, int value) {
        matrix[x][y] = value;
    }
    public int getValue(int[][] matrix) {
      return  matrix[x][y] ;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("(");
        sb.append(x);
        sb.append(", ").append(y);
        sb.append(')');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return x == location.x && y == location.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public boolean validLocation(int[][] maze) {
        return x>=0 && x<maze.length && y>=0 && y<maze[0].length;
    }

    public   List<Location> getNeighbours(  int[][] maze) {
        List<Location> result = new ArrayList<>();
        for(int i=-1;i<=1;i++){
            for(int j=-1;j<=1;j++){
                Location newLocation = new Location( x+i, y+j);
                if(newLocation.validLocation(maze) && !this.equals(newLocation) ){
                    result.add(newLocation);
                }
            }
        }
        return result;
    }

    public   List<Location> getNeighboursUpDownLeftRight(  int[][] maze) {
        List<Location> result = new ArrayList<>();
        for(int i=-1;i<=1;i++){
            for(int j=-1;j<=1;j++){

                if(Math.abs(i) + Math.abs(j)==1){
                    Location newLocation = new Location( x+i, y+j);
                    if(newLocation.validLocation(maze) && !this.equals(newLocation) ){
                        result.add(newLocation);
                    }
                }

            }
        }
        return result;
    }
}
