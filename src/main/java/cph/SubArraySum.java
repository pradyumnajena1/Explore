package cph;

import epp.Pair;

public class SubArraySum {
    public static void main(String[] args) {
        int[] values = new int[]{1,3,2,5,1,1,2,3};
        int sum = 8;
        Pair<Integer,Integer> range = findSumRange(values,sum);
        System.out.println(range);
    }

    /**
     *
     * @param values
     * @param sum
     * @return return  range pair(x,y), x inclusive y exclusive whose sum is sum else returns null
     */
    private static Pair<Integer, Integer> findSumRange(int[] values, int sum) {
        int left = 0;
        int right = 0;
        int currentSum = 0;
        while (right<values.length){
            while (currentSum+values[right]<=sum){
                currentSum+=values[right];
                right++;
            }
            if(currentSum==sum){
                return new Pair<>(left,right);
            }
            currentSum-=values[left];
            left++;
        }

        return null;
    }
}
