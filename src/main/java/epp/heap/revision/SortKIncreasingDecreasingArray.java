package epp.heap.revision;

import java.util.*;

public class SortKIncreasingDecreasingArray {
    public static void main(String[] args) {
        List<Integer> values =  new ArrayList<>(List.of(57, 131, 493, 294, 221, 339, 418, 452, 442, 190));
        List<Integer> result = sortKIncreasingDecreasingArray(values);
        System.out.println( result);
    }

    public static List<Integer> sortKIncreasingDecreasingArray(List<Integer> values) {

         List<List<Integer>> subLists = new ArrayList<>();
         boolean increasing = true;
         int start = 0;
         for(int i=1;i<=values.size();i++){
             if(i==values.size() ||
                     values.get(i-1)<values.get(i) && !increasing ||
                     values.get(i-1)>values.get(i) && increasing ){
                 List<Integer> subList = values.subList(start, i);
                 if(!increasing){
                     Collections.reverse(subList);
                 }
                 subLists.add(subList);
                 start = i;
                 increasing =!increasing;
                  
             }
         }
        List<Integer> result = MergeSortStreams.mergeSortStreams(subLists);
         return result;

    }



}
