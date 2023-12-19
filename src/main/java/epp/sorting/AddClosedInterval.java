package epp.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddClosedInterval {
    public static void main(String[] args) {
        Event[] events =new Event[]{new Event(0,2),new Event(3,5),new Event(6,7),new Event(9,12),new Event(13,22)};
        Event newEvent  = new Event(1,8);
        Event[] newEvents =  addEvents(events,newEvent);
        System.out.println(Arrays.toString(newEvents));

        Event[] results =  addEvents2(events,newEvent);
        System.out.println(results );
    }
    private static Event[] addEvents2(Event[] events,Event newEvent){
        int firstIndex = getFirstOverlapping(events,newEvent);
        int lastIndex = getLastOverlapping(events,newEvent);
        System.out.println(firstIndex+" "+lastIndex);
        Event[] result = new Event[firstIndex+1+events.length-lastIndex-1];
        for(int i=0;i<firstIndex;i++){
            result[i] = events[i];
        }
        result[firstIndex] = mergeAll(events,firstIndex,lastIndex,newEvent);
        for(int i=firstIndex+1;i<result.length;i++){
            result[i] = events[lastIndex+1+i];
        }
        return result;
    }

    private static Event mergeAll(Event[] events, int firstIndex, int lastIndex, Event newEvent) {
        for(int i=firstIndex;i<=lastIndex;i++){
            newEvent = addEvents(newEvent,events[i]);
        }
        return null;
    }

    private static int getFirstOverlapping(Event[] events,Event newEvent){
        int low = 0;
        int high = events.length-1;
        int index = -1;
        while (low<=high){
            int mid = low + (high-low)/2;
            if( isOverlapping(events[mid],newEvent) ){
                index = mid;
                high = mid-1;

            }else if(isBefore(events[mid],newEvent)){
                low = mid+1;
            }else{
                high = mid-1;
            }
        }
        return index;
    }

    private static int getLastOverlapping(Event[] events,Event newEvent){
        int low = 0;
        int high = events.length-1;
        int index = -1;
        while (low<=high){
            int mid = low + (high-low)/2;
            if( isOverlapping(events[mid],newEvent) ){
                index = mid;
                low = mid+1;

            }else if(isBefore(events[mid],newEvent)){
                low = mid+1;
            }else{
                high = mid-1;
            }
        }
        return index;
    }

    private static boolean isBefore(Event event, Event newEvent) {
        return event.end<newEvent.start;
    }

    private static Event[] addEvents(Event[] events, Event newEvent) {
        List<Event> result = new ArrayList<>();
        int i = 0;
        while (i<events.length && ! isOverlapping( events[i],newEvent) ){
            result.add(events[i]);
            i++;
        }
        Event last = new Event(newEvent.start,newEvent.end);
        while (i<events.length &&  isOverlapping(events[i],last)){
            last = addEvents(last,events[i]);
            i++;
        }
        result.add(last);
        while(i<events.length && i<events.length){
            result.add(events[i]);
            i++;
        }

        return result.toArray(new Event[0]);
    }

    private static Event addEvents(Event event1, Event event2) {
        return new Event(Math.min(event1.start,event2.start),Math.max(event1.end,event2.end));
    }

    private static boolean isOverlapping(Event event, Event newEvent) {
        if(event.end< newEvent.start){
            return false;
        }
        if(event.start>newEvent.end){
            return false;
        }
        return true;
    }
}
