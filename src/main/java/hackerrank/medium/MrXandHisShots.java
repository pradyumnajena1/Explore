package hackerrank.medium;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class MrXandHisShots {
    private static class Result{
        public static int solve(List<List<Integer>> shots, List<List<Integer>> players) {
            // Write your code here
            List<Event> events = new ArrayList<>();
            for(int i=0;i<shots.size();i++){
                events.add(new Event(shots.get(i).get(0),false,false,i));
                events.add(new Event(shots.get(i).get(1),true,false,i));
            }
            for(int i=0;i<players.size();i++){
                events.add(new Event(players.get(i).get(0),false,true,i));
                events.add(new Event(players.get(i).get(1),true,true,i));
            }
            events.sort(Comparator.naturalOrder());
            Set<Integer> openShots = new HashSet<>();
            Set<Integer> openPlayers = new HashSet<>();
            int total = 0;
            for(Event event:events){
                if(event.isPlayer){
                    if(!event.isEnd){
                        total+=openShots.size();
                        openPlayers.add(event.index);
                    }else{
                        openPlayers.remove(event.index);
                    }

                }else{
                    if(!event.isEnd){
                        openShots.add(event.index);
                        total+=openPlayers.size();
                    }else{
                        openShots.remove(event.index);
                    }
                }
            }

             return total;
        }
        private static class Event implements Comparable<Event>{
           private static Comparator<Event> eventComparator = Comparator.comparingInt((Event x) -> x.endPoint).thenComparing((Event x) -> x.isEnd);
            int endPoint;
            boolean isEnd;
            boolean isPlayer;
            int index;

            public Event(int endPoint, boolean isEnd, boolean isPlayer, int index) {
                this.endPoint = endPoint;
                this.isEnd = isEnd;
                this.isPlayer = isPlayer;
                this.index = index;
            }

            @Override
            public int compareTo(Event o) {

                return eventComparator.compare(this,o);
            }
        }
    }

        public static void main(String[] args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

            String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            int n = Integer.parseInt(firstMultipleInput[0]);

            int m = Integer.parseInt(firstMultipleInput[1]);

            List<List<Integer>> shots = new ArrayList<>();

            IntStream.range(0, n).forEach(i -> {
                try {
                    shots.add(
                            Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                    .map(Integer::parseInt)
                                    .collect(toList())
                    );
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });

            List<List<Integer>> players = new ArrayList<>();

            IntStream.range(0, m).forEach(i -> {
                try {
                    players.add(
                            Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                    .map(Integer::parseInt)
                                    .collect(toList())
                    );
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });

            int result = Result.solve(shots, players);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();

            bufferedReader.close();
            bufferedWriter.close();
        }

}
