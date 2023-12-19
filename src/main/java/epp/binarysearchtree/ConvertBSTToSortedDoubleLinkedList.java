package epp.binarysearchtree;

import epp.array.ArrayUtils;
import epp.binaryTree.BinaryTreeNode;

public class ConvertBSTToSortedDoubleLinkedList {
    public static void main(String[] args) {
        int[] array = ArrayUtils.randomSortedArray(10,1,10);
        BinaryTreeNode<Integer> root = BSTUtils.buildBSTFromSortedArray(array);
        System.out.println(root);

        BinaryTreeNode<Integer> list = BSTUtils.convertToDoubleLinkedList(root);
        printList(list);
    }

    private static void printList(BinaryTreeNode<Integer> list) {
        while (list!=null){
            System.out.print(" "+list.data);
            list = list.right;
        }
    }

}
