package amaze;

import java.util.ArrayList;
import java.util.List;

class LinkedListNode {
    int data;
      LinkedListNode next;

    public LinkedListNode(int data) {
        this.data = data;
    }

    public LinkedListNode(int data, LinkedListNode next) {
        this.data = data;
        this.next = next;
    }
    public static List<Integer> getItems(LinkedListNode head){
        List<Integer> values = new ArrayList<>();
        while (head!=null){
            values.add(head.data);
            head=head.next;
        }
        return values;
    }
}
