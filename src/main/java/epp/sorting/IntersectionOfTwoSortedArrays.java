package epp.sorting;

import epp.array.ArrayUtils;

import java.util.*;

public class IntersectionOfTwoSortedArrays {
    public static void main(String[] args) {
        int[] a = ArrayUtils.randomSortedArray(10,1,20);
        int[] b = ArrayUtils.randomSortedArray(20,1,20);
        ArrayUtils.printArray(a);
        ArrayUtils.printArray(b);
        System.out.println(getIntersection(a,b));
        System.out.println(getIntersection2(a,b));

    }

    private static List<Integer> getIntersection(int[] a, int[] b) {
        List<Integer> result = new ArrayList<>();
        int ptr_a = 0;
        int ptr_b = 0;
        while (ptr_a<a.length&&ptr_b<b.length){
            if(a[ptr_a]<b[ptr_b]){
                ptr_a++;
            }else if(a[ptr_a]>b[ptr_b]){
                ptr_b++;
            }else{
                if(result.isEmpty() ||    result.get(result.size()-1)<a[ptr_a]){

                    result.add(a[ptr_a]);
                }
                ptr_b++;
                ptr_a++;
            }
        }
        return result;
    }

    private static List<Integer> getIntersection2(int[] a, int[] b) {
        List<Integer> result = new ArrayList<>();
        Set<Integer> setB = new HashSet<>();
        Arrays.stream(b).forEach(x->setB.add(x));
        for(int x:a){
            if(setB.contains(x)){
                if(result.isEmpty() ||    result.get(result.size()-1)<x){

                    result.add(x);
                }
            }
        }

        return result;
    }
}
