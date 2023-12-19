package leetcode.hard;

import java.util.*;

public class VerticalOrderTraversalOfABinaryTree {
    public static void main(String[] args) {
           Solution solution = new Solution();
        TreeNode root = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15),
                new TreeNode(7)));
        System.out.println(solution.verticalTraversal(root));

        root = new TreeNode(1,new TreeNode(2,new TreeNode(4),new TreeNode(5)),new TreeNode(3,new TreeNode(6),
                new TreeNode(7)));
        System.out.println(solution.verticalTraversal(root));

        root = new TreeNode(3,new TreeNode(1,new TreeNode(0),new TreeNode(2)),new TreeNode(4,new TreeNode(2),null));
        System.out.println(solution.verticalTraversal(root));
    }


       private static class TreeNode {
           int val;
           TreeNode left;
           TreeNode right;
           TreeNode() {}
          TreeNode(int val) { this.val = val; }
           TreeNode(int val, TreeNode left, TreeNode right) {
               this.val = val;
               this.left = left;
              this.right = right;
           }
       }

    private static class Solution {
        public List<List<Integer>> verticalTraversal(TreeNode root) {
            List<List<Integer>> result = new ArrayList<>();
            TreeMap<Integer, List<NodeValue>> collector = new TreeMap<>();
             inorderTraverse(root,0,0,collector);
             for(List<NodeValue> value:collector.values()){
                 value.sort(NodeValue::compareTo);
                 ArrayList<Integer> list = new ArrayList<>();
                 result.add(list);
                 for(NodeValue nodeValue:value){
                     list.add(nodeValue.value);
                 }

             }
             return result;
        }

        private void inorderTraverse(TreeNode root, int row, int col, TreeMap<Integer, List<NodeValue>> collector) {
            if(root==null){
                return;
            }
            inorderTraverse(root.left,row+1,col-1,collector);
            List<NodeValue> treeSet = collector.getOrDefault(col, new ArrayList<>());
            treeSet.add( new NodeValue( root.val,row));
            collector.put(col,treeSet);
            inorderTraverse(root.right,row+1,col+1,collector);
        }
        private static class NodeValue implements Comparable<NodeValue>{
            private static final Comparator<NodeValue> nodeValueComparator =
                    Comparator.<NodeValue>comparingInt(x -> x.row).thenComparing(x -> x.value);
            int value;
            int row;

            public NodeValue(int value, int row) {
                this.value = value;
                this.row = row;
            }

            @Override
            public int compareTo(NodeValue o) {
                return nodeValueComparator.compare(this,o);
            }
        }
    }

}
