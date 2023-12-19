package epp.graph.revision;

import java.util.*;
import java.util.stream.Collectors;

public class CloneAGraph {
    public static void main(String[] args) {
        GraphNode node = new GraphNode("node1");
        GraphNode node2 = new GraphNode("node2");
        node.neighbours.add(node2);
        GraphNode node3 = new GraphNode("node3");
        node2.neighbours.add(node3);
        node3.neighbours.add(node2);
        GraphNode cloned =  cloneGraph(node);
    }

    private static GraphNode cloneGraph(GraphNode node) {
        Queue<GraphNode> bfsQueue = new ArrayDeque<>();
        Set<GraphNode> visistedSet = new HashSet<>();
        Map<String,GraphNode> clonedSet = new HashMap<>();
        bfsQueue.offer(node);
        visistedSet.add(node);
        clonedSet.put(node.data,new GraphNode(node.data));
        while (!bfsQueue.isEmpty()){
            GraphNode polled = bfsQueue.poll();
            List<GraphNode> neighbours = polled.neighbours;
            for(GraphNode neighbour:neighbours){

                if(!visistedSet.contains(neighbour)){
                    visistedSet.add(neighbour);
                    bfsQueue.offer(neighbour);

                    GraphNode newNode = new GraphNode(neighbour.data);
                    clonedSet.put(newNode.data, newNode);
                }
            }
            GraphNode polledClone = clonedSet.get(polled.data);
            for(GraphNode neighbour:neighbours){
                GraphNode neighbourClone = clonedSet.get(neighbour.data);
                polledClone.neighbours.add(neighbourClone);
            }


        }
        System.out.println(clonedSet);
        return clonedSet.get(node.data);
    }

    private static class GraphNode {
        String data;
        List<GraphNode> neighbours=new ArrayList<>();

        public GraphNode(String data) {
            this.data=data;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            GraphNode graphNode = (GraphNode) o;

            return Objects.equals(data, graphNode.data);
        }

        @Override
        public int hashCode() {
            return data != null ? data.hashCode() : 0;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", GraphNode.class.getSimpleName() + "[", "]")
                    .add("data='" + data + "'")
                    .add("neighbours=" + neighbours.stream().map(x->x.data).collect(Collectors.toList()))
                    .toString();
        }
    }
}
