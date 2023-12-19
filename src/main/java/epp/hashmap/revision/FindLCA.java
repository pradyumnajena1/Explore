package epp.hashmap.revision;

import epp.array.ArrayUtils;
import epp.binaryTree.BinaryTreeNodeWithParent;
import epp.binarysearchtree.BSTUtils;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FindLCA {
    public static void main(String[] args) {
        int[] sortedArray = ArrayUtils.randomSortedUniqueArray(10, 1, 20);
        ArrayUtils.printArray(sortedArray);
        BinaryTreeNodeWithParent<Integer> root =
                BSTUtils.buildBSTWithParentFromSortedArray(sortedArray);
        System.out.println(root);
        BinaryTreeNodeWithParent<Integer> nodeA = BSTUtils.findNode(root, sortedArray[2]);
        BinaryTreeNodeWithParent<Integer> nodeB = BSTUtils.findNode(root, sortedArray[3]);
        System.out.println(nodeA);
        System.out.println(nodeB);
        BinaryTreeNodeWithParent<Integer> node_lca = findLCA(nodeA,nodeB);
        System.out.println(node_lca);
    }

    private static BinaryTreeNodeWithParent<Integer> findLCA(BinaryTreeNodeWithParent<Integer> nodeA, BinaryTreeNodeWithParent<Integer> nodeB) {
        Set<BinaryTreeNodeWithParent<Integer>> set = new HashSet<>();
        while (nodeA!=null || nodeB!=null){
            if(nodeA!=null){
                if(set.contains(nodeA)){
                    return nodeA;
                }
                set.add(nodeA);
                nodeA = nodeA.parent;
            }
            if(nodeB!=null){
                if(set.contains(nodeB)){
                    return nodeB;
                }
                set.add(nodeB);
                nodeB = nodeB.parent;
            }
       }
        return null;
    }
}
