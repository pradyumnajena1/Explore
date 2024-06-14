package epp.stacknqueue.revision;

import epp.stacknqueue.JumpListNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class JumpOrder {

  public static void main(String[] args) {
    JumpListNode one = new JumpListNode(-1);
    JumpListNode two = new JumpListNode(-1);
    JumpListNode three = new JumpListNode(-1);
    JumpListNode four = new JumpListNode(-1);
    JumpListNode five = new JumpListNode(-1);
    one.next = two;
    two.next = three;
    three.next = four;
    four.next = five;
    five.next = null;

    one.jump = three;
    two.jump = four;
    four.jump = three;
    five.jump = five;

   // setJumpOrder(one);
    setJumpOrderIter(one);
    printOrder(one);
  }

  private static void setJumpOrderIter(JumpListNode node){
    Deque<JumpListNode> stack = new LinkedList<>();
    stack.push(node);
    int order=0;
    while (!stack.isEmpty()){
      JumpListNode pop = stack.pop();
      if(pop!=null && pop.data==-1){
        pop.data = ++order;
        stack.push(pop.next);
        stack.push(pop.jump);
      }
    }
  }

  private static void printOrder(JumpListNode node) {
    JumpListNode iter = node;
    while (iter != null) {
      System.out.println(iter.data);
      iter = iter.next;
    }
  }

  private static void setJumpOrder(JumpListNode node) {
    AtomicInteger order = new AtomicInteger(0);
    setJumpOrder(node, order);
  }

  private static void setJumpOrder(JumpListNode node, AtomicInteger order) {
    if (node != null && node.data == -1) {
      node.data = order.incrementAndGet();
      setJumpOrder(node.jump, order);
      setJumpOrder(node.next, order);
    }
  }

  private static class JumpListNode {
    int data;
    JumpListNode next, jump;

    public JumpListNode(int data) {
      this.data = data;
    }

    public JumpListNode(int data, JumpListNode next) {
      this.data = data;
      this.next = next;
    }

    public JumpListNode(int data, JumpListNode next, JumpListNode jump) {
      this.data = data;
      this.next = next;
      this.jump = jump;
    }
  }
}
