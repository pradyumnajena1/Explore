package guidetocompetitiveprogramming;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SweepLineAlgo {
    public static void main(String[] args) {


        List<List<Integer>> customers = new ArrayList<>(List.of(List.of(1, 3), List.of(2, 7), List.of(3, 9), List.of(5, 11), List.of(12, 17), List.of(13, 19)));
        int maxConcurrentUsers = getMaxConcurrentUsers(customers);
        System.out.println(maxConcurrentUsers);


    }


    private static int getMaxConcurrentUsers(List<List<Integer>> customers) {
        List<Event> events = new ArrayList<>();
        for (List<Integer> cust : customers) {
            events.add(new Event(cust.get(0), true));
            events.add(new Event(cust.get(1), false));
        }
        events.sort(Comparator.naturalOrder());
        int concurrent = 0;
        int maxConcurrent = 0;
        for (Event event : events) {
            if (event.isArrival) {
                concurrent++;
            }
            maxConcurrent = Math.max(maxConcurrent, concurrent);
            if (!event.isArrival) {
                concurrent--;
            }

        }

        return maxConcurrent;
    }

    static class Event implements Comparable<Event> {
        int time;
        boolean isArrival;

        public Event(int time, boolean isArrival) {
            this.time = time;
            this.isArrival = isArrival;
        }

        @Override
        public int compareTo(Event o) {
            int compare = Integer.compare(this.time, o.time);
            if (compare != 0) return compare;
            return -Boolean.compare(this.isArrival, o.isArrival);


        }
    }
}
