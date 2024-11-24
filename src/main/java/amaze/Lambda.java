package amaze;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Lambda {
    public static void main(String[] args){
        BiFunction<String,Integer,Apple> supplier = Apple::new;
        Apple apple = supplier.apply("green", 100);
    System.out.println(apple);
        Function<String,Apple> supplier2 = Apple::new;
        apple = supplier2.apply("green");
    System.out.println(apple);
        List<Apple> apples = List.of(new Apple("green", 12), new Apple("green", 120), new Apple("green", 150));
        apples = new ArrayList<Apple>(apples);
        prettyPrint(apples, (Apple apple2) -> apple2.toString());
        prettyPrint(apples, Apple::toString);

        apples.sort(Comparator.comparing(Apple::getColor).reversed().thenComparing(Apple::getWeight));

        Predicate<Apple> redApple = (Apple a)-> a.getColor().equals("red");
        Predicate<Apple> greenApple = redApple.negate();
        Predicate<Apple> redAndHeavyOrGreen =
                redApple.and((a)->a.getWeight()>150).or((a)->a.getColor().equals("green"));

    someOperation((x,y)->x.add(y));
    someOperation(Foo::add2);

    Function<Integer,Integer> addOne = (x)->x+1;
    Function<Integer,Integer> mulTwo = (x)->x*2;
    Function<Integer,Integer> addThenMul = addOne.andThen(mulTwo);
    System.out.println(addThenMul.apply(1));

    List<Dish> dishList = new ArrayList<Dish>(List.of(new Dish("dish1",123),new Dish("dish2",213),new Dish("dish3",
            312)));
        Predicate<Dish> dishPredicate = dish -> dish.getCalorie() < 200;
        Comparator<Dish> comparator = Comparator.comparing(Dish::getCalorie);
        List<String> lowCalDishes = getDishNames(dishList, dishPredicate, comparator);
        System.out.println(lowCalDishes);
        dishPredicate = dish -> dish.getCalorie() >= 200;
        comparator = Comparator.comparing(Dish::getCalorie).reversed();
        List<String> highCalDishes = getDishNames(dishList, dishPredicate, comparator);
        System.out.println(highCalDishes);
    }

    private static List<String> getDishNames(List<Dish> dishList, Predicate<Dish> dishPredicate, Comparator<Dish> comparator) {
        List<String> lowCalDishes = dishList.stream().filter(dishPredicate)
                .sorted(comparator)
                .map(Dish::getName)
                .collect(Collectors.toList());
        return lowCalDishes;
    }

    static void prettyPrint(Collection<Apple> apples,Function<Apple,String> formatter){
        apples.forEach(apple -> System.out.println( formatter.apply(apple)));
    }

    static void someOperation(BiConsumer<Foo,Bar> consumer){
        consumer.accept(new Foo(), new Bar());
    }

    private static class Dish{
        String name;
        int calorie;

        public Dish(String name, int calorie) {
            this.name = name;
            this.calorie = calorie;
        }

        public String getName() {
            return name;
        }

        public int getCalorie() {
            return calorie;
        }
    }



    private static class Foo{
        void add(Bar bar){
            System.out.println("in foo");
        }

        static void add2(Foo foo,Bar bar){
            foo.add(bar);
        }
    }

    private static class Bar{
         void add(Foo foo){
      System.out.println("in bar");
         }

    }


    private static class Apple{
    private static final int DEFAULT_WEIGHT = 120;
        private String color;
        private int weight;

        public Apple(String color, int weight) {
            this.color = color;
            this.weight = weight;
        }

        public Apple(String color) {
            this.color = color;
            this.weight = DEFAULT_WEIGHT;
        }

        public String getColor() {
            return color;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Apple.class.getSimpleName() + "[", "]")
                    .add("color='" + color + "'")
                    .add("weight=" + weight)
                    .toString();
        }
    }
}
