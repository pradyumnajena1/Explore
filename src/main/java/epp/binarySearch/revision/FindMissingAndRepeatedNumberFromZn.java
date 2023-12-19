package epp.binarySearch.revision;

import epp.array.ArrayUtils;
import epp.utils.StringUtils;

import java.util.StringJoiner;

/**
 * one number missing and one number repeated twice
 */
public class FindMissingAndRepeatedNumberFromZn {
    public static void main(String[] args) {

         int[] values  = new int[]{0,1,2,3,3,5};
        ArrayUtils.shuffle(values);
        ArrayUtils.printArray(values);
         Result result =  findMissingNumber(values);
        System.out.println(result);

        result =  findMissingNumber2(values);
        System.out.println(result);
    }



    private static class Result{
        int missing;
        int repeated;

        public Result(int missing, int repeated) {
            this.missing = missing;
            this.repeated = repeated;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Result.class.getSimpleName() + "[", "]")
                    .add("missing=" + missing)
                    .add("repeated=" + repeated)
                    .toString();
        }
    }

    private static Result findMissingNumber(int[] values) {
         int sum=0,squareSum = 0;
         for(int i=0;i<values.length;i++){
             sum+= i- values[i];
             squareSum+= i*i - values[i]*values[i];
         }
         return new Result(((squareSum/sum)+sum)/2,((squareSum/sum)-sum)/2);
    }
    private static Result findMissingNumber2(int[] values) {
        int miss_XOR_dup = 0;
        for(int i=0;i< values.length;i++){
            miss_XOR_dup = miss_XOR_dup^ i^ values[i];
        }
        System.out.println("miss_XOR_dup "+ StringUtils.getBinaryString(miss_XOR_dup));
        int diff = miss_XOR_dup & ~(miss_XOR_dup-1);
        System.out.println("diff "+StringUtils.getBinaryString(diff));
        int miss_or_dup = 0;
        for(int i=0;i<values.length;i++) {
            if ((i & diff) > 0) {
                miss_or_dup = miss_or_dup ^ i;
                System.out.println("miss_or_dup ^  " + i + " " + StringUtils.getBinaryString(miss_or_dup));
            }
            if((values[i] & diff)>0){
                miss_or_dup = miss_or_dup ^ values[i];
                System.out.println("miss_or_dup ^  "+values[i]+" "+StringUtils.getBinaryString(miss_or_dup));
            }
        }


        for(int value:values){
            if(value==miss_or_dup){
                return new Result(miss_XOR_dup^ miss_or_dup,miss_or_dup);
            }
        }
        return new Result(miss_or_dup,miss_XOR_dup^ miss_or_dup);
    }
}
