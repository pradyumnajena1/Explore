package epp.hashmap.revision;

import java.util.*;

public class LineThroughMaxPoints {

    public static void main(String[] args) {
        List<Point> points = List.of(new Point(0,0),new Point(1,1),new Point(2,2),new Point(0,3),new Point(2,3));
       Line line =  getLineThroughMaxPoints(points);
        System.out.println(line);
    }

    private static Line getLineThroughMaxPoints(List<Point> points) {
        Map<Line, Set<Point>> frequencyCount = new HashMap<>();
        for(int i=0;i<points.size();i++){
            for(int j=i+1;j< points.size();j++){
                Line line = new Line(points.get(i), points.get(j));
                Set<Point> pointSet = frequencyCount.getOrDefault(line, new HashSet<>());

                pointSet.add(points.get(i));
                pointSet.add(points.get(j));
                frequencyCount.put(line, pointSet);
            }
        }
        Map.Entry<Line, Set<Point>> max = null;
        for (Map.Entry<Line, Set<Point>> entry : frequencyCount.entrySet()) {
            if(max==null|| max.getValue().size()<entry.getValue().size()){
                max = entry;
                System.out.println( entry.getValue().size());
            }
        }
        return max.getKey();
    }

    private static class Line{
        IntegerFraction slope;
        IntegerFraction intercept;

        public Line(Point start, Point end) {
             this.slope = getSlope(start,end);
             this.intercept = getIntercept(start,end);
        }
        private IntegerFraction getSlope(Point start, Point end){
            return   new IntegerFraction (end.y-start.y, end.x-start.x);
        }
        private IntegerFraction getIntercept(Point start, Point end){
            return  new IntegerFraction (  (start.y * end.x - end.y* start.x),(end.x-start.x));
        }
        public boolean contains(Point point){
            return   slope.numerator* intercept.denominator* point.x== slope.denominator* intercept.denominator* point.y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Line line = (Line) o;

            if (!slope.equals(line.slope)) return false;
            return intercept.equals(line.intercept);
        }

        @Override
        public int hashCode() {
            int result = slope.hashCode();
            result = 31 * result + intercept.hashCode();
            return result;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Line.class.getSimpleName() + "[", "]")
                    .add("slope=" + slope)
                    .add("intercept=" + intercept)
                    .toString();
        }
    }
    private static class IntegerFraction{
        int numerator;
        int denominator;


        public IntegerFraction(int num, int denom) {
            int sign =  num*denom>=0?1:-1;
            num = Math.abs( num);
            denom =Math.abs(denom);

            this.numerator =  sign* num;
            this.denominator = denom;
            int gcd = gcd(num,denom);
            if(gcd!=0){
                this.numerator /=  gcd;
                this.denominator /= gcd;
            }


        }

        private int gcd(int a, int b) {
             if(a==0) return b;
             if(b==0) return a;
             if(a==b) {
                 return a;
             } else if (a>b) {
                 return gcd(a-b,b);
             }else {

                 return gcd(a,b-a);
             }

        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            IntegerFraction that = (IntegerFraction) o;

            if (numerator != that.numerator) return false;
            return denominator == that.denominator;
        }

        @Override
        public int hashCode() {
            int result = numerator;
            result = 31 * result + denominator;
            return result;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", IntegerFraction.class.getSimpleName() + "[", "]")
                    .add("numerator=" + numerator)
                    .add("denominator=" + denominator)
                    .toString();
        }
    }

    private static class Point{
        int x,y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }


    }
}
