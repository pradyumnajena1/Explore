package epp.heap.revision;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class SortKIncreasingDecreasingArray {
    public static void main(String[] args) {
        int[] values = new int[]{57, 131, 493, 294, 221, 339, 418, 452, 442, 190};
        Integer[] result = sortKIncreasingDecreasingArray(values);
        System.out.println(Arrays.toString(result));
    }

    private static Integer[] sortKIncreasingDecreasingArray(int[] values) {
        List<Range> ranges = getRanges(values);
        System.out.println(ranges);
        Integer[] result = mergeRanges(values, ranges);
        return result;
    }

    private static Integer[] mergeRanges(int[] values, List<Range> ranges) {
        PriorityQueue<Record> queue = new PriorityQueue<>();
        for(int i = 0; i< ranges.size(); i++){
            Record next = ranges.get(i).getNext();
            if(next!=null){
                queue.offer(next);
            }
        }
        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()){
            Record polled = queue.poll();
            result.add( polled.value);
            Record next = ranges.get(polled.rangeIndex).getNext();
            if(next!=null){
                queue.offer(next);
            }
        }
        return result.toArray(new Integer[0]) ;
    }

    private static List<Range> getRanges(int[] values) {
        List<Range> ranges = new ArrayList<>();
        int startIndex = 0;
        boolean increasing = true;
        for (int i = 1; i < values.length; i++) {
            if (increasing && values[i] < values[i - 1] || !increasing && values[i] > values[i - 1]) {
                Range range = new Range(values, startIndex, i - 1, ranges.size());
                ranges.add(range);
                increasing = !increasing;
                startIndex = i;
            }

        }
        Range range = new Range(values, startIndex, values.length - 1, ranges.size());
        ranges.add(range);
        return ranges;
    }

    private static class Record implements Comparable<Record>{
        int value;
        int rangeIndex;

        public Record(int value, int rangeIndex) {
            this.value = value;
            this.rangeIndex = rangeIndex;
        }

        @Override
        public int compareTo(Record o) {
            return Integer.compare(value,o.value);
        }
    }

    private static class Range {
        int[] values;
        // inclusive
        int startIndex;
        //exclusive
        int endIndex;
        int currentIndex;
        int rangeIndex;
        boolean increasing;

        public Range(int[] values, int startIndex, int endIndex, int rangeIndex) {
            this.values = values;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.rangeIndex = rangeIndex;
            this.increasing = values[startIndex]<values[endIndex-1];
            this.currentIndex =increasing? startIndex:endIndex;
        }

        Record getNext() {
            if (isEmpty()) {
                return null;
            }
            int value = increasing ? values[currentIndex++] : values[currentIndex--];
            return new Record(value, rangeIndex);
        }

        private boolean isEmpty() {
            return increasing && currentIndex == endIndex + 1 || !increasing && currentIndex == startIndex - 1;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Range{");
            sb.append("startIndex=").append(startIndex);
            sb.append(", endIndex=").append(endIndex);
            sb.append(", currentIndex=").append(currentIndex);
            sb.append(", rangeIndex=").append(rangeIndex);
            sb.append('}');
            return sb.toString();
        }
    }
}
