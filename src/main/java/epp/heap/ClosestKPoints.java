package epp.heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class ClosestKPoints {

    private static class Point{
        int id;
        int x,y,z;


        public Point(int id, int x, int y, int z) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
    public static void main(String[] args) {

        Point earth = new Point(0,0,0,0);
        Point[] others = new Point[]{ new Point(1,7,3,7),new Point(2,2,2,2),new Point(3,8,9,9),new Point(4,7,5,2),
                new Point(5,12,1,2),new Point(6,1,2,3),new Point(7,12,9,3)};
        List<Integer> closestPoints = getClosestPoints(earth,others,5);
        System.out.println(closestPoints);

    }
    private static class HeapNode  implements Comparable<HeapNode>{
        public int id;
        public int distance;

        public HeapNode(int id, int distance) {
            this.id = id;
            this.distance = distance;
        }

        @Override
        public int compareTo(HeapNode o) {
            return Integer.compare(  o.distance,this.distance);
        }
    }

    private static List<Integer> getClosestPoints(Point earth, Point[] others, int num) {

        PriorityQueue<HeapNode> priorityQueue = new PriorityQueue<>();
        List<Integer> result = new ArrayList<>();
        for(int i=0;i< Math.min( num,others.length);i++){
            priorityQueue.add(new HeapNode(others[i].id, getDistance(earth,others[i])));
        }
        for(int i=num;i<others.length;i++){
            Point currentPoint = others[i];
            int distance = getDistance(earth,currentPoint);
            if(distance<priorityQueue.peek().distance){
                priorityQueue.poll();
                priorityQueue.add(new HeapNode(currentPoint.id,distance));
            }
        }
        while (!priorityQueue.isEmpty()){
            result.add(priorityQueue.poll().id);
        }
        Collections.reverse(result);
        return result;
    }

    private static int getDistance(Point earth, Point other) {
        return (int) Math.sqrt (Math.pow(other.x,2)+Math.pow(other.y,2)+Math.pow(other.z,2));
    }
}
