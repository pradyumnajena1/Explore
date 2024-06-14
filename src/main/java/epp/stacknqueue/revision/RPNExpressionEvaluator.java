package epp.stacknqueue.revision;

import java.util.*;

public class RPNExpressionEvaluator {

    private Deque<Integer> intermediateResult;
    public RPNExpressionEvaluator() {
        init();
    }

    public static void main(String[] args) {
        System.out.println(new RPNExpressionEvaluator().evaluateRPN("3,4,*,1,2,+,+"));
        System.out.println(new RPNExpressionEvaluator().evaluateRPN("1,1,+,-2,*"));
        System.out.println(new RPNExpressionEvaluator().evaluateRPN("4,6,/,2,/"));
        System.out.println(new RPNExpressionEvaluator().evaluatePN("+,4,6"));
    }

    private void init() {
    intermediateResult = new ArrayDeque<>();
    }

    public   int evaluateRPN(String rpnExpression){
        String[] tokens = rpnExpression.split(",");
        return evaluateRPN(tokens);
    }

    public   int evaluatePN(String pnExpression){
        String[] tokens = pnExpression.split(",");
        List<String> list = Arrays.asList(tokens);
        Collections.reverse(list);
        String[] pnTokens = list.toArray(new String[0]);
        return evaluateRPN(pnTokens);
    }

    private Integer evaluateRPN(String[] tokens) {
        init();
        for(String token: tokens){
            token = token.trim();
            Operator operator = Operator.getOperator(token);
            if(operator==null){
                Integer operand = Integer.parseInt(token);
                intermediateResult.push(operand);
            }else{
             Integer value=   operator.evaluate(intermediateResult);
             intermediateResult.push(value);
            }
        }
        if(intermediateResult.size()!=1){
            throw new IllegalArgumentException("Invalid expression");
        }
        return intermediateResult.peek();
    }

    public enum Operator {
        PLUS("+"){
            @Override
            public Integer evaluate(Deque<Integer> expressions) {
                int right = expressions.pop();
                int left = expressions.pop();
                return  left + right;
            }
        },MINUS("-"){
            @Override
            public Integer evaluate(Deque<Integer> expressions) {
                int right = expressions.pop();
                int left = expressions.pop();
                return  left - right;
            }
        },MULTIPLY("*"){
            @Override
            public Integer evaluate(Deque<Integer> expressions) {
                int right = expressions.pop();
                int left = expressions.pop();
                return  left * right;
            }
        },DIVISION("/"){
            @Override
            public Integer evaluate(Deque<Integer> expressions) {
                int right = expressions.pop();
                int left = expressions.pop();
                return left/right;
            }
        };

        private static Map<String, Operator> valuesMap = new HashMap<>();

        static {
            for(Operator op:values()){
                valuesMap.put(op.symbol,op);
            }
        }

        private final String symbol;
        Operator(String symbol) {
            this.symbol = symbol;

        }

        public static Operator getOperator(String symbol){
            Operator operator = valuesMap.get(symbol);
            return  operator;

        }

        public Integer evaluate(Deque<Integer> expressions) {
            return null;
        }
    }
}
