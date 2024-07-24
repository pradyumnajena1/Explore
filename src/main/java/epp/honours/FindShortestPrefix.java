package epp.honours;

import epp.hashmap.revision.Trie;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FindShortestPrefix {
  public static void main(String[] args) {
    String query = "cat";
    Set<String> dictionary = Set.of("dog", "cut", "car", "be");
    String shortestPrefix = findShortestPrefix(query, dictionary);
    System.out.println(shortestPrefix);
  }

  private static String findShortestPrefix(String query, Set<String> dictionary) {
    Trie trie = new Trie();
    for (String key : dictionary) {
      trie.insert(key);
    }

    return trie.getShortestUniquePrefix(query);
  }

  private static class Trie {

    private TrieNode root = new TrieNode();

    public boolean insert(String key) {
      TrieNode current = root;
      for (char c : key.toCharArray()) {
        if (!current.leaves.containsKey(c)) {
          current.leaves.put(c, new TrieNode());
        }
        current = current.leaves.get(c);
      }
      if (current.isString()) {
        return false;
      }
      current.setString(true);
      return true;
    }

    public String getShortestUniquePrefix(String query) {
      TrieNode current = root;
      StringBuilder sb = new StringBuilder();
      for (char c : query.toCharArray()) {
        sb.append(c);
        if (!current.getLeaves().containsKey(c)) {
          return sb.toString();
        }
        current = current.getLeaves().get(c);
      }

      return "";
    }

    private static class TrieNode {
      private Map<Character, TrieNode> leaves = new HashMap<Character, TrieNode>();
      private boolean isString = false;

      public Map<Character, TrieNode> getLeaves() {
        return leaves;
      }

      public boolean isString() {
        return isString;
      }

      public void setString(boolean string) {
        isString = string;
      }
    }
  }
}
