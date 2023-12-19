package epp.binaryTree.revision;

import epp.binaryTree.BinaryTreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class FindLeafWithPathSum {
    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(1,
                new BinaryTreeNode<>(2,
                        new BinaryTreeNode<>(-3,
                                new BinaryTreeNode<>(5),
                                new BinaryTreeNode<>(17)),
                        new BinaryTreeNode<>(11,
                                null,
                                new BinaryTreeNode<>(12,
                                        new BinaryTreeNode<>(10),
                                        null))),
                new BinaryTreeNode<>(17,
                        new BinaryTreeNode<>(2,
                                null,
                                new BinaryTreeNode<>(3,
                                        new BinaryTreeNode<>(-12,
                                                null,
                                                new BinaryTreeNode<>(15)),
                                        new BinaryTreeNode<>(3))),
                        new BinaryTreeNode<>(2,
                                null,
                                new BinaryTreeNode<>(6)))
        );
        System.out.println(root);
      List<BinaryTreeNode<Integer>> nodes =  findLeafNodesWithPath(root,26);
        System.out.println(nodes);
    }

    private static List<BinaryTreeNode<Integer>> findLeafNodesWithPath(BinaryTreeNode<Integer> root, int requiredSum) {
      List<BinaryTreeNode<Integer>> result = new ArrayList<>();

      collectNodesWithPath(root,requiredSum,result,new AtomicInteger(0));
        return result;
    }

    private static void collectNodesWithPath(BinaryTreeNode<Integer> root, int requiredSum,
                                             List<BinaryTreeNode<Integer>> result, AtomicInteger pathSum) {
        if(root==null){
            return;
        }
        pathSum.addAndGet( root.data);
        if(root.isLeafNode() && pathSum.get()==requiredSum){
            result.add(root);
        }
        collectNodesWithPath(root.left,requiredSum,result,pathSum);
        collectNodesWithPath(root.right,requiredSum,result,pathSum);
        pathSum.addAndGet(-root.data);
    }
}
