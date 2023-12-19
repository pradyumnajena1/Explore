package epp.binaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class SumOfLeaves {
    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(1,
                new BinaryTreeNode<>(0,new BinaryTreeNode<>(0,new BinaryTreeNode<>(0),
                        new BinaryTreeNode<>(1)),
                        new BinaryTreeNode<>(1,null,new BinaryTreeNode<>(1,new BinaryTreeNode<>(0),null))),
                new BinaryTreeNode<>(1,new BinaryTreeNode<>(0,new BinaryTreeNode<>(1,null,new BinaryTreeNode<>(1)),new BinaryTreeNode<>(0)),
                        new BinaryTreeNode<>(0,null,new BinaryTreeNode<>(0)))
        );
        System.out.println(root);
        int sum = getSumOfLeaves(root);
        System.out.println(sum);

    }

    private static int getSumOfLeaves(BinaryTreeNode<Integer> root ) {
        List<Integer> path = new ArrayList<>();
        int level = 0;
        AtomicInteger sum = new AtomicInteger(0);
        getSumOfLeaves(root,path,sum,level);
        return sum.get();
    }

    private static void getSumOfLeaves(BinaryTreeNode<Integer> root, List<Integer> path, AtomicInteger sum, int level) {
        if(root==null){
            return;
        }
        path.add(root.data);
        if(root.left!=null){
            getSumOfLeaves(root.left,path,sum,level+1);
        }
        if(root.right!=null){
            getSumOfLeaves(root.right,path,sum,level+1);
        }
        if(root.left==null && root.right==null){
            int value = getIntValue(path,level);

           sum.getAndAdd( value);
        }
        path.remove(level);
    }

    private static int getIntValue(List<Integer> path, int level) {
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<=level;i++){
            sb.append(path.get(i));
        }

        return Integer.parseInt(sb.toString(),2);
    }
}
