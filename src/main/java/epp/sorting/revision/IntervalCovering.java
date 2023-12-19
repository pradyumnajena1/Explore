package epp.sorting.revision;

import epp.Interval;

import java.util.*;

/**
 * given a set of closed interval representing tasks performed in a office,
 * find the minimum cardinality of visit times of manager to cover all tasks
 */
public class IntervalCovering {

    public static void main(String[] args) {
        List<Interval> tasks = new ArrayList<>();
        tasks.add(Interval.getClosedInterval(1, 5));
        tasks.add(Interval.getClosedInterval(6, 10));
        tasks.add(Interval.getClosedInterval(11, 13));
        tasks.add(Interval.getClosedInterval(14, 15));

        tasks.add(Interval.getClosedInterval(2, 7));
        tasks.add(Interval.getClosedInterval(8, 9));
        tasks.add(Interval.getClosedInterval(12, 15));

        tasks.add(Interval.getClosedInterval(4, 5));
        tasks.add(Interval.getClosedInterval(9, 17));
        System.out.println(tasks);
        Set<Integer> visitTimes = getIntervalCover(tasks);
        System.out.println(visitTimes);

        visitTimes = getIntervalCover2(tasks);
        System.out.println(visitTimes);
    }

    private static Set<Integer> getIntervalCover(List<Interval> tasks) {
        tasks.sort(Interval.endComparator);
        System.out.println(tasks);
        Set<Integer> result = new HashSet<>();
        int current = 0;
        while (current < tasks.size()) {
            int visitTime = tasks.get(current).getEnd();
            result.add(visitTime);

            current++;
            while (current < tasks.size() && tasks.get(current).getStart() <= visitTime) {
                current++;
            }
        }

        return result;
    }

    private static Set<Integer> getIntervalCover2(List<Interval> tasks) {
        Set<Interval> starts = new TreeSet<>(Interval.startComparator);
        starts.addAll(tasks);
        Set<Interval> ends = new TreeSet<>(Interval.endComparator);
        ends.addAll(tasks);

        Set<Integer> result = new HashSet<>();
        while (!starts.isEmpty() && !ends.isEmpty()) {
            Interval interval = ends.iterator().next();
            result.add(interval.getEnd());
            Iterator<Interval> startIterator = starts.iterator();
            Interval removed;
            while (startIterator.hasNext() && (removed = startIterator.next()).getStart() <= interval.getEnd()) {
                startIterator.remove();
                ends.remove(removed);
            }
        }
        return result;
    }
}
