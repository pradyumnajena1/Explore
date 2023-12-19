package hackerrank.medium;

import java.util.*;
import java.util.stream.Collectors;

public class ShortPalindrome {
    public static void main(String[] args) {
        System.out.println(shortPalindrome("ghhggh"));
        System.out.println(shortPalindrome("kkkkkkz"));
    }
    public static int shortPalindrome(String s) {
        // Write your code here
        Map<Character, List<Integer>> indexMap = new HashMap<>();
        for(int i=0;i<s.length();i++){
            List<Integer> list = indexMap.getOrDefault(s.charAt(i), new ArrayList<>());
            list.add(i);
            indexMap.put(s.charAt(i),list);
        }
        int numPalindromes = 0;
        for(int i=3;i<s.length();i++){
            int currentPos = i;
            List<Integer> fourthCharPositions = indexMap.get(s.charAt(i));
            int index = Collections.binarySearch(fourthCharPositions, currentPos);
            List<Integer>  firstCharPositions =
                    fourthCharPositions.subList(0,index);


               for(int fistCharPosition:firstCharPositions){

                   Map<Character, List<Integer>> allDuplicatesBetween = findAllDuplicatesBetween(fistCharPosition, i, indexMap);
                    for(Map.Entry<Character, List<Integer>> entry:allDuplicatesBetween.entrySet()){
                           numPalindromes+= nCr(entry.getValue().size(),2);
                    }

               }
        }
        return numPalindromes;

    }

    public static int nCr(int n, int r)
    {
        if (r > n)
            return 0;
        if (r == 0 || r == n)
            return 1;
        return nCr(n - 1, r - 1) + nCr(n - 1, r);
    }

    private static Map<Character, List<Integer>> findAllDuplicatesBetween(int start, int end, Map<Character, List<Integer>> indexMap) {
       Map<Character,List<Integer>> duplicates = new HashMap<>();
        for(Map.Entry<Character, List<Integer>> entry:indexMap.entrySet()){
            List<Integer> indices = entry.getValue();
            int i = Collections.binarySearch(indices, start + 1);
            if(i<0){
                i = -(i+1);
            }
            int j = Collections.binarySearch(indices, end  );
            if(j<0){
                j = -(j+1);
            }
            List<Integer> integerStream = indices.subList(i, j);


            //System.out.println(integerStream);
            if( integerStream. size()>=2){
               duplicates.put(entry.getKey(),integerStream);
           }
        }
        return duplicates;
    }
}
