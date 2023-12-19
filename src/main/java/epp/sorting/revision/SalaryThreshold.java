package epp.sorting.revision;

import epp.array.ArrayUtils;

import java.util.Arrays;

public class SalaryThreshold {
    public static void main(String[] args) {
        int[] salaries = {30,20,40,90,129};
        int maxBudget = 5;
        int threshold = findThreshold(salaries,maxBudget);
        System.out.println(threshold);

        threshold = findThreshold2(salaries,maxBudget);
        System.out.println(threshold);
    }

    /**
     * O(n) extra space
     * @param salaries
     * @param maxBudget
     * @return
     */
    private static int findThreshold(int[] salaries, int maxBudget) {
        Arrays.sort(salaries);
        int[] cumulativeSum = ArrayUtils.cumulativeSum(salaries);
        int left = salaries[0];
        int right = salaries[salaries.length-1];
        while (left<right){
            int mid = (left+right)/2;
            int index =  ArrayUtils.findFirstElementGreaterThan(salaries,mid);
            int leftSum = index>1? cumulativeSum[index-1]:0;
            int rightSum = index<salaries.length? mid * ( salaries.length-index):0;
            int sum = leftSum + rightSum;
            if(sum ==maxBudget){
                return mid;
            } else if (sum >maxBudget) {
                right = mid-1;
            }else{
                left = mid+1;
            }
        }
        return left;
    }

    private static int findThreshold2(int[] salaries, int maxBudget) {
        Arrays.sort(salaries);
        ArrayUtils.cumulativeSumInPlace(salaries);
        int left = salaries[0];
        int right = salaries[salaries.length-1]-salaries[salaries.length-2];
        while (left<right){
            int mid = (left+right)/2;
            int index =   findFirstElementGreaterThan(salaries,mid);
            int leftSum = index>1? salaries[index-1]:0;
            int rightSum = index<salaries.length? mid * ( salaries.length-index):0;
            int sum = leftSum + rightSum;
            if(sum ==maxBudget){
                return mid;
            } else if (sum >maxBudget) {
                right = mid-1;
            }else{
                left = mid+1;
            }
        }
        return left;
    }

    private static int findFirstElementGreaterThan(int[] values, int value) {
        int low =0;
        int high = values.length-1;
        int index = values.length;
        while (low<=high){
            int mid = (low+high)/2;
            if( (mid>0? values[mid]-values[mid-1]:values[mid])>value){
                index=mid;
                high = mid-1;
            }else {
                low = mid+1;
            }
        }
        return index;
    }
}
