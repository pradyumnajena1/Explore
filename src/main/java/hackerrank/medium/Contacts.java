package hackerrank.medium;

import epp.CharTrie;

import java.util.ArrayList;
import java.util.List;

public class Contacts {

    public static List<Integer> contacts(List<List<String>> queries) {
        // Write your code here
        CharTrie trie = new CharTrie();
        List<Integer> counts = new ArrayList<>();
        for (List<String> query : queries) {
            if (query.get(0).equals("add")) {
                trie.add(query.get(1));
            } else if (query.get(0).equals("find")) {
                Integer numWordsWithPrefix = trie.numWordsWithPrefix(query.get(1));
                counts.add(numWordsWithPrefix!=null?numWordsWithPrefix:0);
            }
        }
        return counts;
    }

    public static void main(String[] args) {
        List<Integer> counts = contacts(new ArrayList<>(List.of(List.of("add", "hack"), List.of("add", "hackerrank"), List.of("find", "hac"), List.of("find", "hak"))));
        System.out.println(counts);
    }
}
