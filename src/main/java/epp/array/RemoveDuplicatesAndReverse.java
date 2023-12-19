package epp.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class RemoveDuplicatesAndReverse {
    public static void main(String[] args) {
        int[] values = {5, 3, 7, 11, 2, 3, 13, 7};
        //int lastValidIndex = removeDuplicatesAndReverse(values);
        //System.out.println(lastValidIndex);
        System.out.println(Arrays.toString(values));

        int lastValidIndex =   removeDuplicatesAndReverse2(values);
        ArrayUtils.printArray(values);
        System.out.println(lastValidIndex);
    }
    private static int removeDuplicatesAndReverse2(int[] values) {
        int start = 0;
        int end = values.length-1;
        int numDuplicate =0;
        Set<Integer> uniques = new HashSet<>();
        while (end>=numDuplicate){
            if(start<values.length/2){

                ArrayUtils.swap(values,start++,end);
            }
           if( !uniques.contains(values[end])){
               uniques.add(values[end]);
               end--;
           }else{
               if(uniques.size()>0){

                   numDuplicate++;
               }
           }
             ArrayUtils.printArray(values);
            System.out.println("numDuplicate "+ numDuplicate+" start "+start+" end "+end);
            System.out.println();
        }

        return end;
    }

    private static int removeDuplicatesAndReverse(int[] values) {
        if(values.length<=1){
            return values.length;
        }
        Set<Integer> cache = new HashSet<>();
        int writeIndex = -1;
        int readIndex = values.length-1;
        while (readIndex>writeIndex){
            if(!cache.contains(values[readIndex])){

                cache.add(values[readIndex]);
                ArrayUtils. swap(values,readIndex--,++writeIndex);

            }else{
                readIndex--;
            }
        }
        for(readIndex=writeIndex+1;readIndex<values.length;readIndex++){
            if(!cache.contains(values[readIndex])){
                cache.add(values[readIndex]);
                values[++writeIndex] = values[readIndex];
            }
        }
        return writeIndex;

    }
}
