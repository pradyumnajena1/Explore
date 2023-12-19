package epp.sorting;

import epp.array.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OptimumAssignmentOfTasks {
    public static void main(String[] args) {
        int[] tasks = ArrayUtils.randomArray(20,1,20);
        ArrayUtils.printArray(tasks);
        int max = getMaxTimeToFinish(tasks);
        System.out.println(max);
    }

    private static int getMaxTimeToFinish(int[] tasks) {
        Arrays.sort(tasks);
        ArrayUtils.printArray(tasks);
        int maxTime = Integer.MIN_VALUE;
        List<List<Integer>> assignments = new ArrayList<>();
        for(int i=0,j=tasks.length-1;i<j;i++,j--){
            ArrayList<Integer> assignedTasks = new ArrayList<>();
            assignedTasks.add(tasks[i]);
            assignedTasks.add(tasks[j]);
            assignments.add(assignedTasks);
            maxTime = Math.max(maxTime,tasks[i]+tasks[j]);
        }
        return maxTime;
    }
}
