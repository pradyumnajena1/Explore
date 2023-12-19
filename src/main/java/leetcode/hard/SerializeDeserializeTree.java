package leetcode.hard;

import epp.binaryTree.BinaryTreeNode;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class SerializeDeserializeTree {
    public static void main(String[] args) {
      Codec codec = new Codec();
        String serialize = codec.serialize(new BinaryTreeNode<Integer>(1, new BinaryTreeNode<Integer>(2), new BinaryTreeNode<Integer>(3)));
        System.out.println(serialize);
        BinaryTreeNode<Integer> deserialize = codec.deserialize("1,2,#,#,3,#,#");
        System.out.println(deserialize);
    }


    private static class Codec{
        // Encodes a tree to a single string.
        public String serialize(BinaryTreeNode<Integer> root) {
            if (root == null) {
                return null;
            }
            Stack<BinaryTreeNode<Integer>> s = new Stack<>();
            s.push(root);

            List<String> l = new ArrayList<>();
            while (!s.isEmpty()) {
                BinaryTreeNode<Integer> t = s.pop();

                // If current node is NULL, store marker
                if (t == null) {
                    l.add("#");
                }
                else {

                    // Else, store current node
                    // and recur for its children
                    l.add("" + t.data);
                    s.push(t.right);
                    s.push(t.left);
                }
            }
            return String.join(",", l);
        }




        // Decodes your encoded data to tree.
        public BinaryTreeNode<Integer> deserialize(String data) {
            if (data == null)
                return null;

            String[] arr = data.split(",");
            return helper(arr,new AtomicInteger(0));
        }

        public static BinaryTreeNode<Integer> helper(String[] arr, AtomicInteger t)
        {
            if (arr[t.get()].equals("#"))
                return null;

            // Create node with this item
            // and recur for children
            BinaryTreeNode<Integer> root
                    = new BinaryTreeNode<Integer>(Integer.parseInt(arr[t.get()]));
            t.incrementAndGet();
            root.left = helper(arr,t);
            t.incrementAndGet();
            root.right = helper(arr,t);
            return root;
        }
    }
}
