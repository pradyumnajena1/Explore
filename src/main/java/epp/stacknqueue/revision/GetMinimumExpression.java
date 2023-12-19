package epp.stacknqueue.revision;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class GetMinimumExpression {
    public static void main(String[] args) {
        List<Integer> expressionPath = getMinimumExpression(5);
        System.out.println("expressionPath"+ expressionPath);
    }

    private static List<Integer> getMinimumExpression(int n) {
        if(n==1){
            return List.of(1);
        }
        Queue<List<Integer>> expressions = new ArrayDeque<>();
        expressions.offer(List.of(1));
        while (!expressions.isEmpty()){
            List<Integer> exp = expressions.poll();

            int biggestTerm = exp.get(exp.size() - 1);
            for(int  term:exp){
                int sum = term + biggestTerm;
                if(sum >n){
                    break;
                }
                List<Integer> newExp = new ArrayList<>(exp);
                newExp.add(sum);
                if(sum==n){
                    return newExp;
                }
                expressions.offer(newExp);
                System.out.println(exp +"->"+newExp);
            }
        }
        return null;
    }
}
