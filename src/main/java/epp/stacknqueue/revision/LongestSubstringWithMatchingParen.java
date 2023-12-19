package epp.stacknqueue.revision;

import java.util.Map;
import java.util.Stack;

public class LongestSubstringWithMatchingParen {
    public static void main(String[] args) {
        String expression="(((())][({})](())[[";
        String longestMatchingString = longestMatchingSubstring(expression);
        System.out.println(longestMatchingString);
    }

    private static String longestMatchingSubstring(String expression) {
        Map<Character,Character> mapping = Map.of('(',')','[',']','{','}');
        Stack<Tuple> stack = new Stack<>();
        String result = "";
        for(int i=0;i<expression.length();i++){
            char c = expression.charAt(i);
            if(mapping.keySet().contains(c)){
                stack.push(new Tuple(c,i));
            }else{
                Tuple peek = stack.peek();
                if(mapping.get(peek.ch).equals(c)){
                    stack.pop();
                    int length = stack.isEmpty() ? i+1: (i-stack.peek().position);
                    if(result.length()< length){
                        result = expression.substring(i-length+1,i+1);
                    }
                }else{
                    stack.push(new Tuple(c,i));
                }
            }
        }
        return result;
    }
    private static class Tuple{
        Character ch;
        int position;

        public Tuple(Character ch, int position) {
            this.ch = ch;
            this.position = position;
        }
    }
}
