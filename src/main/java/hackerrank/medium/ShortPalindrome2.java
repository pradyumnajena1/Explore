package hackerrank.medium;

import java.util.*;
import java.util.stream.Collectors;

public class ShortPalindrome2 {

    public static void main(String[] args) {
       System.out.println(shortPalindrome("ghhggh"));
        System.out.println(shortPalindrome("kkkkkkz"));
    }

    public static int shortPalindrome(String s) {
        // Write your code here
        Map<Character, TreeSet<Integer>> charIndexMap = new HashMap<>();
        for(int i=0;i<s.length();i++){
            char currentChar = s.charAt(i);
            TreeSet<Integer> indices = charIndexMap.getOrDefault(currentChar, new TreeSet<>());
            indices.add(i);
            charIndexMap.put(currentChar,indices);
        }
        int totalCount = 0;
        List<Map.Entry<Character, TreeSet<Integer>>> fourRepeatChars =
                charIndexMap.entrySet().stream().filter(x -> x.getValue().size() >= 4).collect(Collectors.toList());
        for(Map.Entry<Character, TreeSet<Integer>> entry:fourRepeatChars){
            int size = entry.getValue().size();
            totalCount+= ( size * (size-1) * (size-2) * (size-3)/24) %1000000007 ;
        }

        List<Map.Entry<Character, TreeSet<Integer>>> twoRepeatChars =
                charIndexMap.entrySet().stream().filter(x -> x.getValue().size() >= 2).collect(Collectors.toList());
         for(int i=0;i<twoRepeatChars.size();i++){
             for(int j=0;j<twoRepeatChars.size();j++){
                 if(i!=j){
                     List<Integer> firstCharIndices = new ArrayList<>( twoRepeatChars.get(i).getValue());

                     for(int k=0;k<firstCharIndices.size()-1;k++){
                         for(int l=k+1;l<firstCharIndices.size();l++){

                             TreeSet<Integer> secondCharIndices = twoRepeatChars.get(j).getValue();
                             Integer firstCharIndex = firstCharIndices.get(k);
                             Integer fourthCharIndex = firstCharIndices.get(l);
                             int secondCharNums = secondCharIndices.subSet(firstCharIndex, false, fourthCharIndex, false).size();

                             totalCount+=secondCharNums>=2? ( (secondCharNums*secondCharNums-1)/2)%1000000007:0;
                         }
                     }
                 }
             }
         }

        return totalCount;
    }
}
