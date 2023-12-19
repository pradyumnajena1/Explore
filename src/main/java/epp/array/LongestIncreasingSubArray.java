package epp.array;

public class LongestIncreasingSubArray {
    public static void main(String[] args) {
        int length = getLongestIncreasingSubArray(new int[]{2,11,3,5,13,7,19,17,23});
        System.out.println(length);
    }

    public static int getLongestIncreasingSubArray(int[] values) {

        int lastLength = 1;
        int maxLength = 1;
        for(int i=1;i<values.length;i++){
            int currentLength = 0;
            if(values[i]>values[i-1]){
                currentLength = lastLength+1;
            }else{
                currentLength =1;
            }
           maxLength = Math.max(currentLength,maxLength);
            lastLength = currentLength;
        }
        return maxLength;
    }
}
