package epp.binarysearchtree.revision;

import epp.binaryTree.BinaryTreeNode;
import epp.binaryTree.BinaryTreeUtils;
import epp.binarysearchtree.BSTUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

public class CreditsTracker {

    public static void main(String[] args) {
        CreditsTracker tracker = new CreditsTracker();
        tracker.addClient("s1",100);
        tracker.addAll(100);
        tracker.addClient("s2",100);

        System.out.println(tracker.lookUp("s1"));
        System.out.println(tracker.lookUp("s2"));
        System.out.println(tracker.max());
        tracker.addClient("s3",400);
        System.out.println(tracker.max());
        tracker.remove("s3");
        System.out.println(tracker.max());


    }
    int creditsForAll;
    BinaryTreeNode<Client> root;
    Map<String,  Client> map;
    Client max;

    public CreditsTracker() {
        map = new HashMap<String,  Client>();
    }

    /**
     * required O(logn) complexity
     * @param s
     * @param credits
     * @return
     */
    public void addClient(String s,int credits){
        Client data = new Client(s, credits - creditsForAll);
        root=  BSTUtils.insertNode(root, data);
        map.put(s,data);
        BinaryTreeNode<Client> maxNode = BinaryTreeUtils.getMaxNode(root);
        if(maxNode!=null){

            max = maxNode.data;
        }
    }

    /**
     * required O(logn) complexity
     * @param s
     * @return
     */
    public boolean remove(String s){
         Client client = map.get(s);
         if(client==null){
             return false;
         }
         root  = BSTUtils.deleteNode(root,client);
         map.remove(s);
        BinaryTreeNode<Client> maxNode = BinaryTreeUtils.getMaxNode(root);
        if(maxNode!=null){

            max = maxNode.data;
        }
         return true;
    }

    /**
     * required O(logn) complexity
     * @param s
     * @return
     */
    public Integer lookUp(String s){
        Client client = map.get(s);
        if(client!=null){

            return client.credits+creditsForAll;
        }
        return null;
    }

    /**
     * required O(1) complexity
     * @param credits
     */
    public void addAll(int credits){
        creditsForAll+=credits;
    }

    /**
     * O(1) complexity
     *
     * @return
     */
    public Client max(){
          if(max!=null){
              return  new Client(max.name,  max.credits+creditsForAll);
         }
         return null;
    }

    private static class Client implements Comparable<Client>,Cloneable{
        String name;
        int credits;

        public Client(String name, int credits) {
            this.name = name;
            this.credits = credits;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

        @Override
        public int compareTo(Client o) {
            return Integer.compare(credits,o.credits);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Client client = (Client) o;

            if (credits != client.credits) return false;
            return Objects.equals(name, client.name);
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + credits;
            return result;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Client.class.getSimpleName() + "[", "]")
                    .add("name='" + name + "'")
                    .add("credits=" + credits)
                    .toString();
        }
    }
}
