package epp.recursion;

import epp.binaryTree.BinaryTreeNodeWithSize;
import epp.binarysearchtree.BSTUtils;

import java.util.*;

public class DiameterOfTree {

    public static void main(String[] args) {
        BinaryTreeNodeWithSize<Integer> root = BSTUtils.buildBSTWithSizeWithUniqueValues(10,1,20);
        System.out.println(root);
        fillDistanceFromParent(root);
        System.out.println(root);

         List<BinaryTreeNodeWithSize<Integer>> longestPath = getLongestPath(root);
        System.out.println( printPath(longestPath));
    }

    private static List<BinaryTreeNodeWithSize<Integer>> getLongestPath(BinaryTreeNodeWithSize<Integer> root) {
        Map<Integer,List<BinaryTreeNodeWithSize<Integer>>> cache = new HashMap<>();
        return getLongestPathRecurse(root,cache);

    }

    private static List<BinaryTreeNodeWithSize<Integer>> getLongestPathRecurse(BinaryTreeNodeWithSize<Integer> root, Map<Integer, List<BinaryTreeNodeWithSize<Integer>>> cache) {
        if(root ==null){
            return new ArrayList<>();
        }
        if(cache.containsKey(root.data)){
            return cache.get(root.data);
        }
        List<BinaryTreeNodeWithSize<Integer>> leftPath = getLongestPath(root.left);
        List<BinaryTreeNodeWithSize<Integer>> rightPath  = getLongestPath(root.right);

        List<BinaryTreeNodeWithSize<Integer>> pathThroughRoot = getPathThroughRoot(root);
        int left = length(leftPath);
        int right = length(rightPath);
        int throughRoot = length(pathThroughRoot);
        List<BinaryTreeNodeWithSize<Integer>> result = null;
        if(left>=right && left>=throughRoot){
            result= leftPath;
        }else if(right>=left && right>=throughRoot){
            result= rightPath;
        }else{
            result= pathThroughRoot;
        }
        cache.put(root.data,result);
        return result;
    }

    private static List<BinaryTreeNodeWithSize<Integer>> getPathThroughRoot(BinaryTreeNodeWithSize<Integer> root) {
        List<BinaryTreeNodeWithSize<Integer>> longestPathFromLeftChild =
                  getLongestPathFromRoot(root.left);
        List<BinaryTreeNodeWithSize<Integer>> longestPathFromRightChild =
                  getLongestPathFromRoot(root.right);
        List<BinaryTreeNodeWithSize<Integer>> result = new ArrayList<>(longestPathFromLeftChild);
        result.add(root);
        Collections.reverse(longestPathFromRightChild);
        result.addAll(longestPathFromRightChild);
        System.out.println(root.data+" getPathThroughRoot   :"+ printPath(result));
        return result;
    }

    private static String printPath(List<BinaryTreeNodeWithSize<Integer>> result) {
        return result.stream().map(x -> x.data + "").reduce("",
                (a, b) -> (a + " ," + b));
    }

    private static List<BinaryTreeNodeWithSize<Integer>> getLongestPathFromRoot(BinaryTreeNodeWithSize<Integer> root) {
        if(root==null){
            return new ArrayList<>();
        }
        List<BinaryTreeNodeWithSize<Integer>> longestPathFromLeft = getLongestPathFromRoot(root.left);
        List<BinaryTreeNodeWithSize<Integer>> longestPathFromRight = getLongestPathFromRoot(root.right);
        longestPathFromLeft.add(root);
        longestPathFromRight.add(root);
        List<BinaryTreeNodeWithSize<Integer>> result = null;
        if(length(longestPathFromLeft)>=length(longestPathFromRight)){
              result = longestPathFromLeft;

        }else{
             result = longestPathFromRight;
        }
      //  System.out.println(root.data+" getLongestPathFromRoot   :"+ printPath(result));
        return result;

    }

    private static int length(List<BinaryTreeNodeWithSize<Integer>> longestPathFromRight) {
        Integer sum = longestPathFromRight.stream().map(x -> x.size).reduce(0, (a, b) -> a + b);
        return sum;
    }

    private static void fillDistanceFromParent(BinaryTreeNodeWithSize<Integer> root) {
        fillDistanceFromParentRecurse(root);
        root.size=0;
    }

    private static void fillDistanceFromParentRecurse(BinaryTreeNodeWithSize<Integer> root) {
        if(root ==null){
            return;
        }
        fillDistanceFromParentRecurse(root.left);
        fillDistanceFromParentRecurse(root.right);
        root.size= 1+ (int) (Math.random()*10);
    }
}
