package epp.binarysearchtree.revision;

import epp.binaryTree.BinaryTreeNode;
import epp.binaryTree.BinaryTreeUtils;

public class RangeQueryWithSizeField<T extends Comparable<T>> {
    public static void main(String[] args) {
        RangeQueryWithSizeField<Integer> rangeQueryWithSizeField = new RangeQueryWithSizeField<>();
        rangeQueryWithSizeField.insert(2);
        rangeQueryWithSizeField.insert(1);
        rangeQueryWithSizeField.insert(4);
        rangeQueryWithSizeField.insert(3);
        rangeQueryWithSizeField.insert(5);


        rangeQueryWithSizeField.insert(6);
        rangeQueryWithSizeField.insert(4);
        System.out.println(rangeQueryWithSizeField.root);
        System.out.println(rangeQueryWithSizeField.numItemsInRange(2,4));
        rangeQueryWithSizeField.delete(4);
        System.out.println(rangeQueryWithSizeField.root);

    }

    BinaryTreeNode<NodeWithSize<T>> root ;

    public void insert(T key){
      root = insert(root,key);
    }

    public void delete(T key){
        root = delete(root,key);
    }

    /**
     * low high inclusive
     * @param low
     * @param high
     * @return
     */
    public int numItemsInRange(T low,T high){
        int numItemsLessThanLow = numItemsLessThan(low);
        int numItemsGreaterThanHigh = numItemsGreaterThan(high);
        System.out.println(numItemsLessThanLow);
        System.out.println(numItemsGreaterThanHigh);
        return root.data.size - numItemsLessThanLow-numItemsGreaterThanHigh;
    }

    private int numItemsLessThan(T low) {
       return numItemsLessThan(root,low);
    }
    private int numItemsGreaterThan(T high) {
        return numItemsGreaterThan(root,high);
    }

    private int numItemsLessThan(BinaryTreeNode<NodeWithSize<T>> root, T low) {
        if(root==null){
            return 0;
        }
        int compare = root.data.data.compareTo(low);
        if(compare<0){
            return (root.left!=null?root.left.data.size:0)+1 + numItemsLessThan(root.right,low);
        }else  {
            return numItemsLessThan(root.left,low);
        }

    }

    private int numItemsGreaterThan(BinaryTreeNode<NodeWithSize<T>> root, T high) {
        if(root==null){
            return 0;
        }
        int compare = root.data.data.compareTo(high);
        if(compare<=0){
            return numItemsGreaterThan(root.right,high);
        }else  {
            return (root.right!=null?root.right.data.size:0)+1 + numItemsGreaterThan(root.left,high);

        }

    }


    private BinaryTreeNode<NodeWithSize<T>> delete(BinaryTreeNode<NodeWithSize<T>> root, T key) {
        if(root==null){
            return null;
        }
        int compare = root.data.data.compareTo(key);
        if(compare ==0 && root.data.data.equals(key) ){
            if(root.left==null){
                return root.right;
            }
            if(root.right==null){
                return root.left;
            }
            BinaryTreeNode<NodeWithSize<T>> minNode = BinaryTreeUtils.getMinNode(root.right);
            root.data.data = minNode.data.data;
            delete(root.right,minNode.data.data);

        }else if(compare<=0){
            root.right = delete(root.right,key);
        }else {
            root.left = delete(root.left,key);
        }
        root.data.size--;
        return root;
    }


    private BinaryTreeNode<NodeWithSize<T>> insert(BinaryTreeNode<NodeWithSize<T>> root, T key) {
        if(root==null){
            return new BinaryTreeNode<>(new NodeWithSize<>(key,1));

        }
        int compare = root.data.data.compareTo(key);
        if(compare<=0){
            root.right = insert(root.right,key);

        }else{
            root.left = insert(root.left,key);

        }
        root.data.size++;
        return root;
    }

    public int getNumItemsInRange(T low ,T high){
        return 0;

    }

}
