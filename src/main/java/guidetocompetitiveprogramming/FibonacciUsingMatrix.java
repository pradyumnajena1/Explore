package guidetocompetitiveprogramming;

import commons.MatrixUtils;
import epp.array.ArrayUtils;

public class FibonacciUsingMatrix {

    public static void main(String[] args) {

        System.out.println(fibonacci(7));
    }

    private static int fibonacci(int n) {
        if(n<=2){
            return 1;
        }
        int[][] coeff = {{0,1},{1,1}};
        int[][] input = { {1},{1}};
       int[][] output =  MatrixUtils.matrixMultiply(MatrixUtils.matrixPower(coeff,n-2,MatrixUtils::matrixMultiply),input);
        return output[1][0];
    }

}
