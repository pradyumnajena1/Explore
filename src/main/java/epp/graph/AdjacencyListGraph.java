package epp.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AdjacencyListGraph {
    private List<GraphNode> nodes;
    private boolean directed;

    public AdjacencyListGraph(boolean directed){
        nodes = new ArrayList<>();
        this.directed = directed;
    }
    public void addNode(GraphNode node){
        if(nodes.contains(node)){
            System.out.println("node is already present!");
            return;
        }
        nodes.add(new GraphNode(node.value));
    }
    public List<String> getAllNodeValues(){
        return nodes.stream().map(x->x.value).collect(Collectors.toList());
    }
    public List<GraphNode> getAllNodes(){
        return nodes.stream().collect(Collectors.toList());
    }
    public void addEdge(String source,String destination){
        addEdge(new GraphNode(source),new GraphNode(destination));
    }
    public void addEdge(GraphNode source,GraphNode destination){
        if(source==null||destination==null){
            throw new IllegalArgumentException("source or destination cant be null");
        }

        GraphNode sourceNode = findNode(source.value);
        GraphNode destinationNode = findNode(destination.value);

        if(sourceNode==null){
            nodes.add(sourceNode =  new GraphNode(source.value) );

        }
        if(destinationNode==null){
            nodes.add(destinationNode = new GraphNode(destination.value));
        }
        if(findNode(destinationNode.value,sourceNode.neighbours)==null){

            sourceNode.neighbours.add(destinationNode);
        }
        if(!directed){
            if(findNode(sourceNode.value,destinationNode.neighbours)==null){

                destinationNode.neighbours.add(sourceNode);
            }
        }
    }
    public GraphNode findNode(String value){
        return findNode(value, nodes);
    }

    private GraphNode findNode(String value, List<GraphNode> nodeList) {
        Optional<GraphNode> first = nodeList.stream().filter(x -> x.value.equals(value)).findFirst();
        if(first.isPresent()){
            return first.get();
        }
        return null;
    }

    public GraphNode findNode(GraphNode node){
        return findNode(node.value);
    }
    public String getAsAdjMatrix(){
        StringBuilder sb = new StringBuilder();
        sb.append(getHeaderRow());
        for(int i=0;i<nodes.size();i++){
            sb.append(getRow(i));
        }
        return sb.toString();
    }

    private String getRow(int i) {
        StringBuilder sb = new StringBuilder();
        int maxNodeValueLength = getMaxNodeValueLength();
        GraphNode sourceNode = nodes.get(i);
        sb.append(prependSpaces(maxNodeValueLength, sourceNode.value));
        for(int j=0;j<nodes.size();j++){
            sb.append(" ");
            GraphNode targetNode = nodes.get(j);
            if(findNode(targetNode.value, sourceNode.neighbours)!=null){
                sb.append(prependSpaces(maxNodeValueLength,"1"));
            }else{
                sb.append(prependSpaces(maxNodeValueLength,"0"));
            }
        }
        sb.append(System.lineSeparator());
        return sb.toString();
    }

    private String getHeaderRow() {
        StringBuilder sb = new StringBuilder();
        int maxNodeValueLength = getMaxNodeValueLength();
        sb.append(prependSpaces(maxNodeValueLength,""));
        for(int i=0;i<nodes.size();i++){
            sb.append(" ");
            sb.append(prependSpaces(maxNodeValueLength, nodes.get(i).value));
        }
        sb.append(System.lineSeparator());
        return sb.toString();
    }
    private int getMaxNodeValueLength(){
      return   nodes.stream().map(x->x.value.length()).max(Integer::compareTo).get();
    }
    private String prependSpaces(int totalLength,String value){
        while (totalLength>value.length()){
            value = " "+value;
        }
        return value;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AdjacencyListGraph{");
        sb.append(System.lineSeparator());
        nodes.forEach(x->sb.append(x).append(System.lineSeparator()));
        sb.append('}');
        return sb.toString();
    }
}
