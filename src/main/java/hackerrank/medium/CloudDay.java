package hackerrank.medium;

import java.io.*;
import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class CloudDay {

   static class Result {

        /*
         * Complete the 'maximumPeople' function below.
         *
         * The function is expected to return a LONG_INTEGER.
         * The function accepts following parameters:
         *  1. LONG_INTEGER_ARRAY p
         *  2. LONG_INTEGER_ARRAY x
         *  3. LONG_INTEGER_ARRAY y
         *  4. LONG_INTEGER_ARRAY r
         */

        public static long maximumPeople(List<Long> p, List<Long> x, List<Long> y, List<Long> r) {
            // Return the maximum number of people that will be in a sunny town after removing exactly one cloud.
            List<Event> events = new ArrayList<>();
            for(int i=0;i<y.size();i++){
                Event start = new Event(y.get(i)-r.get(i),1, i);
                Event end = new Event(y.get(i)+r.get(i),3, i);
                events.add(start);
                events.add(end);
            }
            for(int i=0;i<x.size();i++){
                Event city = new Event( x.get(i),2,i);
                events.add(city);
            }
            events.sort(Comparator.naturalOrder());
            System.out.println(events);
            long maxEffect = 0;

            long totalFree = 0;
            Map<Integer,Long> effectCount = new HashMap<>();
            for(int i=0;i< events.size();i++){
                Event event = events.get(i);

                if(event.eventType ==1){
                    effectCount.put(event.id, 0L);
                }
                if(event.eventType==2){
                    int cityId = event.id;
                    if(effectCount.size()==1){
                        Integer cloudId = effectCount.keySet().iterator().next();
                        Long effect = effectCount.get(cloudId);
                        long newEffect = effect + p.get(cityId);
                        effectCount.put(cloudId, newEffect);
                        if(newEffect >maxEffect){
                            maxEffect = newEffect;
                        }
                    }
                    if(effectCount.size()==0){
                        totalFree+= p.get(cityId);
                    }
                }
                if(event.eventType==3){
                    effectCount.remove(event.id);
                }
            }
            System.out.println(effectCount);
            System.out.println(totalFree);
            System.out.println(maxEffect);
           return totalFree+maxEffect;
        }

        static class Event implements Comparable<Event>{

            long x;
            int eventType  ;

            int id;
           private static Comparator<Event> eventComparator =
                   Comparator.comparingLong(Event::getX).thenComparingInt(Event::getEventType);


            public Event(long x, int eventType, int cloudId) {
                this.x = x;
                this.eventType = eventType;

                this.id = cloudId;
            }

            public long getX() {
                return x;
            }

            public int getEventType() {
                return eventType;
            }

            @Override
            public int compareTo(Event o) {
                 return eventComparator.compare(this,o);
            }

            @Override
            public String toString() {
                return new StringJoiner(", ", Event.class.getSimpleName() + "[", "]")
                        .add("x=" + x)
                        .add("eventType=" + eventType)
                        .add("id=" + id)
                        .toString();
            }
        }

    }

    public static class Solution {
        public static void main(String[] args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

            int n = Integer.parseInt(bufferedReader.readLine().trim());

            List<Long> p = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Long::parseLong)
                    .collect(toList());

            List<Long> x = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Long::parseLong)
                    .collect(toList());

            int m = Integer.parseInt(bufferedReader.readLine().trim());

            List<Long> y = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Long::parseLong)
                    .collect(toList());

            List<Long> r = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Long::parseLong)
                    .collect(toList());

            long result = Result.maximumPeople(p, x, y, r);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();

            bufferedReader.close();
            bufferedWriter.close();
        }
    }

}
