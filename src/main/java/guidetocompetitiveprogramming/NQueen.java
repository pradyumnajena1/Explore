package guidetocompetitiveprogramming;

import java.util.ArrayList;
import java.util.List;

public class NQueen {
    public static void main(String[] args) {
              placeNQueen(0,4,new ArrayList<>());
    }
    private static void placeNQueen(int i, int n, List<Integer> solution){
        if(i==n){
            System.out.println(solution);
            return;
        }
        for(int col=0;col<n;col++){
            solution.add(col);
            if(feasibleColumnPlacement(solution,i)){
                placeNQueen(i+1,n,solution);
            }
            solution.remove(solution.size()-1);
        }
    }
    private static boolean feasibleColumnPlacement(List<Integer> columns, int row) {
        for (int i = 0; i < row; i++) {
            int diff = Math.abs(columns.get( i) - columns.get(row));
            if (diff == 0 || diff == row - i) {
                return false;
            }
        }
        return true;
    }
}
