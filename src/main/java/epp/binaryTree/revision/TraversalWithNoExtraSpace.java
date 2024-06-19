package epp.binaryTree.revision;

import epp.binaryTree.BinaryTreeNodeWithParent;
import java.util.ArrayList;
import java.util.List;

public class TraversalWithNoExtraSpace {
  public static void main(String[] args) {
    BinaryTreeNodeWithParent<Integer> root =
        new BinaryTreeNodeWithParent<>(
            8,
            new BinaryTreeNodeWithParent<>(
                5,
                new BinaryTreeNodeWithParent<>(
                    3, new BinaryTreeNodeWithParent<>(0), new BinaryTreeNodeWithParent<>(2)),
                new BinaryTreeNodeWithParent<>(
                    17,
                    null,
                    new BinaryTreeNodeWithParent<>(12, new BinaryTreeNodeWithParent<>(1), null))),
            new BinaryTreeNodeWithParent<>(
                21,
                new BinaryTreeNodeWithParent<>(
                    10,
                    new BinaryTreeNodeWithParent<>(14, null, new BinaryTreeNodeWithParent<>(19)),
                    new BinaryTreeNodeWithParent<>(4)),
                new BinaryTreeNodeWithParent<>(7, null, new BinaryTreeNodeWithParent<>(8))));
    System.out.println(root);
    List<Integer> traversal = inOrderTraversalNoExtraSpace(root);
    System.out.println(traversal);

    traversal = preOrderTraversalNoExtraSpace(root);
    System.out.println(traversal);

    traversal = postOrderTraversalNoExtraSpace(root);
    System.out.println(traversal);
  }

  public static <T extends Comparable<T>> List<T> inOrderTraversalNoExtraSpace(
      BinaryTreeNodeWithParent<T> root) {
    List<T> result = new ArrayList<>();
    BinaryTreeNodeWithParent<T> previous = null;
    BinaryTreeNodeWithParent<T> current = root;
    BinaryTreeNodeWithParent<T> next = null;
    while (current != null) {
      // if coming down from parent
      if (current.parent == previous) {

        if (current.left != null) {
          next = current.left;
        } else {
          result.add(current.data);
          next = current.right != null ? current.right : current.parent;
        }

      } // if coming up from left
      else if (current.left == previous) {
        result.add(current.data);
        next = current.right != null ? current.right : current.parent;
      } // if coming up from right
      else {
        next = current.parent;
      }
      previous = current;
      current = next;
    }

    return result;
  }

  public static <T extends Comparable<T>> List<T> preOrderTraversalNoExtraSpace(
      BinaryTreeNodeWithParent<T> root) {
    List<T> result = new ArrayList<>();
    BinaryTreeNodeWithParent<T> previous = null;
    BinaryTreeNodeWithParent<T> current = root;
    BinaryTreeNodeWithParent<T> next = null;
    while (current != null) {
      // if coming down from parent
      if (current.parent == previous) {
        result.add(current.data);
        if (current.left != null) {
          next = current.left;
        } else {
          next = current.right != null ? current.right : current.parent;
        }

      } // if coming up from left
      else if (current.left == previous) {
        next = current.right != null ? current.right : current.parent;
      } // if coming up from right
      else {
        next = current.parent;
      }
      previous = current;
      current = next;
    }

    return result;
  }

  public static <T extends Comparable<T>> List<T> postOrderTraversalNoExtraSpace(
      BinaryTreeNodeWithParent<T> root) {
    List<T> result = new ArrayList<>();
    BinaryTreeNodeWithParent<T> previous = null;
    BinaryTreeNodeWithParent<T> current = root;
    BinaryTreeNodeWithParent<T> next = null;
    while (current != null) {
      // if coming down from parent
      if (current.parent == previous) {

        if (current.left != null) {
          next = current.left;
        } else {
          if (current.right != null) {
            next = current.right;
          } else {
            result.add(current.data);
            next = current.parent;
          }
        }

      } // if coming up from left
      else if (current.left == previous) {
        if (current.right != null) {
          next = current.right;
        } else {
          result.add(current.data);
          next = current.parent;
        }
      } // if coming up from right
      else {
        result.add(current.data);
        next = current.parent;
      }
      previous = current;
      current = next;
    }

    return result;
  }
}
