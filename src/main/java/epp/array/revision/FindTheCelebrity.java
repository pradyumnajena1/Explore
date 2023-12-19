package epp.array.revision;

import epp.array.ArrayUtils;

public class FindTheCelebrity {
    public static void main(String[] args) {
        int numGuests = 20;
        int[][] knows = new int[numGuests][numGuests];
        for(int i = 0;i<knows.length;i++){
            for(int j=0;j<knows.length;j++){
                knows[i][j] = (int) (Math.random() * 2);
            }
        }
        for(int i=0;i<knows.length;i++){
            knows[i][5] = 1;
            knows[5][i] = 0;
        }
        knows[5][5]=1;
        ArrayUtils.print2DArray(knows);
      int x =   findTheCelebrity(knows);
        System.out.println(x);
    }

    private static int findTheCelebrity(int[][] knows) {
        int row = 0;
        int col = knows.length-1;
        while (row<knows.length && col >=0 ){
            if(knows[row][col]==0){
                col--;
            }else {
                row++;
            }
        }
        return col;
    }
}
