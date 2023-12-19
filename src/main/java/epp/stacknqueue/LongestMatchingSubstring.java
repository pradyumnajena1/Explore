package epp.stacknqueue;

import java.util.Stack;

public class LongestMatchingSubstring {

    public static void main(String[] args) {
        String string = "((())()(()(";
        String longestSubstring = getLongestmatchingSubstring(string);
        System.out.println(longestSubstring);
    }

    private static String getLongestmatchingSubstring(String string) {
        Stack<Tuple> stack = new Stack<>();
        String maxValidString = "";
        for(int i=0;i<string.length();i++){
            char ch = string.charAt(i);
            if(ch=='('){
                Tuple tuple = new Tuple(ch,i);
                stack.push(tuple);
            }else{

                if(!stack.isEmpty()){
                    if(stack.peek().ch=='('){
                        stack.pop();
                        int startIndex = stack.isEmpty()?0:stack.peek().index;
                        String substring = string.substring(startIndex+1, i + 1);
                        if(substring.length()>maxValidString.length()){
                            maxValidString = substring;
                        }
                    }else {
                        stack.push(new Tuple(ch,i));
                    }


                }else{
                    stack.push(new Tuple(ch,i));
                }
            }
        }

        return maxValidString;
    }
    private static class Tuple{
        char ch;
        int index;

        public Tuple(char ch, int index) {
            this.ch = ch;
            this.index = index;
        }
    }
}
