package epp.array.revision;

import epp.array.ArrayUtils;

public class EvenOdd {

    public static void main(String[] args){
        int[] values = ArrayUtils.randomArray(10, 1, 30);
        int[] values2 = new int[values.length];
        System.arraycopy(values,0,values2,0,values.length);
        ArrayUtils.printArray(values);
        evenOdd(values);
        ArrayUtils.printArray(values);

        ArrayUtils.printArray(values2);
        evenOdd2(values2);
        ArrayUtils.printArray(values2);
    }

    private static void evenOdd(int[] values) {
        int writeIndex = 0;
        for(int i=0;i<values.length;i++){
            if(values[i]%2==0){
                ArrayUtils.swap(values,writeIndex,i);
                writeIndex++;
            }
        }
    }

    private static void evenOdd2(int[] values) {
        int nextEven = 0;
    int nextOdd = values.length-1;
    while (nextEven<nextOdd){
        if(values[nextEven]%2==0){
            nextEven++;
        }else{
            ArrayUtils.swap(values,nextEven,nextOdd);
            nextOdd--;
        }
    }
    }
}
