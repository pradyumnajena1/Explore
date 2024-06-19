package epp.binaryTree.revision;

import epp.binaryTree.BinaryTreeNode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ExteriorOfBinaryTree {
    public static void main(String[] args) {
        BinaryTreeNode<Character> nodeB = new BinaryTreeNode<>('B',
                new BinaryTreeNode<>('C',
                        new BinaryTreeNode<>('D'),
                        new BinaryTreeNode<>('E')),
                new BinaryTreeNode<>('F',
                        null,
                        new BinaryTreeNode<>('G',
                                new BinaryTreeNode<>('H'),
                                null)));
        BinaryTreeNode<Character> nodeI = new BinaryTreeNode<>('I',
                new BinaryTreeNode<>('J',
                        null,
                        new BinaryTreeNode<>('K',
                                new BinaryTreeNode<>('L',
                                        null,
                                        new BinaryTreeNode<>('M')),
                                new BinaryTreeNode<>('N'))),
                new BinaryTreeNode<>('O',
                        null,
                        new BinaryTreeNode<>('P')));
        BinaryTreeNode<Character> root = new BinaryTreeNode<>('A', null, nodeI);
        System.out.println(root);
        System.out.println(getExteriorOfBinaryTree(root));

    }

    private static <T extends Comparable<T>> List<T> getExteriorOfBinaryTree(BinaryTreeNode<T> root) {
        List<T> collector = new ArrayList<T>();
        if(root!=null) {
            collector.add(root.data);
            collector.addAll(getLeftBoundaryAndLeaves(root.left,true));
            collector.addAll(getRightBoundaryAndLeaves(root.right,true));
        }

        return collector;
    }

    private static <T extends Comparable<T>> Collection<? extends T> getRightBoundaryAndLeaves(BinaryTreeNode<T> subtreeRoot, boolean boundary) {
        List<T> result = new ArrayList<T>();
        if(subtreeRoot!=null){

            result.addAll(getRightBoundaryAndLeaves(subtreeRoot.left,boundary&&subtreeRoot.right==null));
            result.addAll(getRightBoundaryAndLeaves(subtreeRoot.right,boundary));
            if(boundary || subtreeRoot.isLeafNode()){
                result.add(subtreeRoot.data);
            }
        }
        return result;
    }

    private static <T extends Comparable<T>> Collection<? extends T> getLeftBoundaryAndLeaves(BinaryTreeNode<T> subtreeRoot,
                                                                                              boolean boundary) {
        List<T> result = new ArrayList<T>();
        if(subtreeRoot!=null){
            if(boundary || subtreeRoot.isLeafNode()){
                result.add(subtreeRoot.data);
            }
            result.addAll(getLeftBoundaryAndLeaves(subtreeRoot.left,boundary));
            result.addAll(getLeftBoundaryAndLeaves(subtreeRoot.right,boundary&&subtreeRoot.left==null ));
        }
        return result;
    }


}
