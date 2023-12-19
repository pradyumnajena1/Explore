package leetcode.hard;

import epp.binaryTree.BinaryTreeNode;
import epp.binarysearchtree.BSTUtils;

import java.util.*;
import java.util.stream.Collectors;

public class MaximumFrequencyStack {
    public static void main(String[] args) {
        FreqStack freqStack = new FreqStack();
        freqStack.push(5); // The stack is [5]
        freqStack.push(7); // The stack is [5,7]
        freqStack.push(5); // The stack is [5,7,5]
        freqStack.push(7); // The stack is [5,7,5,7]
        freqStack.push(4); // The stack is [5,7,5,7,4]
        freqStack.push(5); // The stack is [5,7,5,7,4,5]
        System.out.println(freqStack.pop());   // return 5, as 5 is the most frequent. The stack becomes [5,7,5,7,4].
        System.out.println(freqStack.pop());   // return 7, as 5 and 7 is the most frequent, but 7 is closest to the top. The stack
        // becomes [5,7,5,4].
        System.out.println(freqStack.pop());   // return 5, as 5 is the most frequent. The stack becomes [5,7,4].
        System.out.println(freqStack.pop());   // return 4, as 4, 5 and 7 is the most frequent, but 4 is closest to the top. The stack
        // becomes [5,7].
    }

    private static class FreqStack {
        private Stack<Integer> stack;
        private Map<Integer, Integer> freqMap;
        private BinaryTreeNode<ValueFreqPair> root;

        public FreqStack() {
            stack = new Stack<>();
            freqMap = new HashMap<>();
        }

        public void push(int val) {
            stack.push(val);
            Integer currentFreq = freqMap.getOrDefault(val, 0);
            freqMap.put(val, currentFreq + 1);
            if (currentFreq == 0) {
                root = insert(root, new ValueFreqPair(val, 1));
            } else {
                root = delete(root, new ValueFreqPair(val, currentFreq));
                root = insert(root, new ValueFreqPair(val, currentFreq + 1));
            }


        }

        public int pop() {
            if (stack.isEmpty()) {
                throw new IllegalArgumentException();
            }
            BinaryTreeNode<ValueFreqPair> max = findMax(root);
            if (stack.peek() == max.data.value) {
                update(max);
                return stack.pop();
            } else {
                Stack<Integer> copy = new Stack<>();
                Map<Integer,BinaryTreeNode<ValueFreqPair>>  maxMap = getMaxValues(root);
                while (!maxMap.containsKey( stack.peek())  ) {
                    copy.push(stack.pop());

                }

                update(maxMap.get(stack.peek()));
                Integer pop = stack.pop();
                while (!copy.isEmpty()){
                    stack.push(copy.pop());
                }
                return pop;
            }
        }

        private void update(BinaryTreeNode<ValueFreqPair> max) {
            Integer currentFreq = freqMap.get(max.data.value);
            root = delete(root, max.data);
            if (currentFreq > 1) {
                root = insert(root, new ValueFreqPair(max.data.value, currentFreq - 1));
                freqMap.put(max.data.value, currentFreq - 1);
            } else {
                freqMap.remove(max.data.value);
            }
        }

        public static BinaryTreeNode<Integer> findNode(BinaryTreeNode<Integer> root, int key) {
            if(root==null){
                return null;
            }
            if(root.data.compareTo(key)<0){
                return findNode(root.right,key);
            }else if (root.data.compareTo(key)>0){
                return findNode(root.left,key);
            }else {
                return root;
            }

        }

        public static <T extends Comparable<T>> BinaryTreeNode<T> insert(BinaryTreeNode<T> root, T value) {
            if (root == null) {
                return new BinaryTreeNode<>(value);
            }
            if (root.data.compareTo(value) < 0) {
                root.right = insert(root.right, value);
            } else if (root.data.compareTo(value) > 0) {
                root.left = insert(root.left, value);
            } else {
                System.out.println("value already present " + value);
            }
            return root;
        }



        public static <T extends Comparable<T>> BinaryTreeNode<T> delete(BinaryTreeNode<T> root, T value) {
            if (root == null) {
                System.out.println("value nt found " + value);
                return root;
            }
            if (root.data.compareTo(value) > 0) {
                root.left = delete(root.left, value);
            } else if (root.data.compareTo(value) < 0) {
                root.right = delete(root.right, value);
            } else {

                if (root.left == null) {
                    return root.right;
                } else if (root.right == null) {
                    return root.left;
                }
                BinaryTreeNode<T> min = findMin(root.right);
                root.data = min.data;
                root.right = delete(root.right, min.data);


            }

            return root;
        }



        private static <T extends Comparable<T>> BinaryTreeNode<T> findMin(BinaryTreeNode<T> root) {
            BinaryTreeNode<T> current = root;
            while (current.left != null) {
                current = current.left;
            }
            return current;
        }

        private static <T extends Comparable<T>> BinaryTreeNode<T> findMax(BinaryTreeNode<T> root) {
            BinaryTreeNode<T> current = root;
            while (current.right != null) {
                current = current.right;
            }
            return current;
        }
        private static   Map<Integer,BinaryTreeNode<ValueFreqPair>>  getMaxValues(BinaryTreeNode<ValueFreqPair> root){
            Map<Integer,BinaryTreeNode<ValueFreqPair>> result = new HashMap<>();
            getMaxValues(root,result,findMax(root).data.freq);
            return result;
        }

        private static   void getMaxValues(BinaryTreeNode<ValueFreqPair> root,
                                           Map<Integer,BinaryTreeNode<ValueFreqPair>> result,int max) {
            if(root==null){
                return;
            }
            getMaxValues(root.right,result,max);
            if(root.data.freq>=max){
                result.put(root.data.value, root);
            }else{
                return;
            }
            getMaxValues(root.left,result,max);
        }
        private static class ValueFreqPair implements Comparable<ValueFreqPair> {
            private static Comparator<ValueFreqPair> comparator =
                    Comparator.comparingInt((ValueFreqPair x) -> x.freq).thenComparing((ValueFreqPair x) -> x.value);
            int value;
            int freq;

            public ValueFreqPair(int value, int freq) {
                this.value = value;
                this.freq = freq;
            }

            @Override
            public int compareTo(ValueFreqPair o) {

                return comparator.compare(this, o);
            }

            @Override
            public String toString() {
                final StringBuffer sb = new StringBuffer("ValueFreqPair{");
                sb.append("value=").append(value);
                sb.append(", freq=").append(freq);
                sb.append('}');
                return sb.toString();
            }
        }
    }


}
