package epp.honours;

import epp.binaryTree.BinaryTreeNode;
import epp.binarysearchtree.BSTUtils;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.function.Consumer;

public class PreOrderWithoutRecursion {

  public static void main(String[] args) {
    BinaryTreeNode<Integer> root = BSTUtils.buildBST(10, 1, 20);
    System.out.println(root);
    List<Integer> collector = new ArrayList<>();
    getPreOrder(root, integer -> collector.add(integer));
    System.out.println(collector);

    List<Integer> collector2 = new ArrayList<>();
    getPreOrder2(root, integer -> collector2.add(integer));
    System.out.println(collector2);
  }

  /**
   * more generic pattern that works well with pre,post and inorder traversal
   * @param root
   * @param consumer
   */
  public static void getPreOrder(BinaryTreeNode<Integer> root, Consumer<Integer> consumer) {
    if (root == null) {
      return;
    }
    Deque<BinaryTreeNode<Integer>> path = new ArrayDeque<>();
    path.push(root);
    BinaryTreeNode<Integer> previous = null;
    while (!path.isEmpty()) {
      BinaryTreeNode<Integer> current = path.peek();
      //coming from parent
      if (previous == null || previous.left == current || previous.right == current) {
        consumer.accept(current.data);
        if (current.left != null) {
          path.push(current.left);
        } else if (current.right != null) {
          path.push(current.right);
        } else {
          path.pop();
        }
      }
      //coming from left child
      else if (current.left == previous) {
        if (current.right != null) {
          path.push(current.right);
        } else {
          path.pop();
        }
      }
      //coming from right child
      else {
        path.pop();
      }
      previous = current;
    }
  }

  public static void getPreOrder2(BinaryTreeNode<Integer> root, Consumer<Integer> consumer) {
    if (root == null) {
      return;
    }
    Deque<BinaryTreeNode<Integer>> path = new ArrayDeque<>();
    path.push(root);

    while (!path.isEmpty()) {
      BinaryTreeNode<Integer> current = path.pop();
      consumer.accept(current.data);
      if (current.right != null) {
        path.push(current.right);
      }

      if (current.left != null) {
        path.push(current.left);
      }

    }
  }
}
