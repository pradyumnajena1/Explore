package epp.array.revision;

import epp.array.ArrayUtils;

import java.util.ArrayDeque;
import java.util.Arrays;

public class AwardBudgetCuts {

   static class Solution {
       class Position{
           int x;
           int y;

           @Override
           public boolean equals(Object o) {
               if (this == o) return true;
               if (o == null || getClass() != o.getClass()) return false;

               Position position = (Position) o;

               if (x != position.x) return false;
               return y == position.y;
           }

           @Override
           public int hashCode() {

               int result = x;
               result = 31 * result + y;
               return result;
           }
       }

       static double findGrantsCap(double[] salary, double totalBudget) {
           Arrays.sort(salary);
           ArrayUtils.printArray(salary);
           double[] cumulativeSum =  cumulativeSum(salary);
           ArrayUtils.printArray(cumulativeSum);
           double[] cost = new double[salary.length];
           for (int i = 0; i < salary.length; i++) {
               cost[i] = (i > 0 ? cumulativeSum[i - 1] : 0) + salary[i] * (salary.length - i);
           }
           ArrayUtils.printArray(cost);

           int lower = getLowerBound(cost, totalBudget);
           if (lower == cost.length - 1) {
               return -1;
           }
           if (lower == -1) {
               return totalBudget / salary.length;
           }
           return salary[lower] + (totalBudget - cost[lower]) / (salary.length-1 - lower );
       }
       private static double[] cumulativeSum(double[] values) {
           double[] cumsum = new double[values.length];
           cumsum[0] = values[0];
           for (int i = 1; i < values.length; i++) {
               cumsum[i] = cumsum[i - 1] + values[i];
           }
           return cumsum;
       }

       private static int getLowerBound(double[] cost, double totalBudget) {
           int lower = 0;
           while (lower < cost.length && cost[lower] < totalBudget) {
               lower++;
           }
           return lower - 1;
       }


       //  2 50 100  120 1000


       public static void main(String[] args) {
           System.out.println(findGrantsCap(new double[]{2, 50, 100, 120, 1000}, 190));

           System.out.println(findGrantsCap(new double[]{2,4}, 3));
       }
   }
}
