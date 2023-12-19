package epp.array.revision;

import epp.array.ArrayUtils;

public class MaxStockProfit {
    public static void main(String[] args) {
        int[] values = ArrayUtils.randomArray(10,5,20);
        int maxProfit = getMaxProfit(values);
        ArrayUtils.printArray(values);
        System.out.println(maxProfit);

        maxProfit = getMaxProfit2Times(values);
        ArrayUtils.printArray(values);
        System.out.println(maxProfit);
    }

    private static int getMaxProfit(int[] values) {
        return getMaxProfit(values,0,values.length-1);
    }
    private static int getMaxProfit2Times(int[] values){
        int max = Integer.MIN_VALUE;
        for(int i=2;i<values.length-2;i++){
            int maxLeft = getMaxProfit(values,0,i);
            int maxRight = getMaxProfit(values,i+1,values.length-1);
            max = Math.max(max,maxLeft+maxRight);
        }
        return max;
    }

    private static int getMaxProfit(int[] values, int start, int end) {
        if(start==end){
            return 0;
        }
        int min = values[start];
        int max= Integer.MIN_VALUE;

        for(int i=start+1;i<=end;i++){
            max = Math.max(max,values[i]-min);
            min = Math.min(min,values[i]);
        }
        return max;
    }
}
