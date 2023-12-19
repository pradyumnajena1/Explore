package epp.binarySearch.revision;

import epp.array.ArrayUtils;
import epp.binaryTree.BinaryTreeNode;

public class BuildBSTFromSortedArrayWithMinHeight {
    public static void main(String[] args) {
        int[] values = ArrayUtils.randomSortedArray(10, 1, 10);
        ArrayUtils.printArray(values);
        BinaryTreeNode<Integer> root =  buildBST(values);
        System.out.println(root);
    }

    private static BinaryTreeNode<Integer> buildBST(int[] values) {
        return buildBST(values,0,values.length-1);
    }

    private static BinaryTreeNode<Integer> buildBST(int[] values, int start, int end) {
        if(start>end){
            return null;
        }
        int mid = (start+end)/2;
        mid =  ArrayUtils.lastIndexOf(values,values[mid],mid,end);
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(values[mid]);
        root.left = buildBST(values,start,mid-1);
        root.right = buildBST(values,mid+1,end);
        return root;
    }
}
