package hackerrank.medium;

import epp.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MaximumPalindromes {
    public static void main(String[] args) {
         initialize("week");
        System.out.println(answerQuery(1, 4));
        System.out.println(answerQuery(2, 3));
       // System.out.println(computePermutation(new ArrayList<>(List.of(1))));
    }

    private static Map<Pair<Integer,Integer>,Integer> cache = new HashMap<>();
    public static void initialize(String s) {
        // This function is called once before all queries.
         for(int i=0;i<s.length();i++){
             for(int j=i+1;j<=s.length();j++){
                 String substring = s.substring(i,j);

                 Map<Character, Long> frequencies = substring.chars().mapToObj(x -> Character.valueOf((char) x)).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
                 int numPerm = getNumPerm(frequencies);
                 Pair<Integer, Integer> key = new Pair<>(i, j - 1);
                 cache.put(key,numPerm);

             }
         }
    }

    private static int getNumPerm(Map<Character, Long> frequencies) {
        List<Integer> permGroup = new ArrayList<>();
        int oddfreqCount = 0;
        for(long frequency: frequencies.values()){
            if(frequency%2==1){
                oddfreqCount++;
            }
            if(frequency/2!=0){
                permGroup.add((int) (frequency/2));
            }
        }

        int numPerm =  computePermutation(permGroup);
        numPerm = numPerm*(Math.max(oddfreqCount, 1));
        return numPerm;
    }

    private static int computePermutation(List<Integer> permGroup) {

        int count = (int) permGroup.stream().count();
       int perm =  factorial(count);
        for(int groupCount:permGroup){
            perm = perm / factorial(groupCount);
        }
        return perm;
    }
   static  int factorial(int n){
        int fact=1;
        for(int i=1;i<=n;i++){
            fact= fact*i % 1000000007;
        }
        return fact;
    }

    public static int answerQuery(int l, int r) {
        // Return the answer for this query modulo 1000000007.
          return cache.get(new Pair<>(l-1,r-1));
    }
}
