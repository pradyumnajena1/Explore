package epp.dp;

import epp.array.ArrayUtils;

public class LongestNonDecreasingSubsequence {
    public static void main(String[] args) {
        int[] array = ArrayUtils.randomArray(10,1,20);
        ArrayUtils.printArray(array);
        int length = longestNonDecreasingSubsequence(array);
        System.out.println(length);
    }

    private static int longestNonDecreasingSubsequence(int[] array) {
        int[] lengths = new int[array.length];
        for(int i=0;i<lengths.length;i++){

            lengths[i] = 1;
        }
        int max = 0;
        for(int i=1;i<array.length;i++){
            for(int j=i-1;j>=0;j--){
                if(array[j]>=array[i] && lengths[j]+1 >= lengths[i]){
                    lengths[i] = lengths[j]+1;
                    if(lengths[i]>max){
                        max = lengths[i];
                    }
                }
            }
        }
        return max;
    }


}
