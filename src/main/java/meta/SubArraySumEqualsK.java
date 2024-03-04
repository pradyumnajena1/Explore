package meta;

import epp.Pair;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SubArraySumEqualsK {
    public static void main(String[] args) {
        System.out.println(findSubArraySum(new int[]{1, 4, 20, 3, 10, 5},33));
        System.out.println(findSubArraySum(new int[]{1, 4, 0, 0, 3, 10, 5},7));

        System.out.println(findSubArraySum2(new int[]{1, 4, 20, 3, 10, 5},33));
        System.out.println(findSubArraySum2(new int[]{1, 4, 0, 0, 3, 10, 5},7));
    }

    private static Pair<Integer, Integer> findSubArraySum2(int[] values, int k) {
        int sum  = 0;
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0;i< values.length;i++){
            sum+=values[i];
            if(map.containsKey(sum-k)){
                return new Pair<>(map.get(sum-k)+1,i);
            }
            map.put(sum,i);
        }
        return null;
    }

    private static Pair<Integer, Integer> findSubArraySum(int[] values, int k) {
        int left = 0;
        int right = 0;
        int sum  = 0;
        while (right< values.length){

             while (right< values.length && sum<k){
                 sum+=values[right];
                 right++;
             }
             if(sum==k){
                 return new Pair<>(left,right-1);
             }
             while (left< values.length && sum > k){
                 sum-=values[left];
                 left++;
             }
        }
        return null;
    }
}
