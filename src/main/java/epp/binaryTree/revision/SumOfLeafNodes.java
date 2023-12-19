package epp.binaryTree.revision;

import epp.binaryTree.BinaryTreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SumOfLeafNodes {
    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(1,
                new BinaryTreeNode<>(0,
                        new BinaryTreeNode<>(0,
                                new BinaryTreeNode<>(0),
                                new BinaryTreeNode<>(1)),
                        new BinaryTreeNode<>(1,
                                null,
                                new BinaryTreeNode<>(1,
                                        new BinaryTreeNode<>(0),
                                        null))),
                new BinaryTreeNode<>(1,
                        new BinaryTreeNode<>(0,
                                null,
                                new BinaryTreeNode<>(0,
                                        new BinaryTreeNode<>(1,
                                                null,
                                                new BinaryTreeNode<>(1)),
                                        new BinaryTreeNode<>(0))),
                        new BinaryTreeNode<>(0,
                                null,
                                new BinaryTreeNode<>(0)))
        );
        System.out.println(root);
        int sum = getLeafSum(root);
        System.out.println(sum);
    }

    private static int getLeafSum(BinaryTreeNode<Integer> root) {
        List<Integer> values = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        collectValues(root,values,path);
        System.out.println(values);
        Integer sum = values.stream().collect(Collectors.summingInt(Integer::intValue));
        return sum;
    }

    private static void collectValues(BinaryTreeNode<Integer> root, List<Integer> values, List<Integer> path) {
        if(root==null){
            return;
        }
        path.add(root.data);
        if(root.isLeafNode()){
            int value = getValue(path);
            values.add(value);
        }
        collectValues(root.left,values,path);
        collectValues(root.right,values,path);
        path.remove(path.size()-1);
    }

    private static int getValue(List<Integer> path) {
        int value = 0;
        for(int i=0;i<path.size();i++){
            value = value*2 + path.get(i);
        }
        return value;
    }
}
