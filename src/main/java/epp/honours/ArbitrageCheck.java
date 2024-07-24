package epp.honours;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArbitrageCheck {

  public static void main(String[] args) {
    List<List<Double>> edges = new ArrayList<>();
    edges.add(new ArrayList<>( List.of(1.0, 0.5, 0.7, 0.8, 0.6)));
    edges.add(new ArrayList<>( List.of(1.0, 0.5, 0.7, 0.8, 0.6)));
    edges.add(new ArrayList<>( List.of(1.0, 0.5, 1.1, 0.8, 0.6)));
    edges.add(new ArrayList<>( List.of(0.2, 0.5, 1.0, 0.8, 0.6)));
    edges.add(new ArrayList<>( List.of(1.0, 1.0, 0.7, 0.8, 0.6)));

    boolean isArbitrage = checkArbitrage(edges, 0);
    System.out.println(isArbitrage);
  }

  private static boolean checkArbitrage(List<List<Double>> edges, int source) {
    for (int i = 0; i < edges.size(); i++) {
      for (int j = 0; j < edges.get(i).size(); j++) {
        double weight = edges.get(i).get(j);
        edges.get(i).set(j, -Math.log10(weight));
      }
    }
    return bellman(edges, 0);
  }

  private static boolean bellman(List<List<Double>> edges, int source) {

    List<Double> distances =
        new ArrayList<>(Collections.nCopies(edges.size(), Double.MAX_VALUE));
    distances.set(source, 0.0);

    for (int times = 1; times < edges.size(); times++) {
      boolean haveUpdate = false;
      for (int i = 0; i < edges.size(); i++) {
        for (int j = 0; j < edges.get(i).size(); j++) {
          double weight = edges.get(i).get(j);
          if (distances.get(i) + weight < distances.get(j)) {
            distances.set(j, distances.get(i) + weight);
            haveUpdate = true;
          }
        }
      }
      if (!haveUpdate) {
        return false;
      }
    }

    for (int i = 0; i < edges.size(); i++) {
      for (int j = 0; j < edges.get(i).size(); j++) {
        double weight = edges.get(i).get(j);
        if (distances.get(i) + weight < distances.get(j)) {
          return true;
        }
      }
    }
    return false;
  }
}
