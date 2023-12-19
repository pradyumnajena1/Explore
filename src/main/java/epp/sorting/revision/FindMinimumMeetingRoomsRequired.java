package epp.sorting.revision;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringJoiner;

public class FindMinimumMeetingRoomsRequired {
    public static void main(String[] args) {
        List<Meeting> meetings = new ArrayList<>();
        meetings.add(new Meeting(new EndPoint(1),new EndPoint(5,true)));
        meetings.add(new Meeting(new EndPoint(6),new EndPoint(10,true)));
        meetings.add(new Meeting(new EndPoint(11),new EndPoint(13,true)));
        meetings.add(new Meeting(new EndPoint(14),new EndPoint(15,true)));

        meetings.add(new Meeting(new EndPoint(2),new EndPoint(7,true)));
        meetings.add(new Meeting(new EndPoint(8),new EndPoint(9,true)));
        meetings.add(new Meeting(new EndPoint(12),new EndPoint(15,true)));

        meetings.add(new Meeting(new EndPoint(4),new EndPoint(5,true)));
        meetings.add(new Meeting(new EndPoint(9),new EndPoint(17,true)));

       int numRooms =  findMinimumMeetingRoomsRequired(meetings);
        System.out.println(numRooms);


    }

    private static int findMinimumMeetingRoomsRequired(List<Meeting> meetings) {
         List<EndPoint> endPoints = new ArrayList<>();
         for(Meeting meeting:meetings){
             endPoints.add(meeting.start);
             endPoints.add(meeting.end);
         }
         endPoints.sort(Comparator.comparing(EndPoint::getTime).thenComparing(EndPoint::isEnd));
        System.out.println(endPoints);
         int numRooms = 0;
         int maxRooms = 0;
         for(EndPoint endPoint:endPoints){
             if(!endPoint.isEnd()){
                 numRooms++;
                 maxRooms = Math.max(numRooms,maxRooms);
             }else{
                 numRooms--;
             }
         }
         return maxRooms;
    }

    static class Meeting{
        EndPoint start;
        EndPoint end;

        public Meeting(EndPoint start, EndPoint end) {
            this.start = start;
            this.end = end;
        }
    }
    static class EndPoint  {
        int time;
        boolean isEnd;

        public EndPoint(int time) {
            this.time = time;
        }

        public EndPoint(int time, boolean isEnd) {
            this.time = time;
            this.isEnd = isEnd;
        }

        public int getTime() {
            return time;
        }

        public boolean isEnd() {
            return isEnd;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", EndPoint.class.getSimpleName() + "[", "]")
                    .add("time=" + time)
                    .add("isEnd=" + isEnd)
                    .toString();
        }
    }
}
