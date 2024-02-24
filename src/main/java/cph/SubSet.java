package cph;

import java.util.ArrayList;
import java.util.List;

public class SubSet {
    public static void main(String[] args) {
        printAllSubSets(new int[]{1,2,3,4});
    }

    private static void printAllSubSets(int[] values) {
        List<Integer> subset = new ArrayList<>();
        printAllSubSets(values,0,subset);
    }

    private static void printAllSubSets(int[] values, int i, List<Integer> subset) {
        if(i==values.length){
            System.out.println(subset);
            return;
        }
        printAllSubSets(values,i+1,subset);
        subset.add(values[i]);
        printAllSubSets(values,i+1,subset);
        subset.remove(subset.size()-1);
    }
}
