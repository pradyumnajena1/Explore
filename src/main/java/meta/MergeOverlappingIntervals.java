package meta;

import epp.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Write a code to merge overlapping intervals.
 */
public class MergeOverlappingIntervals {
    public static void main(String[] args) {
        List<Pair<Integer,Integer>> intervals = new ArrayList<>();
        intervals.add(new Pair<>(1,5));
        intervals.add(new Pair<>(3,7));
        intervals.add(new Pair<>(2,9));
        intervals.add(new Pair<>(12,15));
        intervals.add(new Pair<>(16,19));
        intervals.add(new Pair<>(4,13));

     List<Pair<Integer,Integer>> mergedIntervals =    mergeIntervals(intervals);
        System.out.println(mergedIntervals);
    }

    private static List<Pair<Integer, Integer>> mergeIntervals(List<Pair<Integer, Integer>> intervals) {
        intervals.sort(Comparator.comparingInt(Pair::getFirst));
        List<Pair<Integer, Integer>> result = new ArrayList<>();
        for(int i=0;i< intervals.size();i++){
            Pair<Integer, Integer> currentInterval = intervals.get(i);
            if(result.isEmpty()  ){
                result.add(currentInterval);
                continue;
            }
            Pair<Integer, Integer> lastInterval = result.get(result.size()-1);
            if(lastInterval.getSecond()< currentInterval.getFirst()){
                result.add(currentInterval);
            }else{
                lastInterval.setSecond( Math.max(lastInterval.getSecond(), currentInterval.getSecond()));
            }
        }
            return result;
    }
}
