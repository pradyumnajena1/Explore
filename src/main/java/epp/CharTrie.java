package epp;

import epp.hashmap.revision.Trie;

import java.util.*;

public class CharTrie {
    private TrieNode root = new  TrieNode( ' ');

    public CharTrie() {
    }

    public static void main(String[] args) {
        CharTrie trie = new CharTrie();
        trie.add("1");
        trie.add( "123");
        trie.add( "1234");
        trie.add( "234");
        trie.add( "2345");
        trie.add( "23456");
        trie.add( "345");
        trie.add( "34567");
        System.out.println(trie);
        System.out.println(trie.contains( "1"));
        System.out.println(trie.contains( "12"));
        System.out.println(trie.contains( "123"));
        System.out.println(trie.contains( "1234"));
        System.out.println(trie.contains( "12345"));
        System.out.println(trie.getWordsWithPrefix( "1"));
        System.out.println(trie.numWordsWithPrefix("234"));

    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Trie.class.getSimpleName() + "[", "]")
                .add("root=" + root)
                .toString();
    }

    public void add(String word) {
        add(word.toCharArray());
    }
    public void add(char[] sequence) {
         TrieNode  current = root;
        for (int i = 0; i < sequence.length; i++) {
            char value = sequence[ i];
            if (current.children.containsKey(value)) {
                current = current.children.get(value);
                current.prefixCount++;

            } else {
                 TrieNode  newNode = new  TrieNode (value);
                current.addChild(newNode);
                current = newNode;
                current.prefixCount++;
            }

        }

        current.endOfSequence = true;
    }


    public boolean contains(String word) {
        return contains(word.toCharArray());
    }
    public boolean contains(char[] sequence) {
         TrieNode  current = root;
        for (int i = 0; i < sequence.length; i++) {
            char value = sequence[i];
            if (current.children.containsKey(value)) {
                current = current.children.get(value);
            } else {
                return false;
            }
        }
        return current.endOfSequence;

    }

    public Integer numWordsWithPrefix(String sequence) {
        return numWordsWithPrefix(sequence.toCharArray());
    }
    public Integer numWordsWithPrefix(char[] sequence) {
         TrieNode current = root;
        for (int i = 0; i < sequence.length; i++) {
            char value = sequence[ i];
            if (current.children.containsKey(value)) {
                current = current.children.get(value);
            } else {
                return null;
            }
        }
        return current.prefixCount;

    }

    public boolean containsAsPrefix(char[] sequence) {
         TrieNode  current = root;
        for (int i = 0; i < sequence.length; i++) {
            char value = sequence [i];
            if (current.children.containsKey(value)) {
                current = current.children.get(value);
            } else {
                return false;
            }
        }
        return true;

    }
    public List<String> getWordsWithPrefix(String word){
        return getWordsWithPrefix(word.toCharArray());
    }
    public List<String> getWordsWithPrefix(char[] sequence){
         TrieNode  current = root;

        for (int i = 0; i < sequence.length; i++) {
            char value = sequence [i];
            if (current.children.containsKey(value)) {
                current = current.children.get(value);
            } else {
                return new ArrayList<>();
            }
        }
        List<String> result = new ArrayList<>();
        List<String> allSuffixes =  getAllWords(current);
        String seqString = new String(sequence);
        for(String suffix:allSuffixes){

            result.add(seqString.substring(0,seqString.length()-1)+suffix);
        }
        return result;

    }

    private List<String> getAllWords( TrieNode  node) {
        List<String> result = new ArrayList<>();
        if(node.endOfSequence){
            result.add(node.value+"");
        }
        if(  node.children==null||node.children.size()==0){

            return result;
        }

        for(Map.Entry<Character,  TrieNode> entry: node.children.entrySet()){

            List<String> allWords = getAllWords(entry.getValue());
            for(String word:allWords){
                result.add(node.value+word);
            }
        }

        return result;
    }

    private static class TrieNode {
        char value;
        boolean endOfSequence;
        int prefixCount = 0;
        Map<Character,  TrieNode> children = new HashMap<>();

        public TrieNode(char value, boolean endOfSequence) {
            this.value = value;
            this.endOfSequence = endOfSequence;
        }

        public TrieNode(char value) {
            this.value = value;
        }

        public void addChild(char value) {
             TrieNode trieNode = new  TrieNode(value);
            addChild(trieNode);
        }

        public void addChild(char value, boolean end) {
            TrieNode trieNode = new  TrieNode(value, end);
            addChild(trieNode);
        }

        private void addChild(TrieNode trieNode) {
            children.put(trieNode.value, trieNode);
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", CharTrie.class.getSimpleName() + "[", "]")
                    .add("value=" + value)
                    .add("endOfSequence=" + endOfSequence)
                    .add("children=" + children)
                    .toString();
        }
    }
}
