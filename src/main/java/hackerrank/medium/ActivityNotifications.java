package hackerrank.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.TreeSet;

public class ActivityNotifications {
    public static void main(String[] args) {

        /*System.out.println(activityNotifications(new ArrayList<>(List.of(10, 20, 30, 40, 50)), 3));
        System.out.println(activityNotifications(new ArrayList<>(List.of(2, 3, 4, 2, 3, 6, 8, 4, 5)), 5));*/
        System.out.println(activityNotifications(new ArrayList<>(List.of(1, 2, 3, 4, 4)), 4));
    }

    public static int activityNotifications(List<Integer> expenditure, int d) {
        // Write your code here
        TreeSet<Pair> minSet = new TreeSet<>();
        TreeSet<Pair> maxSet = new TreeSet<>();
        for (int i = 0; i < d / 2; i++) {
            Pair pair = new Pair(expenditure.get(i), i);
            minSet.add(pair);
        }
        for (int i = d / 2; i < d; i++) {
            Pair pair = new Pair(expenditure.get(i), i);

            if (pair.value >= minSet.last().value) {
                maxSet.add(pair);
            } else {
                Pair last = minSet.last();
                minSet.remove(last);
                maxSet.add(last);
                minSet.add(pair);
            }

        }
        int notificationCount = 0;
        for (int i = d; i < expenditure.size(); i++) {

            double median = getMedian(minSet, maxSet);
            System.out.println(minSet);
            System.out.println(maxSet);
            System.out.println(median);
            if (expenditure.get(i) >= 2 * median) {
                notificationCount++;
            }
            Pair outgoing = new Pair(expenditure.get(i - d), i - d);
            Pair incoming = new Pair(expenditure.get(i), i);

            if (outgoing.compareTo(minSet.last()) > 0) {
                maxSet.remove(outgoing);

                if (incoming.value >= minSet.last().value) {
                    maxSet.add(incoming);
                } else {
                    Pair last = minSet.last();
                    minSet.remove(last);
                    maxSet.add(last);
                    minSet.add(incoming);
                }

            } else {
                minSet.remove(outgoing);

                if (incoming.value < maxSet.first().value) {
                    minSet.add(incoming);
                } else {
                    Pair first = maxSet.first();
                    maxSet.remove(first);
                    maxSet.add(incoming);
                    minSet.add(first);
                }

            }


        }
        return notificationCount;
    }

    private static double getMedian(TreeSet<Pair> minSet, TreeSet<Pair> maxSet) {
        if (minSet.size() == maxSet.size()) {
            return (minSet.last().value + maxSet.first().value) / 2.0;
        } else {
            return maxSet.size() > minSet.size() ? maxSet.first().value : minSet.last().value;
        }

    }


    private static class Pair implements Comparable<Pair> {
        int value;
        int index;

        public Pair(int value, int index) {
            this.value = value;
            this.index = index;
        }

        @Override
        public int compareTo(Pair o) {
            int compare = Integer.compare(this.value, o.value);
            return compare != 0 ? compare : Integer.compare(this.index, o.index);
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Pair.class.getSimpleName() + "[", "]")
                    .add("value=" + value)
                    .add("index=" + index)
                    .toString();
        }
    }
}
