package epp.list.revision;

import epp.list.PostingListNode;

public class CopyPostingList {
    public static void main(String[] args) {
        PostingListNode n1 = getPostingList();
        System.out.println(n1);
        PostingListNode n2 = copyPostingList(n1);
        System.out.println(n2);
    }

    private static PostingListNode copyPostingList(PostingListNode n1) {
        PostingListNode temp = n1;
        PostingListNode resultHead = null;
        PostingListNode resultTail = null;
        while (temp!=null){
            PostingListNode newNode = new PostingListNode(temp.data);
            if(resultHead==null){
                resultHead=resultTail = newNode;
            }else{
                resultTail.next = newNode;
                resultTail = newNode;
            }
            temp = temp.next;
        }
        System.out.println(resultHead);

        PostingListNode current = n1;
        PostingListNode current2 = resultHead;

        while (current!=null){
            PostingListNode next = current.next;
            PostingListNode next2 = current2.next;

            current.next = current2;
            current2.next=next;



            current=next;
            current2=next2;
        }

        System.out.println(n1);
          current = n1;
          current2 = n1.next;
          while (current!=null){
              PostingListNode next = current2.next;
              current2.jump = current.jump.next;

              current = next;
              if(next!=null){

                  current2 = next.next;
              }
          }
        System.out.println(n1);
          current = n1;
        PostingListNode rh = null;
        PostingListNode rt = null;
          while (current!=null){

              PostingListNode next = current.next.next;
                temp = current.next;
              current.next=temp.next;

              temp.next = null;
                if(rh==null){
                    rh=rt=temp;
                }else{
                    rt.next=temp;
                    rt=temp;
                }



              current = next;
          }
        return rh;
    }

    private static PostingListNode getPostingList() {
        PostingListNode n1 = new PostingListNode(1);
        PostingListNode n2 = new PostingListNode(2);
        PostingListNode n3 = new PostingListNode(3);
        PostingListNode n4 = new PostingListNode(4);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = null;

        n1.jump = n3;
        n2.jump = n4;
        n3.jump = n2;
        n4.jump = n4;
        return n1;
    }
}
