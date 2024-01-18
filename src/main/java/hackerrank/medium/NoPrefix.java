package hackerrank.medium;



import epp.CharTrie;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class NoPrefix {

    private static class CharTrie {
        private  TrieNode root = new  TrieNode( ' ');

        public CharTrie() {
        }




        public boolean add(String word)  {
          return   add(word.toCharArray());
        }
        public boolean add(char[] sequence) {
             TrieNode current = root;
             boolean existingPrefix = false;
            for (int i = 0; i < sequence.length; i++) {
                char value = sequence[ i];
                if (current.children.containsKey(value)) {
                    current = current.children.get(value);
                    existingPrefix|=current.endOfSequence;
                    current.prefixCount++;

                } else {
                     TrieNode newNode = new  TrieNode(value);
                    current.addChild(newNode);
                    current = newNode;
                    current.prefixCount++;
                }

            }

            current.endOfSequence = true;
            return existingPrefix;
        }
        public boolean containsAsPrefix(char[] sequence) {
             TrieNode current = root;
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

            private void addChild( TrieNode trieNode) {
                children.put(trieNode.value, trieNode);
            }

            @Override
            public String toString() {
                return new StringJoiner(", ", TrieNode.class.getSimpleName() + "[", "]")
                        .add("value=" + value)
                        .add("endOfSequence=" + endOfSequence)
                        .add("children=" + children)
                        .toString();
            }
        }
    }

    public static void main2(String[] args) {
        noPrefix(new ArrayList<>(List.of("abcd","bcd","abcde","bcde")));
        noPrefix(new ArrayList<>(List.of("ab","bc","cd" )));
    }

    public static void main(String[] args) throws IOException, IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\Pradyumna\\IdeaProjects" +
                "\\Explore\\src\\main\\resources\\Input.txt"));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> words = IntStream.range(0, n).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .collect(toList());

         noPrefix(words);

        bufferedReader.close();
    }

    public static void noPrefix(List<String> words) {
        // Write your code here

        CharTrie trie = new CharTrie();
        for (int i = 0; i < words.size(); i++) {
            String s = words.get(i);
            if(trie.containsAsPrefix(s.toCharArray()) || trie.add(s)   ){
                System.out.println("BAD SET");
                System.out.println(s);
                return;
            }
        }
        System.out.println("GOOD SET ");
    }

}
