package hackerrank.medium;

public class CheckBST {

    boolean checkBST(Node root) {

          return  checkBST(root,Integer.MIN_VALUE,Integer.MAX_VALUE);
    }

    private boolean checkBST(Node root, int minValue, int maxValue) {
        if(root==null){
            return true;
        }
        if(!(root.data>=minValue && root.data<=maxValue)){
            return false;
        }
        return checkBST(root.left,minValue, root.data-1)&&checkBST(root.right, root.data+1,maxValue );
    }

    static  class Node {
        int data;
        Node left;
        Node right;
    }
}
