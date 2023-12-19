package epp.array.revision;

import java.util.HashSet;
import java.util.Set;

public class PartialSodukuChecker {
    public static void main(String[] args) {
        int[][] soduku = new int[][]{
                {5,3,0,0,7,0,0,0,0},
                {6,0,0,1,9,5,0,0,0},
                {0,9,8,0,0,0,0,6,0},
                {8,0,0,0,6,0,0,0,3},
                {4,0,0,8,0,3,0,0,1},
                {7,0,0,0,2,0,0,0,6},
                {0,6,0,0,0,0,2,8,0},
                {0,0,0,4,1,9,0,0,5},
                {0,0,0,0,8,0,0,7,9}
        };
     boolean isValid =    checkSoduku(soduku);
        System.out.println(isValid);
    }

    private static boolean checkSoduku(int[][] soduku) {
        for(int i=0;i<soduku.length;i++){
            if(!isValidRow(soduku,i)){
                return false;
            }
        }
        for(int i=0;i<soduku[0].length;i++){
            if(!isValidColumn(soduku,i)){
                return false;
            }
        }
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(!isValidSubgroup(soduku, i,j)){
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isValidSubgroup(int[][] soduku,int x, int y) {
        Set<Integer> uniqueDigits = new HashSet<>();
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(soduku[x*3+ i][y*3+j]!=0 && uniqueDigits.contains(soduku[x*3+ i][y*3+j])){
                    return false;
                }
                uniqueDigits.add(soduku[x*3+ i][y*3+j]);
            }

        }
        return true;
    }

    private static boolean isValidColumn(int[][] soduku, int colNum) {
        Set<Integer> uniqueDigits = new HashSet<>();
        for(int i=0;i<soduku.length;i++){
            if(soduku[i][colNum]!=0 && uniqueDigits.contains(soduku[i][colNum])){
                return false;
            }
            uniqueDigits.add(soduku[i][colNum]);
        }
        return true;
    }

    private static boolean isValidRow(int[][] soduku, int rowNum) {
        Set<Integer> uniqueDigits = new HashSet<>();
        for(int i=0;i<soduku[0].length;i++){
            if(soduku[rowNum][i]!=0 && uniqueDigits.contains(soduku[rowNum][i])){
                return false;
            }
            uniqueDigits.add(soduku[rowNum][i]);
        }
        return true;
    }
}
