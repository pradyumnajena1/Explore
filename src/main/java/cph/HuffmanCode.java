package cph;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HuffmanCode {
    public static void main(String[] args) {
        String s = "AABACDACA";
        Map<Character, String> codes = generateHuffmanCodes(s);
        System.out.println(codes);
       String encodedString = huffmanEncode(s,codes);
       s = huffmanDecode(encodedString,codes);
        System.out.println(s);
    }

    private static String huffmanDecode(String encodedString, Map<Character, String> codes) {
        StringBuilder sb = new StringBuilder();
        Map<String, Character> decodeMap = new HashMap<>();
        for(Map.Entry<Character, String> entry: codes.entrySet()){
            decodeMap.put(entry.getValue(),entry.getKey());
        }
        int start = 0;
        while (start<encodedString.length()){
            int j=start+1;
            while (j<= encodedString.length() && !decodeMap.containsKey( encodedString.substring(start,j))){
                j++;
            }
            String substring = encodedString.substring(start, j);
            sb.append(decodeMap.get(substring));
            start=j;
        }
        return sb.toString();
    }

    private static String huffmanEncode(String s, Map<Character, String> codes) {
        StringBuilder sb = new StringBuilder();
        s.chars().mapToObj(c->Character.valueOf((char) c)). forEach(c->{
            String str = codes.get(c);
            sb.append(str);
        });
        return sb.toString();
    }

    private static Map<Character, String> generateHuffmanCodes(String s) {
        Map<Character, Long> frequencies =
                s.chars().mapToObj(x -> Character.valueOf((char) x)).collect(Collectors.groupingBy(Function.identity(),
                        Collectors.counting()));
        PriorityQueue<HuffmanTreeNode> priorityQueue =
                new PriorityQueue<HuffmanTreeNode>(Comparator.comparingLong(HuffmanTreeNode::getFrequency));
        for (Map.Entry<Character, Long> entry : frequencies.entrySet()) {
            priorityQueue.offer(new HuffmanTreeNode(entry.getKey(), entry.getValue()));
        }
        while (priorityQueue.size() > 1) {
            HuffmanTreeNode right = priorityQueue.poll();
            HuffmanTreeNode left = priorityQueue.poll();
            HuffmanTreeNode root = mergeTreeNodes(left, right);
            priorityQueue.offer(root);
        }
        Map<Character, String> result = new HashMap<>();
        for (Character ch : frequencies.keySet()) {
            String encoding = getEncoding(ch, priorityQueue.peek());
            result.put(ch, encoding);
        }
        return result;
    }

    private static String getEncoding(Character ch, HuffmanTreeNode root) {
        StringBuilder sb = new StringBuilder();
        AtomicReference<String> resultHolder = new AtomicReference<>();
        getEncoding(ch, root, sb,resultHolder);
        return resultHolder.get();
    }

    private static void getEncoding(Character ch, HuffmanTreeNode root, StringBuilder sb, AtomicReference<String> resultHolder) {
        if(root==null){
            return;
        }
        if (  ch  == root.character ) {
            resultHolder.set(sb.toString());
            return;
        }
        sb.append('0');
        getEncoding(ch, root.left, sb, resultHolder);
        sb.deleteCharAt(sb.length() - 1);

        sb.append('1');
        getEncoding(ch, root.right, sb, resultHolder);
        sb.deleteCharAt(sb.length() - 1);
    }

    private static HuffmanTreeNode mergeTreeNodes(HuffmanTreeNode left, HuffmanTreeNode right) {
        HuffmanTreeNode root = new HuffmanTreeNode(left.frequency + right.frequency);
        root.left = left;
        root.right = right;
        return root;
    }

    private static class HuffmanTreeNode {
        Character character;
        long frequency;
        HuffmanTreeNode left;
        HuffmanTreeNode right;

        public HuffmanTreeNode(long frequency) {
            this.frequency = frequency;
        }

        public HuffmanTreeNode(Character character, long frequency) {
            this.character = character;
            this.frequency = frequency;
        }

        public long getFrequency() {
            return frequency;
        }
    }
}
