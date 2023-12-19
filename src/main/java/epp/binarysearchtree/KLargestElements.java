package epp.binarysearchtree;

import epp.binaryTree.BinaryTreeNode;

import java.util.ArrayList;
import java.util.List;

public class KLargestElements {
    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(4,
                new BinaryTreeNode<>(2,new BinaryTreeNode<>(1),new BinaryTreeNode<>(4)),
                new BinaryTreeNode<>(5,new BinaryTreeNode<>(5),new BinaryTreeNode<>(6)));
        System.out.println(root);
        List< BinaryTreeNode<Integer>> largest = findKlargest(root,3);
        System.out.println(largest);
    }

    private static List<BinaryTreeNode<Integer>> findKlargest(BinaryTreeNode<Integer> root, int num) {
        List<BinaryTreeNode<Integer>> resultCollector = new ArrayList<>();
        findKlargest(root,num,resultCollector);
        return resultCollector;
    }

    private static void findKlargest(BinaryTreeNode<Integer> root, int num, List<BinaryTreeNode<Integer>> resultCollector) {
        if(root==null){
            return;
        }
        findKlargest(root.right,num,resultCollector);
        if(resultCollector.size()==num){
            return;
        }
        resultCollector.add(root);
        if(resultCollector.size()==num){
            return;
        }
        findKlargest(root.left,num,resultCollector);
    }
}
