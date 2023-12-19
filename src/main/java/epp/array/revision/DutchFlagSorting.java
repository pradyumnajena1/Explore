package epp.array.revision;

import epp.array.ArrayUtils;

public class DutchFlagSorting {
    public static void main(String[] args) {
        int[] randomArray = ArrayUtils.randomArray(15,2,10);
        ArrayUtils.printArray(randomArray);
        dutchFlagSort(randomArray,7);
        ArrayUtils.printArray(randomArray);
       // ArrayUtils.printArray(randomArray);
    }

    private static void dutchFlagSort(int[] randomArray, int index) {
        int sp=0,ep = 0;
        int bp = randomArray.length-1;
        int pivot = randomArray[index];
        while (ep<=bp){
            if(randomArray[ep]<pivot){
                ArrayUtils.swap(randomArray,sp,ep);
                sp++;
                ep++;

            }else if(randomArray[ep]==pivot){
                ep++;
            }else{
                ArrayUtils.swap(randomArray,ep,bp);
                bp--;
            }
        }
    }
}
