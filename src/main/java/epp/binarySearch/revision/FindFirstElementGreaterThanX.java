package epp.binarySearch.revision;

import epp.array.ArrayUtils;

public class FindFirstElementGreaterThanX {
    public static void main(String[] args) {
        int[] values =  new int[]{-14, -10, 2, 108, 108, 243, 285, 285, 285, 401};
        ArrayUtils.printArray(values);
        System.out.println(ArrayUtils.findFirstElementGreaterThan(values,285));
        System.out.println(ArrayUtils.findFirstElementGreaterThan(values,-13));

        System.out.println(ArrayUtils.findFirstElementGreaterThan(values,401));

        values = ArrayUtils.randomSortedArray(20, 1, 10);
        ArrayUtils.printArray(values);
        System.out.println(ArrayUtils.findFirstElementGreaterThan(values,9));
        System.out.println(ArrayUtils.findLastElementSmallerThan(values,9));

    }

}
