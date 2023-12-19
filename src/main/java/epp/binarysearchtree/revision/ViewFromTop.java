package epp.binarysearchtree.revision;

import epp.array.ArrayUtils;
import epp.binaryTree.BinaryTreeNode;
import epp.binaryTree.BinaryTreeUtils;
import epp.binarysearchtree.BSTUtils;
import epp.binarysearchtree.ViewFromAbove;

import java.util.Arrays;
import java.util.Comparator;
import java.util.StringJoiner;
import java.util.TreeMap;

public class ViewFromTop {

    public static void main(String[] args) {
        Line[] lines = getLines();
        String viewFromTop =   findViewFromTop(lines);
        
    }

    private static String findViewFromTop(Line[] lines) {
        EndPoint[] endPoints = new EndPoint[lines.length*2];
        for(int i=0;i<lines.length;i++){
            endPoints[2*i] = new EndPoint(lines[i].start,true,lines[i]);
            endPoints[2*i+1] = new EndPoint(lines[i].end,false,lines[i]);
        }
        Comparator<EndPoint> comparator =
                Comparator.comparingInt((EndPoint point) -> point.x).thenComparing((EndPoint point) -> point.isStart);
        Arrays.sort(endPoints,
                comparator);
        ArrayUtils.printArray(endPoints);
        BinaryTreeNode<Line> root = null;
        TreeMap<Integer,String> view = new TreeMap<>();
        for(EndPoint endPoint:endPoints){
            if(endPoint.isStart){
                root =  BSTUtils.insertNode(root,endPoint.line);
            }else{
                root = BSTUtils.deleteNode(root,endPoint.line);
            }
            if(root!=null){

                String color = BinaryTreeUtils.getMaxNode(root).data.color;

                view.put(endPoint.x,color);
            }
        }
        for(int x: view.keySet()){
            System.out.println(x+" "+ view.get(x));
        }
        return null;
    }

    private static Line[] getLines() {
        Line[] lines = {
                new  Line(0,4,1,"\\\\\\"),
                new Line(1,3,3,"////"),
                new Line(2,7,2,"!!!!"),
                new Line(4,5,4,":::::"),

                new Line(5,6,1,"::::"),
                new Line(6,10,3,"\\\\\\"),
                new Line(8,9,2,"\\\\\\"),
                new Line(9,18,1,"||||||"),

                new Line(11,13,3,"::::"),
                new Line(12,15,2,"||||"),
                new Line(14,15,3,"...."),
                new Line(16,17,3,"!!!!"),
        };
        return lines;
    }

    private static class Line implements Comparable<Line>{
        int start;
        int end;
        int height;
        String color;

        public Line(int start, int end, int height, String color) {
            this.start = start;
            this.end = end;
            this.height = height;
            this.color = color;
        }

        @Override
        public int compareTo(Line o) {
            return Integer.compare(this.height,o.height);
        }
    }
    private static class EndPoint{
        int x;
        boolean isStart;
        Line line;

        public EndPoint(int x, boolean isStart, Line line) {
            this.x = x;
            this.isStart = isStart;
            this.line = line;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", EndPoint.class.getSimpleName() + "[", "]")
                    .add("x=" + x)
                    .add("isStart=" + isStart)
                    .toString();
        }
    }
}
