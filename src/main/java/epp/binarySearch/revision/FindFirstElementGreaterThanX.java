package epp.binarySearch.revision;

import epp.array.ArrayUtils;

public class FindFirstElementGreaterThanX {
    public static void main(String[] args) {
        int[] values = ArrayUtils.randomSortedArray(20, 1, 10);
        ArrayUtils.printArray(values);
        System.out.println(ArrayUtils.findFirstElementGreaterThan(values,7));
        System.out.println(ArrayUtils.findLastElementSmallerThan(values,7));

        values = ArrayUtils.randomSortedArray(20, 1, 10);
        ArrayUtils.printArray(values);
        System.out.println(ArrayUtils.findFirstElementGreaterThan(values,9));
        System.out.println(ArrayUtils.findLastElementSmallerThan(values,9));

    }

}
