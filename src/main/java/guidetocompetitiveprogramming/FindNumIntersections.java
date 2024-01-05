package guidetocompetitiveprogramming;

import epp.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * sweeping line algo with segment tree
 */
public class FindNumIntersections {
    public static void main(String[] args) {
        List<Line> lines = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int rand = (int) (Math.random() * 10);
            boolean isHorizontal = rand < 5;
            int randy = 5 + (int) (Math.random() * 50);
            int randx = 5 + (int) (Math.random() * 50);
            int randLength = 5 + (int) (Math.random() * 30);
            Line line = isHorizontal ? Line.horizontalLine(randx, randx + randLength, randy) : Line.verticalLine(randx, randy, randy + randLength);
            lines.add(line);
        }
        lines.forEach(System.out::println);
        System.out.println(getNumIntersetcions(lines));


    }

    private static int getNumIntersetcions(List<Line> lines) {
        int maxY = lines.stream().mapToInt(line->line.end.getSecond()).max().getAsInt();
        int[] values = new int[maxY + 1];
        SegmentTree segmentTree = new SegmentTree(values, Integer::sum, 0);
        List<Event> events = getEvents(lines);

        Comparator<Event> eventComparator = Comparator.comparingInt((Event e) -> e.point.getFirst())
                .thenComparing(event -> !lines.get(event.lineIndex).isHorizontal())
                .thenComparing(event -> event.isStart);
        events.sort(eventComparator);
        events.forEach(System.out::println);
        int numIntersections = 0;
        for (Event event : events) {
            Line line = lines.get(event.lineIndex);
            if (line.isHorizontal()) {
                if (event.isStart) {

                    segmentTree.increment(line.start.getSecond(), 1);
                } else {
                    segmentTree.increment(line.start.getSecond(), -1);
                }
            } else {
                numIntersections += segmentTree.rangeResult(line.start.getSecond(), line.end.getSecond());
            }
        }
        return numIntersections;
    }

    private static List<Event> getEvents(List<Line> lines) {
        List<Event> events = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            Line line = lines.get(i);
            if (line.isHorizontal()) {

                events.add(new Event(line.start, i, true));
                events.add(new Event(line.end, i, false));

            } else {
                events.add(new Event(line.start, i, true));

            }

        }
        return events;
    }


    private static class Event {

        Pair<Integer, Integer> point;
        int lineIndex;

        boolean isStart;

        public Event(Pair<Integer, Integer> point, int lineIndex, boolean isStart) {
            this.point = point;
            this.lineIndex = lineIndex;
            this.isStart = isStart;
        }

        @Override
        public String toString() {
            return "Event{" +
                    "point=" + point +
                    ", lineIndex=" + lineIndex +
                    ", isStart=" + isStart +
                    '}';
        }
    }

    private static class Line {
        Pair<Integer, Integer> start;
        Pair<Integer, Integer> end;

        public Line(Pair<Integer, Integer> start, Pair<Integer, Integer> end) {
            this.start = start;
            this.end = end;
        }

        public Pair<Integer, Integer> getStart() {
            return start;
        }

        public Pair<Integer, Integer> getEnd() {
            return end;
        }

        boolean isHorizontal() {
            return Objects.equals(getStart().getSecond(), getEnd().getSecond());
        }


        @Override
        public String toString() {
            return "Line{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }

        static Line horizontalLine(int startX, int endX, int y) {
            return new Line(new Pair<>(startX, y), new Pair<>(endX, y));
        }

        static Line verticalLine(int x, int startY, int endY) {
            return new Line(new Pair<>(x, startY), new Pair<>(x, endY));
        }
    }
}
