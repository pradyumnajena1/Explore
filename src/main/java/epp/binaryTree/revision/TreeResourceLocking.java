package epp.binaryTree.revision;

import epp.binaryTree.BinaryTreeNode;
import epp.binaryTree.BinaryTreeNodeWithParent;

public class TreeResourceLocking<T extends Comparable<T>> {
    public static void main(String[] args) {
        BinaryTreeNodeWithParent<Resource<Integer>> node4 = new BinaryTreeNodeWithParent<>(new Resource<>(4));
        BinaryTreeNodeWithParent<Resource<Integer>> node5 =
                new BinaryTreeNodeWithParent<>(new Resource<>(5),
                    new BinaryTreeNodeWithParent<>(new Resource<>(6)),
                    new BinaryTreeNodeWithParent<>(new Resource<>(7)));

        BinaryTreeNodeWithParent<Resource<Integer>> root =
                new BinaryTreeNodeWithParent<>(new Resource<>(1),
                        new BinaryTreeNodeWithParent<>(new Resource<>(2), new BinaryTreeNodeWithParent<>(new Resource<>(3)), node4),
                        node5);
        TreeResourceLocking<Integer> resourceLocking =
                new TreeResourceLocking<>(root);

        System.out.println(resourceLocking.lock(node4));
        System.out.println(resourceLocking.lock(root));
    }

    public static class Resource<T extends Comparable<T>> implements Comparable<Resource<T>> {
        T data;
        boolean locked;
        int lockedCount;

        public Resource(T data) {
            this.data = data;
        }

        @Override
        public int compareTo(Resource<T> o) {
            return this.data.compareTo(o.data);
        }
    }

    private BinaryTreeNodeWithParent<Resource<T>> resource;

    public TreeResourceLocking(BinaryTreeNodeWithParent<Resource<T>> resource) {
        this.resource = resource;
    }

    public boolean isLocked(BinaryTreeNodeWithParent<Resource<T>> node) {
        return node.data.locked;
    }

    public boolean lock(BinaryTreeNodeWithParent<Resource<T>> node) {
        if (node.data.locked || node.data.lockedCount > 0) {
            return false;
        }

        BinaryTreeNodeWithParent<Resource<T>> current = node.parent;
        while (current != null) {
            if (current.data.locked) {
                return false;
            }
            current=current.parent;
        }

        node.data.locked = true;
        current = node.parent;
        while (current != null) {
            current.data.lockedCount++;
            current = current.parent;
        }
        return true;
    }

    public void unlock(BinaryTreeNodeWithParent<Resource<T>> node) {
        if (node.data.locked) {
            node.data.locked = false;
            BinaryTreeNodeWithParent<Resource<T>> current = node.parent;
            while (current != null) {
                node.data.lockedCount--;
                node = node.parent;
            }
        }

    }

}

