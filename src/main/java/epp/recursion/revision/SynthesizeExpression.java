package epp.recursion.revision;

import java.util.*;
import java.util.stream.Collectors;

public class SynthesizeExpression {
    public static void main(String[] args) {
        int[] digits = {1, 2, 3,2,5,3,7,8,5,9};
        int sum = 995;
        char[] test = {'_', '+', '_', '_', '*', '*', '_', '*', '*'};
        List<Character> operators = new ArrayList<>();
        List<Integer> operands = new ArrayList<>();


         getExpressions(digits, sum, operators, operands, 0);
        //System.out.println(evaluate(operands, operators));
    }

    private static boolean getExpressions(int[] digits, int sum, List<Character> operators, List<Integer> operands, int level) {
        if (level == digits.length - 1) {
            operands.add(digits[level]);
            if (evaluate(operands, operators) == sum) {
                System.out.println(sum);
                System.out.println(getExpressionString(operands, operators));
                return true;
            }
            operands.remove(operands.size() - 1);

        } else {
            operands.add(digits[level]);
            operators.add('_');
            if (getExpressions(digits, sum, operators, operands, level + 1)) {
                return true;
            }
            operators.remove(operators.size() - 1);
            operands.remove(operands.size() - 1);

            operands.add(digits[level]);
            operators.add('+');
            if (getExpressions(digits, sum, operators, operands, level + 1)) {
                return true;
            }
            operators.remove(operators.size() - 1);
            operands.remove(operands.size() - 1);

            operands.add(digits[level]);
            operators.add('*');
            if (getExpressions(digits, sum, operators, operands, level + 1)) {
                return true;
            }
            operators.remove(operators.size() - 1);
            operands.remove(operands.size() - 1);


        }

        return false;
    }

    private static String getExpressionString(List<Integer> operands, List<Character> operators) {
        System.out.println(operands);
        System.out.println(operators);
        StringBuilder sb = new StringBuilder();
        sb.append(operands.get(0));
        for(int i=0;i<operators.size();i++){
            sb.append(operators.get(i)=='_'?"":operators.get(i));
            sb.append(operands.get(i+1));
        }

        return sb.toString();
    }

    private static int evaluate(List<Integer> operands, List<Character> operators) {

       operands = new ArrayList<>(operands);
       operators = new ArrayList<>(operators);

        System.out.println(operands);
        System.out.println(operators);

        for (int i = 0; i < operators.size(); i++) {
            if (operators.get(i) == '_') {
              int concat = operands.get(i) * 10 + operands.get(i + 1);
                operands.remove(i);
                operands.set(i,concat);
                operators.remove(i);
                i=i-1;
            }
        }



        System.out.println(operands);
        System.out.println(operators);


        for (int i = 0; i < operators.size(); i++) {
            if (operators.get(i) == '*') {
                int product = operands.get(i) * operands.get(i + 1);
                operands.remove(i);
                operands.set(i,product);
                operators.remove(i);
                 i=i-1;
            }
        }



        System.out.println(operands);
        System.out.println(operators);

        return operands.stream().mapToInt(Integer::intValue).sum();
    }


}
