package epp.stacknqueue;

import java.util.Stack;

public class PrintBST {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(2,new TreeNode(1),new TreeNode(3));
        printBST(root);
    }

    private static void printBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode currentNode = root;

        while ( !stack.isEmpty() ||  currentNode!=null ){
            if(currentNode!=null){
                stack.push(currentNode);
                currentNode = currentNode.left;
            }else{
               currentNode = stack.pop();
               processNode(currentNode);
               currentNode = currentNode.right;
            }

        }
    }

    private static void processNode(TreeNode currentNode) {
        System.out.println(currentNode.data);
    }
}
