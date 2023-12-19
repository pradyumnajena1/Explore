package epp.binarySearch.revision;

import epp.array.ArrayUtils;

import java.util.Arrays;
import java.util.Comparator;

/**
 * items are big statues, so minimize the total distance moved while sorting
 *
 */
public class LeastDistanceMovementSort {
    public static void main(String[] args) {
        int[] statues = ArrayUtils.randomArray(10,100,200);
        ArrayUtils.printArray(statues);
        statues =  sortStatues(statues);
        ArrayUtils.printArray(statues);
    }

    private static int[] sortStatues(int[] statues) {
        Integer[] indices = new Integer[statues.length];
        for(int i=0;i<indices.length;i++){
            indices[i] = i;
        }
        Arrays.sort(indices, Comparator.comparingInt((Integer x)->statues[x]));
        int[] newStatues = new int[statues.length];
        for(int i=0;i< newStatues.length;i++){
            newStatues[i] = statues[indices[i]];
        }
        return newStatues;
    }
}
