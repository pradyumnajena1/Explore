package epp.hashmap.revision;

import epp.array.ArrayUtils;
import epp.hashmap.MapUtils;

import java.util.*;

public class RearrangeArraySuchThatEqualValuesAreKApart {
    public static void main(String[] args) {
        int[] values = ArrayUtils.randomArray(10, 1, 8);
        ArrayUtils.printArray(values);
        rearrangeArraySuchThatEqualValuesAreKApart(values,3);
        ArrayUtils.printArray(values);
    }

    /**
     * TODO need to solve this later
     * @param values
     * @param k
     */
    public static void rearrangeArraySuchThatEqualValuesAreKApart(int[] values, int k) {

         Set<Integer> unique = new HashSet<>();
         int writePos = 0;
         for(int i=0;i< values.length;i++){
             if(!unique.contains(values[i])){
                 values[writePos++] = values[i];
                 unique.add(values[i]);
             }
             if(i>=k){
                 unique.remove(values[i-k]);
             }
         }
        ArrayUtils.printArray(values);
        System.out.println(writePos);

    }

}
