package amaze;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class DishDriver {
  public static void main(String[] args) {
    List<Dish> menu = getMenu();
    Collector<Dish, ?, Map<Dish.Type, List<Dish>>> dishMapCollector = groupingBy(Dish::getType);
    Map<Dish.Type, List<Dish>> groups = menu.stream().collect(dishMapCollector);

    System.out.println(groups);
    List<Integer> list1 = new ArrayList<>(List.of(1, 2, 3));
    List<Integer> list2 = new ArrayList<>(List.of(4, 5, 6, 7));
    List<int[]> collect =
        list1.stream()
            .flatMap(x -> list2.stream().map(y -> new int[] {x, y}))
            .filter(x -> (x[0] + x[1]) % 3 == 0)
            .collect(Collectors.toList());
    collect.forEach(x -> System.out.println(Arrays.toString(x)));

    Optional<Dish> optionalDish = menu.stream().filter(Dish::isVegetarian).findAny();
    System.out.println(
        optionalDish.isPresent() ? optionalDish.get().getName() : "No vegetarian dishes found");

    List<Integer> values = new ArrayList<>(List.of(1, 2, 3, 4, 5));

    Integer sum = values.stream().reduce(0, Integer::sum);
    Integer prod = values.stream().reduce((a, b) -> a * b).orElse(0);
    System.out.println(sum);
    System.out.println(prod);
    Optional<Integer> first = values.stream().map(x -> x * x).filter(x -> x % 3 == 0).findFirst();
    System.out.println(
        first.isPresent() ? first.get() : "No values with square of a multiple of 3");

    Collector<Integer, HashSet<Integer>, HashSet<Integer>> collector =
        Collector.of(
            HashSet::new,
            Set::add,
            (set1, set2) -> {
              set1.addAll(set2);
              return set1;
            });
    Set<Integer> squares = values.stream().map(x -> x * x).collect(collector);
    System.out.println(squares);

    Optional<Dish> maxCal = menu.stream().collect(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)));
    System.out.println(maxCal.isPresent()?maxCal.get():"no dish present");

    Long numDishes = menu.stream().collect(Collectors.counting());
    int totalCal = menu.stream().collect(Collectors.summingInt(Dish::getCalories));
    IntSummaryStatistics intSummaryStatistics = menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));
    System.out.println(intSummaryStatistics);
    String shortMenu = menu.stream().map(Dish::getName).collect(Collectors.joining(","));
    System.out.println(shortMenu);

    Optional<Dish> max = menu.stream().collect(Collectors.reducing((x, y) -> x.getCalories() > y.getCalories() ? x : y));
    System.out.println(max.isPresent()? max.get() : "No dishes present");


    Integer maxCalorie = menu.stream().collect(Collectors.reducing(0, Dish::getCalories, Integer::max));
    Map<Boolean, Map<Dish.Type, List<Dish>>> multilevel = menu.stream().collect(groupingBy(Dish::isVegetarian, groupingBy(Dish::getType)));
    System.out.println(multilevel);

    Map<Dish.Type, Long> countMap = menu.stream().collect(groupingBy(Dish::getType, counting()));
    System.out.println(countMap);
    List<Integer> primes = IntStream.rangeClosed(1, 100).filter(x -> isPrime(x)).boxed().collect(toList());
    System.out.println(primes);
  }

  private static boolean isPrime(int n){
    int sqrt = (int) Math.sqrt(n);
    return IntStream.rangeClosed(2, sqrt).noneMatch(x->n%x==0);
  }

  private static List<Dish> getMenu() {
    return Arrays.asList(
        new Dish("pork", false, 800, Dish.Type.MEAT),
        new Dish("beef", false, 700, Dish.Type.MEAT),
        new Dish("chicken", false, 400, Dish.Type.MEAT),
        new Dish("french fries", true, 530, Dish.Type.OTHER),
        new Dish("rice", true, 350, Dish.Type.OTHER),
        new Dish("season fruit", true, 120, Dish.Type.OTHER),
        new Dish("pizza", true, 550, Dish.Type.OTHER),
        new Dish("prawns", false, 300, Dish.Type.FISH),
        new Dish("salmon", false, 450, Dish.Type.FISH));
  }
}
