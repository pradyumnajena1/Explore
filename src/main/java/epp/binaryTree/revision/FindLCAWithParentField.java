package epp.binaryTree.revision;

import epp.binaryTree.BinaryTreeNodeWithParent;

public class FindLCAWithParentField {
  public static void main(String[] args) {
    BinaryTreeNodeWithParent<Integer> a = new BinaryTreeNodeWithParent<>(5);
    BinaryTreeNodeWithParent<Integer> b = new BinaryTreeNodeWithParent<>(8, null, null);
    BinaryTreeNodeWithParent<Integer> root =
        new BinaryTreeNodeWithParent<>(
            4,
            new BinaryTreeNodeWithParent<>(
                2, new BinaryTreeNodeWithParent<>(1), new BinaryTreeNodeWithParent<>(3)),
            new BinaryTreeNodeWithParent<>(
                6, a, new BinaryTreeNodeWithParent<>(7, new BinaryTreeNodeWithParent<>(9), b)));
    System.out.println(root);
    BinaryTreeNodeWithParent<Integer> lca = findLCA(root, a, b);
    System.out.println(lca);
  }

  private static BinaryTreeNodeWithParent<Integer> findLCA(
      BinaryTreeNodeWithParent<Integer> root,
      BinaryTreeNodeWithParent<Integer> a,
      BinaryTreeNodeWithParent<Integer> b) {
    if (root == null) {
      return null;
    }
    int depthA = getDepth(a);
    int depthB = getDepth(b);
    if (depthA > depthB) {
      BinaryTreeNodeWithParent<Integer> temp = a;
      a = b;
      b = temp;
    }
    int diff = Math.abs(depthA - depthB);
    while (diff-- > 0) {
      b = b.parent;
    }
    while (a != b) {
      a = a.parent;
      b = b.parent;
    }
    return a;
  }

  private static int getDepth(BinaryTreeNodeWithParent<Integer> a) {
    int depth = 0;
    while (a.parent != null) {
      a = a.parent;
      depth++;
    }
    return depth;
  }
}
