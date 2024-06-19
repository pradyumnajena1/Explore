package epp.binaryTree.revision;

import epp.binaryTree.BinaryTreeNode;
import java.util.*;

public class TreeTraversalWithoutRecursion {
  public static void main(String[] args) {
    BinaryTreeNode<Integer> a = new BinaryTreeNode<>(5);
    BinaryTreeNode<Integer> b = new BinaryTreeNode<>(8, null, null);
    BinaryTreeNode<Integer> root =
        new BinaryTreeNode<>(
            4,
            new BinaryTreeNode<>(2, new BinaryTreeNode<>(1), new BinaryTreeNode<>(3)),
            new BinaryTreeNode<>(6, a, new BinaryTreeNode<>(7, new BinaryTreeNode<>(9), b)));
    System.out.println(root);

    List<BinaryTreeNode<Integer>> result = inorderTraverseWithoutRec(root);
    result.stream().map(x -> x.data + ", ").forEach(System.out::print);
    System.out.println();
    result = preorderTraverseWithoutRec(root);
    result.stream().map(x -> x.data + ", ").forEach(System.out::print);
    System.out.println();
    result = postorderTraverseWithoutRec(root);
    result.stream().map(x -> x.data + ", ").forEach(System.out::print);
    System.out.println();

    result = postorderTraverseWithoutRec2(root);
    result.stream().map(x -> x.data + ", ").forEach(System.out::print);
    System.out.println();
  }

  public static List<BinaryTreeNode<Integer>> inorderTraverseWithoutRec(
      BinaryTreeNode<Integer> root) {

    List<BinaryTreeNode<Integer>> result = new ArrayList<>();
    BinaryTreeNode<Integer> current = root;
    Deque<BinaryTreeNode<Integer>> stack = new ArrayDeque<>();
    while (!stack.isEmpty() || current != null) {
      if (current != null) {
        stack.push(current);
        current = current.left;
      } else {
        current = stack.pop();
        result.add(current);
        current = current.right;
      }
    }
    return result;
  }

  public static List<BinaryTreeNode<Integer>> preorderTraverseWithoutRec(
      BinaryTreeNode<Integer> root) {
    List<BinaryTreeNode<Integer>> result = new ArrayList<>();
    Deque<BinaryTreeNode<Integer>> stack = new ArrayDeque<>();
    if (root == null) {
      return result;
    }
    stack.push(root);
    while (!stack.isEmpty()) {
      BinaryTreeNode<Integer> current = stack.pop();

      result.add(current);
      if (current.right != null) {

        stack.push(current.right);
      }
      if (current.left != null) {

        stack.push(current.left);
      }
    }
    return result;
  }

  public static List<BinaryTreeNode<Integer>> postorderTraverseWithoutRec(
      BinaryTreeNode<Integer> root) {

    List<BinaryTreeNode<Integer>> result = new ArrayList<>();
    Deque<BinaryTreeNode<Integer>> stack = new ArrayDeque<>();
    if (root == null) {
      return result;
    }
    stack.push(root);
    while (!stack.isEmpty()) {
      BinaryTreeNode<Integer> current = stack.pop();
      result.add(current);
      if (current.left != null) {
        stack.push(current.left);
      }

      if (current.right != null) {
        stack.push(current.right);
      }
    }
    Collections.reverse(result);
    return result;
  }

  public static List<BinaryTreeNode<Integer>> postorderTraverseWithoutRec2(
          BinaryTreeNode<Integer> root) {

    List<BinaryTreeNode<Integer>> result = new ArrayList<>();
    Deque<BinaryTreeNode<Integer>> stack1 = new ArrayDeque<>();
    Deque<BinaryTreeNode<Integer>> stack2 = new ArrayDeque<>();
    if (root == null) {
      return result;
    }
    stack1.push(root);
    while (!stack1.isEmpty()) {
      BinaryTreeNode<Integer> current = stack1.pop();
      stack2.push(current);
      if (current.left != null) {
        stack1.push(current.left);
      }

      if (current.right != null) {
        stack1.push(current.right);
      }
    }
    while (!stack2.isEmpty()){
      result.add(stack2.pop());
    }

    return result;
  }
}
