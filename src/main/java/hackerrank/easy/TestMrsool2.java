package hackerrank.easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestMrsool2 {
    public static void main(String[] args) {
        List<String> values = new ArrayList<>(List.of("cat","bat","tab"));
        char[] alphates = new char[]{'c','t','b','a'};

        boolean isSorted = isSorted(values,alphates);
        System.out.println(isSorted);

        values = new ArrayList<>(List.of("ta","taca","tacab"));
          alphates = new char[]{'c','t','b','a'};

          isSorted = isSorted(values,alphates);
        System.out.println(isSorted);

        values = new ArrayList<>(List.of("taa","taac","tacab"));
        alphates = new char[]{'c','t','b','a'};

        isSorted = isSorted(values,alphates);
        System.out.println(isSorted);
    }

    private static boolean isSorted(List<String> values, char[] alphates) {
        Map<Character,Integer> indexMap = new HashMap<>();
        for(int i=0;i<alphates.length;i++){
            indexMap.put(alphates[i],i);
        }
        for(int i=1;i< values.size();i++){
            if(compare(values.get(i-1),values.get(i),indexMap) >0){
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @param s1
     * @param s2
     * @param indexMap
     * @return 0 if equal, -1 if s1 < s2 , 1 if s1 > s2
     */
    private static int compare(String s1, String s2, Map<Character,Integer> indexMap) {
        int s1Index = 0;
        int s2Index = 0;
        while (s1Index< s1.length() && s2Index<s2.length() && s1.charAt(s1Index)==s2.charAt(s2Index)){
            s1Index++;
            s2Index++;
        }
        if(s1Index== s1.length() && s2Index==s2.length()){
            return 0;
        }
        if(s1Index== s1.length()){
            return -1;
        }
        if(s2Index==s2.length()){
            return 1;
        }


        return indexMap.get(s1.charAt(s1Index)) - indexMap.get(s2.charAt(s2Index));
    }
}
