package epp.binaryTree;

import java.util.ArrayList;
import java.util.List;

public class IterativeInorderTraverseWithParentField {
    public static void main(String[] args) {
        BinaryTreeNodeWithParent<Integer> root = new BinaryTreeNodeWithParent<>(8,
                new BinaryTreeNodeWithParent<>(5,new BinaryTreeNodeWithParent<>(3,new BinaryTreeNodeWithParent<>(0),
                        new BinaryTreeNodeWithParent<>(2)),
                        new BinaryTreeNodeWithParent<>(17,null,new BinaryTreeNodeWithParent<>(12,new BinaryTreeNodeWithParent<>(1),null))),
                new BinaryTreeNodeWithParent<>(21,new BinaryTreeNodeWithParent<>(10,new BinaryTreeNodeWithParent<>(14,null,new BinaryTreeNodeWithParent<>(19)),
                        new BinaryTreeNodeWithParent<>(4)),
                        new BinaryTreeNodeWithParent<>(7,null,new BinaryTreeNodeWithParent<>(8)))
        );
        System.out.println(root);

        List<BinaryTreeNodeWithParent<Integer>> inorder = inorderTraverse(root);
        for(BinaryTreeNodeWithParent<Integer> node : inorder){
            System.out.print(node.data+" ,");
        }

    }

    private static List<BinaryTreeNodeWithParent<Integer>> inorderTraverse(BinaryTreeNodeWithParent<Integer> root) {
        if(root==null){
            return List.of();
        }
        List<BinaryTreeNodeWithParent<Integer>> result = new ArrayList<>();
        BinaryTreeNodeWithParent<Integer> min = getMinNode(root);
        BinaryTreeNodeWithParent<Integer> current  = min;
        while (current!=null){
            result.add(current);
            current = getNextNode(current);
        }

        return result;
    }

    private static BinaryTreeNodeWithParent<Integer> getNextNode(BinaryTreeNodeWithParent<Integer> root) {
        if(root.right!=null){
          return     getMinNode(root.right);
        }
        BinaryTreeNodeWithParent<Integer> current = root;
        BinaryTreeNodeWithParent<Integer> parent = root.parent;
        while (parent!=null && current!=parent.left){
            current = parent;
            parent = parent.parent;
        }


        return parent;
    }

    private static BinaryTreeNodeWithParent<Integer> getMinNode(BinaryTreeNodeWithParent<Integer> root) {
        if(root==null){
            return null;
        }
        BinaryTreeNodeWithParent<Integer> min = root;
        while (min.left!=null){
            min = min.left;
        }
        return min;
    }
}
