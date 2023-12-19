package leetcode.hard;

import epp.array.ArrayUtils;

public class TrapRainWater {
    public static void main(String[] args) {
        int[] heights = new int[]{4,2,0,3,2,5};
        int rainWater = trapRainWater( heights);
        System.out.println(rainWater);
    }

    private static int trapRainWater(int[] heights) {
        ArrayUtils.printArray(heights);
        int[] left = new int[heights.length];
        int[] right = new int[heights.length];
        int max = Integer.MIN_VALUE;
        for(int i=0;i<heights.length;i++){
            max = Math.max(max,heights[i]);
            left[i] = max;
        }
        max = Integer.MIN_VALUE;
        for(int i=heights.length-1;i>=0;i--){
            max = Math.max(max,heights[i]);
            right[i] = max;
        }
        ArrayUtils.printArray(left);
        ArrayUtils.printArray(right);
        int totalWater =0;
        for(int i=0;i<heights.length;i++){
            int waterTrapped = Math.min(left[i],right[i])-heights[i];
            totalWater+=waterTrapped;
        }

        return totalWater;
    }
}
