package epp.binaryTree.revision;

import epp.binaryTree.BinaryTreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CollectLeavesLeftToRight {

    public static void main(String[] args){
        BinaryTreeNode<Integer> root =
                new BinaryTreeNode<>(
                        1,
                        new BinaryTreeNode<>(
                                0,
                                new BinaryTreeNode<>(0, new BinaryTreeNode<>(0), new BinaryTreeNode<>(1)),
                                new BinaryTreeNode<>(
                                        1, null, new BinaryTreeNode<>(1, new BinaryTreeNode<>(0), null))),
                        new BinaryTreeNode<>(
                                1,
                                new BinaryTreeNode<>(
                                        0,
                                        null,
                                        new BinaryTreeNode<>(
                                                0,
                                                new BinaryTreeNode<>(1, null, new BinaryTreeNode<>(1)),
                                                new BinaryTreeNode<>(0))),
                                new BinaryTreeNode<>(0, null, new BinaryTreeNode<>(0))));
        System.out.println(root);
       List<BinaryTreeNode<Integer>> leaves =  collectLeavesLeftToRight(root );
    System.out.println(leaves);
    }

    private static <T extends Comparable<T>> List<BinaryTreeNode<T>> collectLeavesLeftToRight(BinaryTreeNode<T> root) {
        LinkedList<BinaryTreeNode<T>> collector = new LinkedList<>();
          collectLeavesLeftToRight(root, collector);
          return collector;
    }

    private static <T extends Comparable<T>> void collectLeavesLeftToRight(BinaryTreeNode<T> root,
                                                                        LinkedList<BinaryTreeNode<T>> collector) {
        if(root !=null){
            if(root.isLeafNode()){
                collector.add(root);
            }else{
                collectLeavesLeftToRight(root.left,collector);
                collectLeavesLeftToRight(root.right,collector);
            }

        }

    }
}
