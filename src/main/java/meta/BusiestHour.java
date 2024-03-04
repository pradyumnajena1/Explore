package meta;

import epp.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringJoiner;

public class BusiestHour {
    public static void main(String[] args) {
        List<Activity> activities = new ArrayList<>();
        activities.add(new Activity(1,5));
        activities.add(new Activity(3,9));
        activities.add(new Activity(2,6));
        activities.add(new Activity(6,15));
        activities.add(new Activity(2,7));
        Pair<Long, Long> busiestHour = getBusiestHour(activities);
        System.out.println(busiestHour);
    }

    private static Pair<Long,Long> getBusiestHour(List<Activity> activities) {
        List<Event> events = new ArrayList<>();
        for(Activity activity:activities){
            events.add(new Event(activity.start, true));
            events.add(new Event(activity.end, false));
        }
        events.sort(Comparator.comparingLong(Event::getTimestamp).thenComparing(Event::isOpen));
        System.out.println(events);
        int openCount = 0;
        int maxOpenCount = 0;
        long busiestHourStart = 0;
        Pair<Long,Long> busiestHours = null;
        for(Event event:events){
            if(event.isOpen){
                openCount++;
                if(openCount>maxOpenCount){
                    maxOpenCount = openCount;
                    busiestHourStart = event.timestamp;
                }
            }else {
                if(openCount==maxOpenCount){
                    busiestHours = new Pair<>(busiestHourStart,event.timestamp);
                }
                openCount--;
            }
        }
            return busiestHours;
    }

    static class Event{
        long timestamp;
        boolean isOpen;

        public Event(long timestamp, boolean isOpen) {
            this.timestamp = timestamp;
            this.isOpen = isOpen;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public boolean isOpen() {
            return isOpen;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Event.class.getSimpleName() + "[", "]")
                    .add("timestamp=" + timestamp)
                    .add("isOpen=" + isOpen)
                    .toString();
        }
    }

    static class Activity{
        long start;
        long end;

        public Activity(long start, long end) {
            this.start = start;
            this.end = end;
        }
    }
}
