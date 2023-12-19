package hackerrank.medium;

import java.util.Scanner;

public class LowestCommonAncestor {


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        Node root = null;
        while(t-- > 0) {
            int data = scan.nextInt();
            root = insert(root, data);
        }
        int v1 = scan.nextInt();
        int v2 = scan.nextInt();
        scan.close();
        Node ans = lca(root,v1,v2);
        System.out.println(ans.data);
    }

    public static Node insert(Node root, int data) {
        if(root == null) {
            return new Node(data);
        } else {
            Node cur;
            if(data <= root.data) {
                cur = insert(root.left, data);
                root.left = cur;
            } else {
                cur = insert(root.right, data);
                root.right = cur;
            }
            return root;
        }
    }

    /**
     * Binary search tree node
     * @param root
     * @param v1
     * @param v2
     * @return
     */
    public static Node lca(Node root, int v1, int v2) {
        // Write your code here.
        Node lca = null;
        Node current =root;
        while (current!=null){
            if(current.data==v1||current.data==v2){
                lca = current;
                break;
            } else if(v1<=current.data && v2<= current.data){
                current = current.left;
            }else if(v1>current.data && v2 > current.data){
                current = current.right;
            }else {
                lca= current;
                break;
            }
        }
        return lca;
    }
    static class Node{
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }
    }

}
