package epp.binaryTree.revision;

import epp.binaryTree.BinaryTreeNodeWithSize;
import epp.binarysearchtree.BSTUtils;

import java.util.ArrayList;
import java.util.List;

public class FindKthNodeFromBSTWithSize {
    public static void main(String[] args) {
        BinaryTreeNodeWithSize<Integer> root =  BSTUtils.buildBSTWithSizeWithUniqueValues(10,1,20);
        System.out.println(root);
        BinaryTreeNodeWithSize<Integer> node =  findKthNodeFromBSTWithSize(root,7);
        System.out.println(node);
    }

    private static BinaryTreeNodeWithSize<Integer> findKthNodeFromBSTWithSize(BinaryTreeNodeWithSize<Integer> root, int index) {
        List<BinaryTreeNodeWithSize<Integer>> resultHolder = new ArrayList<>();
        findKthNodeFromBSTWithSize(root,index,resultHolder);
        return resultHolder.size()>0? resultHolder.get(0):null;
    }

    private static void findKthNodeFromBSTWithSize(BinaryTreeNodeWithSize<Integer> root, int index, List<BinaryTreeNodeWithSize<Integer>> resultHolder) {
        if(root==null){
            return;
        }
        int leftSize = 0;
        if(root.left!=null){
              leftSize = root.left.size;
        }
        if(index==leftSize+1){
            resultHolder.add(root);
        }else if(index<=leftSize){
            findKthNodeFromBSTWithSize(root.left,index,resultHolder);
        }else{
            findKthNodeFromBSTWithSize(root.right,index-leftSize-1,resultHolder);
        }
    }
}
