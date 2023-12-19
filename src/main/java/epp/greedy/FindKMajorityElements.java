package epp.greedy;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class FindKMajorityElements {
    public static void main(String[] args) {
        int[] array = new int[]{4,1,1,1,1,2,2,2,5,2,3,3,3,3};
        int[] majorities =  findMajority(array,3);
        System.out.println(Arrays.toString(majorities));
    }

    private static int[] findMajority(int[] array, int k) {
        Map<Integer,Integer> frequencies = new HashMap<>();
        for(int i=0;i<array.length;i++) {
            frequencies.put(array[i], frequencies.getOrDefault(array[i], 0) + 1);
            if (frequencies.size() == k + 1) {
                frequencies.replaceAll((x,y)->y-1);
                frequencies.entrySet().removeIf(entry->entry.getValue()==0);
            }
        }
        frequencies.replaceAll((x,y)->0);
        Arrays.stream(array).filter(i->frequencies.containsKey(i)).forEach(i->frequencies.put(i,frequencies.get(i)+1));
        int[] result = new int[k];
        AtomicInteger wp= new AtomicInteger();
        frequencies.entrySet().stream().filter(e->e.getValue()>=array.length/k).forEach(e->result[wp.getAndIncrement()]=e.getKey());
        return result;
    }
}
