package epp.binarysearchtree.revision;

import epp.Triplet;
import epp.array.ArrayUtils;

public class ClosestEntriesInThreeSortedArray {
    public static void main(String[] args) {
        int[] a = ArrayUtils.randomSortedArray(10, 1, 20);
        int[] b = ArrayUtils.randomSortedArray(10, 2, 20);
        int[] c = ArrayUtils.randomSortedArray(10, 3, 20);
        ArrayUtils.printArray(a);
        ArrayUtils.printArray(b);
        ArrayUtils.printArray(c);
        Triplet<Integer,Integer,Integer> closestTriplet =  findClosestEntries(a,b,c);
        System.out.println(closestTriplet);
    }

    public static Triplet<Integer, Integer, Integer> findClosestEntries(int[] a, int[] b, int[] c) {

        return findClosestEntries(a,0,a.length-1,b,0,b.length-1,c,0,c.length-1);
    }

    private static Triplet<Integer, Integer, Integer> findClosestEntries(int[] a, int a_start, int a_end, int[] b, int b_start, int b_end, int[] c, int c_start, int c_end) {
        int current_a = a_start;
        int current_b = b_start;
        int current_c = c_start;
        Triplet<Integer, Integer, Integer> result = null;
        int minDistance = Integer.MAX_VALUE;
        while (current_a<=a_end && current_b<=b_end&&current_c<=c_end){
            int distance = Math.max(Math.abs(b[current_b]-c[current_c]), Math.max(Math.abs(a[current_a]-b[current_b]),
                    Math.abs(a[current_a]-c[current_c])));
            if(distance<minDistance){
                minDistance = distance;
                result = new Triplet<>(current_a,current_b,current_c);
            }
            if(a[current_a]< Math.min(b[current_b],c[current_c])){
                current_a++;
            } else if (b[current_b]< Math.min(a[current_a],c[current_c])) {
                current_b++;
            }else{
                current_c++;
            }

        }
        return result;
    }
}
