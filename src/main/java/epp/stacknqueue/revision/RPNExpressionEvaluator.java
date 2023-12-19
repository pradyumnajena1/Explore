package epp.stacknqueue.revision;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class RPNExpressionEvaluator {

    public static void main(String[] args) {
        System.out.println(new RPNExpressionEvaluator().evaluate("3,4,*,1,2,+,+"));
        System.out.println(new RPNExpressionEvaluator().evaluate("1,1,+,-2,*"));
        System.out.println(new RPNExpressionEvaluator().evaluate("4,6,/,2,/"));
    }
    private Stack<String> expressions;

    public RPNExpressionEvaluator() {
        init();
    }

    private void init() {
        expressions = new Stack<>();
    }

    public   int evaluate(String rpnExpression){
        init();
        String[] tokens = rpnExpression.split(",");
        for(String token:tokens){
            token = token.trim();
            Operators operator = Operators.getOperator(token);
            if(operator==null){
                expressions.push(token);
            }else{
             Integer value=   operator.evaluate(expressions);
             expressions.push(value.toString());
            }
        }
        if(expressions.isEmpty()||expressions.size()>1){
            throw new IllegalArgumentException("Invalid expression");
        }
        return Integer.parseInt(expressions.peek());
    }

    public enum Operators{
        PLUS("+"){
            @Override
            public Integer evaluate(Stack<String> expressions) {
                String right = expressions.pop();
                String left = expressions.pop();
                return Integer.parseInt(left)+Integer.parseInt(right);
            }
        },MINUS("-"){
            @Override
            public Integer evaluate(Stack<String> expressions) {
                String right = expressions.pop();
                String left = expressions.pop();
                return Integer.parseInt(left)-Integer.parseInt(right);
            }
        },MULTIPLY("*"){
            @Override
            public Integer evaluate(Stack<String> expressions) {
                String right = expressions.pop();
                String left = expressions.pop();
                return Integer.parseInt(left)*Integer.parseInt(right);
            }
        },DIVISION("/"){
            @Override
            public Integer evaluate(Stack<String> expressions) {
                String right = expressions.pop();
                String left = expressions.pop();
                return Integer.parseInt(left)/Integer.parseInt(right);
            }
        };

        private final String symbol;
        private static Map<String,Operators> valuesMap = new HashMap<>();

        Operators(String symbol) {
            this.symbol = symbol;

        }
        static {
            for(Operators op:values()){
                valuesMap.put(op.symbol,op);
            }
        }
        public static Operators getOperator(String symbol){
            return valuesMap.get(symbol);
        }

        public Integer evaluate(Stack<String> expressions) {
            return null;
        }
    }
}
