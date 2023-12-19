package epp.hashmap;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AllStringDecomposition {
    public static void main(String[] args) {
        String s="amanaplanacanal";
        List<String> list = List.of("can","apl","ana");
        String substring = getAllStringDecomposition(s,list);
        System.out.println(substring);
    }

    private static String getAllStringDecomposition(String s, List<String> list) {


        Set<String> set = new HashSet<>(list);
        int start =0;
        int length = list.get(0).length();
        Integer sum = length *list.size();
        int end = sum-1;
        while (end<s.length()){
            int i=0;
            for( ;i<list.size();i++){
                String substring = s.substring(start+length*i, start + length * (i + 1));
                if(!set.contains(substring)){
                    break;
                }
            }
            if(i==list.size()){
                return s.substring(start,end+1);
            }

            start++;
            end++;
        }
        return null;
    }
}
