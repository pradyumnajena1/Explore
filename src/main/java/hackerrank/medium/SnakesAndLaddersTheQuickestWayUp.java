package hackerrank.medium;

import java.util.*;

public class SnakesAndLaddersTheQuickestWayUp {
    public static void main(String[] args) {
        System.out.println(quickestWayUp(
                new ArrayList<>(List.of(List.of(32, 62), List.of(42, 68), List.of(12, 98))),
                new ArrayList<>(List.of(List.of(95, 13),
                        List.of(97, 25),
                        List.of(93, 37),
                        List.of(79, 27),
                        List.of(75, 19),
                        List.of(49, 47),
                        List.of(67, 17)))));

        System.out.println(quickestWayUp(
                new ArrayList<>(List.of(List.of(8,52),
                        List.of(6,80),
                        List.of(26,42),
                        List.of(2,72))),
                new ArrayList<>(List.of(List.of(51,19),
                        List.of(39,11),
                        List.of(37,29),
                        List.of(81,3),
                        List.of(59,5),
                        List.of(79,23),
                        List.of(53,7),
                        List.of(43,33),
                        List.of(77,21)))));
    }

    public static int quickestWayUp(List<List<Integer>> ladders, List<List<Integer>> snakes) {
        Map<Integer, List<Integer>> laddersMap = new HashMap<>();
        Map<Integer, List<Integer>> snakesMap = new HashMap<>();
        for (List<Integer> ladder : ladders) {
            laddersMap.put(ladder.get(0), ladder);
        }
        for (List<Integer> snake : snakes) {
            snakesMap.put(snake.get(0), snake);
        }

        int quickestWayUp = quickestWayUp(laddersMap, snakesMap, 1, 100);

        return quickestWayUp;

    }

    private static int quickestWayUp(Map<Integer, List<Integer>> laddersMap, Map<Integer, List<Integer>> snakesMap,
                                     int start, int end ) {
        Queue<Integer> bfsQueue = new ArrayDeque<>();
        Set<Integer> visitedSet = new HashSet<>();
        Map<Integer,Integer> parent = new HashMap<>();
        visitedSet.add(start);
        bfsQueue.offer(start);
        parent.put(start,null);
        boolean success = false;
        while (!bfsQueue.isEmpty()){

            Integer polled = bfsQueue.poll();
            if(polled.equals(end)){
                success = true;
                break;
            }
            List<Integer> neighbours = getNeighbours(polled,end,laddersMap,snakesMap);
            for(int neighbour:neighbours){
                if(!visitedSet.contains(neighbour)){
                    visitedSet.add(neighbour);
                    bfsQueue.offer(neighbour);
                    parent.put(neighbour,polled);
                }
            }
        }
        if(success){
            int current=end;
            int numSteps = 0;
            while (current!=start){
                numSteps++;
                current = parent.get(current);
            }
            return numSteps;
        }
        return -1;
    }

    private static List<Integer> getNeighbours(int start, int end, Map<Integer, List<Integer>> laddersMap, Map<Integer, List<Integer>> snakesMap) {
        List<Integer> result = new ArrayList<>();
        for(int i=1;i<=6 && start+i<=end;i++){
            int nextPosition = start+i;
            if(laddersMap.containsKey(nextPosition)){
                result.add(laddersMap.get(nextPosition).get(1));
            }else if(snakesMap.containsKey(nextPosition)){
                result.add(snakesMap.get(nextPosition).get(1));
            }else{
                result.add(nextPosition);
            }
        }
        return result;
    }
}
