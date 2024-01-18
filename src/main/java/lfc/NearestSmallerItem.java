package lfc;

import epp.array.ArrayUtils;

public class NearestSmallerItem {
    public static void main(String[] args) {
        int[] values = ArrayUtils.randomArray(10, 1, 30);
        ArrayUtils.printArray(values);
        int low = 0;
        int high = 9;
        ArrayUtils.printArray(findNearestSmallerItemsOnLeft(values, low, high));
        ArrayUtils.printArray(findNearestSmallerItemsOnRight(values, low, high));
        ArrayUtils.printArray(findNearestSmallerItems(values, low, high));

    }

    public static int[] findNearestSmallerItems(int[] values) {
        return findNearestSmallerItems(values, 0, values.length - 1);

    }

    public static int[] findNearestSmallerItems(int[] values, int low, int high) {
        int[] left = findNearestSmallerItemsOnLeft(values, low, high);
        int[] right = findNearestSmallerItemsOnRight(values, low, high);

        int[] result = new int[high - low + 1];
        for (int i = low; i <= high; i++) {

            int index = i-low;
            if(left[index]<0){
                result[index] = right[index];
            }
            else if (right[index]<0){
                result[index] = left[index];
            }else{

                result[index] = Math.abs(index - left[index]) < Math.abs(index - right[index]) ? left[index] : right[index];
            }

        }
        return result;
    }
    public static int[] findNearestSmallerItemsOnRight(int[] values ) {
        return findNearestSmallerItemsOnRight(values,0, values.length-1);
    }
    public static int[] findNearestSmallerItemsOnRight(int[] values, int low, int high) {
        int[] result = new int[high - low + 1];
        result[high-low] = -1;
        for (int i = high-1; i >= low; i--) {
            int current = i + 1;
            while (current >= low && values[current] >= values[i]) {
                current = result[current - low];
            }
            result[i-low] = current;
        }
        return result;
    }
    public static int[] findNearestSmallerItemsOnLeft(int[] values ){
        return findNearestSmallerItemsOnLeft(values,0,values.length-1);
    }
    public static int[] findNearestSmallerItemsOnLeft(int[] values, int low, int high) {
        int[] result = new int[high - low + 1];
        result[0] = -1;
        for (int i = low + 1; i <= high; i++) {
            int current = i - 1;
            while (current >= low && values[current] >= values[i]) {
                current = result[current - low];
            }
            result[i-low] = current;
        }
        return result;
    }
}
