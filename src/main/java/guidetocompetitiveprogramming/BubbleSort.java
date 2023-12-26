package guidetocompetitiveprogramming;

import epp.array.ArrayUtils;

public class BubbleSort {
    public static void main(String[] args) {
        int[] array = ArrayUtils.randomArray(10, 1, 20);
        ArrayUtils.printArray(array);
        bubbleSort(array);
        ArrayUtils.printArray(array);
    }
    public static void bubbleSort(int[] arr){
        for(int round=0;round< arr.length;round++){
            for(int i=0;i<arr.length-1;i++){
                if(arr[i]>arr[i+1]){
                    ArrayUtils.swap(arr,i,i+1);
                }
            }
        }
    }
}
