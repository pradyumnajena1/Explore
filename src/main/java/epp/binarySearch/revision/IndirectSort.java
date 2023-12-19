package epp.binarySearch.revision;

import epp.array.ArrayUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class IndirectSort {
    public static void main(String[] args) {
        int[] values = ArrayUtils.randomArray(10,1,10);
        ArrayUtils.printArray(values);
        int[] result = sortIndirect(values);
        ArrayUtils.printArray(result);
    }

    private static int[] sortIndirect(int[] values) {
         Integer[] result = new Integer[values.length];
        for(int i=0;i< result.length;i++){
            result[i] = i;
        }
        Arrays.sort(result, Comparator.comparingInt((Integer a) -> values[a]));
        return Arrays.stream(result).mapToInt(Integer::intValue).toArray();
    }
}
