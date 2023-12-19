package epp.dp;

import epp.binaryTree.BinaryTreeNode;

public class VoltageTree {
    public static void main(String[] args) {
        BinaryTreeNode<Character> root = new BinaryTreeNode<>('a',new BinaryTreeNode<>('b',new BinaryTreeNode<>('d'),
                new BinaryTreeNode<>('e')),
                new BinaryTreeNode<>('c'));
        System.out.println(root);
        int power =  minimizePower(root);
        System.out.println(power);
    }

    private static int minimizePower(BinaryTreeNode<Character> node) {
      return minimizePower(node,null);
    }

    private static int minimizePower(BinaryTreeNode<Character> node,  Character parent) {
        if(node==null){
            return 0;
        }
        if(node.left==null && node.right==null){
            if(parent=='H'){

                return 1;
            }else{

                return 2;
            }
        }


        int hleft = minimizePower(node.left,'H');
        int hright = minimizePower(node.right,'H');
        int htotal = hleft+hright+2*(1+(node.left!=null?1:0)+(node.right!=null?1:0));

        int lleft = minimizePower(node.left,'L');
        int lright = minimizePower(node.right,'L');
        int ltotal = lleft + lright + 1 * (1 + (node.left != null ? 1 : 0) + (node.right != null ? 1 : 0));
        int min =0;
        if(parent!=null && parent=='L'){
            min = htotal;
            System.out.println(node.data +" L");
        }else{
            min = Math.min(htotal, ltotal);
            if(min==htotal){
                System.out.println(node.data +" H");
            }else{
                System.out.println(node.data +" L");

            }

        }

                return min;

    }
}
