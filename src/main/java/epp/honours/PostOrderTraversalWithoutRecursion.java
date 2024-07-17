package epp.honours;

import epp.binaryTree.BinaryTreeNode;
import epp.binarysearchtree.BSTUtils;
import java.util.*;
import java.util.function.Consumer;

public class PostOrderTraversalWithoutRecursion {

  public static void main(String[] args) {
    BinaryTreeNode<Integer> root = BSTUtils.buildBST(10, 1, 20);
    System.out.println(root);
    List<Integer> postOrder = getPostOrder(root);
    System.out.println(postOrder);
    List<Integer> collector  = new ArrayList<>();
    processPostOrder(root,node -> {
      collector.add(node.data);
    });
    System.out.println(collector);
  }

  /**
   * time complexity O(n) space complexity O(n) . This is fine if we need to return the result as an
   * array or list . But if we need to just process the nodes as they appear in the order we can do
   * better , using only O(h) space complexity as in typical recursive algorithm
   *
   * @param root
   * @return
   */
  public static List<Integer> getPostOrder(BinaryTreeNode<Integer> root) {
    List<Integer> invertedPreOrder = getInvertedPreOrder(root);
    Collections.reverse(invertedPreOrder);
    return invertedPreOrder;
  }

  private static List<Integer> getInvertedPreOrder(BinaryTreeNode<Integer> root) {
    Deque<BinaryTreeNode<Integer>> stack = new ArrayDeque<>();
    List<Integer> result = new ArrayList<>();
    stack.push(root);
    while (!stack.isEmpty()) {
      BinaryTreeNode<Integer> polled = stack.poll();
      result.add(polled.data);
      if (polled.left != null) {

        stack.push(polled.left);
      }
      if (polled.right != null) {

        stack.push(polled.right);
      }
    }
    return result;
  }

  /**
   * time complexity O(n) space complexity O(h) . Use this to process nodes in the postorder order
   * as a stream
   *
   * @param root
   * @return
   */
  public static void processPostOrder(
      BinaryTreeNode<Integer> root, Consumer<BinaryTreeNode<Integer>> consumer) {
    if (root == null) {
      return;
    }
    Deque<BinaryTreeNode<Integer>> path = new ArrayDeque<>();
    BinaryTreeNode<Integer> previous = null;
    path.push(root);
    while (!path.isEmpty()) {
      BinaryTreeNode<Integer> current = path.peek();
      // coming from up
      if (previous == null || previous.left == current || previous.right == current) {
        if (current.left != null) {
          path.push(current.left);
        } else if (current.right != null) {
          path.push(current.right);
        } else {
          path.pop();
          consumer.accept(current);
        }
      }
      // coming from down
      else if (current.left == previous) {
        if (current.right != null) {
          path.push(current.right);
        } else {
          path.pop();
          consumer.accept(current);
        }
      } else if (current.right == previous) {
        path.pop();
        consumer.accept(current);
      }
      previous = current;
    }
  }
}
