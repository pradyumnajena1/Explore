package epp.graph;

import java.util.*;

public class GraphNodeWithEdge {
    String value;
    List<Edge> neighbours;
    Map<String,Object> additionalNodeData = new HashMap<>();


    public GraphNodeWithEdge(String value) {
        this(value,null);
    }

    public GraphNodeWithEdge(String value, List<Edge> neighbours) {
        this(value,neighbours,null);
    }

    public GraphNodeWithEdge(String value, List<Edge> neighbours, Map<String, Object> additionalNodeData) {
        this.value = value;
        this.neighbours = neighbours==null?new ArrayList<>():neighbours;
        this.additionalNodeData = additionalNodeData==null?new HashMap<>():additionalNodeData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GraphNodeWithEdge graphNode = (GraphNodeWithEdge) o;
        return Objects.equals(value, graphNode.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("GraphNode{");
        sb.append("value='").append(value).append('\'').append(" additionalNodeData=").append(additionalNodeData);
        sb.append(", neighbours=[ ");
        neighbours.stream().forEach(x-> sb.append(x).append(" ,"));
        sb.append("]}");
        return sb.toString();
    }


}
