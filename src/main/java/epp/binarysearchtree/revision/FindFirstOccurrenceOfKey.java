package epp.binarysearchtree.revision;

import epp.array.ArrayUtils;
import epp.binaryTree.BinaryTreeNode;
import epp.binarysearchtree.BSTUtils;

import java.util.Stack;

public class FindFirstOccurrenceOfKey {
    public static void main(String[] args) {
        int[] ages = ArrayUtils.randomSortedArray(10, 1, 10);
        Person[] persons = new Person[ages.length];
        for (int i = 0; i < persons.length; i++) {
            persons[i] = new Person(ages[i], "Person_" + i);
        }
        ArrayUtils.printArray(persons);
        BinaryTreeNode<Person> root = BSTUtils.buildBSTFromSortedArray(persons);
        System.out.println(root);
        Person node = findFirstOccurrenceOfKey(root, new Person(2));
        System.out.println(node);

        node = findFirstOccurrenceOfKeyIterative(root, new Person(2));
        System.out.println(node);

        node = findLastOccurrenceOfKey(root, new Person(2));
        System.out.println(node);
        node = findLastOccurrenceOfKeyIterative(root, new Person(2));
        System.out.println(node);
    }

    public static <T extends Comparable<T>> T findFirstOccurrenceOfKey(BinaryTreeNode<T> root, T key) {
        if (root == null) {
            return null;
        }
        T left = findFirstOccurrenceOfKey(root.left, key);
        if (left != null) {
            return left;
        }
        if (root.data.compareTo(key) == 0) {
            return root.data;
        }
        return findFirstOccurrenceOfKey(root.right, key);
    }

    public static <T extends Comparable<T>> T findFirstOccurrenceOfKeyIterative(BinaryTreeNode<T> root, T key) {
        Stack<BinaryTreeNode<T>> stack = new Stack<>();
        BinaryTreeNode<T> current = root;
        T result = null;
        while (current != null || !stack.isEmpty()) {
            if (current != null) {
                stack.push(current);
                current = current.left;
            } else {
                BinaryTreeNode<T> popped = stack.pop();
                if(popped.data.compareTo(key)==0){
                    result = popped.data;
                    break;
                }
                current = popped.right;
            }


        }
        return result;
    }

    public static <T extends Comparable<T>> T findLastOccurrenceOfKeyIterative(BinaryTreeNode<T> root, T key) {
        Stack<BinaryTreeNode<T>> stack = new Stack<>();
        BinaryTreeNode<T> current = root;
        T result = null;
        while (current != null || !stack.isEmpty()) {
            if (current != null) {
                stack.push(current);
                current = current.left;
            } else {
                BinaryTreeNode<T> popped = stack.pop();
                int compare = popped.data.compareTo(key);
                if(compare ==0){
                    result = popped.data;

                }
                if(compare>0){
                    break;
                }
                current = popped.right;
            }


        }
        return result;
    }

    public static <T extends Comparable<T>> T findLastOccurrenceOfKey(BinaryTreeNode<T> root, T key) {
        if (root == null) {
            return null;
        }
        T right = findLastOccurrenceOfKey(root.right, key);
        if (right != null) {
            return right;
        }
        if (root.data.compareTo(key) == 0) {
            return root.data;
        }
        return findLastOccurrenceOfKey(root.left, key);
    }

}
