package epp.hashmap;

import java.util.*;

public class LongestRangeInArray {
    public static void main(String[] args) {
        int[] array = new int[]{3,-2,7,9,8,1,2,0};
        int longestRange = findLongestRange(array);
        System.out.println(longestRange);

        longestRange = findLongestRange2(array);
        System.out.println(longestRange);
    }

    private static int findLongestRange2(int[] array) {
        Set<Integer> values = new HashSet<>();
        for(int value:array){
            values.add(value);
        }
        int maxLength = Integer.MIN_VALUE;
         for(int i=0;i<array.length;i++){
             int currentValue = array[i];
             if(values.contains(currentValue)){

                 List<Integer> range = new ArrayList<>();
                 range.add(currentValue);
                 int temp    = currentValue-1;
                 while (values.contains(temp)){
                     values.remove(temp);
                     range.add(temp);
                     temp=temp-1;
                 }
                   temp    = currentValue+1;
                 while (values.contains(temp)){
                     values.remove(temp);
                     range.add(temp);
                     temp=temp+1;
                 }
                 maxLength = Math.max(maxLength,range.size());
             }
         }
         return maxLength;
    }

    private static int findLongestRange(int[] array) {
        Map<Integer,Integer> rangeMap = new HashMap<>();
        int maxLength = Integer.MIN_VALUE;
        for(int i=0;i<array.length;i++){
            int currentValue = array[i];
            int length = rangeMap.getOrDefault(currentValue-1,0)+1;

            rangeMap.put(currentValue, length);
            maxLength = Math.max(maxLength,length);
            int next =  currentValue+1;
            int nextLength = length+1;
            while (rangeMap.containsKey(next)){
                rangeMap.put(next,nextLength);
                maxLength = Math.max(maxLength,nextLength);
                nextLength++;
                next++;
            }

        }

        System.out.println(rangeMap);
        return maxLength;
    }
}
