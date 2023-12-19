package epp.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ShortestDistanceBetweenPoints {
    private static class Point{
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        int distance(Point another){
            return (int) (Math.pow(another.x-x,2)+Math.pow(another.y-y,2));
        }
        int getX(){
            return x;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Point{");
            sb.append("x=").append(x);
            sb.append(", y=").append(y);
            sb.append('}');
            return sb.toString();
        }
    }
    public static void main(String[] args) {
        Point[] points = new Point[20];
        for(int i=0;i<points.length;i++){
            points[i] = new Point((int)(Math.random()*50),(int)(Math.random()*50));
        }
        System.out.println(Arrays.toString(points));
        Arrays.sort(points, Comparator.comparing(Point::getX));
        System.out.println(Arrays.toString(points));

        int minDistance = getMinDistance(points);
        System.out.println(minDistance);

    }

    private static int getMinDistance(Point[] points) {
        return getMinDistance(points,points[0].x,  points[points.length-1].x );
    }

    private static int getMinDistance(Point[] points, int startX, int endX) {
        System.out.println(startX+" "+endX);
        if(points.length<=1){
          return   Integer.MAX_VALUE;
        }else if(points.length==2){
            return points[0].distance(points[1]);
        }

        int midx = (startX+endX)/2;
        int leftDistance = getMinDistance(getPoints(points, startX,midx),startX,midx);
        int rightDistance = getMinDistance(getPoints(points, midx+1,endX),midx+1,endX);

        int min = Math.min(leftDistance,rightDistance);

        Point[] middlePointsLeft = getPoints(points,  midx-min/2,midx);
        Point[] middlePointsRight = getPoints(points,  midx+1,midx+min/2);
        int minDistance  = Integer.MAX_VALUE;
        for(Point left:middlePointsLeft){
            for(Point right:middlePointsRight){
                minDistance = Math.min(minDistance,left.distance(right));
            }
        }



        return minDistance;
    }

    private static Point[] getPoints(Point[] points, int minValue, int Maxvalue) {
        List<Point> result = new ArrayList<>();
        for(int i=0;i<=points.length-1;i++){
            if(points[i].x>=minValue && points[i].x<=Maxvalue){
                result.add(points[i]);
            }
        }
        return result.toArray(new Point[0]);
    }
}
