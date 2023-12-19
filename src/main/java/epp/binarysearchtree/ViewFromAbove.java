package epp.binarysearchtree;

import epp.binaryTree.BinaryTreeNode;

import java.util.*;

public class ViewFromAbove {
    private static class EndPoint implements Comparable<EndPoint>{
        private final Comparator<EndPoint> endPointComparator = Comparator.comparing(EndPoint::getX).thenComparing(EndPoint::isStart, Comparator.reverseOrder());
        int x;
        boolean isStart;
        LineSegment segment;

        public int getX() {
            return x;
        }

        public boolean isStart() {
            return isStart;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("EndPoint{");
            sb.append("x=").append(x);
            sb.append(", isStart=").append(isStart);
            sb.append('}');
            return sb.toString();
        }

        public EndPoint(int x, boolean isStart,LineSegment lineSegment) {
            this.x = x;
            this.isStart = isStart;
            this.segment = lineSegment;
        }

        @Override
        public int compareTo(EndPoint o) {
            return endPointComparator.compare(this,o);
        }
    }
    private static class LineSegment implements Comparable<LineSegment>{
        public static Comparator leftComparator = Comparator.comparing(LineSegment::getLeft);
        int left;
        int right;
        int height;
        String color;

        public LineSegment(int left, int right, int height, String color) {
            this.left = left;
            this.right = right;
            this.height = height;
            this.color = color;
        }

        public int getLeft() {
            return left;
        }

        @Override
        public int compareTo(LineSegment o) {
            return Integer.compare(height,o.height);
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("LineSegment{");
            sb.append("left=").append(left);
            sb.append(", right=").append(right);
            sb.append(", height=").append(height);
            sb.append(", color='").append(color).append('\'');
            sb.append('}');
            return sb.toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            LineSegment that = (LineSegment) o;
            return left == that.left && right == that.right && height == that.height;
        }

        @Override
        public int hashCode() {
            return Objects.hash(left, right, height);
        }
    }
    public static void main(String[] args) {
        LineSegment[] lineSegments = new LineSegment[]{
                new LineSegment(0,4,1,"\\\\\\"),
                new LineSegment(1,3,3,"////"),
                new LineSegment(2,7,2,"!!!!"),
                new LineSegment(4,5,4,":::::"),

                new LineSegment(5,6,1,"::::"),
                new LineSegment(6,10,3,"\\\\\\"),
                new LineSegment(8,9,2,"\\\\\\"),
                new LineSegment(9,18,1,"||||||"),

                new LineSegment(11,13,3,"::::"),
                new LineSegment(12,15,2,"||||"),
                new LineSegment(14,15,3,"...."),
                new LineSegment(16,17,3,"!!!!"),
        };

        List<LineSegment> topView = getTopView(lineSegments);
        System.out.println(topView);


    }

    private static List<LineSegment> getTopView(LineSegment[] lineSegments) {
           EndPoint[] endPoints = new EndPoint[2*lineSegments.length];
           int writePos = 0;
           for(int i=0;i<lineSegments.length;i++){
               endPoints[writePos++] = new EndPoint(lineSegments[i].left,true,lineSegments[i]);
               endPoints[writePos++] = new EndPoint(lineSegments[i].right,false,lineSegments[i]);
           }
           Arrays.sort(endPoints);
        System.out.println(Arrays.toString(endPoints));
        BinaryTreeNode<LineSegment> root = null;
        Map<LineSegment,BinaryTreeNode<LineSegment>> map = new HashMap<>();
        List<LineSegment> result = new ArrayList<>();
        for(int i=0;i<endPoints.length;i++){
            LineSegment segment = endPoints[i].segment;
            if(endPoints[i].isStart){
                BinaryTreeNode<LineSegment> newNode  = new BinaryTreeNode<>(segment);
                map.put(segment,newNode);
                root= insert(root,newNode);

            }else{
               root =  delete(root,map.get(segment));
            }
         if(root!=null){
             LineSegment maxSegment =    getMax(root);
             LineSegment view = new LineSegment(endPoints[i].x,endPoints[i].x+1,maxSegment.height,maxSegment.color);
             result.add(view);
         }

        }

        return result;
    }

    private static BinaryTreeNode<LineSegment> insert(BinaryTreeNode<LineSegment> root, BinaryTreeNode<LineSegment> value) {
        if(root==null){
            return  value;
        }
        if(root.data.compareTo(value.data)<0){
            root.right= insert(root.right,value);
        }else if(root.data.compareTo(value.data)>0){
            root.left= insert(root.left,value);
        }else{
            System.out.println("value already present "+value);
        }
        return root;
    }
    private static BinaryTreeNode<LineSegment> delete(BinaryTreeNode<LineSegment> root,BinaryTreeNode<LineSegment> value){
        if(root==null){
            System.out.println("value nt found "+value);
            return root;
        }
        if(root.data.compareTo(value.data)>0){
            root.left= delete(root.left,value);
        }else if(root.data.compareTo(value.data)<0){
            root.right = delete(root.right,value);
        }else{
            System.out.println("value successfully deleted "+value);
            if(root.left==null){
                return root.right;
            }
            else if(root.right==null){
                return root.left;
            }
            BinaryTreeNode<LineSegment> min = findMin(root.right);
            root.data = min.data;
            root.right = delete(root.right,min);



        }

        return root;
    }

    private static BinaryTreeNode<LineSegment> findMin(BinaryTreeNode<LineSegment> root) {
        BinaryTreeNode<LineSegment> current = root;
        while (current.left!=null){
            current = current.left;
        }
        return current;
    }

    private static LineSegment getMax(BinaryTreeNode<LineSegment> root) {

        while (root.right!=null){
            root = root.right;
        }
        return root.data;
    }
}
