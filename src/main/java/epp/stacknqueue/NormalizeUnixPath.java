package epp.stacknqueue;

import java.util.Stack;
import java.util.StringTokenizer;

public class NormalizeUnixPath {
    public static void main(String[] args) {
        String path = "usr/./scripts/dbscripts/../pyscripts/projectA/xyz.py";
        String normalizedPath = getNormalizedUnixPath(path);
        System.out.println(normalizedPath);
    }

    private static String getNormalizedUnixPath(String path) {
        Stack<String> stack = new Stack<>();
        StringTokenizer tokenizer = new StringTokenizer(path,"/",false);
        while (tokenizer.hasMoreTokens()){
            String token = tokenizer.nextToken();
            if(token.equals(".")){

            }else if(token.equals("..")){
                if(!stack.isEmpty()){
                    stack.pop();
                }
            }else{
                stack.push(token);
            }
        }
        String result = "";
        while (!stack.isEmpty()){
            result = stack.pop()+"/" + result;
        }
        return result;
    }
}
