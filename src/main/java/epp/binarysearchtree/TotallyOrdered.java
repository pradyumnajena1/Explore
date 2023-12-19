package epp.binarysearchtree;

import epp.array.ArrayUtils;
import epp.binaryTree.BinaryTreeNode;

import java.util.Arrays;

public class TotallyOrdered {
    public static void main(String[] args) {
        int[] uniqueArray = ArrayUtils.randomUniqueArray(20, 1, 30);
        int r = uniqueArray[0];
        int s = uniqueArray[1];
        int m = uniqueArray[2];
        System.out.println("r= "+r+" s= "+s+" m= "+m);
        Arrays.sort(uniqueArray);
        BinaryTreeNode<Integer> root = BSTUtils.buildBSTFromSortedArray(uniqueArray);
        System.out.println(root);
        BinaryTreeNode<Integer> rNode  = BSTUtils.findNode(root,r);
        BinaryTreeNode<Integer> sNode  = BSTUtils.findNode(root,s);
        BinaryTreeNode<Integer> mNode  = BSTUtils.findNode(root,m);
        boolean totalOrdered =  checkTotallyOrdered(rNode,sNode,mNode);
        System.out.println(totalOrdered);

    }

    private static boolean checkTotallyOrdered(BinaryTreeNode<Integer> rNode, BinaryTreeNode<Integer> sNode, BinaryTreeNode<Integer> mNode) {

        if(BSTUtils.findNode(rNode,mNode.data)!=null){
            return BSTUtils.findNode(mNode,sNode.data)!=null;
        }else if(BSTUtils.findNode(sNode,mNode.data)!=null){
            return BSTUtils.findNode(mNode,rNode.data)!=null;
        }

        return false;
    }

}
