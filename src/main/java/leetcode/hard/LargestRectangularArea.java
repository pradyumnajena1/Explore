package leetcode.hard;

import epp.array.ArrayUtils;

import java.util.Stack;

public class LargestRectangularArea {
    public static void main(String[] args) {



      int  largestRectangleArea = largestRectangleArea(new int[]{2,1,5,6,2,3});
        System.out.println("largestRectangleArea"+ largestRectangleArea) ;

        largestRectangleArea = largestRectangleArea(new int[]{2,1,2});
        System.out.println("largestRectangleArea"+ largestRectangleArea) ;

        largestRectangleArea = largestRectangleArea(new int[]{1,2,3,4,5,6});
        System.out.println("largestRectangleArea"+ largestRectangleArea) ;


    }
    public static int largestRectangleArea(int[] heights) {
        Stack<Integer> positions = new Stack<>();
        int maxArea = 0;
        int i=0;
        for(  i=0;i<heights.length;i++){
            if(positions.isEmpty()){
                positions.push(i);
            }else{
                while (!positions.isEmpty() && heights[positions.peek()] > heights[i]){
                    Integer pop = positions.pop();
                    int width = i - (positions.isEmpty() ? -1: positions.peek()) - 1;
                    System.out.println("item "+pop+" min height " + heights[pop] + " width "+ width);
                    int areaAsMinSide = heights[pop] * width;
                    maxArea=Math.max(maxArea,areaAsMinSide);
                }
                positions.push(i);
            }
        }

        while (!positions.isEmpty()){
            Integer pop = positions.pop();
            int width = i - (positions.isEmpty() ? -1 : positions.peek()) - 1;
            System.out.println("item "+pop+" min height " + heights[pop] + " width "+ width);
            int areaAsMinSide = heights[pop] * width;
            maxArea=Math.max(maxArea,areaAsMinSide);
        }
        return maxArea;

    }


}
