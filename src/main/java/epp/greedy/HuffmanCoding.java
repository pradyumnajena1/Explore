package epp.greedy;

import epp.binaryTree.BinaryTreeNode;

import java.util.*;

public class HuffmanCoding {
    public static void main(String[] args) {
        Map<Character, Double> frequencies = getCharacterFrequencies();
        System.out.println(frequencies);
        Map<Character,String> encoding = getHuffmanEncoding(frequencies);
        System.out.println(encoding);

    }

    private static Map<Character, String> getHuffmanEncoding(Map<Character, Double> frequencies) {
        BinaryTreeNode<Tuple> root = buildHuffmanEncodingTree(frequencies);
        System.out.println(root);
        Map<Character,String> encodings = new HashMap<>();
        generateHuffmanEncoding(root,"",encodings);
        return encodings;
    }

    private static void generateHuffmanEncoding(BinaryTreeNode<Tuple> root,
                                                String path, Map<Character, String> encodings) {
        if(root==null){
            return;
        }
        if(root.data.ch!='_'){
            //System.out.println(root.data.ch);
            encodings.put(root.data.ch,path);

        }
        generateHuffmanEncoding(root.left,path+"0",encodings);
        generateHuffmanEncoding(root.right,path+"1",encodings);
    }

    private static class Tuple implements Comparable<Tuple>{
        Character ch;
        double frequency;

        public Tuple(Character ch, double frequency) {
            this.ch = ch;
            this.frequency = frequency;
        }

        public Character getCh() {
            return ch;
        }

        public double getFrequency() {
            return frequency;
        }

        @Override
        public int compareTo(Tuple o) {
          return   Comparator.comparing(Tuple::getFrequency).thenComparing(Tuple::getCh).compare(this,o);

        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("(");
            sb.append("c=").append(ch);
            sb.append(", f=").append(frequency);
            sb.append(')');
            return sb.toString();
        }
    }

    private static BinaryTreeNode<Tuple> buildHuffmanEncodingTree(Map<Character, Double> frequencies) {
        Comparator<BinaryTreeNode<Tuple>> comparator = new Comparator<BinaryTreeNode<Tuple>>() {
            @Override
            public int compare(BinaryTreeNode<Tuple> o1, BinaryTreeNode<Tuple> o2) {
                return o1.data.compareTo(o2.data);
            }
        };
        PriorityQueue<BinaryTreeNode<Tuple>> queue =
                new PriorityQueue<>(comparator);
        for(Map.Entry<Character,Double> entry:frequencies.entrySet()){
            queue.offer(new BinaryTreeNode<>(new Tuple(entry.getKey(),entry.getValue())));
        }
        //System.out.println(queue);
        while (queue.size()>1){
            BinaryTreeNode<Tuple> min = queue.poll();
            BinaryTreeNode<Tuple> secondMin = queue.poll();


            BinaryTreeNode<Tuple> root = new BinaryTreeNode<>(new Tuple('_', min.data.frequency + secondMin.data.frequency));
            root.left=min;
            root.right = secondMin;
            queue.offer(root);
        }

        return queue.peek();
    }

    private static Map<Character, Double> getCharacterFrequencies() {
        Map<Character,Double> frequencies = new HashMap<>();
        frequencies.put('a',8.17);
        frequencies.put('b',1.49);
        frequencies.put('c',2.78);
        frequencies.put('d',4.25);
        frequencies.put('e',12.70);
        frequencies.put('f',2.23);
        frequencies.put('g',2.02);
        frequencies.put('h',6.09);
        frequencies.put('i',6.97);
        frequencies.put('j',0.15);
        frequencies.put('k',0.77);
        frequencies.put('l',4.03);
        frequencies.put('m',2.41);
        frequencies.put('n',6.75);
        frequencies.put('o',7.51);
        frequencies.put('p',1.93);
        frequencies.put('q',0.10);
        frequencies.put('r',5.99);
        frequencies.put('s',6.33);
        frequencies.put('t',9.06);
        frequencies.put('u',2.76);
        frequencies.put('v',0.98);
        frequencies.put('w',2.36);
        frequencies.put('x',0.15);
        frequencies.put('y',1.97);
        frequencies.put('z',0.07);
        return frequencies;
    }
}
