package epp.greedy;

import epp.array.ArrayUtils;

public class TrappingWater {
    public static void main(String[] args) {
       // int[] heights =  ArrayUtils.randomArray(10,1,10);
        int[] heights = new int[]{1,2,1,3,4,4,5,1,2,0,3};
        int trappedWater = getTrappedWater(heights);
        System.out.println(trappedWater);
    }

    private static int getTrappedWater(int[] heights) {
        ArrayUtils.printArray(heights);
        int[] left = new int[heights.length];
        int max = 0;

        for(int i=0;i<left.length;i++){
            max = Math.max(max,heights[i]);
            left[i] = max;
        }
        int[] right = new int[heights.length];
        max=0;
        for(int i=right.length-1;i>=0 ;i--){
            max = Math.max(max,heights[i]);
            right[i] = max;
        }
        ArrayUtils.printArray(left);
        ArrayUtils.printArray(right);
        int totalWaterTrapped = 0;
        for(int i=0;i<heights.length;i++){
            int min = Math.min( i>0?left[i-1]:0,i<heights.length-1? right[i+1]:0);
            int waterTrapped =0;
            if(min>heights[i]){
                waterTrapped = min -heights[i];
            }
            totalWaterTrapped+=waterTrapped;
            System.out.println(waterTrapped);
        }
        return totalWaterTrapped;
    }
}
