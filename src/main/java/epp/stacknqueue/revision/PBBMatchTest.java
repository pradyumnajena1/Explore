package epp.stacknqueue.revision;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.Stack;

public class PBBMatchTest {
    public static void main(String[] args) {
        String expression = "[[()()()]]()[[()()()]]";
        boolean isPBBMatched = isPBBMatched(expression);
        System.out.println(isPBBMatched);
    }

    private static boolean isPBBMatched(String expression) {
    Deque<Character> stack = new ArrayDeque<>();
        Map<Character,Character> mapping = Map.of('(',')','[',']','{','}');
        char[] charArray = expression.toCharArray();
        for(char c:charArray){
            if(mapping.keySet().contains(c)){
                stack.push(c);
            }else{
                if(stack.isEmpty()){
                    return false;
                }
                Character pop = stack.pop();
                if(mapping.get(pop) !=c){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
