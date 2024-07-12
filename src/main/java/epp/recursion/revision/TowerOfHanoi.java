package epp.recursion.revision;

import java.util.*;

public class TowerOfHanoi {
  public static void main(String[] args) {

    computeTowerHanoi(5);
  }

  public static void computeTowerHanoi(int numRings) {
    List<Tower> towers = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
      towers.add(new Tower("tower " + i));
    }
    for (int i = numRings; i > 0; i--) {
      towers.get(0).push(i);
    }
    System.out.println(towers.get(0));
    moveTowersIterative(towers.get(0), towers.get(1), towers.get(2), numRings);
    System.out.println(towers.get(1));
  }

  private static void moveTowers(Tower a, Tower b, Tower c, int size) {
    if (size > 0) {

      moveTowers(a, c, b, size - 1);
      System.out.println("moving " + a.peek() + " from " + a.name + " to " + b.name);
      b.push(a.pop());
      moveTowers(c, b, a, size - 1);
    }
  }


  private static void moveTowersIterative(Tower a, Tower b, Tower c, int size) {
    Deque<StackEntry> stack = new ArrayDeque<>();
    stack.push(new StackEntry(a, b, c, size));
    while (!stack.isEmpty()){
      StackEntry pop = stack.pop();
      if(pop.numRings>0){
        if(pop.numRings==1){
          System.out.println("moving " + pop.a.peek() + " from " + pop.a.name + " to " + pop.b.name);
          pop.b.push(pop.a.pop());
        }else{
          stack.push(new StackEntry(pop.c,pop.b,pop.a,pop.numRings-1));
          stack.push(new StackEntry(pop.a,pop.b,pop.c,1));
          stack.push(new StackEntry(pop.a,pop.c,pop.b,pop.numRings-1));
        }
      }
    }

  }
  private static class StackEntry{
    Tower a;
    Tower b;
    Tower c;
    int  numRings;

    public StackEntry(Tower a, Tower b, Tower c, int numRings) {
      this.a = a;
      this.b = b;
      this.c = c;
      this.numRings = numRings;
    }
  }

  private static class Tower {
    private String name;
    private Deque<Integer> stack = new LinkedList<>();

    public Tower(String name) {
      this.name = name;
    }

    public void push(int i) {
      if (stack.size() > 0 && stack.peek() < i) {
        throw new IllegalArgumentException("invalid param " + i);
      }
      stack.push(i);
    }

    public int peek() {
      return stack.peek();
    }

    public int pop() {
      return stack.pop();
    }

    public int size() {
      return stack.size();
    }

    @Override
    public String toString() {
      return new StringJoiner(", ", Tower.class.getSimpleName() + "[", "]")
          .add("name='" + name + "'")
          .add("stack=" + stack)
          .toString();
    }
  }
}
