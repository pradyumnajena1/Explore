package epp.array;

import java.util.HashSet;
import java.util.Set;

public class SodukuChecker {

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
        boolean verify = checkSoduku(soduku);
        System.out.println(verify);
    }

    private static boolean checkSoduku(int[][] soduku) {
        for(int rg=0;rg<3;rg++){
            for(int cg=0;cg<3;cg++){
              boolean valid=  checkSoduku(soduku,3*rg,3*rg+2,3*cg,3*cg+2);
              if(!valid){
                  return false;
              }
            }
        }
        return true;
    }
    private static boolean checkSoduku(int[][] soduku,int rs,int re,int cs,int ce){
        System.out.println(rs+" "+re+" "+cs+" "+ce);
        Set<Integer> elements = new HashSet<>();
        for(int row = rs;row<=re;row++){
            for(int col = cs;col<=ce;col++){
                if(soduku[row][col]!=0 ){
                    if(elements.contains(soduku[row][col])){

                        return false;
                    }
                    elements.add(soduku[row][col]);
                }

            }
        }
        return true;
    }
}
