package epp.greedy;

import epp.array.ArrayUtils;

import java.util.Arrays;

public class MinimizeWaitingTime {
    public static void main(String[] args) {
        int[] queryTimes = ArrayUtils.randomArray(10,2,10);
        ArrayUtils.printArray(queryTimes);
        int avgWaitingTime  = getMinimumAvgWaitingTime(queryTimes);
        System.out.println(avgWaitingTime);
    }

    private static int getMinimumAvgWaitingTime(int[] queryTimes) {
        Arrays.sort(queryTimes);
        int totalWaitingTime = 0;
        for(int i=1;i<queryTimes.length;i++){
            totalWaitingTime+=queryTimes[i-1];
        }
        return totalWaitingTime/queryTimes.length;
    }
}
