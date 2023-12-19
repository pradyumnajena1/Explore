package epp.sorting;

import java.util.*;

public class IntervalCovering {
    public static void main(String[] args) {
        Event[] events = new Event[10];
        for(int i=0;i<events.length;i++){
            int start = (int) (Math.random() * 15);
            int duration =1+ (int) (Math.random() * 5);
            events[i] = new Event(start, start+duration);
        }
        System.out.println(Arrays.toString(events));
        TreeSet<Event> starts = new TreeSet<>(Comparator.comparing(Event::getStart));
        starts.addAll(Arrays.asList(events));
        for(Event event:starts){
            System.out.println(event);
        }
        TreeSet<Event> ends = new TreeSet<>(Comparator.comparing(Event::getEnd));

        ends.addAll(Arrays.asList(events));
        for(Event event:ends){
            System.out.println(event);
        }
        List<Integer> visits=new ArrayList<>();

        while (!ends.isEmpty()&& !starts.isEmpty()){
            Iterator<Event> endI = ends.iterator();
            Iterator<Event> startI = starts.iterator();
            Event nextEnd = endI.next();
            endI.remove();
            System.out.println("nextEnd->"+ nextEnd.end);
            Event nextStart = null;
            while (startI.hasNext() &&( nextStart= startI.next()).start<=nextEnd.end){
                System.out.println("nextStart->"+ nextStart.start);
                startI.remove();
                ends.remove(nextStart);

            }

            visits.add(nextEnd.end);
        }

        System.out.println(visits);
    }
}
