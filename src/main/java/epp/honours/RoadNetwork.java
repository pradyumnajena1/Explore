package epp.honours;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;

public class RoadNetwork {

  public static void main(String[] args) {
    ArrayList<HighwaySection> existing = new ArrayList<>();
    existing.add(new HighwaySection(0, 1, 10.0));
    existing.add(new HighwaySection(1, 2, 20.0));
    existing.add(new HighwaySection(2, 3, 10.0));
    existing.add(new HighwaySection(0, 3, 12.0));
    existing.add(new HighwaySection(3, 4, 40.0));
    existing.add(new HighwaySection(4, 5, 20.0));
    existing.add(new HighwaySection(2, 5, 25.0));
    ArrayList<HighwaySection> proposals = new ArrayList<>();
    proposals.add(new HighwaySection(0, 4, 5.0));
    proposals.add(new HighwaySection(1, 5, 2.0));
    proposals.add(new HighwaySection(1, 3, 3.0));
    HighwaySection best = findBestProposal(existing, proposals);
    System.out.println(best);
  }

  private static HighwaySection findBestProposal(
      List<HighwaySection> existing, List<HighwaySection> proposals) {
    List<List<Double>> G = new ArrayList<>();
    for (int i = 0; i < existing.size(); i++) {
      G.add(new ArrayList<>(Collections.nCopies(existing.size(), Double.MAX_VALUE)));
    }
    for (int i = 0; i < existing.size(); i++) {
      G.get(i).set(i, 0.0);
    }
    for (HighwaySection section : existing) {
      G.get(section.x).set(section.y, section.distance);
    }
    floydWarshall(G);
    HighwaySection bestSection = getBestHighwaySection(proposals, G);

    return bestSection;
  }

  private static HighwaySection getBestHighwaySection(List<HighwaySection> proposals, List<List<Double>> G) {
    double bestDistanceSaving = Double.MIN_VALUE;
    HighwaySection bestSection = new HighwaySection(-1, -1, 0);
    for (HighwaySection proposed : proposals) {
      double proposalSaving = 0.0;
      for (int i = 0; i < G.size(); i++) {
        for (int j = 0; j < G.size(); j++) {
          double saving =
              G.get(i).get(j)
                  - (G.get(i).get(proposed.x) + proposed.distance + G.get(proposed.y).get(j));
          proposalSaving += saving > 0.0 ? saving : 0.0;
        }
      }
      if (proposalSaving > bestDistanceSaving) {
        bestDistanceSaving = proposalSaving;
        bestSection = proposed;
      }
    }
    return bestSection;
  }

  private static void floydWarshall(List<List<Double>> G) {
    int n = G.size();
    for (int k = 0; k < n; k++) {
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          if (G.get(i).get(k) != Double.MAX_VALUE
              && G.get(k).get(j) != Double.MAX_VALUE
              && G.get(i).get(k) + G.get(k).get(j) < G.get(i).get(j)) {
            G.get(i).set(j, G.get(i).get(k) + G.get(k).get(j));
          }
        }
      }
    }
  }

  private static class HighwaySection {
    int x;
    int y;
    double distance;

    public HighwaySection(int x, int y, double distance) {
      this.x = x;
      this.y = y;
      this.distance = distance;
    }

    @Override
    public String toString() {
      return new StringJoiner(", ", HighwaySection.class.getSimpleName() + "[", "]")
              .add("x=" + x)
              .add("y=" + y)
              .add("distance=" + distance)
              .toString();
    }
  }
}
