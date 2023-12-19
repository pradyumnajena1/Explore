package epp.hashmap.revision;

import java.util.StringJoiner;

public class Range {
    private int start;
    private int end;

    public Range(int start, int end) {
        this.start = start;
        this.setEnd(end);
    }

    public int dist() {
        return getEnd() - getStart() + 1;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Range.class.getSimpleName() + "[", "]").add("start=" + getStart()).add("end=" + getEnd()).toString();
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }
}
