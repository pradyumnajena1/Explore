package hackerrank.easy;

import epp.Pair;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class TopView {
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
        topView(root);
    }

    public static void topView(Node root) {

        TreeMap<Integer, Integer> map = new TreeMap<>();
        topView(root, map );
        System.out.println(map);
        ArrayList<Integer> integers = new ArrayList<>(map.values());
        for(int i=0;i<integers.size();i++){
            System.out.print(integers.get(i));
            if(i<integers.size()-1){
                System.out.print(" ");
            }
        }

    }

    private static void topView(Node root, TreeMap<Integer, Integer> map ) {
        if(root==null){
            return;
        }
        Queue<Pair<Node,Integer>> queue = new ArrayDeque<>();
        queue.offer(new Pair<>(root,0));
        while (!queue.isEmpty()){
            Pair<Node, Integer> polled = queue.poll();
            if(!map.containsKey(polled.getSecond())){

                map.put(polled.getSecond(), polled.getFirst().data);
            }
            if(polled.getFirst().left!=null){
                queue.offer(new Pair<>(polled.getFirst().left, polled.getSecond()-1));
            }
            if(polled.getFirst().right!=null){
                queue.offer(new Pair<>(polled.getFirst().right, polled.getSecond()+1));
            }

        }


    }

    static  class Node {
       int data;
       Node left;
       Node right;

        public Node(int data) {
            this.data = data;
        }
    }
}
