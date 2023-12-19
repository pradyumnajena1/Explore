package epp.graph;

import java.util.List;

public class GraphUtils {
    public static AdjacencyListGraph createRandomGraph(String[] nodeValues,int[][] adjacencyMatrix){
        AdjacencyListGraph graph = createRandomGraph(nodeValues,adjacencyMatrix,false,false);
         return graph;
    }
     public static AdjacencyListGraph createRandomGraph(String[] nodeValues,int[][] adjacencyMatrix,boolean directed,
                                                 boolean cycleOfUnitLengthAllowed){
        if(nodeValues==null || adjacencyMatrix==null ){
            throw new IllegalArgumentException("nodeValues and adjacencyMatrix cant be null");
        }
         if(nodeValues.length!=adjacencyMatrix.length && adjacencyMatrix.length!=adjacencyMatrix[0].length){
             throw new IllegalArgumentException("adjacencyMatrix must be a square matrix of length equal to " +
                     "nodeValues length");
         }

         AdjacencyListGraph graph = new AdjacencyListGraph(directed);
         for (String value:nodeValues){
             graph.addNode(new GraphNode(value));

         }
         for(int i=0;i<adjacencyMatrix.length;i++){
             for(int j=0;j<adjacencyMatrix[0].length;j++){
                 if(( i!=j || cycleOfUnitLengthAllowed) &&   adjacencyMatrix[i][j] ==1){

                     graph.addEdge(nodeValues[i],nodeValues[j]);
                 }
             }
         }
         return graph;
     }

}
