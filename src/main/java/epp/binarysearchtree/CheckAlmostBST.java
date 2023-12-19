package epp.binarysearchtree;

import epp.array.ArrayUtils;
import epp.binaryTree.BinaryTreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CheckAlmostBST {
    public static void main(String[] args) {
        int[] uniqueArray = ArrayUtils.randomUniqueArray(20, 1, 30);
        Arrays.sort(uniqueArray);
        BinaryTreeNode<Integer> root = BSTUtils.buildBSTFromSortedArray(uniqueArray);
        System.out.println(root);
        int s = uniqueArray[(int) (Math.random()*5)];
        int r = uniqueArray[ 10 + (int) (Math.random()*5)];
        BinaryTreeNode<Integer> sNode = BSTUtils.findNode(root, s);
        BinaryTreeNode<Integer> rNode = BSTUtils.findNode(root, r);
        sNode.data = r;
        rNode.data = s;
        System.out.println("swapped "+r+"<=>"+s);
        System.out.println(root);
        boolean almostBST = checkAlmostBST(root);
        System.out.println(almostBST);

    }

    private static boolean checkAlmostBST(BinaryTreeNode<Integer> root) {
        BinaryTreeNode<Integer>[] binaryTreeNodes = outofplaceNodes(root);
        System.out.println(Arrays.toString(binaryTreeNodes));
        if(binaryTreeNodes.length!=2){
            return false;
        }
        Integer temp = binaryTreeNodes[0].data;
        binaryTreeNodes[0].data = binaryTreeNodes[1].data;
        binaryTreeNodes[1].data = temp;


        return outofplaceNodes(root).length==0;
    }

    private static BinaryTreeNode<Integer>[] outofplaceNodes(BinaryTreeNode<Integer> root) {
        List<BinaryTreeNode<Integer>> nodeCollector = new ArrayList<>();
        outofplaceNodes(root,Integer.MIN_VALUE,Integer.MAX_VALUE,nodeCollector);
        return nodeCollector.toArray(new BinaryTreeNode[0]);
    }

    private static void outofplaceNodes(BinaryTreeNode<Integer> root, int minValue, int maxValue, List<BinaryTreeNode<Integer>> nodeCollector) {
        if(root==null){
            return;
        }
        if(root.data>maxValue || root.data<minValue){
            nodeCollector.add(root);
            outofplaceNodes(root.left,minValue,maxValue,nodeCollector);
            outofplaceNodes(root.right,minValue,maxValue, nodeCollector);
        }else {
            outofplaceNodes(root.left,minValue,root.data,nodeCollector);
            outofplaceNodes(root.right,root.data,maxValue, nodeCollector);
        }

    }
}
