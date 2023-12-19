package leetcode.hard;

import java.util.ArrayList;
import java.util.List;

class Point {
    int row;
    int col;


    public Point(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (row != point.row) return false;
        return col == point.col;
    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + col;
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("(");
        sb.append(row);
        sb.append(",").append(col);
        sb.append(')');
        return sb.toString();
    }

    public static List< Point> getNeighbourPoints(Point point, int maxRow, int maxCol) {
        ArrayList< Point> points = new ArrayList<>();
        for (int i = -1; i <= 1; i++)
            for (int j = -1; j <= 1; j++) {

                if (i * j == 0 && i + j != 0) {
                    if (point.row + i >= 0 && point.row + i <= maxRow && point.col + j >= 0 && point.col + j <= maxCol) {
                        Point newPoint = new  Point(point.row + i, point.col + j);
                        points.add(newPoint);
                    }
                }
            }

        return points;
    }
}
