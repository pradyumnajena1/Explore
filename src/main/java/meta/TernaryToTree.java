package meta;

import org.w3c.dom.Node;

import java.util.concurrent.atomic.AtomicInteger;

public class TernaryToTree {
    public static void main(String[] args) {
         String expression = "a?b?c:d:e?f:g";
         TreeNode root = convertToTree(expression);
         printTree(root);

    }

    private static TreeNode convertToTree(String expression) {
        return convertToTree(expression,new AtomicInteger(0));
    }

    private static TreeNode convertToTree(String expression, AtomicInteger i) {
        System.out.println(expression.substring(i.get()));
        TreeNode root =  new TreeNode(expression.charAt(i.get()));
        if(i.get()==expression.length()-1){
            return root;
        }
        i.incrementAndGet();
        if(expression.charAt(i.get())=='?'){
            i.incrementAndGet();//skip ?
            root.left = convertToTree(expression,i);
            i.incrementAndGet();//skip :
            root.right = convertToTree(expression,i);
        }
        return root;

    }
    public static void printTree( TreeNode root)
    {
        if (root == null)
            return;

        System.out.print(root.ch +" ");
        printTree(root.left);
        printTree(root.right);
    }

    static class TreeNode{
        char ch;
        TreeNode left,right;

        public TreeNode(char ch) {
            this.ch = ch;
        }
    }
}
