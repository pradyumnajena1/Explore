package epp.binarysearchtree.revision;

import epp.array.ArrayUtils;
import epp.binaryTree.BinaryTreeNode;
import epp.binarysearchtree.BSTUtils;

import java.util.ArrayList;
import java.util.List;

public class FindKLargestItems {
    public static void main(String[] args) {
        int[] values = ArrayUtils.randomSortedArray(10, 1, 30);
        BinaryTreeNode<Integer> root = BSTUtils.buildBSTFromSortedArray(values);
        System.out.println(root);
       List<Integer> result =  getLargestItems(root,4);
        System.out.println(result);
    }

    private static List<Integer> getLargestItems(BinaryTreeNode<Integer> root, int k) {
        List<Integer> collector = new ArrayList<>();
          getLargestItems(root,k,collector);
          return collector;
    }

    private static void getLargestItems(BinaryTreeNode<Integer> root, int k, List<Integer> collector) {
        if(root==null ){
            return ;
        }
        getLargestItems(root.right,k,collector);
        if(collector.size()==k){
            return;
        }
        collector.add(root.data);
        getLargestItems(root.left,k,collector);

    }
}
