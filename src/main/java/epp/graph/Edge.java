package epp.graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Edge {
    GraphNodeWithEdge sourceNode;
    GraphNodeWithEdge targetNode;
    Map<String, Object> additionalEdgeData = new HashMap<>();

    public Edge(GraphNodeWithEdge sourceNode,GraphNodeWithEdge targetNode, Map<String, Object> additionalEdgeData) {
        this.sourceNode = sourceNode;
        this.targetNode = targetNode;
        this.additionalEdgeData = additionalEdgeData;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Edge{");
        sb.append("sourceNode=").append(sourceNode.value);
        sb.append(", targetNode=").append(targetNode.value);
        sb.append(", additionalEdgeData=").append(additionalEdgeData);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return Objects.equals(sourceNode, edge.sourceNode) && Objects.equals(targetNode, edge.targetNode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sourceNode, targetNode);
    }
}
