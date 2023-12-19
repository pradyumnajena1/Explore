package epp.binaryTree.revision;

import epp.binaryTree.BinaryTreeNode;

import java.util.ArrayList;
import java.util.Collections;
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
        BinaryTreeNode<Character> root = new BinaryTreeNode<>('A', null, null);
        System.out.println(root);
        System.out.println(getExteriorOfBinaryTree(root));

    }

    private static <T extends Comparable<T>> List<T> getExteriorOfBinaryTree(BinaryTreeNode<T> root) {
        List<T> result = new ArrayList<>();
        if(root==null){
            return result;
        }
        if(root.isLeafNode()){
            result.add(root.data);
            return result;
        }
        List<T> leftSide = getLeftWall(root);
        List<T> rightSide = getRightWall(root);
        List<T> leafNodes = getLeaves(root);

        if (leftSide.size() > 1) {
            leftSide = leftSide.subList(0, leftSide.size()-1);
        }
        result.addAll(leftSide);
        result.addAll(leafNodes);
        if (rightSide.size() >= 2) {
            rightSide = rightSide.subList(1, rightSide.size()-1);
        }else {
            rightSide = rightSide.subList(1, rightSide.size());
        }
        Collections.reverse(rightSide);
        result.addAll(rightSide);
        return result;
    }

    private static <T extends Comparable<T>> List<T> getLeaves(BinaryTreeNode<T> root) {
        List<T> resultCollector = new ArrayList<>();
        getLeaves(root, resultCollector);
        return resultCollector;
    }

    private static <T extends Comparable<T>> void getLeaves(BinaryTreeNode<T> root, List<T> resultCollector) {
        if (root != null) {
            getLeaves(root.left, resultCollector);
            if (root.isLeafNode()) {
                resultCollector.add(root.data);
            }
            getLeaves(root.right, resultCollector);
        }
    }

    private static <T extends Comparable<T>> List<T> getLeftWall(BinaryTreeNode<T> root) {
        List<T> result = new ArrayList<>();
        while (root != null) {
            result.add(root.data);
            root = root.left;

        }
        return result;
    }

    private static <T extends Comparable<T>> List<T> getRightWall(BinaryTreeNode<T> root) {
        List<T> result = new ArrayList<>();
        while (root != null) {
            result.add(root.data);
            root = root.right;

        }
        return result;
    }
}
