package hackerrank.medium;

import epp.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class JimAndTheSkyscrapers {
    public static void main(String[] args) {
        System.out.println(solve(new ArrayList<>(List.of(3, 2, 1, 2, 3, 3))));
        System.out.println(solve(new ArrayList<>(List.of(1, 1000 ,1))));
    }
    public static int solve(List<Integer> arr) {
        // Write your code here

        Stack<Pair< Integer,Integer>> stack = new Stack<>();
        int count=0;
        for (int i=0;i<arr.size();i++){
            Integer value = arr.get(i);
            while (!stack.isEmpty() && stack.peek().getFirst()< value){
                stack.pop();
            }
            if(!stack.isEmpty() && stack.peek().getFirst().equals(value) ){
                count+=2*stack.peek().getSecond();
                stack.push(new Pair<>(value,stack.peek().getSecond()+1));
            }else{
                stack.push(new Pair<>(value,1));
            }
        }
        return count;
    }
}
