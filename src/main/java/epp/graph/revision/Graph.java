package epp.graph.revision;

import epp.Pair;

import java.util.*;

public class Graph<T extends Comparable<T>> {
    private boolean directed;
    private TreeMap<GraphNode<T>, TreeSet<GraphNode<T>>> nodes = new TreeMap<>();
    private Map<Pair<GraphNode<T>,GraphNode<T>> , GraphEdge<T>> edges = new HashMap<>();

    public Graph(boolean directed) {
        this.directed = directed;
    }

    public boolean addNode(GraphNode<T> node) {
        if (nodes.containsKey(node)) {
            return false;
        }
        nodes.put(node, new TreeSet<>());
        return true;
    }

    public boolean addNode(T nodeValue) {
        GraphNode<T> node = getNode(nodeValue);
        if (node == null) {
            node = new GraphNode<>(nodeValue);
        }
        return addNode(node);
    }

    public boolean addEdge(GraphNode<T> source, GraphNode<T> destination) {
        nodes.putIfAbsent(source, new TreeSet<>());
        nodes.putIfAbsent(destination, new TreeSet<>());
        if (directed) {
            boolean success = nodes.get(source).add(destination);
            if(success){

                edges.put(new Pair<>(source,destination),new GraphEdge<>(source,destination));
            }
            return success;
        } else {
            boolean success = nodes.get(source).add(destination) &&
                    nodes.get(destination).add(source);
            if(success){
                edges.put(new Pair<>(source,destination),new GraphEdge<>(source,destination));
                edges.put(new Pair<>(destination,source),new GraphEdge<>(destination,source));
            }

            return success;
        }

    }

    public boolean addEdge(T source, T destination) {
        GraphNode<T> sourceNode = getNode(source);
        if (sourceNode == null) {
            sourceNode = new GraphNode<>(source);
        }
        GraphNode<T> destinationNode = getNode(destination);
        if (destinationNode == null) {
            destinationNode = new GraphNode<>(destination);
        }
        return addEdge(sourceNode, destinationNode);
    }

    public boolean addEdge(GraphEdge<T> edge) {
        return addEdge(edge.getSource(), edge.getDestination());
    }
    public GraphEdge<T> getEdge(T source,T destination){
        GraphNode<T> sourceNode = getNode(source);
        GraphNode<T> destinationNode = getNode(destination);
        GraphEdge<T> graphEdge = edges.get(new Pair<>(sourceNode, destinationNode));
        return graphEdge;
    }
    public Collection<GraphEdge<T>> getAllEdges(){
        return   edges.values();
    }
    public void addEdgeProperty(T source,T destination,String key,Object value){
        GraphEdge<T> edge = getEdge(source, destination);
        edge.getProperties().put(key,value);
    }

    public boolean removeEdge(GraphEdge<T> edge) {
        return removeEdge(edge.getSource(), edge.getDestination());
    }

    public boolean removeEdge(T source, T destination) {
        GraphNode<T> sourceNode = getNode(source);
        if (sourceNode == null) {
            sourceNode = new GraphNode<>(source);
        }
        GraphNode<T> destinationNode = getNode(destination);
        if (destinationNode == null) {
            destinationNode = new GraphNode<>(destination);
        }
        return removeEdge(sourceNode, destinationNode);
    }

    private boolean removeEdge(GraphNode<T> source, GraphNode<T> destination) {
        if (!nodes.containsKey(source) || !nodes.containsKey(destination)) {
            throw new IllegalArgumentException("node are not in the graph");
        }
        if (directed) {
            TreeSet<GraphNode<T>> children = nodes.getOrDefault(source, new TreeSet<>());
            boolean success = children.remove(destination);
            return success;
        } else {
            TreeSet<GraphNode<T>> children = nodes.getOrDefault(source, new TreeSet<>());
            boolean success = children.remove(destination);
            if (!success) {
                return false;
            }
            //children  must be not null and  non empty
            children = nodes.get(destination);
            success = children.remove(source);
            assert success;
            return true;
        }

    }

