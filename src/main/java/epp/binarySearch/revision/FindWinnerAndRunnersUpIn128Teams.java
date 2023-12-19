package epp.binarySearch.revision;

import epp.Pair;

import java.util.*;

public class FindWinnerAndRunnersUpIn128Teams {
    public static void main(String[] args) {
        Team[] teams = new Team[128];
        for(int i=0;i< teams.length;i++){
            teams[i] = new Team(i+1);
        }
        Pair<Team,Team> winnerAndRunnersUp = findWinnerAndRunnersUp(teams);
        System.out.println(winnerAndRunnersUp);

    }

    private static Pair<Team, Team> findWinnerAndRunnersUp(Team[] teams) {
         List< Pair<Team,Team>> matches = new ArrayList<>();
        for(int i=0;i+1<teams.length;i+=2){
            matches.add( new Pair<>(teams[i],teams[i+1]));
        }
        Team winner = null;
        Map<Team,List<Team>> playedAgainst = new HashMap<>();
        while (!matches.isEmpty()){
            List< Pair<Team,Team>> newMatches = new ArrayList<>();
            if(matches.size()==1){
                Pair<Team, Team> match = matches.get(0);
                winner = playMatch(match);
                addToPlayedAgainst(playedAgainst, winner, match);
                break;
            }else{
                for(int i=0;i+1< matches.size();i+=2){
                    Team winner1 = playMatch(matches.get(i));
                    addToPlayedAgainst(playedAgainst,winner1,matches.get(i));
                    Team winner2 = playMatch(matches.get(i+1));
                    addToPlayedAgainst(playedAgainst,winner2,matches.get(i+1));
                    newMatches.add(new Pair<>(winner1,winner2));
                }
                matches = newMatches;
            }

        }
        List<Team> teamsBeatenByWinner = playedAgainst.get(winner);
        Team runner = teamsBeatenByWinner.get(0);
        for(int i=1;i< teamsBeatenByWinner.size();i++){
            runner = playMatch(new Pair<>(runner,teamsBeatenByWinner.get(i)));
        }
        System.out.println(teamsBeatenByWinner);
        return new Pair<>(winner,runner);
    }

    private static void addToPlayedAgainst(Map<Team, List<Team>> playedAgainst, Team winner, Pair<Team, Team> match) {
        List<Team> against  = playedAgainst.getOrDefault(winner, new ArrayList<>());
        against.add(match.getFirst().teamId== winner.teamId? match.getSecond(): match.getFirst());
        playedAgainst.put(winner,against);
    }

    private static Team playMatch(Pair<Team, Team> teamTeamPair) {
        int random = (int) (Math.random() * 2);
        return random ==0?teamTeamPair.getFirst():teamTeamPair.getSecond();
    }

    private static class Team{
        int teamId;

        public Team(int teamId) {
            this.teamId = teamId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Team team = (Team) o;

            return teamId == team.teamId;
        }

        @Override
        public int hashCode() {
            return teamId;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Team.class.getSimpleName() + "[", "]")
                    .add("teamId=" + teamId)
                    .toString();
        }
    }
}
