package epp.dp.revision;

import epp.array.ArrayUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MaximumSubArraySum {
    public static void main(String[] args) {
        int[] values = ArrayUtils.randomArray(10, -30, 20);
        ArrayUtils.printArray(values);
        Result result = findMaxSubArraySum(values);
        System.out.println(result);



    }





    public static Result findMaxSubArraySum(int[] values) {
        int sum = 0;
        int maxSum = Integer.MIN_VALUE;
        int startIndex = 0;
        Result result = null;
        for(int i=0;i<values.length;i++){
            sum+=values[i];
            if(sum<values[i]){
                sum = values[i];
                startIndex = i;
            }
            if(sum>maxSum){
                maxSum = sum;
                result = new Result(startIndex,i,maxSum);

            }
        }
        return result;
    }


}
