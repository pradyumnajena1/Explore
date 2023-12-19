package epp.recursion;

import epp.array.ArrayUtils;

import java.util.Arrays;

public class Permutation {
    public static void main(String[] args) {
        int[] array = new int[]{2,3,1};
        printPermutationsInLexicographyOrder(array);
    }

    private static void printPermutationsInLexicographyOrder(int[] array) {
        Arrays.sort(array);
        int[] nextPermutation;
        do{
            ArrayUtils.printArray(array);
            array = getNextPermutation(array);

        }while (array!=null);
    }

    private static int[] getNextPermutation(int[] array) {

        // number is max when digits are descending from msb to lsb
        // or ascending from lsb to msb
        //find first non increasing digit
        int i = array.length-1;
        while (i>0 && array[i]<array[i-1]){
            i--;
        }
        if(i==0){
            return null;
        }
        //find nex bigger digit
        int j= array.length-1;
        while(array[j]<array[i-1]){
            j--;
        }
        ArrayUtils.swap(array,i-1,j);
        ArrayUtils.reverse(array,i,array.length-1);
        return array;
    }
}
