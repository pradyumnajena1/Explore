package tlap.chapter2;

import epp.array.ArrayUtils;

import java.util.Arrays;

public class SalesManWithBestMedian {
    public static void main(String[] args) {
        double[][] sales = {
                ArrayUtils.randomArray(12,3.0,20.0),
                ArrayUtils.randomArray(12,2.0,22.0),
                ArrayUtils.randomArray(12,3.0,17),
                ArrayUtils.randomArray(12,3.0,20.0),
        };
        ArrayUtils.print2DArray(sales);
        int index = findSalesManWithHighestMedian(sales);
        System.out.println(index);
    }

    private static int findSalesManWithHighestMedian(double[][] sales) {
        int numAgents = sales.length;
        double highestMedian = findMedian(sales[0]);
        int highestMedianIndex = 0;
        for(int i=1;i<numAgents;i++){
            double median = findMedian(sales[i]);
            if(median>highestMedian){
                highestMedian = median;
                highestMedianIndex = i;
            }
        }


        return highestMedianIndex;
    }

    private static double findMedian(double[] sales) {
        Arrays.sort(sales);
        if(sales.length%2==0){
            return (sales[sales.length/2] + sales[sales.length/2-1])/2;
        }else{
            return (sales[sales.length/2]  ) ;
        }

    }
}
