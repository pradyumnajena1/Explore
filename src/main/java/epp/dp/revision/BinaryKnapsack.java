package epp.dp.revision;

import epp.array.ArrayUtils;
import java.util.*;

public class BinaryKnapsack {
  public static void main(String[] args) {
    int[] cost = {65, 35, 245, 195, 65, 150, 275, 155, 120, 320, 75, 40, 200, 100, 220, 99};
    int[] weight = {20, 8, 60, 55, 40, 70, 85, 25, 30, 65, 75, 10, 95, 50, 40, 10};
    ArrayUtils.printArray(cost);
    ArrayUtils.printArray(weight);
    Result maxValue = getMaxValue(cost, weight, 130);
    System.out.println(maxValue);
  }

  /**
   * Returns max value,with the selected items for given knapsack size
   * time complexity
   * space complexity O(nw) where n is the number of items and w is total capacity
   * @param cost
   * @param weight
   * @param capacity
   * @return
   */
  public static Result getMaxValue(int[] cost, int[] weight, int capacity) {
    List<Item> items = new ArrayList<>();
    for (int i = 0; i < cost.length; i++) {
      items.add(new Item(cost[i], weight[i]));
    }
    return getMaxValue(items, capacity);
  }

  public static Result getMaxValue(List<Item> items, int capacity) {

    Map<List<Integer>, Result> cache = new HashMap<>();
    return getMaxValue(items, capacity, items.size()-1, cache);
  }

  private static Result getMaxValue(
      List<Item> items, int capacity, int index, Map<List<Integer>, Result> cache) {
    System.out.println(capacity + " " + index  );
    if (index < 0  || capacity == 0) {
      return new Result();
    }
    List<Integer> key = List.of(capacity, index);
    if (!cache.containsKey(key)) {
      Result notIncluding = getMaxValue(items, capacity, index - 1, cache);
      Result including = new Result();
      if (capacity >= items.get(index).weight) {
        Result partial = getMaxValue(items, capacity - items.get(index).weight, index - 1, cache);
        including = new Result(partial);
        including.add(items.get(index));
      }
      Result result = notIncluding;
      if (including.totalCost > notIncluding.totalCost) {
        result = including;
      }
      cache.put(key, result);
    }
    return cache.get(key);
  }





  public static class Result {
    List<Item> items = new ArrayList<>();
    int totalWeight = 0;
    int totalCost = 0;

    public Result() {}

    public Result(Result copy) {
      this.items = new ArrayList<>(copy.items);
      this.totalWeight = copy.totalWeight;
      this.totalCost = copy.totalCost;
    }

    public void add(Item item) {
      items.add(item);
      totalWeight += item.weight;
      totalCost += item.cost;
    }

    @Override
    public String toString() {
      return new StringJoiner(", ", Result.class.getSimpleName() + "[", "]")
          .add("items=" + items)
          .add("totalWeight=" + totalWeight)
          .add("totalCost=" + totalCost)
          .toString();
    }
  }

  public static class Item {
    int cost;
    int weight;

    public Item(int cost, int weight) {
      this.cost = cost;
      this.weight = weight;
    }

    @Override
    public String toString() {
      return new StringJoiner(", ", Item.class.getSimpleName() + "[", "]")
          .add("cost=" + cost)
          .add("weight=" + weight)
          .toString();
    }
  }
}
