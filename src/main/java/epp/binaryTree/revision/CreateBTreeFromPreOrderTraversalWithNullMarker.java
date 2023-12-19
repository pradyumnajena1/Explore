package epp.binaryTree.revision;

import epp.binaryTree.BinaryTreeNode;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class CreateBTreeFromPreOrderTraversalWithNullMarker {
    public static void main(String[] args) {
        List<Character> preOrderTraversal = Arrays.asList('H', 'B', 'F', null, null, 'E', 'A', null, null, null,
                'C', null
                , 'D', null,
                'G', 'I', null, null, null);
          BinaryTreeNode<Character> root = createBTreeFromPreOrderTraversalWithNullMarker(preOrderTraversal);
        System.out.println(root);

        List<Character> postOrderTraversal = Arrays.asList( null,null,'F',null,null,'A',null,'E','B',null,null,null,
                null,'I',null,'G','D','C','H');
          root = createBTreeFromPostOrderTraversalWithNullMarker(postOrderTraversal);
        System.out.println(root);
    }

    private static BinaryTreeNode<Character> createBTreeFromPostOrderTraversalWithNullMarker(List<Character> postOrderTraversal) {
        Stack<BinaryTreeNode<Character>> stack = new Stack<>();
        for(int i=0;i<postOrderTraversal.size();i++){
            Character value = postOrderTraversal.get(i);
            if(value==null){
                stack.push(null);
            }else{
                BinaryTreeNode<Character> right = stack.pop();
                BinaryTreeNode<Character> left = stack.pop();
                stack.push(new BinaryTreeNode<>(value,left,right));
            }
        }
        return stack.pop();
    }

    private static BinaryTreeNode<Character> createBTreeFromPreOrderTraversalWithNullMarker(List<Character> preOrderTraversalInt) {
        Stack<BinaryTreeNode<Character>> stack = new Stack<>();
        for(int i=preOrderTraversalInt.size()-1;i>=0;i--){
            Character value = preOrderTraversalInt.get(i);
            if(value==null){
                stack.push(null);
            }else{
                BinaryTreeNode<Character> left = stack.pop();
                BinaryTreeNode<Character> right = stack.pop();
                stack.push(new BinaryTreeNode<>(value,left,right));
            }
        }
        return stack.pop();
    }
}
