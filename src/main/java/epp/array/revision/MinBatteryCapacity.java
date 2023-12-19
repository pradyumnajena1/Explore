package epp.array.revision;

import epp.array.ArrayUtils;

public class MinBatteryCapacity {
    public static void main(String[] args) {
        int[] arr = ArrayUtils.randomArray(10,1,20);
        int minCapacity = getMinCapacity(arr);
        ArrayUtils.printArray(arr);
        System.out.println(minCapacity);
    }

    private static int getMinCapacity(int[] arr) {
        int min = 0;
        int max = 0;
        for(int i=0;i<arr.length;i++){
            max = Math.max(max,arr[i]-min);
            min = Math.min(min,arr[i]);
        }
        return max;
    }
}
