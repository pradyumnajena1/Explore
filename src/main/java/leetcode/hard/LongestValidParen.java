package leetcode.hard;

import java.util.Stack;

public class LongestValidParen {
    public static void main(String[] args) {
        System.out.println(longestValidParentheses("((()(())"));
    }
    public static int longestValidParentheses(String s) {
        Stack<Pair> stack = new Stack<>();
        int maxLength = 0;
      for(int i=0;i<s.length();i++){
          if(s.charAt(i)=='('){
              stack.push(new Pair('(',i));
          }else{
              if(stack.peek().paren=='('){
                  stack.pop();
              }
              int length;
              if(stack.isEmpty()){
                  length = i+1;
              }else{
                  length = i-stack.peek().position;
              }
              maxLength = Math.max(length,maxLength);
          }
      }
      return maxLength;
    }

    private static class Pair {
        char paren;
        int position;

        public Pair(char paren, int position) {
            this.paren = paren;
            this.position = position;
        }
    }
}
