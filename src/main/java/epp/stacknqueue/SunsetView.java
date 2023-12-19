package epp.stacknqueue;

import java.util.*;

public class SunsetView {
    public static void main(String[] args) {
        List<Integer> buildings = Arrays.asList(5,21,2,7,17,9,3);
        List<Integer> sunsetViewBuildings = getSunsetViewBuilds(buildings);
        System.out.println(sunsetViewBuildings);
    }

    private static List<Integer> getSunsetViewBuilds(List<Integer> buildings) {

        Stack<Integer> stack  = new Stack<>();
        for(Integer building:buildings){
            if(stack.isEmpty()){
                stack.push(building);
            }else{
                while (!stack.isEmpty() && stack.peek().compareTo(building)<=0){
                    stack.pop();
                }
                stack.push(building);
            }
        }
        List<Integer> result = new ArrayList<>();
        while (!stack.isEmpty()){
            result.add(stack.pop());
        }
        Collections.reverse(result);
        return result;
    }
}
