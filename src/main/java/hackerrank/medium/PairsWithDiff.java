package hackerrank.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PairsWithDiff {
    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.addAll(List.of(1, 5, 3, 4, 2));
        System.out.println(pairs(2, arr));
    }
    public static int pairs(int k, List<Integer> arr) {
        // Write your code here
        int count=0;
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<arr.size();i++){
            if(map.containsKey(arr.get(i)-k)){
                count++;
            }
            if(map.containsKey(arr.get(i)+k)){
                count++;
            }
            map.put(arr.get(i),i);
        }
        return count;

    }
}
