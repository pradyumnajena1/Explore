package epp.greedy;

import epp.array.ArrayUtils;

import java.util.*;

public class LargestDistanceUnderSkyline {
    private static class EndPoint implements Comparable<EndPoint>{
        private static final Comparator<EndPoint> endPointComparator =
                Comparator.comparing(EndPoint::getPoint).thenComparing(EndPoint::isStart, Comparator.reverseOrder());
        private static final Comparator<EndPoint> endPointTreeComparator =
                Comparator.comparing(EndPoint::getHeight).thenComparing(EndPoint::getPoint).thenComparing(EndPoint::isStart,
                                                                           Comparator.reverseOrder());

        int point;
        Building building;
        boolean isStart;

        public EndPoint(int point,Building building, boolean isStart) {
            this.point = point;
            this.isStart = isStart;
            this.building=building;
        }

        public int getPoint() {
            return point;
        }

        public boolean isStart() {
            return isStart;
        }

        public int getHeight() {
            return building.height;
        }

        @Override
        public int compareTo(EndPoint o) {
            return endPointComparator.compare(this,o);
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("[");
            sb.append("x=").append(point);
            sb.append(",h=").append(building.height);
            sb.append(","+(isStart?'S':'E'));
            sb.append(']');
            return sb.toString();
        }
    }
    public static void main(String[] args) {
        Building[] buildings = new Building[]{

                new Building(1,4,1),
                new Building(2,7,3),
                new Building(5,9,4),
                new Building(6,10,2),
                new Building(8,15,3),
                new Building(11,13,6),
                new Building(12,18,1),
                new Building(14,17,2)
        };
        int[] skyline = getSkyline(buildings);
        ArrayUtils.printArray(skyline);
        int maxRectangleSize = getMaxRectangleSize(skyline);
        System.out.println(maxRectangleSize);

    }

    private static int getMaxRectangleSize(int[] skyline) {
        int max = Arrays.stream(skyline).max().getAsInt();

        int[][] heights = new int[skyline.length][max+1];

        int maxRectangleSize = 0;
        for(int i = 0; i< skyline.length; i++){
            for(int j = 1; j<= skyline[i]; j++){
                heights[i][j] = 1 +( i>0?heights[i-1][j]:0);
                maxRectangleSize = Math.max(j*heights[i][j],maxRectangleSize);
            }
        }
        ArrayUtils.print2DArray(heights);
        return maxRectangleSize;
    }

    private static int[] getSkyline(Building[] buildings) {
        Map<Integer,EndPoint> endPoints = new HashMap<>() ;
        int wp=0;
        int max=0;
        for(int i=0;i<buildings.length;i++){
            endPoints.put(buildings[i].start,  new EndPoint(buildings[i].start, buildings[i],true));
            endPoints.put(buildings[i].end,  new EndPoint(buildings[i].end,buildings[i],false));
            max=Math.max(max,buildings[i].end);
        }
        System.out.println(endPoints.size());

        int[] heights = new int[max+1];

        TreeSet<EndPoint> tree = new TreeSet<>(EndPoint.endPointTreeComparator);
        for(int i=0;i<max;i++){
            EndPoint endPoint = endPoints.get(i);

            if(endPoint!=null){
                if(endPoint.isStart){
                    tree.add(endPoint);
                }else {
                    EndPoint start = new EndPoint(endPoint.building.start, endPoint.building, true);
                    tree.remove(start);
                }
            }


            if(tree.size()>0){

                EndPoint maxEnd = tree.descendingIterator().next();
                heights[i] = maxEnd.building.height;
            }
        }

        return heights;
    }
}
