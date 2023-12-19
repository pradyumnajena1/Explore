package epp.graph.revision;

public class GraphUtils {
    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<>(true);
        System.out.println(graph);
        graph.addEdge(Integer.valueOf(1),Integer.valueOf(2));
         graph.addEdge(Integer.valueOf(2),Integer.valueOf(3));
        graph.addEdge(Integer.valueOf(2),Integer.valueOf(4));
         graph.addEdge(Integer.valueOf(2),Integer.valueOf(5));
        graph.addEdge(Integer.valueOf(5),Integer.valueOf(1));
        System.out.println(graph);

    }
}
