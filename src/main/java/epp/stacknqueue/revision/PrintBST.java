package epp.stacknqueue.revision;

import epp.binaryTree.BinaryTreeNode;
import epp.binarysearchtree.BSTUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PrintBST {
    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = BSTUtils.buildBSTWithUniqueValues(7, 1, 10);
        System.out.println(root);
        // printBSTInOrder(root);
        printBSTPreOrder(root);
        printBSTPostOrder(root);
        printBSTPostOrder2(root);
        printBSTPostOrder3(root);
        printBSTPostOrder5(root);
    }

    private static void printBSTInOrder(BinaryTreeNode<Integer> root) {
        Stack<BinaryTreeNode<Integer>> stack = new Stack<>();
        BinaryTreeNode<Integer> current = root;
        List<Integer> result = new ArrayList<>();
        while (current != null || !stack.isEmpty()) {

            if (current != null) {
                stack.push(current);
                current = current.left;
            } else {
                current = stack.pop();
                result.add(current.data);
                current = current.right;
            }
        }
        System.out.println(result);
    }

    private static void printBSTPreOrder(BinaryTreeNode<Integer> root) {
        Stack<BinaryTreeNode<Integer>> stack = new Stack<>();
        stack.push(root);
        List<Integer> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            BinaryTreeNode<Integer> pop = stack.pop();
            if (pop != null) {
                result.add(pop.data);
                stack.push(pop.right);
                stack.push(pop.left);
            }

        }
        System.out.println(result);
    }

    private static void printBSTPostOrder(BinaryTreeNode<Integer> root) {
        Stack<BinaryTreeNode<Integer>> stack = new Stack<>();
        Stack<BinaryTreeNode<Integer>> stack2 = new Stack<>();
        List<Integer> result = new ArrayList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            BinaryTreeNode<Integer> pop = stack.pop();
            if (pop != null) {
                stack2.push(pop);
                stack.push(pop.left);
                stack.push(pop.right);
            }

        }
        while (!stack2.isEmpty()) {
            result.add(stack2.pop().data);
        }
        System.out.println(result);
    }

    private static void printBSTPostOrder2(BinaryTreeNode<Integer> node) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        Stack<BinaryTreeNode<Integer>> S = new Stack<>();
        // Check for empty tree
        if (node == null)
            return  ;
        S.push(node);
        BinaryTreeNode<Integer> prev = null;
        while (!S.isEmpty()) {
            BinaryTreeNode<Integer> current = S.peek();
            /* go down the tree in search of a leaf an if so
			process it and pop stack otherwise move down */
            if (prev == null || prev.left == current || prev.right == current) {
                if (current.left != null)
                    S.push(current.left);
                else if (current.right != null)
                    S.push(current.right);
                else {
                    S.pop();
                    list.add(current.data);
                }

				/* go up the tree from left node, if the
				child is right push it onto stack otherwise
				process parent and pop stack */
            } else if (current.left == prev) {
                if (current.right != null)
                    S.push(current.right);
                else {
                    S.pop();
                    list.add(current.data);
                }

				/* go up the tree from right node and after
				coming back from right node process parent
				and pop stack */
            } else if (current.right == prev) {
                S.pop();
                list.add(current.data);
            }

            prev = current;
        }

        System.out.println(list);
    }
    private static void printBSTPostOrder3(BinaryTreeNode<Integer> root) {
        Stack<BinaryTreeNode<Integer>> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        while (true){
            while (root!=null){
                stack.push(root);
                stack.push(root);
                root=root.left;
            }

            if(stack.isEmpty()){
                break;
            }
            root = stack.pop();
            if(!stack.isEmpty()&& stack.peek()==root){
                root = root.right;
            }else {
                result.add(root.data);
                root=null;
            }
        }
        System.out.println(result);
    }

    /**
     * done for practice
     * @param node
     */
    private static void printBSTPostOrder5(BinaryTreeNode<Integer> node) {
        if(node==null){
            return;
        }
        List<Integer> result = new ArrayList<>();
        Stack<BinaryTreeNode<Integer>> stack = new Stack<>();
        stack.push(node);
        BinaryTreeNode<Integer> prev = null;
        while (!stack.isEmpty()){
            BinaryTreeNode<Integer> current = stack.peek();
            //going down the path till we find a leaf node,
            // found process it and pop from stack
            if(prev==null|| prev.left==current||prev.right==current){
                if(current.left!=null){
                    stack.push(current.left);
                }else if(current.right!=null){
                    stack.push(current.right);
                }else{
                   result.add( stack.pop().data);
                }
            }
            // go up the tree from left
            else if (current.left==prev) {
                if(current.right!=null){
                    stack.push(current.right);
                }else{
                    result.add( stack.pop().data);
                }
            }else // go up from right
                if(current.right==prev){
                result.add( stack.pop().data);
            }
                prev=current;
        }
        System.out.println(result);
    }
    }


