package epp.dp.revision;

import java.util.StringJoiner;

public class Result {
    int start;
    int end;
    int sum;

    public Result(int start, int end, int sum) {
        this.start = start;
        this.end = end;
        this.sum = sum;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Result.class.getSimpleName() + "[", "]")
                .add("start=" + start)
                .add("end=" + end)
                .add("sum=" + sum)
                .toString();
    }
}
