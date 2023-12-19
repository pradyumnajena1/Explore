package epp.hashmap;

import java.util.HashSet;
import java.util.Set;

public class ShortestUniquePrefix {
    public static void main(String[] args) {
        String s = "cat";
        Set<String> domain = Set.of("dog","be","cut");
        String shortestUniquePrefix = getShortestUniquePrefix(s,domain);
        System.out.println(shortestUniquePrefix);
    }

    private static String getShortestUniquePrefix(String s, Set<String> domain) {
        Set<String> prefixes = getPrefixes(domain);
        for(int i=1;i<=s.length();i++){
            String prefix = s.substring(0, i);
            if(!prefixes.contains(prefix)){
                return prefix;
            }
        }
            return null;
    }

    private static Set<String> getPrefixes(Set<String> domain) {
        Set<String> prefixes = new HashSet<>(domain);
        for(String value:domain){
            for(int i=1;i<value.length();i++){
              prefixes.add(  value.substring(0,i));
            }
        }
        return prefixes;
    }
}
