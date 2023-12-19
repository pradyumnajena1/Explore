package epp.binaryTree;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class PostFixWithNullMarker {
    public static void main(String[] args) {
        List<Character> postfix = Arrays.asList('H','B','F',null,null,'E','A',null,null,null,'C',null,'D',null,'G',
                'I',
                null,null,null) ;


        BinaryTreeNode<Character> root = getTreeFromPostfix(postfix);
        System.out.println(root);
    }

    private static BinaryTreeNode<Character> getTreeFromPostfix(List<Character> postfix) {

        Stack<BinaryTreeNode<Character>> stack = new Stack<>();
        for(int i=postfix.size()-1;i>=0 ;i--){
            Character ch = postfix.get(i);
            BinaryTreeNode<Character> node = ch!=null?new BinaryTreeNode<>(ch):null;
            if(node==null){
                stack.push(node);
            }else{
                node.left = stack.pop();
                node.right = stack.pop();
                stack.push(node);
            }
        }
        return stack.peek();
    }
}
