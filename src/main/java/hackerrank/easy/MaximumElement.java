package hackerrank.easy;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MaximumElement {
    public static void main(String[] args) {
        System.out.println(getMax(new ArrayList<>(List.of("1 97",
                "2",
                "1 20",
                "2",
                "1 26",
                "1 20",
                "2",
                "3",
                "1 91",
                "3"))));
    }

    public static List<Integer> getMax(List<String> operations) {
        // Write your code here
        List<Integer> result = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> max = new Stack<>();
        for (String operation : operations) {
            String[] split = operation.split(" ");
            int opcode = Integer.parseInt(split[0]);
            if (opcode == 1) {
                Integer value = Integer.parseInt(split[1]);
                if (stack.isEmpty()) {
                    stack.push(value);
                    max.push(value);
                } else {
                    stack.push(value);
                    if (value >= max.peek()) {
                        max.push(value);
                    }
                }
            } else if (opcode == 2) {
                Integer pop = stack.pop();
                if (pop == max.peek()) {
                    max.pop();
                }
            } else {
                result.add(max.peek());
            }
        }
        return result;
    }
}
