package epp.dp.revision;

import epp.binaryTree.BinaryTreeNode;

import java.util.StringJoiner;

public class MinimalCircuitPower {

    public static final String HIGH = "High";
    public static final String LOW = "Low";

    public static void main(String[] args) {
        BinaryTreeNode<Gate> root = new BinaryTreeNode<>(new Gate());
        root.left = new BinaryTreeNode<>(new Gate());
        root.right = new BinaryTreeNode<>(new Gate());
        root.left.left = new BinaryTreeNode<>(new Gate());
        root.left.right = new BinaryTreeNode<>(new Gate());
        System.out.println(root);
        int minPower = getMinimalCircuitPower(root);
        System.out.println(minPower);
        System.out.println(root);
    }

    private static int getMinimalCircuitPower(BinaryTreeNode<Gate> root) {


        return getMinimalCircuitPower(root, null);
    }

    private static int getMinimalCircuitPower(BinaryTreeNode<Gate> root, String parentLevel) {
        if (root == null) {
            return 0;
        }

        int h_left = getMinimalCircuitPower(root.left, HIGH);
        int h_right = getMinimalCircuitPower(root.right, HIGH);

        int l_left = getMinimalCircuitPower(root.left, LOW);
        int l_right = getMinimalCircuitPower(root.right, LOW);




        String level = null;
        int childCount = (root.left != null ? 1 : 0) + (root.right != null ? 1 : 0);
        if (LOW.equals(parentLevel)) {

          int  totalPower = h_right + h_left + 1 + 2 * childCount;

            root.data.power = totalPower;
            root.data.voltage = HIGH;
            return totalPower;

        } else {
            int minPower = Integer.MAX_VALUE;
            int   totalPower = h_left + h_right + 1 + 2 * childCount;
            if (minPower > totalPower) {
                minPower = totalPower;
                level = HIGH;
            }

            totalPower = l_right + l_left + 1 + childCount;
            if (minPower > totalPower) {
                minPower = totalPower;
                level = LOW;
            }
            root.data.power = minPower;
            root.data.voltage = level;
            return minPower;
        }


    }

    private static class Gate implements Comparable<Gate> {

        int power;
        String voltage;


        @Override
        public int compareTo(Gate o) {
            return 0;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Gate.class.getSimpleName() + "[", "]")
                    .add("power=" + power)
                    .add("voltage='" + voltage + "'")
                    .toString();
        }
    }
}
