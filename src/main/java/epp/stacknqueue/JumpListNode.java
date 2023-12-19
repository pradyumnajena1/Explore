package epp.stacknqueue;

import epp.list.PostingListNode;

public class JumpListNode extends PostingListNode {
    public int order = -1;

    public JumpListNode(int data) {
        super(data);
    }
}
