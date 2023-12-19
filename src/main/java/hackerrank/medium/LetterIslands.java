package hackerrank.medium;

import epp.Interval;
import epp.hashmap.revision.Range;

import java.util.*;
import java.util.stream.Collectors;

public class LetterIslands {
    public static void main(String[] args) {
        System.out.println(letterIslands("ababaewabaq", 2));
        System.out.println(letterIslands("abaab", 2));
    }
    public static int letterIslands(String s, int k) {
        // Write your code here
        Map<Set<Character>, List<Interval>> frequencies = new HashMap<>();
         for(int i=0;i<s.length();i++){
             for(int j=i+1;j<=s.length();j++){
                 if(j-i  <= s.length()/k){
                     String subString = s.substring(i,j);
                     Set<Character> uniqueChars = subString.chars().mapToObj(x -> Character.valueOf((char) x)).collect(Collectors.toSet());
                     List<Interval> frequency = frequencies.getOrDefault(uniqueChars, new ArrayList<>());
                     mergeranges(frequency, Interval.getClosedInterval(i,j-1));
                     frequencies.put(uniqueChars,frequency );
                 }


             }
         }
        System.out.println(frequencies);
        long count = frequencies.entrySet().stream().filter(x -> x.getValue().size() == k).count();
        return (int) count;
    }

    private static void mergeranges(List<Interval> frequency, Interval range ) {
        if(frequency.isEmpty()){
            frequency.add(range);

        }
        Interval last = frequency.get(frequency.size() - 1);
        if(last.isOverlapping(range)){
            last.merge(range);
        }else{

            frequency.add(range);
        }
    }
}
