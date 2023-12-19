package epp.graph.revision;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class GraphEdge <T extends Comparable<T>>{
    private GraphNode<T> source;
    private GraphNode<T> destination;
    private Map<String,Object> additionalEdgeProperties = new HashMap<>();

    public GraphEdge(GraphNode<T> source, GraphNode<T> destination) {
        this.source = source;
        this.destination = destination;
    }

    public GraphEdge(T source, T destination) {
        this.source =  new GraphNode<>( source);
        this.destination = new GraphNode<>( destination);
    }

    public GraphNode<T> getSource() {
        return source;
    }

    public GraphNode<T> getDestination() {
        return destination;
    }

    public Map<String, Object> getProperties() {
        return additionalEdgeProperties;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", GraphEdge.class.getSimpleName() + "[", "]")
                .add("source=" + source)
                .add("destination=" + destination)
                .add("additionalEdgeProperties=" + additionalEdgeProperties)
                .toString();
    }
}
