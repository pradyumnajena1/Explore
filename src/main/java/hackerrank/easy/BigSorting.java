package hackerrank.easy;

import java.math.BigInteger;
import java.util.*;

public class BigSorting {
    public static void main(String[] args) {
        ArrayList<String> unsorted = new ArrayList<>();
        unsorted.add("31415926535897932384626433832795");
        unsorted.add("31415");
        unsorted.add("314159265395");
        unsorted.add("97932384626433832795");
        System.out.println(bigSorting(unsorted));
    }

    public static List<String> bigSorting(List<String> unsorted) {
        // Write your code here
        Map<String,BigInteger> bigInts = new HashMap<>();
        for(String s:unsorted){
            bigInts.put(s,new BigInteger(s,10));
        }
        unsorted.sort(Comparator.comparing(bigInts::get));
        return unsorted;
    }

}
