package hackerrank.easy;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class LevelOrder {
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

    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader(System.getenv("INPUT_PATH"));
        Scanner scan = new Scanner(fileReader);
        int t = scan.nextInt();
        Node root = null;
        while(t-- > 0) {
            int data = scan.nextInt();
            root = insert(root, data);
        }
        scan.close();
        fileReader.close();
        levelOrder(root);
    }

    public static void levelOrder(Node root) {

        if (root == null) {
            return;
        }
        Queue<Node> queue = new ArrayDeque<>();
        List<Integer> values = new ArrayList<>();
        queue.offer(root);
        int count = 1;
        while (!queue.isEmpty()) {
            int newCount = 0;
            while (count != 0) {
                Node poll = queue.poll();
                values.add(poll.data );
                count--;
                if (poll.left != null) {
                    queue.offer(poll.left);
                    newCount++;
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                    newCount++;
                }
            }
            count=newCount;
        }
        for(int i=0;i<values.size();i++){
            System.out.print(values.get(i));
            if(i<values.size()-1){
                System.out.print(" ");
            }
        }
    }

    static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }
    }
}
