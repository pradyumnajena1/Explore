package epp.honours;

import epp.binaryTree.BinaryTreeNode;
import epp.binarysearchtree.BSTUtils;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.function.Consumer;

public class InOrderTraversalWithoutRecursion {
  public static void main(String[] args) {
    BinaryTreeNode<Integer> root = BSTUtils.buildBST(10, 1, 20);
    System.out.println(root);
    List<Integer> collector = new ArrayList<>();
    getInOrder(
        root,
        new Consumer<Integer>() {
          @Override
          public void accept(Integer integer) {
            collector.add(integer);
          }
        });
    System.out.println(collector);
  }

  private static void getInOrder(BinaryTreeNode<Integer> root, Consumer<Integer> consumer) {
    if (root == null) {
      return;
    }
    Deque<BinaryTreeNode<Integer>> path = new ArrayDeque<>();
    path.add(root);
    BinaryTreeNode<Integer> previous = null;
    while (!path.isEmpty()) {
      BinaryTreeNode<Integer> current = path.peek();

      if (previous == null || previous.left == current || previous.right == current) {
        if (current.left != null) {
          path.push(current.left);
        }else if(current.right !=null){
          consumer.accept(current.data);
          path.push(current.right);
        }else{
          consumer.accept(current.data);
          path.pop();
        }
      } else if (current.left == previous) {
        consumer.accept(current.data);
        if (current.right != null) {
          path.push(current.right);
        }else {
          path.pop();
        }
      } else {
        path.pop();
      }
      previous = current;
    }
  }
}
