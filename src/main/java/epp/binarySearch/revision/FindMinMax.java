package epp.binarySearch.revision;

import epp.array.ArrayUtils;

import java.util.Arrays;
import java.util.List;

public class FindMinMax {
  public static void main(String[] args) {

    MinMax minMax = findMinMax(Arrays.asList(1,2,3,4,5,6,7,8,9));
    System.out.println(minMax);
  }

  private static<T extends Comparable<T>> MinMax<T> findMinMax(List<T> values) {
    if (values.size() == 1) {
      return MinMax.minMax(values.get(0),values.get(0));
    }
    MinMax result = MinMax.minMax (values.get(0), values.get(1));
    // 3 compare for processing 2 elements, total (n/2)*3 = 3n/2
    for (int i = 2; i + 1 < values.size(); i += 2) {
      MinMax local = MinMax.minMax(values.get(i), values.get(i+1));
      result.merge(local);
    }
    if (values.size() % 2 == 1) {
      result.merge(new MinMax(values.get(values.size() - 1), values.get(values.size() - 1)));
    }
    return result;
  }

  private static class MinMax<T extends Comparable<T>> {
    T min;
    T max;

    public MinMax(T min, T max) {
      this.min = min;
      this.max = max;
    }

    private static<T extends Comparable<T>> MinMax<T> minMax(T a, T b) {
      if (a.compareTo(b) < 0) {
       return new MinMax<T>(a, b);
      } else {
       return new MinMax<T>(b,a);
      }
    }

    public void merge(MinMax<T> other) {
      max = max.compareTo(other.max) < 0 ? other.max : max;
      min = min.compareTo(other.min) < 0 ? min : other.min;
    }

    @Override
    public String toString() {
      final StringBuffer sb = new StringBuffer("Result{");
      sb.append("min=").append(min);
      sb.append(", max=").append(max);
      sb.append('}');
      return sb.toString();
    }
  }
}
