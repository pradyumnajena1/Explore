package epp.honours;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class ExpressionSynthesis {

  public static void main(String[] args) {

    System.out.println(evaluate(new ArrayList<>(List.of(12,3,4,5)), new ArrayList<>(List.of('+','+', '*'))));
    List<Integer> digits = new ArrayList<>(List.of(1,2,3,4,5 ));
    int target = 35;
    boolean feasible = expressionSynthesis(digits, target);
    System.out.println(feasible);

  }

  private static boolean expressionSynthesis(List<Integer> digits, int target) {
    List<Integer> operands = new ArrayList<>();
    List<Character> operators = new ArrayList<>();

    return expressionSynthesis(digits, target, operands, operators, 0, 0);
  }

  private static boolean expressionSynthesis(
      List<Integer> digits,
      int target,
      List<Integer> operands,
      List<Character> operators,
      int currentTerm,
      int offset) {
    currentTerm = currentTerm * 10 + digits.get(offset);
    if (offset == digits.size() - 1) {
      operands.add(currentTerm);
      if (evaluate(operands, operators) == target) {
        return true;
      }
      operands.remove(operands.size() - 1);
      return false;
    }

    // no operator
    if (expressionSynthesis(digits, target, operands, operators, currentTerm, offset + 1)) {
      return true;
    }

    // try * operator
    operands.add(currentTerm);
    operators.add('*');
    if (expressionSynthesis(digits, target, operands, operators, 0, offset + 1)) {
      return true;
    }
    operators.remove(operators.size() - 1);
    operands.remove(operands.size() - 1);

    // try + operator
    operands.add(currentTerm);
    if (target - evaluate(operands, operators) <= remainingInt(digits, offset + 1)) {
      operators.add('+');
      if (expressionSynthesis(digits, target, operands, operators, 0, offset + 1)) {
        return true;
      }
      operators.remove(operators.size() - 1);
    }
    operands.remove(operands.size() - 1);

    return false;
  }

  private static int evaluate(List<Integer> operands, List<Character> operators) {
    Deque<Integer> intermediateOperands = new ArrayDeque<>();
    int index = 0;
    intermediateOperands.offerFirst(operands.get(index++));
    for (char operator : operators) {
      if (operator == '*') {
        intermediateOperands.offerFirst(intermediateOperands.pollFirst() * operands.get(index++));
      } else {
        intermediateOperands.offerFirst(operands.get(index++));
      }
    }
    int sum = 0;
    while (!intermediateOperands.isEmpty()) {
      sum += intermediateOperands.pollFirst();
    }
    return sum;
  }

  // Calculates the int represented by digits . subList (idx , digits.size()).
  private static int remainingInt(List<Integer> digits, int idx) {
    int val = 0;
    for (int i = idx; i < digits.size(); ++i) {
      val = val * 10 + digits.get(i);
    }
    return val;
  }
}
