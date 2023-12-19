package epp.recursion;

import java.util.*;
import java.util.stream.Collectors;

public class NQueen {

    private static class Position{
        int row;
        int col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return row == position.row && col == position.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Position{");
            sb.append("row=").append(row);
            sb.append(", col=").append(col);
            sb.append('}');
            return sb.toString();
        }
    }
    public static void main(String[] args) {
        List<Position> solution = solveNqueen(4);
        System.out.println("solution"+ solution);

        System.out.println(isValidSolution(List.of( new Position(0,2) ,new Position(1, 0), new Position(2, 3),
                        new Position(3,1) )
                , 3, 4));

        System.out.println(isValidSolution(List.of( new Position(0,0) ,new Position(1, 0), new Position(2, 0),
                        new Position(3,0) )
                , 3, 4));
    }

    private static List<Position> solveNqueen(int n) {
         List<Position> solution = new ArrayList<>();
         for(int i=0;i<n;i++){
             solution.add(new Position(i,-1));
         }
         solveNqueen(0,n,solution);
         return solution;
    }

    private static boolean solveNqueen(int row,int n, List<Position> solution) {
        if(row == n && isValidSolution(solution,row-1,n)){
            return  true;
        }
        solution.set(row, new Position(row,-1));
        for(int i=0;i<n;i++){
            solution.set(row, new Position(row,i) );
            if(isValidSolution(solution,row,n) && solveNqueen(row+1,n,solution)){
                return true;
            }
        }
        solution.set ( row, new Position(row,-1));
        return false;
    }

    private static boolean isValidSolution(List<Position> solution, int row,int n) {
        //System.out.println(solution);
        Set<Position> previousPlacements = new HashSet<>( solution.subList(0,row));
        int currentRow = row;
        int currentCol = solution.get(row).col;
        Set<Integer> rowsSet = previousPlacements.stream().map(x -> x.row).collect(Collectors.toSet());
        Set<Integer> colsSet = previousPlacements.stream().map(x -> x.col).collect(Collectors.toSet());

        if(rowsSet.contains(currentRow)||colsSet.contains(currentCol)){
            return false;
        }

        for(int r =currentRow+1,c=currentCol+1;c<n && r<n;r++,c++ ){
            if(previousPlacements.contains(new Position(r,c))){
                return false;
            }
        }
        for(int r =currentRow-1,c=currentCol-1;c>=0 && r>=0;r--,c-- ){
            if(previousPlacements.contains(new Position(r,c))){
                return false;
            }
        }
        for(int r =currentRow-1,c=currentCol+1;r>=0 && c<n;r--,c++ ){
            if(previousPlacements.contains(new Position(r,c))){
                return false;
            }
        }
        for(int r =currentRow+1,c=currentCol-1;r<n && c>=0;r++,c-- ){
            if(previousPlacements.contains(new Position(r,c))){
                return false;
            }
        }


        return true;
    }
}
