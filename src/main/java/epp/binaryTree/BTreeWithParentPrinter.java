package epp.binaryTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BTreeWithParentPrinter {

    public static <T extends Comparable<T>> String getNodeAsString(BinaryTreeNodeWithParent<T> root) {
        int maxLevel = BTreeWithParentPrinter.maxLevel(root);
        StringBuilder collector = new StringBuilder();
        getNodeInternal(Collections.singletonList(root), 1, maxLevel,collector);

        return collector.toString();
    }

    private static <T extends Comparable<T>> void getNodeInternal(List<BinaryTreeNodeWithParent<T>> nodes, int level,
                                                                  int maxLevel, StringBuilder collector) {
        if (nodes.isEmpty() || BTreeWithParentPrinter.isAllElementsNull(nodes))
            return;

        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        printWhitespaces(firstSpaces,collector);

        List<BinaryTreeNodeWithParent<T>> newNodes = new ArrayList<BinaryTreeNodeWithParent<T>>();
        for (BinaryTreeNodeWithParent<T> node : nodes) {
            if (node != null) {
                printString(node.data+"",collector);
                newNodes.add(node.left);
                newNodes.add(node.right);
            } else {
                newNodes.add(null);
                newNodes.add(null);
                printSpace(collector);
            }

             printWhitespaces(betweenSpaces, collector);
        }
        printNewLine(collector);

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                BTreeWithParentPrinter.printWhitespaces(firstSpaces - i, collector);
                if (nodes.get(j) == null) {
                    BTreeWithParentPrinter.printWhitespaces(endgeLines + endgeLines + i + 1, collector);
                    continue;
                }

                if (nodes.get(j).left != null)
                    printString("/", collector);
                else
                    BTreeWithParentPrinter.printWhitespaces(1, collector);

                BTreeWithParentPrinter.printWhitespaces(i + i - 1, collector);

                if (nodes.get(j).right != null)
                    printString("\\", collector);
                else
                    BTreeWithParentPrinter.printWhitespaces(1, collector);

                BTreeWithParentPrinter.printWhitespaces(endgeLines + endgeLines - i, collector);
            }

            printNewLine(collector);
        }

        getNodeInternal(newNodes, level + 1, maxLevel, collector);
    }

    private static void printString(String s, StringBuilder collector) {
       // System.out.print(s);
        collector.append(s);
    }

    private static void printNewLine(StringBuilder collector) {
       // System.out.println("");
        collector.append(System.lineSeparator());
    }
    private static void printSpace(StringBuilder collector) {
        printString(" ", collector);
    }

    private static void printWhitespaces(int count, StringBuilder collector) {
        for (int i = 0; i < count; i++)
            printSpace(collector);
    }



    private static <T extends Comparable<T>> int maxLevel(BinaryTreeNodeWithParent<T> node) {
        if (node == null)
            return 0;

        return Math.max(BTreeWithParentPrinter.maxLevel(node.left), BTreeWithParentPrinter.maxLevel(node.right)) + 1;
    }

    private static <T> boolean isAllElementsNull(List<T> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }

        return true;
    }

    public static <T extends Comparable<T>> String getNodeAsString(BinaryTreeNodeWithNext tBinaryTreeNodeWithNext) {
        return null;
    }
}
