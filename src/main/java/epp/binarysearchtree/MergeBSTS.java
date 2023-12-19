package epp.binarysearchtree;

import epp.binaryTree.BinaryTreeNode;

public class MergeBSTS {
    public static void main(String[] args) {
        BinaryTreeNode<Integer> root1 =BSTUtils.buildBST(10,1,20);
        BinaryTreeNode<Integer> root2 =BSTUtils.buildBST(10,1,20);
        System.out.println(root1);
        System.out.println(root2);
        BinaryTreeNode<Integer> root =  mergeBST(root1,root2);

        System.out.println(root);
    }

    private static BinaryTreeNode<Integer> mergeBST(BinaryTreeNode<Integer> root1, BinaryTreeNode<Integer> root2) {
        BinaryTreeNode<Integer> head1 = BSTUtils.convertToDoubleLinkedList(root1);
        printList(head1);
        BinaryTreeNode<Integer> head2 = BSTUtils.convertToDoubleLinkedList(root2);
        printList(head2);
        BinaryTreeNode<Integer>[] mergedList   = merge(head1, head2);
        printList(mergedList[0]);
        BinaryTreeNode<Integer> root =  convertListToBST(mergedList[0],mergedList[1]);
        return root;
    }

    private static BinaryTreeNode<Integer> convertListToBST(BinaryTreeNode<Integer> head,
                                                            BinaryTreeNode<Integer> tail) {
        int length = getLength(head);
        BinaryTreeNode<Integer>[] headHolder = new BinaryTreeNode[1];
        headHolder[0] = head;
        BinaryTreeNode<Integer> root =convertListToBST(head,0,length-1,headHolder);

        return root;
    }

    private static BinaryTreeNode<Integer> convertListToBST(BinaryTreeNode<Integer> head, int start, int end, BinaryTreeNode<Integer>[] headHolder) {
        if(start>end){
            return null;
        }
        int mid = (start+end)/2;
        BinaryTreeNode<Integer> left = convertListToBST(head,start,mid-1,headHolder);

        BinaryTreeNode<Integer> root = headHolder[0];
        headHolder[0] = headHolder[0].right;

        BinaryTreeNode<Integer> right = convertListToBST(head,mid+1,end,headHolder);

        root.right = right;
        root.left = left;
        return root;
    }
    private static void printList(BinaryTreeNode<Integer> list) {
        while (list!=null){
            System.out.print(" "+list.data);
            list = list.right;
        }
        System.out.println();
    }

    private static int getLength(BinaryTreeNode<Integer> head) {
        int count=0;
        while (head!=null){
            count++;
            head = head.right;
        }
        return count;
    }

    private static BinaryTreeNode<Integer>[] merge(BinaryTreeNode<Integer> head1, BinaryTreeNode<Integer> head2) {
        BinaryTreeNode<Integer> head=null,tail = null;
        while (head1!=null&&head2!=null){
            BinaryTreeNode<Integer> temp;
            if(head1.data.compareTo(head2.data)<=0){
                temp = head1;
                head1 = head1.right;

            }else{
                temp = head2;
                head2 = head2.right;

            }
            temp.right=null;
            temp.left=null;

            if(head==null){
                head=tail=temp;
            }else{
                tail.right=temp;
                temp.left = tail;

                tail = temp;
            }

        }
        printList(head);
        while (head1!=null){
            BinaryTreeNode<Integer> temp = head1;
            head1 = head1.right;
            temp.right = null;
            temp.left = null;
            tail.right=temp;
            temp.left = tail;
            tail = temp;

        }

        while (head2!=null){
            BinaryTreeNode<Integer> temp = head2;
            head2 = head2.right;
            temp.right = null;
            temp.left = null;
            tail.right=temp;
            temp.left=tail;
            tail = temp;

        }
        return new BinaryTreeNode[]{head,tail};
    }
}
