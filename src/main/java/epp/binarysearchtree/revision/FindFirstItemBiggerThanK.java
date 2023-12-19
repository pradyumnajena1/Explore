package epp.binarysearchtree.revision;

import epp.array.ArrayUtils;
import epp.binaryTree.BinaryTreeNode;
import epp.binaryTree.BinaryTreeUtils;
import epp.binarysearchtree.BSTUtils;

public class FindFirstItemBiggerThanK {
    public static void main(String[] args) {
        int[] ages = ArrayUtils.randomSortedArray(10, 1, 10);
        Person[] persons = new Person[ages.length];
        for (int i = 0; i < persons.length; i++) {
            persons[i] = new Person(ages[i], "P" + i);
        }
        ArrayUtils.printArray(persons);
        BinaryTreeNode<Person> root = BSTUtils.buildBSTFromSortedArray(persons);
        System.out.println(root);
        Person searchKey = new Person(ages[9]);
        BinaryTreeNode<Person> next = findFirstBiggerItem(root, searchKey);
        System.out.println( next!=null? next.data:null);

        next = findFirstBiggerItem2(root, searchKey);
        System.out.println( next!=null? next.data:null);
    }

    private static BinaryTreeNode<Person> findFirstBiggerItem(BinaryTreeNode<Person> root, Person person) {
        BinaryTreeNode<Person> current = root;
        BinaryTreeNode<Person> bigger = null;
        BinaryTreeNode<Person> result = null;
        while (current != null) {

            int compare = current.data.compareTo(person);
            if (compare == 0) {
                break;
            } else if (compare > 0) {
                bigger = current;
                current = current.left;

            } else {
                current = current.right;
            }
        }

        if (current == null || current.right == null) {
            result = bigger;
        } else {
            result = BinaryTreeUtils.getMinNode(current.right);
        }
        return result;
    }

    private static BinaryTreeNode<Person> findFirstBiggerItem2(BinaryTreeNode<Person> root, Person person) {
        BinaryTreeNode<Person> current = root;
        BinaryTreeNode<Person> bigger = null;

        while (current != null) {
            int compare = current.data.compareTo(person);
            if (compare <= 0) {
                current = current.right;
            } else if (compare > 0) {
                bigger = current;
                current = current.left;

            }
        }
        return bigger;
    }
}