    public Set<GraphNode<T>> getChildren(GraphNode<T> node) {
        return new TreeSet<>(nodes.getOrDefault(node, new TreeSet<>()));
    }

    public GraphNode<T> getNode(T nodeValue) {
        GraphNode<T> node = new GraphNode<>(nodeValue);
        Optional<GraphNode<T>> first = nodes.keySet().stream().filter(n -> n.equals(node)).findFirst();
        return first.orElse(null);
    }

    public Set<GraphNode<T>> isCyclePresent(Set<GraphNode<T>> excludingSet) {
        if (nodes.isEmpty()) {
            return null;
        }
        GraphNode<T> root;
        if (excludingSet == null) {
            excludingSet = new HashSet<>();
        }
        if (excludingSet.isEmpty()) {
            root = nodes.firstEntry().getKey();
        } else {
            Set<GraphNode<T>> finalExcludingSet = excludingSet;
            root = nodes.keySet().stream().filter(x -> !finalExcludingSet.contains(x)).findFirst().orElse(null);
        }
        if (root == null) {
            return null;
        }
        return dfsForCycle(root, excludingSet);
    }

    private Set<GraphNode<T>> dfsForCycle(GraphNode<T> root, Set<GraphNode<T>> excludingSet) {
        Set<GraphNode<T>> visitedSet = new HashSet<>();
        Map<GraphNode<T>, GraphNode<T>> parentMap = new HashMap<>();
        return dfsForCycleHelper(root, null, visitedSet, excludingSet, parentMap);
    }

    private Set<GraphNode<T>> dfsForCycleHelper(GraphNode<T> root, GraphNode<T> parent, Set<GraphNode<T>> visitedSet,
                                                Set<GraphNode<T>> excludingSet, Map<GraphNode<T>, GraphNode<T>> parentMap) {
        visitedSet.add(root);
        parentMap.put(root, parent);
        Set<GraphNode<T>> children = getChildren(root);
        for (GraphNode<T> child : children) {
            if(!excludingSet.contains(child)){
                if (!visitedSet.contains(child)  ) {
                    Set<GraphNode<T>> cycleNodes = dfsForCycleHelper(child, root, visitedSet, excludingSet, parentMap);
                    if(cycleNodes!=null){
                        return cycleNodes;
                    }
                } else {
                    if(child!=parent){
                        Set<GraphNode<T>> cycleSet = new HashSet<>();
                        GraphNode<T> current = root;
                        do{
                            cycleSet.add(current);
                            current = parentMap.get(current);

                        }while (current!=child);
                        cycleSet.add(current);
                        return cycleSet;
                    }

                }
            }

        }

        return null;
    }

    public boolean checkIfGraphIsConnected() {
        if (getNumNodes() == 0) {
            return true;
        }
        GraphNode<T> root = nodes.firstKey();
        Queue<GraphNode<T>> bfsQueue = new ArrayDeque<>();
        Set<GraphNode<T>> visitedSet = new HashSet<>();
        bfsQueue.offer(root);
        visitedSet.add(root);
        while (!bfsQueue.isEmpty()) {
            GraphNode<T> polled = bfsQueue.poll();
            Set<GraphNode<T>> children = getChildren(polled);
            for (GraphNode<T> child : children) {
                if (!visitedSet.contains(child)) {
                    visitedSet.add(child);
                    bfsQueue.offer(child);
                }
            }
        }
        return getNumNodes() == visitedSet.size();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Graph.class.getSimpleName() + "[", "]")
                .add("directed=" + directed)
                .add("nodes=" + nodes)
                .toString();
    }

    public int getNumNodes() {
        return nodes.size();
    }
    public Set<GraphNode<T>> getAllNodes(){
        return new HashSet<>(nodes.keySet());
    }

    public boolean isDirectedGraph() {
        return directed;
    }
}
