package epp.graph.revision;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

public class GraphNode<T extends Comparable<T>> implements Comparable<GraphNode<T>> {
    private T data;
    private Map<String,Object> additionalNodeProperties = new HashMap<>();

    public GraphNode(T data) {
        if(data==null){
            throw new IllegalArgumentException("data must not be null");
        }
        this.data = data;

    }

    @Override
    public int compareTo(GraphNode<T> o) {
        return data.compareTo(o.data);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", GraphNode.class.getSimpleName() + "[", "]")
                .add("data=" + data)
                .add("additionalNodeProperties=" + additionalNodeProperties)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GraphNode<?> graphNode = (GraphNode<?>) o;

        return Objects.equals(data, graphNode.data);
    }

    @Override
    public int hashCode() {
        return data != null ? data.hashCode() : 0;
    }
    public void addProperty(String key,Object value){
        additionalNodeProperties.put(key,value);
    }
    public Object getProperty(String key){
        return additionalNodeProperties.get(key);
    }

    public T getData() {
        return data;
    }
}
