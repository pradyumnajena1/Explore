package epp.binaryTree.revision;

import epp.binaryTree.BinaryTreeNodeWithParent;
import epp.binaryTree.BinaryTreeUtils;
import epp.binarysearchtree.BSTUtils;

import java.util.Arrays;
import java.util.List;

public class CreateTreeFromInOrderAndPreOrderTraversal {
    public static void main(String[] args) {
        BinaryTreeNodeWithParent<Integer> root = BSTUtils.buildBSTWithParentWithUniqueValues(10,1,12);
        System.out.println(root);
        List<Integer> inOrderTraversal = BinaryTreeUtils.inOrderTraversal(root);
        List<Integer> preOrderTraversal = BinaryTreeUtils.preOrderTraversal(root);
        BinaryTreeNodeWithParent<Integer> newRoot =  createTreeFromInOrderAndPreOrder(inOrderTraversal,
                preOrderTraversal);
        System.out.println(newRoot);
    }

    private static BinaryTreeNodeWithParent<Integer> createTreeFromInOrderAndPreOrder(List<Integer> inOrderTraversal, List<Integer> preOrderTraversal) {
        if(inOrderTraversal==null|| preOrderTraversal==null ){
            return null;
        }
        Integer[] inOrderTraversalArray = inOrderTraversal.toArray(new Integer[0]);
        Integer[] preOrderTraversalArray = preOrderTraversal.toArray(new Integer[0]);
       return createTreeFromInOrderAndPreOrder(inOrderTraversalArray,
                preOrderTraversalArray);

    }

    private static BinaryTreeNodeWithParent<Integer> createTreeFromInOrderAndPreOrder(Integer[] inOrderTraversalArray, Integer[] preOrderTraversalArray) {
         return createTreeFromInOrderAndPreOrder(inOrderTraversalArray,0,inOrderTraversalArray.length-1,
                 preOrderTraversalArray,0,preOrderTraversalArray.length-1);
    }

    private static BinaryTreeNodeWithParent<Integer> createTreeFromInOrderAndPreOrder(Integer[] inOrderTraversalArray
            , int inStart, int inEnd, Integer[] preOrderTraversalArray, int preStart, int preEnd) {
        if(inStart>inEnd || preStart>preEnd){
            return null;
        }
        if(preStart==preEnd){
            return new BinaryTreeNodeWithParent<>(preOrderTraversalArray[preStart]);
        }

        Integer root = preOrderTraversalArray[preStart];
        int rootIndex = Arrays.binarySearch(inOrderTraversalArray, inStart, inEnd, root);
        int leftStart = inStart;
        int leftEnd = rootIndex-1;

        int rightStart = rootIndex+1;
        int rightEnd  = inEnd;
        int leftSize =  leftEnd - leftStart + 1;

        BinaryTreeNodeWithParent<Integer> rootNode = new BinaryTreeNodeWithParent<>(root);
        rootNode.left = createTreeFromInOrderAndPreOrder(inOrderTraversalArray,leftStart,leftEnd,
                preOrderTraversalArray,preStart+1,preStart + leftSize);

        rootNode.right = createTreeFromInOrderAndPreOrder(inOrderTraversalArray,rightStart,rightEnd,
                preOrderTraversalArray,preStart+leftSize+1,preEnd);
        return rootNode;
    }
}
