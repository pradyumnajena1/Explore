package epp.binaryTree;

import java.util.ArrayList;
import java.util.List;

public class FindNodeWithPathSum {
    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(8,
                new BinaryTreeNode<>(5,new BinaryTreeNode<>(3,new BinaryTreeNode<>(0),
                        new BinaryTreeNode<>(2)),
                        new BinaryTreeNode<>(17,null,new BinaryTreeNode<>(12,new BinaryTreeNode<>(1),null))),
                new BinaryTreeNode<>(21,new BinaryTreeNode<>(10,new BinaryTreeNode<>(14,null,new BinaryTreeNode<>(19)),
                        new BinaryTreeNode<>(4)),
                        new BinaryTreeNode<>(7,null,new BinaryTreeNode<>(8)))
        );
        System.out.println(root);

        List<BinaryTreeNode<Integer>> nodes = getNodesWithPathSum(root,43);
        System.out.println(nodes);

    }

    private static List<BinaryTreeNode<Integer>> getNodesWithPathSum(BinaryTreeNode<Integer> root, int sum) {
        List<BinaryTreeNode<Integer>> result = new ArrayList<>();
        List<BinaryTreeNode<Integer>> path = new ArrayList<>();
        getNodesWithPathSum(root,sum,path,result);

        return result;
    }

    private static void getNodesWithPathSum(BinaryTreeNode<Integer> root, int sum, List<BinaryTreeNode<Integer>> path, List<BinaryTreeNode<Integer>> result) {
        if(root==null || result.size()>0 ){
            return;
        }
        path.add(root);
        if(pathSum(path)==sum){
            result.add(root);
        }
        if(root.left!=null){
            getNodesWithPathSum(root.left,sum,path, result);
        }
        if(root.right!=null){
            getNodesWithPathSum(root.right,sum,path, result);
        }
        path.remove(path.size()-1);
    }

    private static int pathSum(List<BinaryTreeNode<Integer>> path) {
        int sum = 0;
        for(BinaryTreeNode<Integer> node:path){
            sum+=node.data;
        }
        return sum;
    }
}
