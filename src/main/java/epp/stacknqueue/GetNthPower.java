package epp.stacknqueue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class GetNthPower {

    public static void main(String[] args) {
        List<Integer> expressionPath = getNthPower(20);
        System.out.println(expressionPath);
    }

    private static List<Integer> getNthPower(int reqdPower) {
        if(reqdPower==1){
            return List.of(1);
        }
        Queue<List<Integer>> queue = new ArrayDeque<>();
        queue.offer(List.of(1));

        while (!queue.isEmpty()){

            List<Integer> expressionPath = queue.poll();
            for(int x:expressionPath){

                int sum = x + expressionPath.get(expressionPath.size()-1);
                if(sum>reqdPower){
                    break;
                }
                List<Integer> newExpressionPath = new ArrayList<>(expressionPath);
                newExpressionPath.add(sum);
                if(sum==reqdPower){
                    return newExpressionPath;
                }
                queue.offer(newExpressionPath);
            }
        }

         return null;
    }
}
