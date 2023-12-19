package hackerrank.medium;

import java.util.ArrayList;
import java.util.List;

public class SurfaceArea {
    public static void main(String[] args) {
        System.out.println(surfaceArea(new ArrayList<>(List.of(List.of(1, 3, 4),
                List.of(2, 2, 3),
                List.of(1, 2, 4)))));
    }

    public static int surfaceArea(List<List<Integer>> A) {
        // Write your code here
        int maxRow = A.size()-1;
        int maxCol = A.get(0).size()-1;
        int totalSurfaceArea = 0;
        for(int i=0;i<=maxRow;i++){
            for(int j=0;j<=maxCol;j++){

                int leftSurface =  Math.max(0, getHeight(A,i,j) - getHeight(A,i ,j-1));
                int rightSurface =  Math.max(0, getHeight(A,i,j) - getHeight(A,i ,j+1));
                int upSurface =  Math.max(0, getHeight(A,i,j) - getHeight(A,i-1,j));
                int downSurface =  Math.max(0, getHeight(A,i,j) - getHeight(A,i+1,j));

                int surfaceAreaForCell = (leftSurface + rightSurface + downSurface + upSurface) + 1;
                if(getHeight(A,i,j)>0){
                    //for bottom
                    surfaceAreaForCell =  surfaceAreaForCell + 1;
                }
              //  System.out.println(surfaceAreaForCell);
                totalSurfaceArea+= surfaceAreaForCell;

            }
        }

         return totalSurfaceArea   ;
    }

    private static int getHeight(List<List<Integer>> A,int i,int j){
        if(i>=A.size() || i<0){
            return 0;
        }
        if(j>=A.get(0).size()||j<0){
            return 0;
        }
        return A.get(i).get(j);
    }
}
