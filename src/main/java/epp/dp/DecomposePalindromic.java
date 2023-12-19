package epp.dp;

import java.util.ArrayList;
import java.util.List;

public class DecomposePalindromic {
    public static void main(String[] args) {
        String s = "0204451881";
        List<String> palindromes = decomposePalindromic(s);
        System.out.println(palindromes);
    }

    private static List<String> decomposePalindromic(String s) {
        if(s.isEmpty()){
            return new ArrayList<>();
        }
        if(s.length()<2 || ispalindrome(s)){
            ArrayList<String> strings = new ArrayList<>();
            strings.add(s);
            return strings;
        }
        int min = Integer.MAX_VALUE;
        List<String> result = null;
        for(int i=1;i<s.length();i++){
            String prefix = s.substring(0, i);
            String suffix = s.substring(i);
            if(ispalindrome(prefix) ){
                List<String> splits = decomposePalindromic(suffix);
                if(splits!=null){
                    if(min>splits.size()+1){

                        result= new ArrayList<>( );
                        result.add(prefix);
                        result.addAll(splits);
                    }

                }
            }
        }
        return result;
    }
    public static boolean ispalindrome(String s){
        if(s.length()<2){
            return true;
        }
        int start = 0;
        int end  = s.length()-1;
        while (start<end){
            if(s.charAt(start)!=s.charAt(end)){
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
