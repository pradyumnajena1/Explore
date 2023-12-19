package epp.sorting;

import epp.array.ArrayUtils;

import java.util.Arrays;

public class SalaryThresshold  {
    public static void main(String[] args) {
        int[] salary = new int[]{90,30,100,40,20};
        int total = 230;
        int threshold = findThreshold(salary,total);
        System.out.println(threshold);
    }

    private static int findThreshold(int[] salary, int totalBudget) {
        Arrays.sort(salary);
        ArrayUtils.printArray(salary);
        int[] cumulativeSum = ArrayUtils.cumulativeSum(salary);
        ArrayUtils.printArray(cumulativeSum);
        int[] cost = new int[salary.length];
        for(int i=0;i<salary.length;i++){
            cost[i] = (i>0? cumulativeSum[i-1]:0)+   salary[i]* (salary.length-i);
        }
        ArrayUtils.printArray(cost);

        int lower = getLowerBound(cost,totalBudget);
        if(lower == cost.length-1){
            return -1;
        }
        if(lower==0){
            return totalBudget/salary.length;
        }



        return salary[lower] + ( totalBudget - cost[lower])/(salary.length-lower);
    }

    private static int getLowerBound(int[] cost, int totalBudget) {
       int lower = 0;
       while (lower<cost.length && cost[lower]<totalBudget){
           lower++;
       }
        return lower-1;
    }
}
