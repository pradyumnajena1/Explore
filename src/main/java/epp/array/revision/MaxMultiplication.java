package epp.array.revision;

import epp.array.ArrayUtils;

public class MaxMultiplication {
    public static void main(String[] args) {
        double[] values = ArrayUtils.randomArray(10,-10d,10d);
        ArrayUtils.printArray(values);
        double result =maxMultiplication(values);
        System.out.println(result);

    }

    private static double maxMultiplication(double[] values) {
        double left[] = new double[values.length];
        double right[] = new double[values.length];
        left[0] = values[0];
        for(int i=1;i<values.length-1;i++){
            left[i] = values[i]*left[i-1];
        }
        right[values.length-1] = values[values.length-1];
        for(int i= values.length-2;i>0;i--){
            right[i] = values[i]*right[i+1];
        }
        double maxProd = Double.MIN_VALUE;
        for(int i=1;i<values.length-1;i++){
            double prod = left[i-1] * right[i+1];
            maxProd = Math.max(prod,maxProd);
        }
        return maxProd;
    }
}
