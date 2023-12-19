package epp.binaryTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class PostorderWithoutRecurse {
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
        List<BinaryTreeNode<Integer>> preorder = getPostOrder(root);
        for(BinaryTreeNode<Integer> node :preorder){
            System.out.print(node.data+" ,");
        }

    }

    private static List<BinaryTreeNode<Integer>> getPostOrder(BinaryTreeNode<Integer> root) {
        List<BinaryTreeNode<Integer>> result = new ArrayList<>();
        if(root==null){
            return result;
        }
        Stack<BinaryTreeNode<Integer>> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            BinaryTreeNode<Integer> node = stack.pop();
            result.add(node);

            if(node.left!=null){
                stack.push(node.left);
            }
            if(node.right!=null){

                stack.push(node.right);
            }
        }
          Collections.reverse( result);
        return result;
    }
}
