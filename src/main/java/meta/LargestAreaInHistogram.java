package meta;

import java.util.Stack;

public class LargestAreaInHistogram {
    public static void main(String[] args) {
        System.out.println(getLargestArea(new int[]{6, 2, 5, 4, 5, 1, 6}));
        System.out.println(getLargestArea(new int[]{3, 5, 1, 7, 5, 9}));
    }

    private static int getLargestArea(int[] heights) {
        int[] left = new int[heights.length];
        int[] right = new int[heights.length];
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i< heights.length;i++){
            while (!stack.isEmpty() &&  heights[ stack.peek()]>heights[i]){
                stack.pop();
            }
            if(stack.isEmpty()){
                left[i] = -1;
            }else{
                left[i] = stack.peek();
            }
            stack.push(i);
        }

        stack = new Stack<>();
        for(int i=heights.length-1;i>=0 ;i--){
            while (!stack.isEmpty() &&  heights[ stack.peek()]>heights[i]){
                stack.pop();
            }
            if(stack.isEmpty()){
                right[i] = heights.length;
            }else{
                right[i] = stack.peek();
            }
            stack.push(i);
        }
        int maxArea = 0;
        for(int i=0;i<heights.length;i++){
            int width = right[i]-left[i]-1;
            int area = heights[i] * width;
            maxArea = Math.max(maxArea,area);
        }
        return maxArea;
    }
}
