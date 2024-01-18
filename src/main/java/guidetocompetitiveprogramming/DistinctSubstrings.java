package guidetocompetitiveprogramming;

import java.util.HashSet;
import java.util.Set;

public class DistinctSubstrings {
    public static void main(String[] args) {
        String s = "ABABABAB";
        System.out.println(numDIstinctSubstring(s,3));
    }

    private static int numDIstinctSubstring(String s, int length) {
        RollingHash rollingHash = new RollingHash(s);
        Set<Integer> uniques = new HashSet<>();
        for(int i=0;i+length-1<s.length();i++){
            uniques.add(rollingHash.getHash(i,i+length-1));
        }
        return uniques.size();
    }
}
