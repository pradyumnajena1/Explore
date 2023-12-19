package epp.binaryTree.revision;

import epp.binaryTree.BinaryTreeNodeWithSize;
import epp.binarysearchtree.BSTUtils;

import java.util.ArrayList;
import java.util.List;

public class FetchRangeNodesFromBSTWithSize {
    public static void main(String[] args) {
        BinaryTreeNodeWithSize<Integer> root =  BSTUtils.buildBSTWithSizeWithUniqueValues(10,1,20);
        System.out.println(root);
        List<BinaryTreeNodeWithSize<Integer>> range = fetchRangeNodesFromBSTWithSize(root,3,7);
        range.stream().map(x->x.data).forEach(System.out::println);
    }

    private static List<BinaryTreeNodeWithSize<Integer>> fetchRangeNodesFromBSTWithSize(BinaryTreeNodeWithSize<Integer> root, int start, int end) {
        List<BinaryTreeNodeWithSize<Integer>> result = new ArrayList<>();
        fetchRangeNodesFromBSTWithSize(root,start,end,result);
        return result;
    }

    private static void fetchRangeNodesFromBSTWithSize(BinaryTreeNodeWithSize<Integer> root, int start, int end, List<BinaryTreeNodeWithSize<Integer>> result) {
        if(root==null){
            return;
        }
        int leftSize = root.left!=null?root.left.size:0;
        if(leftSize>=start){
            fetchRangeNodesFromBSTWithSize(root.left,start,end,result);
        }
        int nodeIndex = leftSize + 1;
        if(nodeIndex >=start&& nodeIndex <=end){
            result.add(root);
            int rangeSize = end - start + 1;
            if(result.size()== rangeSize){
                return;
            }
        }
        fetchRangeNodesFromBSTWithSize(root.right,start-nodeIndex,end-nodeIndex,result);


    }
}
