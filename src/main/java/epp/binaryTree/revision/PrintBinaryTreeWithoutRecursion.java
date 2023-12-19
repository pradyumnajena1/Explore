package epp.binaryTree.revision;

import epp.binaryTree.BinaryTreeNode;
import epp.binarysearchtree.BSTUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PrintBinaryTreeWithoutRecursion {
    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = BSTUtils.buildBSTWithUniqueValues(7, 1, 10);
        System.out.println(root);
        printBinaryTreePreOrder(root);
        printBinaryTreePostOrder(root);
        printBinaryTreePostOrder2(root);
    }

    private static void printBinaryTreePreOrder(BinaryTreeNode<Integer> root) {
       List<Integer> values =  preOrderTraverseWithoutRecursion(root);
       System.out.println(values);

    }
    private static void printBinaryTreePostOrder(BinaryTreeNode<Integer> root) {
        List<Integer> values =  postOrderTraverseWithoutRecursion(root);
        System.out.println(values);

    }
    private static void printBinaryTreePostOrder2(BinaryTreeNode<Integer> root) {
        List<Integer> values =  postOrderTraverseWithoutRecursion2(root);
        System.out.println(values);

    }

    private static List<Integer> postOrderTraverseWithoutRecursion(BinaryTreeNode<Integer> root) {
        List<Integer> result = new ArrayList<>();
        Stack<BinaryTreeNode<Integer>> stack = new Stack<>();
        if(root==null){
            return result;
        }
        stack.push(root);
        while (!stack.isEmpty()){
            BinaryTreeNode<Integer> popped = stack.pop();
            result.add(popped.data);
            if(popped.left!=null){
                stack.push(popped.left);
            }
            if(popped.right!=null){
                stack.push(popped.right);
            }
        }
        reverse(result);
        return result;
    }

    private static void reverse(List<Integer> result) {
        int start = 0;
        int end = result.size()-1;
        while (start<end){
            Integer temp = result.get(start);
            result.set(start,result.get(end));
            result.set(end,temp);
            start++;
            end--;
        }
    }

    private static List<Integer> preOrderTraverseWithoutRecursion(BinaryTreeNode<Integer> root) {
        List<Integer> result = new ArrayList<>();
        if(root==null){
            return result;
        }
        Stack<BinaryTreeNode<Integer>> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            BinaryTreeNode<Integer> pop = stack.pop();
            result.add(pop.data);
            if(pop.right!=null){
                stack.push(pop.right);
            }
            if(pop.left!=null){
                stack.push(pop.left);
            }
        }
        return result;
    }

    private static List<Integer> postOrderTraverseWithoutRecursion2(BinaryTreeNode<Integer> root) {
        List<Integer> result = new ArrayList<>();
        if(root==null){
            return result;
        }
        Stack<BinaryTreeNode<Integer>> stack = new Stack<>();
        stack.push(root);
        BinaryTreeNode<Integer> prev = null;
        BinaryTreeNode<Integer> current = null;
        while (!stack.isEmpty()){
            current = stack.peek();
            //going down the path till we find a leaf node,
            // found process it and pop from stack
            if(prev==null|| prev.left==current||prev.right==current){
                if(current.left!=null){
                    stack.push(current.left);
                }else if(current.right!=null){
                    stack.push(current.right);
                }else{
                    BinaryTreeNode<Integer> pop = stack.pop();
                    result.add(pop.data);
                }
            }else if(current.left==prev ){
                if(current.right!=null){
                    stack.push(current.right);
                }else{
                    BinaryTreeNode<Integer> pop = stack.pop();
                    result.add(pop.data);
                }
            }else if(current.right == prev ){
                BinaryTreeNode<Integer> pop = stack.pop();
                result.add(pop.data);
            }
            prev = current;
        }
        return result;

    }

}
