package epp.stacknqueue;

import java.util.*;

public class PostfixEvaluater {


    public static void main(String[] args) {
        int value = getValue(Arrays.asList("12", "5", "*","27","3","/","+"));
        System.out.println(value);
    }

    public static enum Operator{

        PLUS("+"){

            @Override
            int getValue(int op1, int op2) {
                return op1+op2;
            }
        },MINUS("-"){

            @Override
            int getValue(int op1, int op2) {
                return op1-op2;
            }
        },DIV("/"){
            @Override
            int getValue(int op1, int op2) {
                return op1/op2;
            }
        },MUL("*"){
            @Override
        int getValue(int op1, int op2) {
            return op1*op2;
        }};

        private final String symbol;
        private static final Map<String,Operator> map = new HashMap<>();
        Operator(String s) {
            this.symbol = s;

        }
        static {
            for(Operator operator:Operator.values()){
                map.put(operator.symbol,operator);
            }
        }
       abstract  int getValue(int op1,int op2);
        int getOperandCount(){
            return 2;
        }
       static Operator getOperator(String token){
            return map.get(token);
        }
    }
    public static int getValue(List<String> tokens){

        Stack<Integer> stack
                =new Stack<>();
        for (String token:tokens){
            Operator operator = Operator.getOperator(token);
            if(operator==null){
               stack.push(Integer.parseInt(token));


            }else{
                if(stack.size()>=operator.getOperandCount()){
                    Integer op1 =  stack.pop( );
                    Integer op2 =  stack.pop( );
                    stack.push(operator.getValue(op2,op1));
                }else {
                    throw new IllegalArgumentException("Invalid expression");
                }
            }
        }
        if(stack.size()!=1){
            throw new IllegalArgumentException("Invalid Expression");
        }
        return stack.pop();
    }
}
