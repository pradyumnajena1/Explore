package leetcode.hard;

import java.util.HashMap;
import java.util.Map;

public class GraphEdge {
    private GraphNode source;
    private GraphNode destination;
    private Map<String,Object> edgeAttributes;




    public GraphEdge(GraphNode source, GraphNode destination) {
       this(source,destination,new HashMap<>());
    }

    public GraphEdge(GraphNode source, GraphNode destination, Map<String, Object> edgeAttributes) {
        if(source==null||destination==null){
            throw new IllegalArgumentException("nodes cant be null");
        }
        this.source = source;
        this.destination = destination;
        this.edgeAttributes = new HashMap<>( edgeAttributes);
    }

    public GraphNode getSource() {
        return source;
    }

    public GraphNode getDestination() {
        return destination;
    }
    public Object getAttribute(String attributeName){
        return edgeAttributes.get(attributeName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GraphEdge graphEdge = (GraphEdge) o;

        if (!source.equals(graphEdge.source)) return false;
        return destination.equals(graphEdge.destination);
    }

    @Override
    public int hashCode() {
        int result = source.hashCode();
        result = 31 * result + destination.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("GraphEdge{");
        sb.append("source=").append(source.getName());
        sb.append(", destination=").append(destination.getName());
        sb.append(", edgeAttributes=").append(edgeAttributes);
        sb.append('}');
        return sb.toString();
    }
}
