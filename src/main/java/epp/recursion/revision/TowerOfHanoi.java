package epp.recursion.revision;

import java.util.Stack;
import java.util.StringJoiner;

public class TowerOfHanoi {
    public static void main(String[] args) {
        Tower a = new Tower("a");
        Tower b = new Tower("b");
        Tower c = new Tower("c");
        a.push(5);
        a.push(4);
        a.push(3);
        a.push(2);
        a.push(1);
        moveTowers(a,b,c);
        System.out.println(b);
    }
    private static class Tower{
        private String name;
        private Stack<Integer> stack = new Stack<>();

        public Tower(String name) {
            this.name = name;
        }
        public void push(int i){
            if(stack.size()>0 && stack.peek()<i){
                throw new IllegalArgumentException("invalid param "+i);
            }
            stack.push(i);
        }
        public int peek(){
            return stack.peek();
        }
        public int pop(){
            return stack.pop();
        }

        public int size() {
            return stack.size();
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Tower.class.getSimpleName() + "[", "]")
                    .add("name='" + name + "'")
                    .add("stack=" + stack)
                    .toString();
        }
    }

    private static void moveTowers(Tower a, Tower b, Tower c) {
        moveTowers(a,b,c,a.size());
    }

    private static void moveTowers(Tower a, Tower b, Tower c, int size) {
        if(size==0){
            return;
        }
        moveTowers(a,c,b,size-1);
        System.out.println("moving "+a.peek() +" from "+a.name+" to "+b.name );
        b.push(a.pop());
        moveTowers(c,b,a,size-1);
    }


}
