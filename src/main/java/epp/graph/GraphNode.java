package epp.graph;

import java.util.*;

public class GraphNode {
    String value;
    List<GraphNode> neighbours;
    Map<String,Object> additionalNodeData = new HashMap<>();

    public GraphNode(String value) {
        this(value,null);
    }

    public GraphNode(String value, List<GraphNode> neighbours) {
        this(value,neighbours,null);
    }

    public GraphNode(String value, List<GraphNode> neighbours, Map<String, Object> additionalData) {
        this.value = value;
        this.neighbours = neighbours==null?new ArrayList<>():neighbours;
        this.additionalNodeData = additionalData==null?new HashMap<>():additionalData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GraphNode graphNode = (GraphNode) o;
        return Objects.equals(value, graphNode.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("GraphNode{");
        sb.append("value='").append(value).append('\'').append(" additionalData=").append(additionalNodeData);
        sb.append(", neighbours=[ ");
        neighbours.stream().forEach(x->sb.append(x.value+" ,"));
        sb.append("]}");
        return sb.toString();
    }


}
