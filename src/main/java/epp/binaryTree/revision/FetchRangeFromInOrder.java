package epp.binaryTree.revision;

import epp.binaryTree.BinaryTreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class FetchRangeFromInOrder {
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
        List<BinaryTreeNode<Integer>> range = fetchRangeFromInOrder(root,3,7);
        for (BinaryTreeNode<Integer> node:range){
            System.out.println(node.data);
        }
    }

    private static <T extends Comparable<T>> List<BinaryTreeNode<T>> fetchRangeFromInOrder(BinaryTreeNode<T> root,
                                                                                        int start,
                                                                             int end) {
        List<BinaryTreeNode<T>> result = new ArrayList<>();

          fetchRangeFromInOrder(root,start,end,result,new AtomicInteger(0));
          return result;
    }

    private static <T extends Comparable<T>> void fetchRangeFromInOrder(BinaryTreeNode<T> root, int start, int end, List<BinaryTreeNode<T>> result, AtomicInteger count) {
        if(root==null){
            return;
        }
        fetchRangeFromInOrder(root.left,start,end,result,count);
        if(count.incrementAndGet()>=start&&count.get()<=end){
            result.add(root);
        }
        fetchRangeFromInOrder(root.right,start,end,result,count);
    }
}
