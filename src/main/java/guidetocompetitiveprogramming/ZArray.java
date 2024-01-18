package guidetocompetitiveprogramming;

import epp.array.ArrayUtils;

import java.util.Arrays;

public class ZArray {


    public static void main(String[] args) {
        String s = "ABCABCABAB";
        int[] zArray = computeZArray(s);

    }



    public static int[] computeZArray(String s) {
        char[] chars = s.toCharArray();
        int[] zarray = new int[s.length()];
        zarray[0] = -1;
        int x = 0;
        int y = -1;
        for (int k = 1; k < s.length(); k++) {
            if (y < k) {
                x = k;
                int length = Arrays.mismatch(chars, 0, chars.length, chars, k, chars.length);
                zarray[k] = length;
                y = k + length - 1;
            } else if (k + zarray[k - x] <= y) {

                zarray[k] = zarray[k - x];
            } else if (k + zarray[k - x] > y) {

                int length = Arrays.mismatch(chars, y - k + 1, chars.length, chars, y + 1, chars.length);
                zarray[k] = y - k + 1 + length;
                y = y + length;

            }
        }
        return zarray;
    }
}
