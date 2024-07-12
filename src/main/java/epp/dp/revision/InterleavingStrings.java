package epp.dp.revision;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InterleavingStrings {
    public static void main(String[] args){
        System.out.println(isInterleaving("gtaa", "atc", "gattaca"));
        System.out.println(isInterleaving("gtaa", "atc", "gtataac"));
        System.out.println(isInterleaving("gtaa", "atc", "gatacta"));
    }

    private static boolean isInterleaving(String str1, String str2, String str3) {
        Map<List<Integer>,Boolean> cache = new HashMap<List<Integer>,Boolean>();
        return isInterleaving(str1, str2, str3, 0,0,0,cache);
    }

    private static boolean isInterleaving(String str1, String str2, String str3, int i, int j, int k, Map<List<Integer>, Boolean> cache) {
        if(i==str1.length() && j==str2.length() && k==str3.length() ){
            return true;
        }else if(k==str3.length() ){
            return false;
        }else if(i==str1.length()  ){
            return str2.substring(j).equals(str3.substring(k));
        }else if(j==str2.length()){
            return str1.substring(i).equals(str3.substring(k));
        }
        List<Integer> key = List.of(i,j,k);
        boolean result;
        if(!cache.containsKey(key)){
            result = str1.charAt(i)==str3.charAt(k) && isInterleaving(str1, str2, str3, i+1,j,k+1, cache);
            result |=  str2.charAt(j)==str3.charAt(k) && isInterleaving(str1, str2, str3, i,j+1,k+1, cache);
            cache.put(key, result);
        }
        return cache.get(key);
    }

}
