package amaze;

import java.util.ArrayList;
import java.util.List;

public class InterleavingOfTwoStrings {
    public static void main(String[] args) {
        System.out.println(getAllInterleavingStrings("AB", "CD"));
        System.out.println(getAllInterleavingStrings("AB", "C"));
    }

    private static List<String> getAllInterleavingStrings(String s1, String s2) {
        List<String> result = new ArrayList<>();
        StringBuilder sb  = new StringBuilder();
        getAllInterleavingStrings(s1,s2,0,0, result,sb);
        return result;
    }

    private static void getAllInterleavingStrings(String s1, String s2, int i, int j, List<String> result, StringBuilder sb) {
        if(i==s1.length() && j==s2.length()){
            result.add(sb.toString());
            return;
        }
        if(i<s1.length()){
            sb.append(s1.charAt(i));
            getAllInterleavingStrings(s1,s2,i+1,j,result,sb);
            sb.deleteCharAt(sb.length()-1);
        }

        if(j<s2.length()){
            sb.append(s2.charAt(j));
            getAllInterleavingStrings(s1,s2,i,j+1,result,sb);
            sb.deleteCharAt(sb.length()-1);
        }

    }
}
