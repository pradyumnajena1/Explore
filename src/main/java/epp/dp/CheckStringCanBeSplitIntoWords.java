package epp.dp;

import java.util.*;

public class CheckStringCanBeSplitIntoWords {
    public static void main(String[] args) {
         String s = "bedbathandbeyondmadam";
       // String s = "am";
        Set<String> dictionary = Set.of("bed","bath","and","beyond","hand","bat","mad","am","madam");
        Map<String,List<List<String>>> cache = new HashMap<>();
        List<List<String>> canbesplit = canbesplit(s,dictionary,cache);
        System.out.println(canbesplit);
    }

    private static List<List<String>> canbesplit(String s, Set<String> dictionary, Map<String, List<List<String>>> cache) {
        System.out.println(s);
        if(  s.isEmpty()){
            ArrayList<List<String>> lists = new ArrayList<>();
            lists.add(new ArrayList<>());
            return lists;
        }
        if(cache.containsKey(s)){
            return cache.get(s);
        }
        List<List<String>> solution = null;
        for(int i=1;i<=s.length();i++){
            String prefix = s.substring(0, i);
            boolean contains = dictionary.contains(prefix);
            if(contains ){
                List<List<String>> splits = canbesplit(s.substring(i), dictionary, cache);
                if(splits!=null){
                    if(solution==null){
                        solution = new ArrayList<>();
                    }
                    for(List<String> split:splits){
                        List<String> result = new ArrayList<>();
                        result.add(prefix);
                        result.addAll(split);
                        solution.add(result);

                    }

                }

            }
        }
        cache.put(s,solution);
        return solution;
    }
}
