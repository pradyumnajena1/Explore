package epp.binaryTree.revision;

import epp.binaryTree.BinaryTreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class MirrorViewOfTree {
    public static void main(String[] args) {
        BinaryTreeNode<Character> nodeB = new BinaryTreeNode<>('B',
                new BinaryTreeNode<>('C',
                        new BinaryTreeNode<>('D'),
                        new BinaryTreeNode<>('E')),
                new BinaryTreeNode<>('F',
                        null,
                        new BinaryTreeNode<>('G',
                                new BinaryTreeNode<>('H'),
                                null)));
        BinaryTreeNode<Character> nodeI = new BinaryTreeNode<>('I',
                new BinaryTreeNode<>('J',
                        null,
                        new BinaryTreeNode<>('K',
                                new BinaryTreeNode<>('L',
                                        null,
                                        new BinaryTreeNode<>('M')),
                                new BinaryTreeNode<>('N'))),
                new BinaryTreeNode<>('O',
                        null,
                        new BinaryTreeNode<>('P')));
        BinaryTreeNode<Character> root = new BinaryTreeNode<>('A', nodeB, nodeI);
        System.out.println(root);
        List<BinaryTreeNode<Character>> leftMirrorView=getLeftMirrorView(root);
    System.out.println(
        leftMirrorView.stream().map(x -> x.data.toString()).collect(Collectors.joining(", ")));

        List<BinaryTreeNode<Character>> rightMirrorView=getRightMirrorView(root);
        System.out.println(
                rightMirrorView.stream().map(x -> x.data.toString()).collect(Collectors.joining(", ")));
    }

    public static<T extends Comparable<T>> List<BinaryTreeNode<T>> getLeftMirrorView(BinaryTreeNode<T> root) {
        List<BinaryTreeNode<T>> result = new ArrayList<BinaryTreeNode<T>>();
        AtomicInteger maxLevelSeen = new AtomicInteger(0);
        getLeftMirrorView(root,result,1,maxLevelSeen);
        return result;
    }

    private static <T extends Comparable<T>> void getLeftMirrorView(BinaryTreeNode<T> root, List<BinaryTreeNode<T>> result, int level, AtomicInteger maxLevelSeen) {
        if(root!=null){
            if(level>maxLevelSeen.get()){
                result.add(root);
                maxLevelSeen.set(level);
            }
            getLeftMirrorView(root.left,result,level+1,maxLevelSeen);
            getLeftMirrorView(root.right,result,level+1,maxLevelSeen);
        }
    }

    public static<T extends Comparable<T>> List<BinaryTreeNode<T>> getRightMirrorView(BinaryTreeNode<T> root) {
        List<BinaryTreeNode<T>> result = new ArrayList<BinaryTreeNode<T>>();
        AtomicInteger maxLevelSeen = new AtomicInteger(0);
        getRightMirrorView(root,result,1,maxLevelSeen);
        return result;
    }

    private static <T extends Comparable<T>> void getRightMirrorView(BinaryTreeNode<T> root, List<BinaryTreeNode<T>> result, int level, AtomicInteger maxLevelSeen) {
        if(root!=null){
            if(level>maxLevelSeen.get()){
                result.add(root);
                maxLevelSeen.set(level);
            }
            getRightMirrorView(root.right,result,level+1,maxLevelSeen);
            getRightMirrorView(root.left,result,level+1,maxLevelSeen);
        }
    }


}
