package epp.honours;

import java.util.*;

public class FaultyMugs {

  public static void main(String[] args) {
    List<Jug> jugs = new ArrayList<Jug>();
    jugs.add(new Jug(230, 240));
    jugs.add(new Jug(290, 310));
    jugs.add(new Jug(500, 515));
    VolumeRange volumeRange = new VolumeRange(2100, 2300);
    System.out.println(canMeasure(jugs, volumeRange));
  }

  private static List<Jug> canMeasure(List<Jug> jugs, VolumeRange volumeRange) {
    Map<VolumeRange, Jug> cache = new HashMap<>();
    List<Jug> result = new ArrayList<>();
    canMeasure(jugs, volumeRange, cache);
    Jug current = cache.get(volumeRange);
    while (current != null) {
      result.add(current);
      volumeRange =  volumeRange.reduce(current);
      current = cache.get(volumeRange);
    }
    return result;
  }

  private static Jug canMeasure(
      List<Jug> jugs, VolumeRange volumeRange, Map<VolumeRange, Jug> cache) {
    if (volumeRange.low > volumeRange.high || volumeRange.low < 0 || volumeRange.high < 0) {
      return null;
    }
    if (!cache.containsKey(volumeRange)) {
      Jug last = null;
      for (Jug jug : jugs) {
        if (volumeRange.low <= jug.low && jug.high <= volumeRange.high) {
          last = jug;
          break;
        }
        VolumeRange newVolumeRange = volumeRange.reduce(jug);

        if (canMeasure(jugs, newVolumeRange, cache) != null) {
          last = jug;
          break;
        }
      }
      cache.put(volumeRange, last);
    }

    return cache.get(volumeRange);
  }

  private static class VolumeRange {
    int low;
    int high;

    public VolumeRange(int low, int high) {
      this.low = low;
      this.high = high;
    }
    public   VolumeRange reduce(Jug jug){
      return new VolumeRange( low - jug.low,   high - jug.high);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      VolumeRange that = (VolumeRange) o;
      return low == that.low && high == that.high;
    }

    @Override
    public int hashCode() {
      return Objects.hash(low, high);
    }
  }

  private static class Jug {
    int low;
    int high;

    public Jug(int low, int high) {
      this.low = low;
      this.high = high;
    }

    @Override
    public String toString() {
      return new StringJoiner(", ", Jug.class.getSimpleName() + "[", "]")
          .add("low=" + low)
          .add("high=" + high)
          .toString();
    }
  }
}
