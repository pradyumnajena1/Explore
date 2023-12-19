package epp.binarysearchtree;

import epp.array.ArrayUtils;
import epp.binaryTree.BinaryTreeNode;

import static epp.binarysearchtree.BSTUtils.buildBSTFromSortedArray;

public class BuildBSTFromSortedArray {
    public static void main(String[] args) {
        int[] sortedArray = ArrayUtils.randomSortedArray(10,1,50);
        ArrayUtils.printArray(sortedArray);
        BinaryTreeNode<Integer> root = buildBSTFromSortedArray(sortedArray);
        System.out.println(root);
    }


}
