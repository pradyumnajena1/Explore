package epp.array;

public class TwoSumProblem {

    public static void main(String[] args) {
        int count = getTwoSum(new int[]{2,4,6,14,16,22},20);
        System.out.println(count);
    }

    private static int getTwoSum(int[] values, int sum) {
        int start=0;
        int end = values.length-1;
        int count=0;
        while (start<end){
            int currentSum = values[start] + values[end];
            if(currentSum ==sum){
                count++;
                start++;
                end--;
            }else if(currentSum<sum){
                start++;
            }else {
                end--;
            }
        }
        return count;
    }
}
