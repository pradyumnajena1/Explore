package epp.binaryTree;

import java.util.*;

public class LowestCommonAncestor {
    public static void main(String[] args) {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(4,
                new BinaryTreeNode<>(2,new BinaryTreeNode<>(1),new BinaryTreeNode<>(3)),
                new BinaryTreeNode<>(6,new BinaryTreeNode<>(5),new BinaryTreeNode<>(7,
                        null,new BinaryTreeNode<>(8,null,new BinaryTreeNode<>(9))))
        );
        System.out.println(root);
        BinaryTreeNode<Integer> lca = findLCA(root,5,9);
        System.out.println(lca);

    }

    private static BinaryTreeNode<Integer> findLCA(BinaryTreeNode<Integer> root, int i, int j) {
        List<BinaryTreeNode<Integer>> path1 = find(root,i);
        List<BinaryTreeNode<Integer>> path2 = find(root,j);
        printPath(path1);
        printPath(path2);
        return getLCA(path1,path2);
    }

    private static void printPath(List<BinaryTreeNode<Integer>> path1) {
        for(int i=0;i<path1.size();i++){

            BinaryTreeNode<Integer> node = path1.get(i);
            System.out.print( (i==0?"":" ,")+( node==null?"null": node.data));
        }
        System.out.println();
    }

    private static List<BinaryTreeNode<Integer>> find(BinaryTreeNode<Integer> root, int value) {
        Queue<BinaryTreeNode<Integer>> queue= new ArrayDeque<>();
        Map<BinaryTreeNode<Integer>, BinaryTreeNode<Integer>> parentMap = new HashMap<>();
        queue.offer(root);
        parentMap.put(root,null);
        while (!queue.isEmpty()){

            BinaryTreeNode<Integer> poll = queue.poll();
            if(poll.data==value){
                break;
            }
            if(poll.left!=null){
                parentMap.put(poll.left,poll);
                queue.offer(poll.left);
            }
            if(poll.right!=null){
                parentMap.put(poll.right,poll);
                queue.offer(poll.right);
            }
        }
        BinaryTreeNode<Integer> current = new BinaryTreeNode<>(value);
        List<BinaryTreeNode<Integer>> path = new LinkedList<>();

        while (current!=null){
            path.add(0,current);
            current = parentMap.get(current);
        }
        return path;
    }

    private static BinaryTreeNode<Integer> getLCA(List<BinaryTreeNode<Integer>> path1, List<BinaryTreeNode<Integer>> path2) {
        BinaryTreeNode<Integer> lca = null;
        for(int i=0;i<Math.min(path1.size(),path2.size());i++){
            BinaryTreeNode<Integer> node1 = path1.remove(0);
            BinaryTreeNode<Integer> node2 = path2.remove(0);
            if(node1 == node2){
                lca = node1;
            }
        }
        return lca;
    }
}
