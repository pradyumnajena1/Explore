package epp.dp.revision;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DivideSpoilEvenly {
  public static void main(String[] args) {
    int[] cost = {65, 35, 245, 195, 65, 150, 275, 155, 120, 320, 75, 40, 200, 100, 220, 99};
    System.out.println(divideEvenly(cost));
    System.out.println(divideEvenlyCostAndNumWise(cost));
  }

  private static List<Integer> divideEvenly(int[] cost) {
    int totalCost = 0;
    for (int c : cost) {
      totalCost += c;
    }
    int target = totalCost / 2;
    int split1 = getMaxValue(cost, target);
    return List.of(split1, totalCost - split1);
  }

  private static List<List<Integer>> divideEvenlyCostAndNumWise(int[] cost) {
    int totalCost = 0;
    for (int c : cost) {
      totalCost += c;
    }
    int targetCost = totalCost / 2;
    int targetCount = cost.length / 2;
    List<Integer> split1 = getMaxValue(cost, targetCost, targetCount);
    return List.of(split1, List.of( totalCost - split1.get(0),cost.length - split1.get(1)));
  }

  private static int getMaxValue(int[] cost, int targetSum) {
    Map<List<Integer>, Integer> cache = new HashMap<>();
    return getMaxValue(cost, targetSum, cost.length - 1, cache);
  }

  private static int getMaxValue(int[] cost, int targetSum, int i, Map<List<Integer>, Integer> cache) {
    if (i < 0 || targetSum == 0) {
      return 0;
    }
    List<Integer> key = List.of(targetSum, i);
    if (!cache.containsKey(key)) {
      int notIncluding = getMaxValue(cost, targetSum, i - 1, cache);
      int including =targetSum - cost[i]>=0? cost[i] + getMaxValue(cost, targetSum - cost[i], i - 1, cache):0;
      cache.put(key, Math.max(including, notIncluding));
    }
    return cache.get(key);
  }


  private static List<Integer> getMaxValue(int[] cost, int targetSum,int targetCount) {
    Map<List<Integer>, List<Integer>> cache = new HashMap<>();
    return getMaxValue(cost, targetSum,targetCount, cost.length - 1, cache);
  }

  private static List<Integer> getMaxValue(int[] cost, int targetSum,int targetCount, int i,
  Map<List<Integer>, List<Integer>> cache) {
    if (i < 0 || targetSum == 0 || targetCount == 0) {
      return new ArrayList<>( List.of(0,0));
    }
    List<Integer> key = List.of(targetSum,targetCount, i);
    if (!cache.containsKey(key)) {
      List<Integer> notIncluding = getMaxValue(cost, targetSum,targetCount, i - 1, cache);
      List<Integer> including = new ArrayList<>( List.of(0,0));
      if(targetSum - cost[i]>=0 && targetCount-1>=0 ){
        including =    getMaxValue(cost,
                targetSum - cost[i],targetCount-1,
                i - 1,
                cache);
        including = new ArrayList<>(List.of(including.get(0) + cost[i],including.get(1) + 1));

      };
      List<Integer> max= notIncluding;
      if(including.get(0)>  notIncluding.get(0)){
        max = including;
      }
      cache.put(key, max);
    }
    return cache.get(key);
  }
}
