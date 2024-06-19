package epp.binaryTree.revision;

import epp.binaryTree.BinaryTreeNodeWithSize;
import epp.binarysearchtree.BSTUtils;
import java.util.concurrent.atomic.AtomicReference;

public class FindKthNodeFromBSTWithSize {
    public static void main(String[] args) {
        BinaryTreeNodeWithSize<Integer> root =  BSTUtils.buildBSTWithSizeWithUniqueValues(10,1,20);
        System.out.println(root);
        BinaryTreeNodeWithSize<Integer> node =  findKthNodeFromBSTWithSize(root,7);
        System.out.println(node);

        node =  findKthNodeFromBSTWithSizeIter(root,7);
        System.out.println(node);
    }

    public static BinaryTreeNodeWithSize<Integer> findKthNodeFromBSTWithSize(BinaryTreeNodeWithSize<Integer> root,
                                                                        int index) {
    AtomicReference<BinaryTreeNodeWithSize<Integer>> resultHolder = new AtomicReference<>();
        findKthNodeFromBSTWithSize(root,index,resultHolder);
        return  resultHolder.get() ;
    }

    public static BinaryTreeNodeWithSize<Integer> findKthNodeFromBSTWithSizeIter(BinaryTreeNodeWithSize<Integer> root,
                                                                             int index) {
        BinaryTreeNodeWithSize<Integer> iter = root;
        while (iter!=null){
            int leftSize = iter.left==null?0:root.left.size;
            if(index==leftSize+1){
                return iter;
            }else if(index<=leftSize){
                iter = iter.left;
            }else{
                index = index -leftSize-1;
                iter = iter.right;
            }
        }
        return null;
    }

    public static void findKthNodeFromBSTWithSize(BinaryTreeNodeWithSize<Integer> root, int index,
                                                   AtomicReference<BinaryTreeNodeWithSize<Integer>> resultHolder) {
        if(root!=null){
            int leftSize = root.left==null?0:root.left.size;

            if(index==leftSize+1){
                resultHolder.set(root);
            }else if(index<=leftSize){
                findKthNodeFromBSTWithSize(root.left,index,resultHolder);
            }else{
                findKthNodeFromBSTWithSize(root.right,index-leftSize-1,resultHolder);
            }
        }

    }
}
