package epp.binaryTree.revision;

import epp.binaryTree.BinaryTreeNode;
import epp.binaryTree.BinaryTreeUtils;
import epp.binarysearchtree.BSTUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ReconstructTreeWithMarkers {
  public static void main(String[] args) {
    List<Character> values =
        new ArrayList<Character>(
            Arrays.asList(
                'H', 'B', 'F', null, null, 'E', 'A', null, null, null, 'C', null, 'D', null, 'G',
                'I', null, null, null));

    BinaryTreeNode<Character> root = reconstructTreeFromPreOrder(values);
    System.out.println(root);
    values = BinaryTreeUtils.postOrderTraversalWithNull(root);
    System.out.println(values);
    root = reconstructTreeFromPostOrder(values);
    System.out.println(root);
  }

  public static <T extends Comparable<T>> BinaryTreeNode<T> reconstructTreeFromPreOrder(List<T> values) {
    AtomicInteger index = new AtomicInteger(0);
    return reconstructTreeFromPreOrder(values, index);
  }

  public static <T extends Comparable<T>> BinaryTreeNode<T> reconstructTreeFromPostOrder(List<T> values) {
    AtomicInteger index = new AtomicInteger(values.size()-1);
    return reconstructTreeFromPostOrder(values, index);
  }

  private static <T extends Comparable<T>> BinaryTreeNode<T> reconstructTreeFromPostOrder(List<T> values,
                                                                                                  AtomicInteger index) {
    T c = values.get(index.getAndDecrement());
    if (c == null) {
      return null;
    }

    BinaryTreeNode<T> rightChild = reconstructTreeFromPostOrder(values, index);
    BinaryTreeNode<T> leftChild = reconstructTreeFromPostOrder(values, index);
    return new BinaryTreeNode(
            c, leftChild, rightChild);
  }

  private static <T extends Comparable<T>> BinaryTreeNode<T> reconstructTreeFromPreOrder(
      List<T> values, AtomicInteger index) {
    T c = values.get(index.getAndIncrement());
    if (c == null) {
      return null;
    }

    return new BinaryTreeNode(
        c, reconstructTreeFromPreOrder(values, index), reconstructTreeFromPreOrder(values, index));
  }
}
