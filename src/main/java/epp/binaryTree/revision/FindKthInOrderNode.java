package epp.binaryTree.revision;

import epp.binaryTree.BinaryTreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class FindKthInOrderNode {
    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(8,
                new BinaryTreeNode<>(5, new BinaryTreeNode<>(3, new BinaryTreeNode<>(0),
                        new BinaryTreeNode<>(2)),
                        new BinaryTreeNode<>(17, null, new BinaryTreeNode<>(12, new BinaryTreeNode<>(1), null))),
                new BinaryTreeNode<>(21, new BinaryTreeNode<>(10, new BinaryTreeNode<>(14, null, new BinaryTreeNode<>(19)),
                        new BinaryTreeNode<>(4)),
                        new BinaryTreeNode<>(7, null, new BinaryTreeNode<>(8)))
        );
        System.out.println(root);
        BinaryTreeNode<Integer> node= findNodeInOrder(root,7);
        System.out.println(node.data);

    }

    private static <T extends Comparable<T>> BinaryTreeNode<T> findNodeInOrder(BinaryTreeNode<T> root,
                                                                       int index) {
        List<BinaryTreeNode<T>> resultHolder = new ArrayList<>();
        findNodeInOrder(root,index,resultHolder,new AtomicInteger(0));
        return resultHolder.size()==1? resultHolder.get(0):null;

    }

    private static <T extends Comparable<T>> void findNodeInOrder(BinaryTreeNode<T> root, int index,
                                                                  List<BinaryTreeNode<T>> resultHolder,
                                                                  AtomicInteger count) {
        if(root==null){
            return;
        }
        findNodeInOrder(root.left,index,resultHolder,count);

        if(index==count.incrementAndGet()){
            resultHolder.add(root);
            return;
        }
        findNodeInOrder(root.right,index,resultHolder,count);
    }


}
