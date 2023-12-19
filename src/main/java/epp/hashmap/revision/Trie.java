package epp.hashmap.revision;

import java.util.*;

public class Trie<T> {

    private TrieNode<T> root = new TrieNode<>(null);

    public Trie() {
    }

    public static void main(String[] args) {
        Trie<Integer> trie = new Trie<>();
        trie.add(List.of(1));
        trie.add(List.of(1, 2, 3));
        trie.add(List.of(1, 2, 3, 4));
        trie.add(List.of(2, 3, 4));
        trie.add(List.of(2, 3, 4, 5));
        trie.add(List.of(2, 3, 4, 6));
        trie.add(List.of(3, 4, 5));
        trie.add(List.of(3, 4, 5, 6, 7));
        System.out.println(trie);
        System.out.println(trie.contains(List.of(1)));
        System.out.println(trie.contains(List.of(1,2)));
        System.out.println(trie.contains(List.of(1, 2, 3)));
        System.out.println(trie.contains(List.of(1, 2, 3, 4)));
        System.out.println(trie.contains(List.of(1, 2, 3, 4, 5)));
        System.out.println(trie.getWordsWithPrefix(List.of(2, 3, 4)));
        System.out.println(trie.numWordsWithPrefix(List.of(2, 3, 4)));

    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Trie.class.getSimpleName() + "[", "]")
                .add("root=" + root)
                .toString();
    }

    public void add(T[] sequence) {
        TrieNode<T> current = root;
        for (int i = 0; i < sequence.length; i++) {
            T value = sequence[ i];
            if (current.children.containsKey(value)) {
                current = current.children.get(value);
                current.prefixCount++;

            } else {
                TrieNode<T> newNode = new TrieNode<>(value);
                current.addChild(newNode);
                current = newNode;
                current.prefixCount++;
            }

        }

        current.endOfSequence = true;
    }

    public void add(List<T> sequence) {
        TrieNode<T> current = root;
        for (int i = 0; i < sequence.size(); i++) {
            T value = sequence.get(i);
            if (current.children.containsKey(value)) {
                current = current.children.get(value);
                current.prefixCount++;

            } else {
                TrieNode<T> newNode = new TrieNode<>(value);
                current.addChild(newNode);
                current = newNode;
                current.prefixCount++;
            }

        }

        current.endOfSequence = true;


    }

    public boolean contains(List<T> sequence) {
        TrieNode<T> current = root;
        for (int i = 0; i < sequence.size(); i++) {
            T value = sequence.get(i);
            if (current.children.containsKey(value)) {
                current = current.children.get(value);
            } else {
                return false;
            }
        }
        return current.endOfSequence;

    }
    public Integer numWordsWithPrefix(List<T> sequence) {
        TrieNode<T> current = root;
        for (int i = 0; i < sequence.size(); i++) {
            T value = sequence.get(i);
            if (current.children.containsKey(value)) {
                current = current.children.get(value);
            } else {
                return null;
            }
        }
        return current.prefixCount;

    }

    public boolean containsAsPrefix(List<T> sequence) {
        TrieNode<T> current = root;
        for (int i = 0; i < sequence.size(); i++) {
            T value = sequence.get(i);
            if (current.children.containsKey(value)) {
                current = current.children.get(value);
            } else {
                return false;
            }
        }
        return true;

    }
    public List<List<T>> getWordsWithPrefix(List<T> sequence){
        TrieNode<T> current = root;

        for (int i = 0; i < sequence.size(); i++) {
            T value = sequence.get(i);
            if (current.children.containsKey(value)) {
                current = current.children.get(value);
            } else {
                return new ArrayList<>();
            }
        }
        List<List<T>> result = new ArrayList<>();
        List<List<T>> allSuffixes =  getAllWords(current);
        for(List<T> suffix:allSuffixes){
            ArrayList<T> e = new ArrayList<>();
            e.addAll(sequence.subList(0,sequence.size()-1));
            e.addAll(suffix);
            result.add(e);
        }
        return result;

    }

    private List<List<T>> getAllWords(TrieNode<T> node) {
        List<List<T>> result = new ArrayList<>();
        if( node.children==null||node.children.size()==0){
            ArrayList<T> e = new ArrayList<>();
            e.add(node.value);
            result.add(e);
            return result;
        }
        for(Map.Entry<T,TrieNode<T>> entry: node.children.entrySet()){

            List<List<T>> allWords = getAllWords(entry.getValue());
            for(List<T> word:allWords){
                ArrayList<T> e = new ArrayList<>();
                e.add(node.value);
                e.addAll(word);
                result.add(e);
            }
        }

        return result;
    }

    private static class TrieNode<T> {
        T value;
        boolean endOfSequence;
        int prefixCount = 0;
        Map<T, TrieNode<T>> children = new HashMap<>();

        public TrieNode(T value, boolean endOfSequence) {
            this.value = value;
            this.endOfSequence = endOfSequence;
        }

        public TrieNode(T value) {
            this.value = value;
        }

        public void addChild(T value) {
            TrieNode<T> trieNode = new TrieNode<>(value);
            addChild(trieNode);
        }

        public void addChild(T value, boolean end) {
            TrieNode<T> trieNode = new TrieNode<>(value, end);
            addChild(trieNode);
        }

        private void addChild(TrieNode<T> trieNode) {
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
