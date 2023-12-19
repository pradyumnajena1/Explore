package epp.stacknqueue.revision;

import epp.array.ArrayUtils;

import java.util.Stack;

public class ShortestEquivalentPath {
    public static void main(String[] args) {
        System.out.println(getShortestEquivalentPath("/usr/lib/../bin/gcc"));
        System.out.println(getShortestEquivalentPath("scripts/test/./../script2/awkscripts"));
    }

    private static String getShortestEquivalentPath(String path) {
        Stack<String> stack = new Stack<>();
        String[] parts = path.split("((?<=/)|(?=/))");
        for(String aPart:parts){
            if(aPart.equals("/")){
                stack.push(aPart);
            }else if(aPart.equals(".")){
                stack.pop();

            } else if (aPart.equals("..")) {
               stack.pop();
               stack.pop();
               stack.pop();
            }else {
             stack.push(aPart);
            }


        }
        String result = "";
        while (!stack.isEmpty()){
            result = stack.pop()+result;
        }
        return result;
    }
}
