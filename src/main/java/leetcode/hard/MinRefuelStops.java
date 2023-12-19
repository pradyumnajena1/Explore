package leetcode.hard;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MinRefuelStops {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] stations = {
                {10, 60},
                {20, 30},
                {30, 30},
                {60, 40}
        };
       /*  int[] range = solution.getRange(stations, 0, 35);
        int minRefuelStops = solution.minRefuelStops(100, 10, stations);
        System.out.println(minRefuelStops);

       minRefuelStops = solution.minRefuelStops(100, 1, new int[][]{
                {10, 100}
        });
        System.out.println(minRefuelStops);

        minRefuelStops = solution.minRefuelStops(1, 1, new int[][]{

        });
        System.out.println(minRefuelStops);

        minRefuelStops = solution.minRefuelStops(100, 50, new int[][]{
                {25, 30}
        });
        System.out.println(minRefuelStops);

         stations =new int[][] {
                {25, 25}, {50, 25}, {75, 25}
        };
          range = solution.getRange(stations, 50, 25);
          minRefuelStops = solution.minRefuelStops(100, 25, stations);
        System.out.println(minRefuelStops);*/


        stations =new int[][] {
                {5510987,84329695},{10682248,76273791},{56227783,136858069},{91710087,18854476},{111148380,127134059},{165982642,122930004},{184216180,124802819},{217578071,7123113},{233719001,95862544},{339631786,7676497},{349762649,136128214},{403119403,21487501},{423890164,61095325},{424072629,50415446},{572994480,13561367},{609623597,69207102},{662818314,84432133},{678679727,20403175},{682325369,14288077},{702137485,6426204},{716318901,47662322},{738137702,129579140},{761962118,23765733},{820353983,70497719},{895811889,75533360}
        };

       int minRefuelStops = solution.minRefuelStops(1000000000, 145267354, stations);
        System.out.println(minRefuelStops);
    }

    private static class Solution {
        public int minRefuelStops(int target, int startFuel, int[][] stations) {
            HashMap<String, Integer> cache = new HashMap<>();
            int minRefuelStops = minRefuelStops(0, target, startFuel, stations, cache);
            System.out.println(cache);
            return minRefuelStops;
        }

        private int minRefuelStops(int source, int target, int startFuel, int[][] stations, Map<String,Integer> cache) {
            if (source == target || source + startFuel >= target) {
                return 0;
            }
            String key = source + "_" + target + "_" + startFuel;
            if(cache.containsKey(key)){
                return cache.get(key);
            }
            int minStops = Integer.MAX_VALUE;
            int[] range = getRange(stations, source, startFuel);
            for (int i = range[0]; i <= range[1]; i++) {
                if (source<stations[i][0] && source + startFuel >= stations[i][0]) {
                    int remainingOil = startFuel - (stations[i][0] - source);
                    int minRefuelStops = minRefuelStops(stations[i][0], target, remainingOil + stations[i][1], stations
                            , cache);
                    if(minRefuelStops!=-1){
                        int numStops = 1 + minRefuelStops;
                        minStops = Math.min(numStops, minStops);
                    }

                }
            }

            int result = minStops == Integer.MAX_VALUE ? -1 : minStops;
            cache.put(key,result);
            return result;
        }

        int[] getRange(int[][] stations,int source,int startFuel){
             if(stations==null || stations.length==0){
                 return new int[]{0,-1};
             }
             int start = findEqualOrSmaller(stations,source);
             int end = findEqualOrSmaller(stations,source+startFuel);
             return new int[]{start,end};
        }

        private int findEqualOrSmaller(int[][] stations, int source) {
            int low =0;
            int high = stations.length-1;
            int index = 0;
            while (low<=high){
                int mid = (low+high)/2;
                if(stations[mid][0]==source){
                    index=mid;
                    break;
                }else if(stations[mid][0]<source){
                    index=mid;
                    low=mid+1;
                }else{
                    high=mid-1;
                }

            }
            return index;
        }
    }
}
