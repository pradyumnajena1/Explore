package epp.stacknqueue.revision;

import epp.binaryTree.BinaryTreeNode;
import epp.binarysearchtree.BSTUtils;

import java.util.*;

public class PrintBSTLevelOrder {
  public static void main(String[] args) {
    BinaryTreeNode<Integer> root = BSTUtils.buildBST(10, 1, 10);
    System.out.println(root);
    printBinaryTreeLevelOrder(root);
    printBinaryTreeLevelOrderAlternative(root);
    System.out.println(getBinaryTreeNodesLevelAverage(root));
    printBinaryTreeLevelOrder2(root);
  }

  private static List<Integer> getBinaryTreeNodesLevelAverage(
          BinaryTreeNode<Integer> root) {
    List<Integer> result = new ArrayList<>();
    if (root == null) {
      return result;
    }
    Queue<BinaryTreeNode<Integer>> current = new ArrayDeque<>();
    current.offer(root);

    while (!current.isEmpty()) {
      Queue<BinaryTreeNode<Integer>> nextLevel = new ArrayDeque<>();

      int sum = 0;
      int count = 0;
      while (!current.isEmpty()) {
        BinaryTreeNode<Integer> poll = current.poll();
         sum+=poll.data;
         count++;
        if (poll.left != null) nextLevel.offer(poll.left);
        if (poll.right != null) nextLevel.offer(poll.right);
      }
      result.add(sum/count);
      current = nextLevel;

    }

    return result;
  }



  private static List<List<BinaryTreeNode<Integer>>> getBinaryTreeNodesLevelOrderAlternative(
          BinaryTreeNode<Integer> root) {
    List<List<BinaryTreeNode<Integer>>> result = new ArrayList<>();
    if (root == null) {
      return result;
    }
    Queue<BinaryTreeNode<Integer>> current = new ArrayDeque<>();
    current.offer(root);
    int turn = 0;
    while (!current.isEmpty()) {
      Queue<BinaryTreeNode<Integer>> nextLevel = new ArrayDeque<>();
      LinkedList<BinaryTreeNode<Integer>> thisLevel = new LinkedList<>();
      while (!current.isEmpty()) {

        BinaryTreeNode<Integer> poll = current.poll();
        if(turn==0){
          thisLevel.addLast(poll);
        }else{
          thisLevel.addFirst(poll);
        }
        if (poll.left != null) nextLevel.offer(poll.left);
        if (poll.right != null) nextLevel.offer(poll.right);
      }
      if (!thisLevel.isEmpty()) {
        result.add(thisLevel);
      }
      current = nextLevel;
      turn^=1;
    }

    return result;
  }


  private static List<List<BinaryTreeNode<Integer>>> getBinaryTreeNodesLevelOrder(
      BinaryTreeNode<Integer> root) {
    List<List<BinaryTreeNode<Integer>>> result = new ArrayList<>();
    if (root == null) {
      return result;
    }
    Queue<BinaryTreeNode<Integer>> current = new ArrayDeque<>();
    current.offer(root);
    while (!current.isEmpty()) {
      Queue<BinaryTreeNode<Integer>> nextLevel = new ArrayDeque<>();
      List<BinaryTreeNode<Integer>> thisLevel = new ArrayList<>();
      while (!current.isEmpty()) {
        BinaryTreeNode<Integer> poll = current.poll();
        thisLevel.add(poll);
        if (poll.left != null) nextLevel.offer(poll.left);
        if (poll.right != null) nextLevel.offer(poll.right);
      }
      if (!thisLevel.isEmpty()) {
        result.add(thisLevel);
      }
      current = nextLevel;
    }

    return result;
  }

  private static void printBinaryTreeLevelOrder(BinaryTreeNode<Integer> root) {
    List<List<BinaryTreeNode<Integer>>> binaryTreeNodesLevelOrder =
        getBinaryTreeNodesLevelOrder(root);
    for (List<BinaryTreeNode<Integer>> level : binaryTreeNodesLevelOrder) {
      level.stream().map(x -> x.data + ",").forEach(System.out::print);
      System.out.println();
    }
  }

  private static void printBinaryTreeLevelOrderAlternative(BinaryTreeNode<Integer> root) {
    List<List<BinaryTreeNode<Integer>>> binaryTreeNodesLevelOrder =
            getBinaryTreeNodesLevelOrderAlternative(root);
    for (List<BinaryTreeNode<Integer>> level : binaryTreeNodesLevelOrder) {
      level.stream().map(x -> x.data + ",").forEach(System.out::print);
      System.out.println();
    }
  }

  /**
   * using only one queue
   *
   * @param root
   */
  private static void printBinaryTreeLevelOrder2(BinaryTreeNode<Integer> root) {
    if (root == null) {
      return;
    }
    Queue<BinaryTreeNode<Integer>> queue = new ArrayDeque<>();
    queue.offer(root);
    int count = queue.size();
    while (!queue.isEmpty()) {
      BinaryTreeNode<Integer> poll = queue.poll();
      count--;
      if (poll.left != null) {
        queue.offer(poll.left);
      }
      if (poll.right != null) {
        queue.offer(poll.right);
      }
      System.out.print(poll.data + ",");
      if (count == 0) {
        count = queue.size();
        System.out.println();
      }
    }
  }
}
