package epp.greedy;

import java.util.*;

public class MaxWaterTrapped {
    public static void main(String[] args) {
        int[] lines  = new int[]{1,2,1,3,4,4,5,6,2,1,3,1,3,2,1,2,4,1};
        System.out.println(Arrays.toString(getmaxWaterTrapped(lines)));
        System.out.println(Arrays.toString(getmaxWaterTrapped2(lines)));
    }
    private static class Tuple{
        int length;
        int position;

        public Tuple(int length, int position) {
            this.length = length;
            this.position = position;
        }
    }
    private static int[] getmaxWaterTrapped2(int[] lines) {

        int start =0;
        int end = lines.length-1;
        int maxWaterTrapped = 0;
        int[] result = new int[2];
        while (start<end){
            int waterTrapped = Math.min(lines[start],lines[end])*(end-start);
            if(waterTrapped>maxWaterTrapped){
                maxWaterTrapped = waterTrapped;
                result =new int[] {start,end};
            }
            if(lines[start]<lines[end]){
                start++;
            }else if(lines[start]>lines[end]){
                end--;
            }else {
                start++;
                end--;
            }
        }
        return result;
    }

    private static int[] getmaxWaterTrapped(int[] lines) {
        Map<Integer, Integer> map = new HashMap<>();
        int maxWater = 0;
        int[] result = new int[2];
        for(int i=0;i<lines.length;i++){
            for(int j=lines[i];j>=1 ;j--){
                Integer prevPosition = map.get(j);
                if(prevPosition!=null){
                    int water = j*i-prevPosition;
                    if(water>maxWater){
                        maxWater = water;
                        result = new int[]{prevPosition,i};
                    }

                }
            }

            map.putIfAbsent(lines[i],i);
        }
        return result;
    }
}
