package amaze;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class TreeViews {
    public static void main(String[] args) {
        TreeNode root =
                new TreeNode(4,
                        new TreeNode(5),
                        new TreeNode(2,
                                new TreeNode(3, new TreeNode(6), new TreeNode(7)),
                                new TreeNode(1)));
        System.out.println("leftView:" + leftViewOfTree(root));
        System.out.println("leftView:" + leftViewOfTreeRec(root));
        System.out.println("leftView:" + leftViewOfTree2(root));
        System.out.println("rightView:" + rightViewOfTreeRec(root));
        System.out.println("rightView:" + rightViewOfTree(root));
        System.out.println("topViewOfTree" + topViewOfTree(root));
        System.out.println("bottomViewOfTree" + bottomViewOfTree(root));

          root =
                new TreeNode(8,
                        new TreeNode(3,
                                new TreeNode(1),
                                null),
                        new TreeNode(10,
                                new TreeNode(6,
                                        new TreeNode(4),
                                        new TreeNode(7)),
                                new TreeNode(14,
                                        new TreeNode(13),
                                        null)));
        System.out.println(diagonalViewOfTree(root));
    }

    public static List<Integer> leftViewOfTreeRec(TreeNode root) {
        List<Integer> resultCollector = new ArrayList<>();
        leftViewOfTreeRec(root, 1, new AtomicInteger(0), resultCollector);
        return resultCollector;
    }

    private static void leftViewOfTreeRec(TreeNode root, int level, AtomicInteger max, List<Integer> resultCollector) {
        if (root == null) {
            return;
        }
        if (max.get() < level) {
            resultCollector.add(root.data);
            max.set(level);
        }
        leftViewOfTreeRec(root.left, level + 1, max, resultCollector);
        leftViewOfTreeRec(root.right, level + 1, max, resultCollector);
    }

    public static List<Integer> rightViewOfTreeRec(TreeNode root) {
        List<Integer> resultCollector = new ArrayList<>();
        rightViewOfTreeRec(root, 1, new AtomicInteger(0), resultCollector);
        return resultCollector;
    }

    private static void rightViewOfTreeRec(TreeNode root, int level, AtomicInteger max, List<Integer> resultCollector) {
        if (root == null) {
            return;
        }
        if (max.get() < level) {
            resultCollector.add(root.data);
            max.set(level);
        }
        rightViewOfTreeRec(root.right, level + 1, max, resultCollector);
        rightViewOfTreeRec(root.left, level + 1, max, resultCollector);
    }

    public static List<Integer> leftViewOfTree(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        List<TreeNode> currentLevelNodes = new ArrayList<>();
        currentLevelNodes.add(root);
        while (!currentLevelNodes.isEmpty()) {
            result.add(currentLevelNodes.get(0).data);
            List<TreeNode> nextLevelNodes = new ArrayList<>();
            for (TreeNode node : currentLevelNodes) {
                if (node.left != null) {

                    nextLevelNodes.add(node.left);
                }
                if (node.right != null) {

                    nextLevelNodes.add(node.right);
                }
            }
            currentLevelNodes = nextLevelNodes;
        }
        return result;
    }

    public static List<Integer> topViewOfTree(TreeNode root) {
        Map<Integer, Integer> levelMap = new HashMap<>();
        topViewOfTree(root, 0, levelMap);
        Integer min = levelMap.keySet().stream().min(Integer::compareTo).get();
        Integer max = levelMap.keySet().stream().max(Integer::compareTo).get();
        List<Integer> result = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            result.add(levelMap.get(i));
        }
        return result;
    }

    private static void topViewOfTree(TreeNode root, int xcordinate, Map<Integer, Integer> levelMap) {
        if (root == null) {
            return;
        }
        if (!levelMap.containsKey(xcordinate)) {
            levelMap.put(xcordinate, root.data);
        }
        topViewOfTree(root.left, xcordinate - 1, levelMap);
        topViewOfTree(root.right, xcordinate + 1, levelMap);
    }


    public static List<Integer> rightViewOfTree(TreeNode root) {
        Map<Integer, Integer> levelMap = new HashMap<>();
        rightViewOfTree(root, 0, levelMap);
        Integer min = levelMap.keySet().stream().min(Integer::compareTo).get();
        Integer max = levelMap.keySet().stream().max(Integer::compareTo).get();
        List<Integer> result = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            result.add(levelMap.get(i));
        }
        return result;
    }

    private static void rightViewOfTree(TreeNode root, int level, Map<Integer, Integer> levelMap) {
        if (root == null) {
            return;
        }
        levelMap.put(level, root.data);

        rightViewOfTree(root.left, level + 1, levelMap);
        rightViewOfTree(root.right, level + 1, levelMap);
    }

    public static List<Integer> leftViewOfTree2(TreeNode root) {
        Map<Integer, Integer> levelMap = new HashMap<>();
        leftViewOfTree2(root, 0, levelMap);
        Integer min = levelMap.keySet().stream().min(Integer::compareTo).get();
        Integer max = levelMap.keySet().stream().max(Integer::compareTo).get();
        List<Integer> result = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            result.add(levelMap.get(i));
        }
        return result;
    }

    private static void leftViewOfTree2(TreeNode root, int level, Map<Integer, Integer> levelMap) {
        if (root == null) {
            return;
        }
        if (!levelMap.containsKey(level)) {
            levelMap.put(level, root.data);
        }

        leftViewOfTree2(root.left, level + 1, levelMap);
        leftViewOfTree2(root.right, level + 1, levelMap);
    }


    public static List<Integer> bottomViewOfTree(TreeNode root) {
        Map<Integer, Integer> levelMap = new HashMap<>();
        bottomViewOfTree(root, 0, levelMap);
        Integer min = levelMap.keySet().stream().min(Integer::compareTo).get();
        Integer max = levelMap.keySet().stream().max(Integer::compareTo).get();
        List<Integer> result = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            result.add(levelMap.get(i));
        }
        return result;
    }

    private static void bottomViewOfTree(TreeNode root, int xcordinate, Map<Integer, Integer> levelMap) {
        if (root == null) {
            return;
        }

        levelMap.put(xcordinate, root.data);

        bottomViewOfTree(root.left, xcordinate - 1, levelMap);
        bottomViewOfTree(root.right, xcordinate + 1, levelMap);
    }

    public static List<List<Integer>> diagonalViewOfTree(TreeNode root) {
        Map<Integer, List<Integer>> diagonalMap = new HashMap<>();
        diagonalViewOfTree(root, 0, 0, diagonalMap);
        Integer min = diagonalMap.keySet().stream().min(Integer::compareTo).get();
        Integer max = diagonalMap.keySet().stream().max(Integer::compareTo).get();
        List<List<Integer>> result = new ArrayList<>();
        for (int i = min; i <= max; i+=2) {
            if(diagonalMap.containsKey(i)){
                List<Integer> diagonalElements = diagonalMap.get(i);
                result.add(diagonalElements);
            }
        }
        return result;
    }

    private static void diagonalViewOfTree(TreeNode root, int xcordinate,
                                           int ycordinate, Map<Integer, List<Integer>> diagonalMap) {
        if (root == null) {
            return;
        }

        int diagonalKey =ycordinate- xcordinate  ;
        List<Integer> list = diagonalMap.getOrDefault(diagonalKey, new ArrayList<>());
        list.add(root.data);
        diagonalMap.put(diagonalKey, list);
        diagonalViewOfTree(root.left, xcordinate - 1, ycordinate + 1, diagonalMap);
        diagonalViewOfTree(root.right, xcordinate + 1, ycordinate + 1, diagonalMap);
    }

}
