package epp.sorting;

import java.util.*;

public class MaximumConcurrentEvent {

    private static class EndPoint {
      static   Comparator<EndPoint> endPointComparator = Comparator.comparing(EndPoint::getTime).thenComparing(EndPoint::isStart, Comparator.reverseOrder());
        int time;
        boolean isStart;

        public EndPoint(int time, boolean isStart) {
            this.time = time;
            this.isStart = isStart;
        }

        public int getTime() {
            return time;
        }

        public boolean isStart() {
            return isStart;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("EndPoint{");
            sb.append("time=").append(time);
            sb.append(", isStart=").append(isStart);
            sb.append('}');
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        Event[] events = new Event[10];
        for(int i=0;i<events.length;i++){
            int start = (int) (Math.random() * 15);
            int duration =1+ (int) (Math.random() * 5);
            events[i] = new Event(start, start+duration);
        }
        System.out.println(Arrays.toString(events));
        List<List<Event>> parallelEvents = getMaxparallelEvents(events);
        System.out.println(parallelEvents.size());

        int maxParallelEvents2 = getMaxParallelEvents2(events);
        System.out.println(maxParallelEvents2);
    }

    private static int getMaxParallelEvents2(Event[] events){

        List<EndPoint> endPoints = new ArrayList<>();
        for(int i=0;i<events.length;i++){
            endPoints.add(new EndPoint(events[i].start,true));
            endPoints.add(new EndPoint(events[i].end,false));
        }
        Collections.sort(endPoints,EndPoint.endPointComparator);
        System.out.println(endPoints);
        int count = 0;
        int maxCount = Integer.MIN_VALUE;
        for(int i=0;i<endPoints.size();i++){
            if(endPoints.get(i).isStart){
             maxCount = Math.max(++ count,maxCount);

            }else{
                count--;
            }
        }

     return maxCount;
    }

    private static List<List<Event>> getMaxparallelEvents(Event[] events) {
        List<List<Event>> parallelEventGroups = new ArrayList<>();
        Arrays.sort(events);
        System.out.println(Arrays.toString(events));
        for(int i=0;i<events.length;i++){

            Event currentEvent = events[i];
            boolean added = false;
            for(int j = 0; j<parallelEventGroups.size(); j++){
                List<Event> parallelEventGroup = parallelEventGroups.get(j);
                if(parallelEventGroup.size()==0 || parallelEventGroup.get(parallelEventGroup.size()-1).end< currentEvent.start){
                    parallelEventGroup.add(currentEvent);
                    added = true;
                    break;
                }
            }
            if(!added){
                ArrayList<Event> parallelEventGroup = new ArrayList<>();
                parallelEventGroups.add(parallelEventGroup);
                parallelEventGroup.add(currentEvent);
            }

        }
            return parallelEventGroups;
    }
}
