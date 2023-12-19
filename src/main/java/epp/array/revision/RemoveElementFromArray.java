package epp.array.revision;

import epp.array.ArrayUtils;

public class RemoveElementFromArray {
    public static void main(String[] args) {
        int[] array = ArrayUtils.randomArray(10,2,10);
        ArrayUtils.printArray(array);
       int numElements =   removeElementFromArray(array,array[3]);
        ArrayUtils.printArray(array);

        System.out.println(numElements);
    }

    private static int removeElementFromArray(int[] array, int value) {
        int writeIndex = 0;
        for(int i=0;i<array.length;i++){
            if(array[i]!=value){
                array[writeIndex] = array[i];
                writeIndex++;
            }
        }
        return writeIndex;
    }
}
