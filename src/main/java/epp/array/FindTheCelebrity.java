package epp.array;

public class FindTheCelebrity {
    public static void main(String[] args) {
        boolean[][] matrix = new boolean[][]{
                {true,true,true,false,true},
                {false,true,false,false,false},
                {false,true,true,true,false},
                {true,true,false,true,true},
                {true,true,false,true,true}
        };
      int celeb=  findTheCelebrity(matrix);
        System.out.println(celeb);
    }

    private static int findTheCelebrity(boolean[][] matrix) {
        int row =0;
        int col = matrix.length-1;
        while (  row< col){
            if(   matrix[row][col]){
                row ++;
            }else{
                col--;
            }

        }
        return row;
    }
}
