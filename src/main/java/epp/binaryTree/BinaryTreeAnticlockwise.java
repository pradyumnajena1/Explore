package epp.binaryTree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class BinaryTreeAnticlockwise {
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

        List<Integer> anticlockCollection = getAntiClockCollection(root);
        System.out.println(anticlockCollection);

    }

    private static List<Integer> getAntiClockCollection(BinaryTreeNode<Integer> root) {
        List<Integer> result = new ArrayList<>();
        result.addAll(getLeftSide(root));
        result.addAll(getBottomSide(root));
        result.addAll(getRightSide(root));
        return result;
    }

    private static List<Integer> getRightSide(BinaryTreeNode<Integer> root) {
        ArrayList<Integer> result = new ArrayList<>();
        BinaryTreeNode<Integer> current = root;
        while (current.right!=null){
            result.add(current.data);
            current = current.right;
        }
        result.remove(0);
        Collections.reverse(result);
        return result;
    }

    private static List<Integer> getBottomSide(BinaryTreeNode<Integer> root) {
        ArrayList<Integer> result = new ArrayList<>();
        inorderTraverse(root,result);
        return result;
    }

    private static void inorderTraverse(BinaryTreeNode<Integer> root, ArrayList<Integer> result) {
        if(root == null){
            return;
        }
        inorderTraverse(root.left,result);
        processNode(root,result);
        inorderTraverse(root.right,result);
    }

    private static void processNode(BinaryTreeNode<Integer> root, ArrayList<Integer> result) {
        if(root.left==null && root.right==null){
            result.add(root.data);
        }
    }

    private static Collection<Integer> getLeftSide(BinaryTreeNode<Integer> root) {
        List<Integer> result = new ArrayList<>();
        BinaryTreeNode<Integer> current = root;
        while(current.left!=null){
            result.add(current.data);
            current = current.left;
        }

        return result;
    }
}
