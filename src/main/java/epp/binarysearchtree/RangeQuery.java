package epp.binarysearchtree;

import epp.binaryTree.BinaryTreeNode;

import java.util.ArrayList;
import java.util.List;

public class RangeQuery {
    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = BSTUtils.buildBSTWithUniqueValues(20, 1, 50);
        System.out.println(root);
        List<Integer> items = findItems(root,30,35);
        System.out.println(items);
    }

    private static List<Integer> findItems(BinaryTreeNode<Integer> root, int low, int high) {
        if(low>high){
            throw new IllegalArgumentException("low cant be greater than high , low="+low+" high="+high);
        }
        ArrayList<Integer> collector = new ArrayList<>();

        findItems(root,low,high, collector);
        return collector;
    }

    private static void findItems(BinaryTreeNode<Integer> root, int low, int high, ArrayList<Integer> collector) {
        if(root==null){
            return;
        }
        if(root.data< low){
            findItems(root.right,low,high,collector);
        }else if(root.data> high){
            findItems(root.left,low,high,collector);
        }else{
            collector.add(root.data);
            findItems(root.left,low,root.data,collector);
            findItems(root.right, root.data, high,collector);
        }
    }
}
