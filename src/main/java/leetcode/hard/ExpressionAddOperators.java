package leetcode.hard;

import java.util.*;

public class ExpressionAddOperators {
    public static void main(String[] args) {
        Solution solution = new Solution();
         System.out.println(solution.addOperators("123", 6));
        System.out.println(solution.addOperators("232", 8));
        System.out.println(solution.addOperators("3456237490", 9191));
        System.out.println(solution.addOperators("105", 5));
        System.out.println(solution.addOperators("00", 0));

    }

    private static class Solution {

        public List<String> addOperators(String num, int target) {
            Map<String,List<String>> cache = new HashMap<>();
           return addOperators(num,target,cache);
        }
        public List<String> addOperators(String num, int target,Map<String,List<String>> cache) {
            ArrayList<String> result = new ArrayList<>();
            if (num.isEmpty()) {
                return result;
            }
            try {
                if (num.length()==1 && target == Integer.parseInt(num)) {
                    result.add(num);
                    return result;
                }
            } catch (NumberFormatException e) {
                return result;
            }
            if (num.length() == 2) {
                int i = Integer.parseInt(num.substring(0, 1));
                int j = Integer.parseInt(num.substring(1));
                if (i + j == target) {
                    result.add(i + "+" + j);
                }
                if (i * j == target) {
                    result.add(i + "*" + j);
                }
                if (j!=0 && i / j == target && i % j == 0) {
                    result.add(i + "/" + j);
                }
                if (i - j == target) {
                    result.add(i + "-" + j);
                }
                return result;
            }
            String key = num + "_" + target;
            if(cache.containsKey(key)){
                return cache.get(key);
            }
            Set<String> expressions = new HashSet<>();
            for (int i = 1; i < num.length(); i++) {
                String first = num.substring(0, i);
                String second = num.substring(i);
                if( (first.length()==1 ||
                        first.length()>1 && !first.startsWith("0")) && (second.length()==1 ||
                        second.length()>1 && !second.startsWith("0") )){
                    getExpressions(target, expressions, first, second,cache);

                }



            }
            result = new ArrayList<>(expressions);
            cache.put(key,result);

            return result;
        }

        private void getExpressions(int target, Set<String> result, String first, String second, Map<String, List<String>> cache) {
            int firstOperand = Integer.parseInt(first);
            List<String> expressions = addOperators(second, target - firstOperand,cache);
            for (String exp : expressions) {


                    result.add(firstOperand + "+" + exp);

            }
            expressions = addOperators(second, firstOperand - target,cache);
            for (String exp : expressions) {
                result.add(firstOperand + "-" + exp);
            }
            if (target % firstOperand == 0) {
                expressions = addOperators(second, target / firstOperand,cache);
                for (String exp : expressions) {
                    if(!exp.contains("+") && !exp.contains("-")) {
                        result.add(firstOperand + "*" + exp);
                    }
                }
            }
            if (target!=0 && firstOperand % target == 0) {
                expressions = addOperators(second, firstOperand / target,cache);
                for (String exp : expressions) {
                    if(!exp.contains("+") && !exp.contains("-")) {
                        result.add(firstOperand + "/" + exp);
                    }
                }
            }

            int secondOperand = Integer.parseInt(second);
            expressions = addOperators(first, target - secondOperand,cache);
            for (String exp : expressions) {
                result.add(exp + "+" + secondOperand);
            }
            expressions = addOperators(first, target + secondOperand,cache);
            for (String exp : expressions) {
                result.add(exp + "-" + secondOperand);
            }
            if (secondOperand!=0 && target % secondOperand == 0) {
                expressions = addOperators(first, target / secondOperand,cache);
                for (String exp : expressions) {
                    if(!exp.contains("+") && !exp.contains("-")) {
                        result.add(exp + "*" + secondOperand);
                    }
                }
            }

            expressions = addOperators(first, secondOperand * target,cache);
            for (String exp : expressions) {
                if(!exp.contains("+") && !exp.contains("-")) {
                    result.add(exp + "/" + secondOperand);
                }
            }

        }


    }
}
