package hackerrank.hard;

import epp.CharTrie;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * For two strings A and B, we define the similarity of the strings to be the length of the longest prefix common to both strings. For example, the similarity of strings "abc" and "abd" is 2, while the similarity of strings "aaa" and "aaab" is 3.
 * <p>
 * Calculate the sum of similarities of a string S with each of it's suffixes.
 */
public class StringSimilarity {

    public static void main(String[] args) {
        System.out.println(stringSimilarity("ababaa"));
        System.out.println(stringSimilarity("aa"));
    }

    public static int stringSimilarity(String s) {
        // Write your code here
        CharTrie trie = new CharTrie();
        addAllSuffixes(s, trie);
        int totalSimilarity = 0;
        Set<String> seen = new HashSet<>();
        for (int i = s.length(); i > 0; i--) {
            String prefix = s.substring(0, i);
            List<String> wordsWithPrefix = trie.getWordsWithPrefix(prefix);
            for (String word : wordsWithPrefix) {

                if (!seen.contains(word)) {
                    //  System.out.println(prefix + " " + word);
                    totalSimilarity += prefix.length();
                    seen.add(word);
                }
            }
        }
        return totalSimilarity;
    }

    private static void addAllSuffixes(String s, CharTrie trie) {
        for (int i = 0; i < s.length(); i++) {
            String suffix = s.substring(i, s.length());
            trie.add(suffix);
        }
    }
}
