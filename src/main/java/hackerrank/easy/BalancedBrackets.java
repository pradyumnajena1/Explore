package hackerrank.easy;

import java.util.Map;
import java.util.Stack;

public class BalancedBrackets {
    public static void main(String[] args) {
        System.out.println(isBalanced("{[()]}"));
        System.out.println(isBalanced("{[(])}"));
        System.out.println(isBalanced("{{[[(())]]}}"));
    }

    public static String isBalanced(String s) {
        // Write your code here
        if (isBalancedHelper(s)) {
            return "YES";
        }
        return "NO";
    }

    public static boolean isBalancedHelper(String s) {
        Stack<Character> stack = new Stack<>();
        Map<Character,Character> openClosingMap = Map.of('{','}', '[',']','(',')');

        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            if ( openClosingMap.keySet().contains(currentChar)) {
                stack.push(currentChar);
            } else {
                if(stack.isEmpty()){
                    return false;
                }
                Character openChar = stack.pop();
                if( openClosingMap.get(openChar )!= currentChar){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
