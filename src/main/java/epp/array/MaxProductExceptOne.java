package epp.array;

import java.util.Arrays;

public class MaxProductExceptOne {
    public static void main(String[] args) {
        double maxProduct = getMaxproductExceptOne(new double[]{3,2,-1,0.5});
        System.out.println(maxProduct);
    }

    private static double getMaxproductExceptOne(double[] values) {
        double[] left = new double[values.length];
        double[] right = new double[values.length];
        double leftProduct =1;
        for(int i=0;i<values.length;i++){
            leftProduct = leftProduct* values[i];
            left[i] = leftProduct;
        }
        System.out.println(Arrays.toString(left));

        double rightProduct = 1;
        for(int i=values.length-1;i>=0;i--){
            rightProduct = rightProduct* values[i];
            right[i] = rightProduct;
        }
        System.out.println(Arrays.toString(right));

        double maxProduct = 0;
        for(int i=0;i<values.length;i++){
            double product = (i>0? left[i-1]:1.0)  * (i<values.length-1? right[i+1]:1.0);
            maxProduct = Math.max(product,maxProduct);
        }
        return maxProduct;
    }
}
