package epp.hashmap.revision;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ShortestPrefixNotPrefixOfAnyString {
    public static void main(String[] args) {
        System.out.println(shortestPrefixNotPrefixOfAnyString("cat", Set.of("dog","be","cut")));
        System.out.println(shortestPrefixNotPrefixOfAnyString("cat", Set.of("dog","be","cut","car")));
        System.out.println(shortestPrefixNotPrefixOfAnyString("cat", Set.of("dog","be","cut","car","cat")));
    }

    private static String shortestPrefixNotPrefixOfAnyString(String s, Set<String> domain) {
        Trie<Character> trie = new Trie<>();
        for(String string:domain){

            List<Character> list = string.chars().mapToObj(x -> Character.valueOf((char) x)).collect(Collectors.toList());
            trie.add(list);
        }
        for(int i=1;i<=s.length();i++){
            String prefix = s.substring(0, i);

            List<Character> list = prefix.chars().mapToObj(x -> Character.valueOf((char) x)).collect(Collectors.toList());
            if(!trie.containsAsPrefix(list)){
                return prefix;
            }
        }
        return  "";
    }
}
