package guidetocompetitiveprogramming;

public class NumWaysToPlace2Queens {
    public static void main(String[] args) {
        int numWays = numWaysToPlace2Queens(3);
        System.out.println(numWays);
    }

    private static int numWaysToPlace2Queens(int n) {
        if(n<3){
            return 0;
        }
        int oneQueenOnLastRowOrColumn = (2 * n - 1) * (n * n - 1 - 3 * (n - 1));
        int twoQueensOnLastRowOrColumn = (n - 1) * (n - 2);
        return numWaysToPlace2Queens(n-1)+ oneQueenOnLastRowOrColumn - twoQueensOnLastRowOrColumn;
    }
}
