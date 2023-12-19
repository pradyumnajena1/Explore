import java.util.*;

public class IntervalMerger {

    private static class Interval implements Comparable<Interval>{
        private static final Comparator<Interval> intervalComparator =
                Comparator.comparingInt((Interval x) -> x.start).thenComparing((Interval x) -> x.start);
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Interval o) {
            return intervalComparator.compare(this,o);
        }

        public boolean isOverlapping(Interval other){
            if(this.end < other.start){
                return false;
            }
            if(this.start > other.end){
                return false;
            }
            return true;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Interval{");
            sb.append("start=").append(start);
            sb.append(", end=").append(end);
            sb.append('}');
            return sb.toString();
        }

        public Interval merge(Interval interval) {
            return new Interval(Math.min(this.start,interval.start),Math.max(this.end,interval.end));
        }
    }
    public static void main(String[] args) {
        Interval[] intervals = new Interval[]{new Interval(1,2),new Interval(8,
                10),new Interval(12,16)};
        Interval[] result = merge(intervals,new Interval(3,5));
        System.out.println(Arrays.toString(result));
    }

    private static Interval[] merge(Interval[] intervals, Interval newInterval) {
        Arrays.sort(intervals);
        List<Interval> result = new ArrayList<>();
        for(Interval interval:intervals){
            if(interval.isOverlapping(newInterval)){
                newInterval = newInterval.merge(interval);

            }else{
                result.add(interval);
            }
        }
        result.add(newInterval);
        Collections.sort(result);
        return result.toArray(new Interval[0]) ;
    }
}
