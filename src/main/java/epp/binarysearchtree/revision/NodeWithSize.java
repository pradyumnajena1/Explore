package epp.binarysearchtree.revision;

import java.util.StringJoiner;

class NodeWithSize<T extends Comparable<T>> implements Comparable<NodeWithSize<T>> {
    T data;
    int size;

    public NodeWithSize(T data, int size) {
        this.data = data;
        this.size = size;
    }

    public NodeWithSize(T data) {
        this.data = data;
    }

    @Override
    public int compareTo(NodeWithSize<T> o) {
        return this.data.compareTo(o.data);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ",    "[", "]")
                .add("d=" + data)
                .add("s=" + size)
                .toString();
    }
}
