package epp.binarysearchtree;

import epp.MutablePrimitive;
import epp.binaryTree.BinaryTreeNodeWithSize;

public class RangeQueryWithSize {
    public static void main(String[] args) {
        BinaryTreeNodeWithSize<Integer> root =  BSTUtils.buildBSTWithSizeWithUniqueValues(10,1,20);
        System.out.println(root);
        int numItems = getNumItemsWithinRange(root,6,12 );
        System.out.println(numItems);

        numItems = getNumItemsWithinRange2(root,6,12 );
        System.out.println(numItems);
    }

    private static int getNumItemsWithinRange2(BinaryTreeNodeWithSize<Integer> root, int low, int high) {
             int less = getNumItemsLessThan(root,low);
             int greater = getNumItemsGreaterThan(root,high);
        System.out.println(less);
        System.out.println(greater);
             return root.size - less - greater;
    }

    private static int getNumItemsLessThan(BinaryTreeNodeWithSize<Integer> root, int low) {
        if(root==null){
            return 0;
        }
        if(root.data>=low){
            return getNumItemsLessThan(root.left,low);
        }else{
            return  (root.left!=null? root.left.size:0) +1 + getNumItemsLessThan(root.right,low);
        }

    }
    private static int getNumItemsGreaterThan(BinaryTreeNodeWithSize<Integer> root, int high) {
        if(root==null){
            return 0;
        }
        if(root.data<=high){
            return  getNumItemsGreaterThan(root.right,high);
        }else{
            return  (root.right!=null? root.right.size:0)+1+  getNumItemsGreaterThan(root.left,high);
        }

    }

    private static int getNumItemsWithinRange(BinaryTreeNodeWithSize<Integer> root, int low, int high) {
        MutablePrimitive<Integer> counter = new MutablePrimitive<>(0);
          getNumItemsWithinRange(root,low,high,counter);
          return counter.getValue();
    }

    private static void getNumItemsWithinRange(BinaryTreeNodeWithSize<Integer> root, int low, int high,
                                            MutablePrimitive<Integer> counter) {
        if(root==null){
            return;
        }
        if(root.data<low){
            getNumItemsWithinRange(root.right,low,high,counter);
        }else if(root.data>high){
            getNumItemsWithinRange(root.left,low,high,counter);
        }else {
            counter.setValue(counter.getValue()+1);
            getNumItemsWithinRange(root.left,low,high,counter);
            getNumItemsWithinRange(root.right,low,high,counter);
        }
    }
}
