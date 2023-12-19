package epp.stacknqueue.revision;

import epp.array.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class SunsetView {
    public static void main(String[] args) {
        List<Integer> buildingHeights = Arrays.asList(2,11, 2,5,3,6,7,9,4,2,1);
        List<Integer> buildingWithSunsetView = getBuildingsWithSunsetView(buildingHeights);
        System.out.println(buildingWithSunsetView);
    }

    private static List<Integer> getBuildingsWithSunsetView(List<Integer> buildingHeights) {
        List<Integer> result = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        for(Integer height:buildingHeights){
            if(stack.isEmpty()){
                stack.push(height);
            }else{
                while (!stack.isEmpty() && stack.peek()<=height){
                    stack.pop();
                }
                stack.push(height);
            }
        }
        while (!stack.isEmpty()){
            result.add(stack.pop());
        }
        return result;
    }
}
