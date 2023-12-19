package leetcode.hard;

import java.util.HashMap;
import java.util.Map;

public class MinEditDistance {
    public static void main(String[] args) {
        MinEditDistance med = new MinEditDistance();
        System.out.println(med.minDistance("horse", "ros"));
        System.out.println(med.minDistance("intention", "execution"));
        System.out.println(med.minDistance("", "execution"));
        System.out.println(med.minDistance("intention", ""));
        System.out.println(med.minDistance("", ""));
    }
    public     int minDistance(String word1, String word2) {
       return minDistance(word1.toCharArray(),word2.toCharArray());
    }

    private int minDistance(char[] arr1, char[] arr2) {
        Map<String,Integer> cache = new HashMap<>();
        return minDistance(arr1,0,arr1.length-1,arr2,0,arr2.length-1,cache);
    }

    private int minDistance(char[] arr1, int start1, int end1, char[] arr2  , int start2, int end2, Map<String, Integer> cache) {

        if(start1>end1 && start2>end2){
            return 0;
        }else if(start1>end1){
            return end2-start2+1;
        }else if(start2>end2){
            return end1-start1+1;
        }else{

            String key =  start1 + "_" + end1 + "_" + start2 + "_" + end2;

            if(cache.containsKey(key)){
                return cache.get(key);
            }else{
                int min = 0;
                if(arr1[start1]==arr2[start2]){
                    min = minDistance(arr1,start1+1,end1,arr2,start2+1,end2,cache);
                }else{
                    int delete =1+ minDistance(arr1,start1+1,end1,arr2,start2,end2,cache);
                    int replace =1+ minDistance(arr1,start1+1,end1,arr2,start2+1,end2,cache);
                    int insert =1+ minDistance(arr1,start1,end1,arr2,start2+1,end2,cache);
                    min = Math.min(delete, Math.min( replace,insert));


                }
                cache.put(key,min);
                return min;
            }
        }
    }
}
