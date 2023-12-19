package epp.hashmap.revision;

import epp.array.ArrayUtils;

import java.util.HashMap;
import java.util.Map;

public class NearestRepeatedEntriesInArray {
    public static void main(String[] args) {
        int[] values = ArrayUtils.randomArray(20,1,60);
        ArrayUtils.printArray(values);
        int nearestRepeatedEntry = findNearestRepeatedEntry(values);
        System.out.println(nearestRepeatedEntry);

    }

    private static int findNearestRepeatedEntry(int[] values) {
        Map<Integer,Integer> lastIndices = new HashMap<>();
        int nearest = Integer.MAX_VALUE;
        int nearestRepeatedIndex = -1;
        for(int i=0;i<values.length;i++){
           Integer lastIndex =  lastIndices.get(values[i]);
           if(lastIndex!=null){
               if(i-lastIndex<nearest){
                   nearest = i-lastIndex;
                   nearestRepeatedIndex = i;
               }
           }
           lastIndices.put(values[i],i);
        }
        return nearestRepeatedIndex;
    }
}
