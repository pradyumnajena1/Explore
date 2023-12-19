package epp.hashmap.revision;

import epp.array.ArrayUtils;

import java.util.Arrays;
import java.util.stream.Collectors;

public class SmallestSubArrayCoveringAllUniqueValues {
    public static void main(String[] args) {
        int[] values = ArrayUtils.randomArray(20, 1, 8);
        int[] requiredValues = Arrays.stream(values).boxed().collect(Collectors.toSet()).stream().mapToInt(x -> x.intValue()).toArray();
        ArrayUtils.printArray(values);
        ArrayUtils.printArray(requiredValues);
        Range range = SmallestSubArrayCoveringAllValues.getSmallestSubArrayCover3(values, requiredValues);
        System.out.println(range);
    }
}
