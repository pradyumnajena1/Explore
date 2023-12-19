package epp.binarysearchtree;

import epp.binaryTree.BinaryTreeNode;

import java.util.List;

public class RecreateBSTFromPreOrder {
    public static void main(String[] args) {
        List<Integer> preorder = List.of(4,4,2,1,3,6,5,7);
        BinaryTreeNode<Integer> root = createBST(preorder);
        System.out.println(root);
    }

    private static BinaryTreeNode<Integer> createBST(List<Integer> preorder) {
        return createBST(preorder,0,preorder.size()-1);
    }

    private static BinaryTreeNode<Integer> createBST(List<Integer> preorder, int start, int end) {
        if(start>end){
            return null;
        }
        if(start==end){
            return new BinaryTreeNode<>(preorder.get(start));
        }
        int index = start+1;
        while (index<=end && preorder.get(index)<=preorder.get(start)){
            index++;
        }

        return new BinaryTreeNode<>(preorder.get(start),createBST(preorder,start+1,index-1),createBST(preorder,index,
                end));
    }
}
