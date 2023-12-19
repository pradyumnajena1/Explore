package epp.graph;

import java.util.*;
import java.util.stream.Collectors;

public class AdjacencyListGraphWithEdge {
    private List<GraphNodeWithEdge> nodes;
    private boolean directed;

    public AdjacencyListGraphWithEdge(boolean directed){
        nodes = new ArrayList<>();
        this.directed = directed;
    }
    public void addNode(GraphNode node){
        if(nodes.contains(node)){
            System.out.println("node is already present!");
            return;
        }
        nodes.add(new GraphNodeWithEdge(node.value));
    }
    public List<String> getAllNodeValues(){
        return nodes.stream().map(x->x.value).collect(Collectors.toList());
    }
    public List<GraphNodeWithEdge> getAllNodes(){
        return nodes.stream().collect(Collectors.toList());
    }
    public Set<Edge> getAllEdges(){
        Set<Edge> allEdges = new HashSet<>();
        for(GraphNodeWithEdge node:nodes){
            allEdges.addAll(node.neighbours);
        }
        return allEdges;
    }

    public void addEdge(String source, String destination, Map<String,Object> additionalEdgeData){
        addEdge(new GraphNodeWithEdge(source),new GraphNodeWithEdge(destination),additionalEdgeData );
    }
    public void addEdge(GraphNodeWithEdge source,GraphNodeWithEdge destination,Map<String,Object> additionalEdgeData){
        if(source==null||destination==null){
            throw new IllegalArgumentException("source or destination cant be null");
        }

        GraphNodeWithEdge sourceNode = findNode(source.value);
        GraphNodeWithEdge destinationNode = findNode(destination.value);

        if(sourceNode==null){
            nodes.add(sourceNode =  new GraphNodeWithEdge(source.value) );

        }
        if(destinationNode==null){
            nodes.add(destinationNode = new GraphNodeWithEdge(destination.value));
        }
        if(findNodeInNeighbours(destinationNode.value,sourceNode.neighbours)==null){

            sourceNode.neighbours.add( new Edge(sourceNode, destinationNode,additionalEdgeData));
        }
        if(!directed){
            if(findNodeInNeighbours(sourceNode.value,destinationNode.neighbours)==null){

                destinationNode.neighbours.add(new Edge(destinationNode, sourceNode,additionalEdgeData));
            }
        }
    }
    public GraphNodeWithEdge findNode(String value){
        return findNode(value, nodes);
    }

    private GraphNodeWithEdge findNode(String value, List<GraphNodeWithEdge> nodeList) {
        Optional<GraphNodeWithEdge> first = nodeList.stream().filter(x -> x.value.equals(value)).findFirst();
        if(first.isPresent()){
            return first.get();
        }
        return null;
    }

    private GraphNodeWithEdge findNodeInNeighbours(String value, List<Edge> nodeList) {
        Optional<Edge> first = nodeList.stream().filter(x -> x.targetNode.value.equals(value)).findFirst();
        if(first.isPresent()){
            return first.get().targetNode;
        }
        return null;
    }

    public GraphNodeWithEdge findNode(GraphNodeWithEdge node){
        return findNode(node.value);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AdjacencyListGraphWithEdge{");
        sb.append(System.lineSeparator());
        nodes.forEach(x->sb.append(x).append(System.lineSeparator()));
        sb.append('}');
        return sb.toString();
    }
}
