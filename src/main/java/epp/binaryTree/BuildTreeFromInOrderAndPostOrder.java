package epp.binaryTree;

import java.util.List;

public class BuildTreeFromInOrderAndPostOrder {
    public static void main(String[] args) {
        List<Character> inOrder = List.of('F','B','A','E','H','C','D','I','G');
        List<Character> postOrder = List.of( 'H','B','F','E','A','C','D','G','I');

        BinaryTreeNode<Character> root = createTree(inOrder,postOrder);
        System.out.println(root);
    }

    private static BinaryTreeNode<Character> createTree(List<Character> inOrder, List<Character> postOrder) {
        if(inOrder==null || inOrder.isEmpty()){
            return null;
        }
        if(postOrder==null||postOrder.isEmpty()){
            return null;
        }
        return createTree(inOrder,0,inOrder.size()-1,postOrder,0,postOrder.size()-1);
    }

    private static BinaryTreeNode<Character> createTree(List<Character> inOrder, int start_in, int end_in,
                                                        List<Character> postOrder, int start_post, int end_post) {

        if(start_in>end_in){
            return null;
        }
        Character rootValue = postOrder.get(start_post);
        int inorderIndex = inOrder.indexOf(rootValue);
        int leftLength = inorderIndex-start_in;

        BinaryTreeNode<Character> root = new BinaryTreeNode<>(rootValue);
        root.left = createTree(inOrder,start_in,inorderIndex-1,postOrder,start_post+1,start_post+leftLength );
        root.right  = createTree(inOrder,inorderIndex+1,end_in,postOrder,start_post+leftLength+1,end_post);
        return root;

    }
}
