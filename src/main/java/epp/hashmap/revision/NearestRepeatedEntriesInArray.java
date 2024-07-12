package epp.hashmap.revision;

import epp.array.ArrayUtils;

import java.util.HashMap;
import java.util.Map;

public class NearestRepeatedEntriesInArray {
    public static void main(String[] args) {

        int nearestRepeatedEntry = findNearestRepeatedEntry(new String[]{"All", "work", "and", "no", "play",
                "makes", "for", "no", "work", "no", "fun", "and", "no", "results"});
        System.out.println(nearestRepeatedEntry);

    }

    private static<T> int findNearestRepeatedEntry(T[] values) {
        Map<T,Integer> lastIndices = new HashMap<>();
        int nearest = Integer.MAX_VALUE;
        int nearestRepeatedIndex = -1;
        for(int i=0;i<values.length;i++){
           Integer lastIndex =  lastIndices.put(values[i],i);
           if(lastIndex!=null){
               if(i-lastIndex<nearest){
                   nearest = i-lastIndex;
                   nearestRepeatedIndex = i;
               }
           }

        }
        return nearestRepeatedIndex;
    }
}
