package hackerrank.easy;

import epp.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

public class LargestPermutation {
    public static void main(String[] args) {
      //  System.out.println(largestPermutation(1, new ArrayList<>(List.of(1, 2, 3, 4))));
        System.out.println(largestPermutation(2, new ArrayList<>(List.of(1, 2, 3, 4))));
         System.out.println(largestPermutation(1, new ArrayList<>(List.of(4, 2, 3, 5, 1))));
         System.out.println(largestPermutation(2, new ArrayList<>(List.of(4, 2, 3, 5, 1))));
         System.out.println(largestPermutation(3, new ArrayList<>(List.of(4, 2, 3, 5, 1))));
    }

    public static List<Integer> largestPermutation(int k, List<Integer> arr) {
        // Write your code here
        TreeMap<Integer, TreeSet<Integer>> digitIndexMap = new TreeMap<>();
        for(int i=0;i<arr.size();i++){
            TreeSet<Integer> indices = digitIndexMap.getOrDefault(arr.get(i), new TreeSet<>());
            indices.add(i);
            digitIndexMap.put(arr.get(i),indices);

        }
        //System.out.println(digitIndexMap);
        for(int i=0;i<arr.size()-1;i++){
            if(k>0){
               Pair<Integer, Integer> maxDigit =  findMaxDigit(digitIndexMap );
                if(maxDigit.getFirst()!=arr.get(i)){
                   // System.out.println("moving "+maxDigit.getFirst() +" from "+maxDigit.getSecond() +" to "+i);
                   // System.out.println(digitIndexMap);
                    moveDigitToIndex(digitIndexMap, new Pair<>( arr.get(i),i),maxDigit.getSecond());
                    swap(arr,maxDigit.getSecond(),i);
                   // System.out.println(digitIndexMap);
                    k--;
                }
            }else{
                break;
            }
        }
        return arr;
    }

    private static void swap(List<Integer> arr,int i,int j){
        int t = arr.get(i);
        arr.set(i,arr.get(j));
        arr.set(j,t);
    }

    private static void moveDigitToIndex(TreeMap<Integer, TreeSet<Integer>> digitIndexMap,
                                         Pair<Integer,Integer> digit, Integer toIndex) {

        TreeSet<Integer> indexSet = digitIndexMap.getOrDefault(digit.getFirst(),new TreeSet<>());
        indexSet.remove(digit.getSecond());
        indexSet.add(toIndex);
        digitIndexMap.put(digit.getFirst(),indexSet);
    }


   static Pair<Integer,Integer> findMaxDigit(TreeMap<Integer, TreeSet<Integer>> digitIndexMap){
        Integer max = digitIndexMap.lastKey();
       TreeSet<Integer> indexSet = digitIndexMap.get(max);
       Integer firstIndex = indexSet.first();
       indexSet.remove(firstIndex);
       if(indexSet.size()==0){
           digitIndexMap.remove(max);
       }
       return new Pair<>(max,firstIndex);
    }
}
