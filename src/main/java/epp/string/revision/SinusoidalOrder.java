package epp.string.revision;

import java.util.ArrayList;
import java.util.List;

public class SinusoidalOrder {
  public static void main(String[] args) {
    String s = "Hello_World!";
    printSinusoidalOrder(s);
  }

  private static void printSinusoidalOrder(String s) {
    List<Character> firstRow = new ArrayList<>();
    List<Character> secondRow = new ArrayList<>();
    List<Character> thirdRow = new ArrayList<>();
    for (int i = 1; i < s.length(); i += 4) {
      firstRow.add(s.charAt(i));
    }
    for (int i = 0; i < s.length(); i += 2) {
      secondRow.add(s.charAt(i));
    }
    for (int i = 3; i < s.length(); i += 4) {
      thirdRow.add(s.charAt(i));
    }
    printSnakeOrder(firstRow, secondRow, thirdRow);
    printSinosodialOrder(firstRow, secondRow, thirdRow);
  }

  private static void printSinosodialOrder(
      List<Character> firstRow, List<Character> secondRow, List<Character> thirdRow) {
    for (int i = 0; i < firstRow.size(); i++) {
      printSpace(i == 0 ? 2 : 7);
      System.out.print(firstRow.get(i));
    }
    System.out.println();
    for (int i = 0; i < secondRow.size(); i++) {
      printSpace(i == 0 ? 0 : 3);
      System.out.print(secondRow.get(i));
    }
    System.out.println();
    for (int i = 0; i < thirdRow.size(); i++) {
      printSpace(i == 0 ? 6 : 7);
      System.out.print(thirdRow.get(i));
    }
  }

  private static void printSpace(int spaces) {
    for (int i = 0; i < spaces; i++) System.out.print(" ");
  }

  private static void printSnakeOrder(
      List<Character> firstRow, List<Character> secondRow, List<Character> thirdRow) {
    List<Character> result = new ArrayList<>();
    result.addAll(firstRow);
    result.addAll(secondRow);
    result.addAll(thirdRow);
    System.out.println(result);
  }
}
