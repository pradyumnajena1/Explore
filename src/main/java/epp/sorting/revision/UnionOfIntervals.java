package epp.sorting.revision;

import epp.Interval;

import java.util.*;

public class UnionOfIntervals {
    public static void main(String[] args) {
        Set<Interval> intervals = new HashSet<>();
        intervals.add(  Interval.getInterval(0,true,3,true));
        intervals.add(Interval.getInterval(5,false,7,true));
        intervals.add(Interval.getInterval(9,true,11,false));
        intervals.add(Interval.getInterval(12,false,14,false));

        intervals.add(Interval.getInterval(1,false,1,false));
        intervals.add(Interval.getInterval(3,false,4,true));
        intervals.add(Interval.getInterval(7,false,8,true));
        intervals.add(Interval.getInterval(12,true,16,false));

        intervals.add(Interval.getInterval(2,false,4,false));
        intervals.add(Interval.getInterval(8,false,11,true));
        intervals.add(Interval.getInterval(13,true,15,true));
        intervals.add(Interval.getInterval(16,true,17,true));
        System.out.println(intervals);
        List<Interval> union = unionOfIntervals(intervals);
        System.out.println(union);
    }

    private static List<Interval> unionOfIntervals(Set<Interval> set) {
        List<Interval> list = new ArrayList<>(set);
        Comparator<Interval> intervalComparator = Comparator.comparingInt((Interval x) -> x.getStart()).thenComparing((Interval x) -> x.isStartOpen());
        list.sort(intervalComparator);
        System.out.println(list);
        List<Interval> result = new ArrayList<>();
        result.add(list.get(0));
        Interval last = list.get(0);
        for(int i=1;i< list.size();i++){
            Interval current = list.get(i);

            if(last.isOverlapping(current)){
                 last.merge(current);
            }else{
                result.add(current);
                last = current;
            }
        }

        return result;
    }
}

