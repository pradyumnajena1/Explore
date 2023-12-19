package epp.binarySearch.revision;

import epp.array.ArrayUtils;
import epp.utils.StringUtils;

public class FindItemThatAppearsOnlyOnceWhileRestAppearsThrice {
    public static void main(String[] args) {
        int[] values = new int[]{3, 3, 3, 5, 2, 2, 2, 4, 4, 4, 7, 7, 7, 10, 10, 10 };
        ArrayUtils.shuffle(values);
        int value = findItemThatAppearsOnce(values);
        System.out.println(value);


        values = new int[]{9,9,9,9,6,6,6,6, 3,3, 3, 3, 5 ,7,7,7,7};
        ArrayUtils.shuffle(values);
          value = findItemThatAppearsOnce2(values);
        System.out.println(value);
    }

    private static int findItemThatAppearsOnce(int[] values) {
        ArrayUtils.printArray(values);
        int ones = 0;
        int twos = 0;
        int next_ones = 0;
        int next_twos = 0;
        for (int value : values) {
            System.out.println(value);
            next_ones = (~value & ones) | (value & ~ones & ~twos);
            next_twos = (~value & twos) | (value & ones);
            ones = next_ones;
            twos = next_twos;
            System.out.println(StringUtils.getBinaryString(ones));
            System.out.println(StringUtils.getBinaryString(twos));
        }
        return ones;
    }

    private static int findItemThatAppearsOnce2(int[] values) {
        ArrayUtils.printArray(  values);
        int ones = 0;
        int twos = 0;
        int threes = 0;
        int next_ones = 0;
        int next_twos = 0;
        int next_threes = 0;
        for (int value : values) {
            System.out.println(value);
            next_ones = (~value & ones) | (value & ~ones & ~twos & ~threes);
            next_twos = (~value & twos) | (value & ones);
            next_threes = (~value & threes) | (value & twos);

            ones = next_ones;
            twos = next_twos;
            threes = next_threes;
            System.out.println(StringUtils.getBinaryString(ones));
            System.out.println(StringUtils.getBinaryString(twos));
            System.out.println(StringUtils.getBinaryString(threes));
        }
        return ones;
    }

}
