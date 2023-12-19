package epp.sorting.revision;

import epp.array.ArrayUtils;

import java.util.Arrays;

/**
 * distribute n = 2*m tasks among m workers so that total time to finish is minimum.
 */
public class OptimalAssignmentOfTasks {
    public static void main(String[] args) {
        int[] taskDurations = ArrayUtils.randomArray(12, 1, 10);
        int maxTimeToFinish = assignTasks(taskDurations);
        ArrayUtils.printArray(taskDurations);
        System.out.println(maxTimeToFinish);
    }

    private static int assignTasks(int[] taskDurations) {
        Arrays.sort(taskDurations);
        int start = 0;
        int end  = taskDurations.length-1;
        int maxTime = 0;
        while (start<end){
            int totalTime = taskDurations[start++]+taskDurations[end--];
            maxTime = Math.max(totalTime,maxTime);
        }
        return maxTime;
    }
}
