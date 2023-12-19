package leetcode.hard;

import java.util.*;
import java.util.stream.Collectors;

public class BusRoutes {
    public static void main(String[] args) {
        System.out.println(numBusesToDestination(new int[][]{{1, 2, 7}, {3, 6, 7}}, 1, 6));
        System.out.println(numBusesToDestination(new int[][]{{7,12},{4,5,15},{6},{15,19},{9,12,13}}, 15, 12));
    }
    public static int numBusesToDestination(int[][] routes,int source,int destination){
        Queue<Integer> bfsQueue = new ArrayDeque<>();
        Set<Integer> visitedSet = new HashSet<>();
        Map<Integer,Integer> parentMap = new HashMap<>();
        visitedSet.add(source);
        bfsQueue.offer(source);
        parentMap.put(source,null);
        boolean found = false;
        while (!bfsQueue.isEmpty()){
            Integer busStop = bfsQueue.poll();
            if(busStop==destination){
                found=true;
                break;
            }
            Set<Integer> nextStops = getNextStops(routes,busStop);
            for(Integer stop:nextStops){
                if(!visitedSet.contains(stop)){
                    visitedSet.add(stop);
                    bfsQueue.offer(stop);
                    parentMap.put(stop,busStop);
                }
            }

        }
        if(found){
            int count =0;
            Integer current = destination;
            while(current!=source){
                count++;
                current = parentMap.get(current);
            }
            return count;
        }else{
            return -1;
        }
    }

    private static Set<Integer> getNextStops(int[][] routes, Integer busStop) {
        Set<Integer> result = new HashSet<>();
        for(int[] stops:routes){
            List<Integer> ints =  Arrays.stream(stops).boxed().collect(Collectors.toList());;
            if(  ints.contains(busStop)){
              result.addAll(ints);
          }
        }
        return result;
    }
}
