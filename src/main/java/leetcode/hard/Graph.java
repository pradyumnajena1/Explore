package leetcode.hard;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Graph {
    private Set<GraphNode> nodes;
    private Set<GraphEdge> edges;

    public Graph() {
        this(new HashSet<>(),new HashSet<>());
    }

    public Graph(Set<GraphNode> nodes, Set<GraphEdge> edges) {
        this.nodes = nodes;
        this.edges = edges;
    }
    public void addEdge(String source, String destination, Map<String,Object> attributes){
         GraphNode sourceNode = getNode(source) .or(()->Optional.of(new GraphNode(source))).get();
         GraphNode destNode = getNode(destination).or(()->Optional.of(new GraphNode(destination))).get();

        addEdge(new GraphEdge(sourceNode,destNode,attributes));
    }
    public void addEdge(GraphEdge edge){
        edges.add(edge);
        nodes.add(edge.getSource());
        nodes.add(edge.getDestination());
    }

    public Set<GraphNode> getNodes() {
        return nodes;
    }

    public Set<GraphEdge> getEdges() {
        return edges;
    }
    public Set<GraphNode> getNeighbours(String nodeName){
        Optional<GraphNode> graphNode =   getNode(nodeName);
        Set<GraphNode> result = new HashSet<>();
        graphNode.ifPresent(x->{
           result.addAll(    edges.stream().filter(edge->edge.getSource().equals(x)).map(edge->edge.getDestination()).collect(Collectors.toSet()));
        } );
        return result;
    }

    public Optional<GraphNode> getNode(String nodeName) {
        return nodes.stream().filter(x->x.getName().equals(nodeName)).findFirst();
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Graph{");
        sb.append("nodes=").append(nodes).append(System.lineSeparator());
        sb.append(", edges=").append(edges).append(System.lineSeparator());
        sb.append('}');
        return sb.toString();
    }
}
