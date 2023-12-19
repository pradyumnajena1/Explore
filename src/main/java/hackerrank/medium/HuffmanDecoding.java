package hackerrank.medium;

public class HuffmanDecoding {
    public static void main(String[] args) {

    }

    void decode(String s, Node root) {
        String result = "";
        int start = 0;
        while (start<s.length()){
            Node current = root;
            while (current.data==0){
                if(s.charAt(start)=='0'){
                    current = current.left;
                }else{
                    current = current.right;
                }
                start++;
            }
            result = result+ current.data;


        }

        System.out.println(result);

    }

   static  class Node {
       public int frequency; // the frequency of this tree
       public char data;
       public Node left, right;
   }

}
