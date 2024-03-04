package meta;

import epp.array.ArrayUtils;

/**
 * You are given a set of random numbers. Write a code to shift all the zeros to the left.
 */
public class ShiftValuesToLeft {
    public static void main(String[] args) {
        int[] values = ArrayUtils.randomArray(10,0,5);
        ArrayUtils.printArray(values);
        shiftToLeft(values,0);
        ArrayUtils.printArray(values);
    }

    private static void shiftToLeft(int[] values, int value) {
        shiftToLeft(values,value,0,values.length);
    }

    private static void shiftToLeft(int[] values, int value, int left, int right) {
        int writePosition=left;
        for(int i=left;i<right;i++){
            if(values[i]==value){
                ArrayUtils.swap(values,writePosition,i);
                writePosition++;
            }
        }
    }
}
