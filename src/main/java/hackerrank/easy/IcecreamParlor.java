package hackerrank.easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IcecreamParlor {
    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.addAll(List.of(1, 4, 5, 3, 2));
        System.out.println(icecreamParlor(4, arr));
    }
    public static List<Integer> icecreamParlor(int m, List<Integer> arr) {
        // Write your code here

        List<Integer> result = new ArrayList<>();
        Map<Integer,Integer>    map = new HashMap<>();
        for(int i=0;i<arr.size();i++){
            if(map.containsKey(m-arr.get(i))){
                result.add(map.get(m-arr.get(i))+1);
                result.add(i+1);
            }
            map.put(arr.get(i),i);
        }

        return result;

    }
}
