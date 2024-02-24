package cph;

import epp.array.ArrayUtils;

public class Sorting {
    public static void main(String[] args) {
        int[] values = ArrayUtils.randomArray(10, -5, 10);
        ArrayUtils.printArray(values);
        bubbleSort(values);
        ArrayUtils.printArray(values);
    }

    public static  void bubbleSort(int[] values){

        for(int i=0;i< values.length;i++){
            for(int j=0;j< values.length-1;j++){
                if(values[j]>values[j+1]){
                    ArrayUtils.swap(values,j,j+1);
                }
            }
        }

    }
}
