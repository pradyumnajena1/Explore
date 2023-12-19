package hackerrank.medium;

import java.util.ArrayList;
import java.util.List;

public class TheMaximumSubarray {
    public static void main(String[] args) {
        System.out.println(maxSubarray(new ArrayList<>(List.of(2,-1,2,3,4,-5))));
         System.out.println(maxSubarray(new ArrayList<>(List.of(1,2,3,4))));
        System.out.println(maxSubarray(new ArrayList<>(List.of(-2, -3, -1 ,-4, -6))));
    }

    public static List<Integer> maxSubarray(List<Integer> arr) {
        // Write your code here
         int maxSubArraySum =  getMaxSubArraySum(arr);
         int maxSubSeqSum =  getMaxSubSeqSum(arr);
         return new ArrayList<>(List.of(maxSubArraySum,maxSubSeqSum));
    }

    private static int getMaxSubSeqSum(List<Integer> arr) {
        return getMaxSubSeqSum(arr,0 );
    }

    private static int getMaxSubSeqSum(List<Integer> arr, int i) {
        if(i==arr.size()-1){
            return arr.get(i);
        }
        int maxSubSeqSum = getMaxSubSeqSum(arr, i + 1);
        if(maxSubSeqSum>=0 && arr.get(i)>=0){
            return maxSubSeqSum+arr.get(i);
        }else if(maxSubSeqSum>=0 && arr.get(i)<0){
            return maxSubSeqSum;
        }else if(maxSubSeqSum<0 && arr.get(i)>=0){
            return arr.get(i);
        }else{
            return Math.max(maxSubSeqSum,arr.get(i));
        }


    }

    private static int getMaxSubArraySum(List<Integer> arr) {
        long sum = 0;
        int maxSum = Integer.MIN_VALUE;
        for(int i=0;i<arr.size();i++){
            sum+=arr.get(i);
            if(sum<arr.get(i)){
                sum = arr.get(i);
            }
            maxSum = (int) Math.max(sum,maxSum);
        }
        return maxSum;
    }
}
