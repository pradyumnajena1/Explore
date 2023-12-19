package epp.sorting;

import epp.array.ArrayUtils;

public class MergeInPlace {
    public static void main(String[] args) {
        int[] a = ArrayUtils.randomSortedArray(10,1,20);
        int[] b = ArrayUtils.randomSortedArray(15,1,20);
        int[] temp = new int[a.length+b.length];
        System.arraycopy(b,0,temp,0,b.length);

        b = temp;
        ArrayUtils.printArray(a);
        ArrayUtils.printArray(b);
        mergeInPlace(a,b);
        ArrayUtils.printArray(b);
    }

    private static void mergeInPlace(int[] a, int[] b) {
        int writePosition = b.length-1;
        int read_a = a.length-1;
        int read_b = b.length-a.length-1;
        while (read_a>=0  && read_b>=0 ){

            if(a[read_a]>=b[read_b]){
                b[writePosition--] = a[read_a--];
            }else{
                b[writePosition--] = b[read_b--];
            }


        }

        while (read_a>=0){
            b[writePosition--] = a[read_a--];
        }

    }
}
