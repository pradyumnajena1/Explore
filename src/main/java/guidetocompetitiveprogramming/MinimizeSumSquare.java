package guidetocompetitiveprogramming;

import java.util.Arrays;
/**
 * find x such that sum of  (x-ai)^2 where ai belongs to array is minimum.
 *
 * find x using ternary search as the function is a convex function, though the problem can be solved by finding avg of the array.
 */
public class MinimizeSumSquare {
    public static void main(String[] args) {
        int[] values ={1,2,9,2,6};// ArrayUtils.randomArray(10,1,20);
        int x = findMinimumSquareSum(values);
        System.out.println(x);
    }

    private static int findMinimumSquareSum(int[] values) {
        int low = findMin(values);
        int high =findMax(values);
        while (high-low>=3){
            System.out.println("low = " + low);
            System.out.println("high = " + high);
            int a =( 2*low+high)/3;
            int b =( low+2*high)/3;
            int fa = func(a,values);
            int fb = func(b,values);
            if(fa<fb){
                high = b;
            }else{
                low = a;
            }

        }
        int min= func(low, values);
        int minX=low;
        for(int x=low+1;x<=high;x++){
            int func = func(x, values);
            if(min> func){
                min = func;
                minX=x;
            }
        }
        return minX;
    }

    private static int func(int a, int[] values) {
        return Arrays.stream(values).map(x-> (x-a)*(x-a)).sum();
    }

    private static int findMin(int[] values) {
        return Arrays.stream(values).min().getAsInt();
    }

    private static int findMax(int[] values) {
        return Arrays.stream(values).max().getAsInt();
    }
}
