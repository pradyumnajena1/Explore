package hackerrank.medium;

import epp.array.ArrayUtils;
import epp.binaryTree.BinaryTreeNode;
import epp.binaryTree.BinaryTreeUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SwapNodes {
    public static void main(String[] args) {
        System.out.println(swapNodes(new ArrayList<>(List.of(
                List.of(2, 3),
                List.of(4, -1),
                List.of(5, -1),
                List.of(6, -1),
                List.of(7, 8),
                List.of(-1, 9),
                List.of(-1, -1),
                List.of(10, 11),
                List.of(-1, -1),
                List.of(-1, -1),
                List.of(-1, -1)

        )), new ArrayList<>(List.of(2, 4))));
    }

    public static List<List<Integer>> swapNodes(List<List<Integer>> indexes, List<Integer> queries) {
        // Write your code here
        BinaryTreeNode<Integer> tree = buildTree(indexes);
        System.out.println(tree);
        List<List<Integer>> result = new ArrayList<>();
        for(int level:queries){
            boolean[] swaps = new boolean[indexes.size()+1];
            int i=1;
            while (level*i< swaps.length){
                swaps[level*i] = !swaps[level * i];
                i++;
            }
            ArrayUtils.printArray(swaps);
            dfs(tree,1,swaps);
            System.out.println(tree);
            List<Integer> nodes =  inOrderTraversal(tree);
            result.add(nodes);
        }

        return result;
    }

    public static <T extends Comparable<T>> List<T> inOrderTraversal(BinaryTreeNode<T> root) {
        List<T> collector = new ArrayList<>();
        inOrderTraversal(root, collector);
        return collector;
    }


    private static <T extends Comparable<T>> void inOrderTraversal(BinaryTreeNode<T> root, List<T> collector) {
        if (root == null) {
            return;
        }
        inOrderTraversal(root.left, collector);
        collector.add(root.data);
        inOrderTraversal(root.right, collector);
    }

    private static void dfs(BinaryTreeNode<Integer> tree, int level, boolean[] swaps) {

        if(tree!=null){
            if(swaps[level]){
                BinaryTreeNode<Integer> temp = tree.left;
                tree.left = tree.right;
                tree.right = temp;
            }
            dfs(tree.left,level+1,swaps);
            dfs(tree.right,level+1,swaps);
        }
    }

    private static BinaryTreeNode<Integer> buildTree(List<List<Integer>> indexes) {

        BinaryTreeNode<Integer>[] nodes  = new BinaryTreeNode[indexes.size()+1];
        for(int i=1;i<nodes.length;i++){
            nodes[i] = new BinaryTreeNode<>(i);
        }

        for(int i=0;i<indexes.size();i++){
            Integer leftIndex = indexes.get(i).get(0);
            Integer rightIndex = indexes.get(i).get(1);

            nodes[i+1].left = leftIndex !=-1? nodes[leftIndex] :null;
            nodes[i+1].right = rightIndex !=-1? nodes[rightIndex] :null;
        }

        return nodes[1];
    }
}
