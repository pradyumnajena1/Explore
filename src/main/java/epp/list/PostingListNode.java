package epp.list;

public class PostingListNode {
    public int data;
    public PostingListNode next;
    public PostingListNode jump;


    public PostingListNode(int data) {
        this.data = data;
    }

    public PostingListNode(int data, PostingListNode next) {
        this.data = data;
        this.next = next;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PostingListNode[");
        int count = 0;
        StringBuilder nodeString = new StringBuilder();
        PostingListNode current = this;
        while (current != null) {
            nodeString.append("(" + current.data + ", jumpdata=" + (current.jump != null ? current.jump.data : null)+
                      " )" +
                    "->");
            current = current.next;
            count++;
        }
        nodeString.append("NULL");
        sb.append(" size = " + count + " ");
        sb.append(nodeString.toString());
        sb.append("]");
        return sb.toString();
    }
}
