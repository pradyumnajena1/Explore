package epp.dp;

import epp.array.ArrayUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class KnapsackProblem {
    public static void main(String[] args) {
        int[] prices = ArrayUtils.randomArray(10,5,30);
        int[] weights = ArrayUtils.randomArray(10,5,20);
         ArrayUtils.printArray(prices);
         ArrayUtils.printArray(weights);

        int max = getMaxValue(prices,weights,100);
        System.out.println(max);

        max = getMaxValue(new int[]{65,35,245,195,65,150,275,155,120,320,75,40,200,100,220,99},
                          new int[]{20,8,60,55,40,70,85,25,30,65,75,10,95,50,40,10 } ,130);
        System.out.println(max);
    }

    public static int getMaxValue(int[] prices, int[] weights, int capacity) {
        List<Integer> pricesList = Arrays.stream(prices).boxed().collect(Collectors.toList());
        List<Integer> weightsList = Arrays.stream(weights).boxed().collect(Collectors.toList());
        Map<Position,Integer> cache = new HashMap<>();
        return  getMaxValue(pricesList,
                weightsList,0,capacity,cache);
    }

    private static int getMaxValue(List<Integer> pricesList, List<Integer> weightsList, int offset, int capacity,Map<Position,Integer> cache) {

        if(capacity==0){
            return 0;
        }
        if(capacity<0){
            return Integer.MIN_VALUE;
        }
        if(offset == pricesList.size()){
            return 0;
        }
        Position key = new Position(offset, capacity);
        if(cache.containsKey(key)){
            return cache.get(key);
        }


        int maxValue =  Math.max(
                pricesList.get(offset)+ getMaxValue(pricesList ,weightsList,offset+1,capacity-weightsList.get(offset)
                        ,cache) ,

                getMaxValue(pricesList ,weightsList ,offset+1,capacity,cache));

        cache.put(key,maxValue);
        return maxValue;
    }
}
