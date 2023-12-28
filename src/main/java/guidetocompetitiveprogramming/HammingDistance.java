package guidetocompetitiveprogramming;

import epp.array.ArrayUtils;

public class HammingDistance {
    public static void main(String[] args) {
        int[] values =  {7,13,30};
        int min =  findMinHammingDistance(values);
        System.out.println(min);
    }

    private static int findMinHammingDistance(int[] values) {
        int min = 32;
        for(int i=0;i<values.length-1;i++){
            for(int j=i+1;j<values.length;j++){
              min = Math.min(min,  hammingDistance(values[i],values[j]));
            }
        }
        return min;
    }

    private static int hammingDistance(int a, int b) {
        return Integer.bitCount(a^b);
    }
}
