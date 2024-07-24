package epp.honours;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HUffmanEncoding {
    public static void main(String[] args){
        String input = "abracadabra";
        String huffmanCode = huffmanCoding(input);
        System.out.println("Huffman Codes: " + huffmanCode);
    }

    private static String huffmanCoding(String input) {
        Map<Character, Long> frequencyMap = input.chars().mapToObj(x -> Character.valueOf((char) x))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        Map<Character,String> codeMap = generateCodeMap(frequencyMap);
    System.out.println(codeMap );
        return input.chars().mapToObj(x -> codeMap.get(Character.valueOf((char) x))).collect(Collectors.joining());

    }

    private static Map<Character, String> generateCodeMap(Map<Character, Long> frequencyMap) {
        PriorityQueue<BinaryTreeNode> priorityQueue = new PriorityQueue<BinaryTreeNode>();
        for (Map.Entry<Character, Long> entry : frequencyMap.entrySet()) {
            priorityQueue.offer(new BinaryTreeNode(entry.getValue(), new CharWithFrequency(entry.getKey(),
                    entry.getValue()),null,null));
        }
        while (priorityQueue.size()>1){
            BinaryTreeNode left = priorityQueue.poll();
            BinaryTreeNode right = priorityQueue.poll();
            BinaryTreeNode parent = new BinaryTreeNode(left.aggregatedFrequency + right.aggregatedFrequency, null, left, right);
            priorityQueue.offer(parent);
        }
        Map<Character, String> result = new HashMap<Character, String>();
        StringBuilder code =  new StringBuilder();
        buildCodeMap(result, priorityQueue.peek(), code  );
        return result;
    }

    private static void buildCodeMap(Map<Character, String> result, BinaryTreeNode root,StringBuilder code ) {
        if(root==null) {
            return;
        }
        //if leaf node
        if(root.charWithFrequency!=null){
            result.put(root.charWithFrequency.c, code.toString() );
        }
        code .append("0");
        buildCodeMap(result, root.left, code  );
        code.setLength(code.length()-1);

        code .append("1");
        buildCodeMap(result, root.right, code  );
        code.setLength(code.length()-1);

    }

    private static class CharWithFrequency {
        char c;
        long frequency;
        String code;

        public CharWithFrequency(char c, long frequency) {
            this.c = c;
            this.frequency = frequency;
        }
    }

    private static class BinaryTreeNode implements Comparable<BinaryTreeNode> {
        long aggregatedFrequency;
        CharWithFrequency charWithFrequency;
        BinaryTreeNode left;
        BinaryTreeNode right;

        public BinaryTreeNode(long aggregatedFrequency, CharWithFrequency charWithFrequency, BinaryTreeNode left, BinaryTreeNode right) {
            this.aggregatedFrequency = aggregatedFrequency;
            this.charWithFrequency = charWithFrequency;
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(BinaryTreeNode o) {
            return Long.compare(aggregatedFrequency,o.aggregatedFrequency);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            BinaryTreeNode that = (BinaryTreeNode) o;
            return aggregatedFrequency == that.aggregatedFrequency;
        }

        @Override
        public int hashCode() {
            return Objects.hash(aggregatedFrequency);
        }
    }

}
