package epp.recursion;

import java.util.ArrayList;
import java.util.List;

public class TowerOfHanoi {
    private static class Move{
        int source;
        int destination;
        int using;
        int disc;

        public Move(int source, int destination, int using, int disc) {

            this.source = source;
            this.destination = destination;
            this.using = using;
            this.disc = disc;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Move{");
            sb.append("source=").append(source);
            sb.append(", destination=").append(destination);
            sb.append(", using=").append(using);
            sb.append(", disc=").append(disc);
            sb.append('}');
            return sb.toString();
        }
    }
    public static void main(String[] args) {
        List<Move> moves = solveTowerOfHanoi(1,2,3,List.of(2,1));
        System.out.println(moves);
    }

    private static List<Move> solveTowerOfHanoi(int source, int destination, int using, List<Integer> discs) {
        ArrayList<Move> movesCollector = new ArrayList<>();
        solveTowerOfHanoi(source,destination,using,discs, movesCollector);
        return movesCollector;
    }

    private static void solveTowerOfHanoi(int source, int destination, int using, List<Integer> discs, List<Move> movesCollector) {
        if(discs.size()==1){
            movesCollector.add(new Move(source,destination,using,discs.get(discs.size()-1)));
        }else{
            solveTowerOfHanoi(source,using,destination,discs.subList(1,discs.size()),movesCollector);
            movesCollector.add(new Move(source,destination,using, discs.get(0)));
            solveTowerOfHanoi(using,destination,source,discs.subList(1,discs.size()),movesCollector);
        }
    }
}
