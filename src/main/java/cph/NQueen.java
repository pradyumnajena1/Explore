package cph;

import java.util.ArrayList;
import java.util.List;

public class NQueen {

    public static void main(String[] args) {
        List<List<Integer>> nQueens = findNQueens(4);
        System.out.println(nQueens);
    }

    private static List<List<Integer>> findNQueens(int n) {
          List<Integer> columns= new ArrayList<>();
          List<List<Integer>> resultCollector = new ArrayList<>();
          findNQueens(n,0,columns, resultCollector);
          return resultCollector;
    }

    private static  void findNQueens(int n, int i, List<Integer> columns,List<List<Integer>> resultCollector) {
        if(i==n){
           resultCollector.add(new ArrayList<>(columns));
           return;
        }
        for(int j=0;j<n;j++){
            columns.add(j);
            if(validPosition(columns)){
                findNQueens(n,i+1,columns,resultCollector);
            }
            columns.remove(columns.size()-1);
        }

    }

    private static boolean validPosition(List<Integer> result ) {
        int j = result.size()-1;
        for(int i=0;i<j;i++){
            int diff = Math.abs(result.get(j)- result.get(i));
            if(diff==0|| diff == j-i){
                return false;
            }
        }
        return true;
    }
}
