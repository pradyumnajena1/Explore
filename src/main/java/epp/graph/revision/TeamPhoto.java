package epp.graph.revision;

import epp.array.ArrayUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * team x connected to team y iff x<y
 *
 */
public class TeamPhoto {
    public static void main(String[] args) {
        Graph<Team> graph = new Graph<>(true);

        int teamId = 1;
        Team[] teams = new Team[5];
        for(int i=0;i<teams.length;i++){
            teams[i] = new Team(ArrayUtils.randomArray(3,1,20),"Team_"+teamId++);
        }
        for(int i=0;i<teams.length;i++){
            for(int j=i+1;j<teams.length;j++){

                int compare = Arrays.compare(teams[i].getHeights(), teams[j].getHeights());
                if(compare <0){
                    graph.addEdge(teams[i], teams[j]);
                }else if(compare>0){
                    graph.addEdge(teams[j], teams[i]);
                }
            }
        }
       int teamsForPhoto =  getMaxTeamsForPhoto(graph);
        System.out.println( teamsForPhoto);

    }

    private static int getMaxTeamsForPhoto(Graph<Team> graph) {
        List<GraphNode<Team>> topologicalSort = TopologicalSorting.getTopologicalSort(graph);
        System.out.println( topologicalSort.stream().map(x->x.getData()).collect(Collectors.toList()));
        int maxDistance = 0;
        for(int i=0;i<topologicalSort.size();i++){
            GraphNode<Team> teamGraphNode = topologicalSort.get(i);
            maxDistance = Math.max(maxDistance,teamGraphNode.getData().distance);

            Set<GraphNode<Team>> children = graph.getChildren(teamGraphNode);
            for (Iterator<GraphNode<Team>> iterator = children.iterator(); iterator.hasNext(); ) {
                GraphNode<Team> next = iterator.next();
                next.getData().distance = Math.max(next.getData().distance,teamGraphNode.getData().distance+1);
            }
        }
        return maxDistance;
    }
    private static class Team implements Comparable<Team>{

        int[] heights;
        String teamId;
        int distance = 0;

        public Team(int[] heights, String teamId) {
            this.heights =   heights;
            Arrays.sort(this.heights);
            this.teamId = teamId;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Team.class.getSimpleName() + "[", "]")
                    .add("heights=" + Arrays.toString(heights))
                    .add("teamId='" + teamId + "'")
                    .toString();
        }

        @Override
        public int compareTo(Team o) {
            int compare = Arrays.compare(this.heights, o.heights);
            if(compare!=0){
                return compare;
            }
            return this.teamId.compareTo(o.teamId);
        }

        public int[] getHeights() {
            return heights;
        }

        public String getTeamId() {
            return teamId;
        }
    }
}
