package epp.array.revision;

import java.util.Arrays;

public class ShiftedArrayBS {


    static int shiftedArrSearch(int[] shiftArr, int num) {
        // your code goes here hi
        int rotationIndex = getRotationIndex(shiftArr);
        System.out.println(rotationIndex);
        if (rotationIndex == 0) {
            return Arrays.binarySearch(shiftArr, 0, shiftArr.length, num);
        } else if (num >= shiftArr[0] && num <= shiftArr[rotationIndex - 1]) {
            return Arrays.binarySearch(shiftArr, 0, rotationIndex + 1, num);
        } else {
            return Arrays.binarySearch(shiftArr, rotationIndex, shiftArr.length, num);
        }
    }


    //   3, 4, 5, 1, 2

    static int getRotationIndex(int[] shiftArr) {

        int low = 0;
        int high = shiftArr.length - 1;
        while (low <= high) {
            if (shiftArr[low] <= shiftArr[high]) {
                return low;
            }
            int mid = (low + high) / 2;
            if ((mid > 0 && shiftArr[mid - 1] > shiftArr[mid]) && (mid < shiftArr.length - 1 && shiftArr[mid] < shiftArr[mid + 1])) {
                return mid;
            }

            if (shiftArr[low] <= shiftArr[mid]) {
                low = mid + 1;
            } else {
                high = mid;
            }

        }
        return low;
    }

    public static void main(String[] args) {
        System.out.println(shiftedArrSearch(new int[]{3, 4, 5, 1, 2}, 4));
        System.out.println(shiftedArrSearch(new int[]{  1, 2}, 2));
    }
}
