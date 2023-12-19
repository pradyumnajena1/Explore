package epp.sorting;

public class Event implements Comparable<Event> {
    int start;
    int end;

    public Event(int start, int end) {
        if (start >= end) {
            throw new IllegalStateException("start cant be greater than end start: " + start + " end: " + end);
        }
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Event{");
        sb.append("start=").append(start);
        sb.append(", end=").append(end);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int compareTo(Event o) {
        return Integer.compare(end, o.end);
    }
}
