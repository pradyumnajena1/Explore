package epp.honours;

import epp.ListUtils;
import java.util.Comparator;
import java.util.List;
import java.util.StringJoiner;

public class FindPairSumInAbsSorted {
  public static void main(String[] args) {
    List<Integer> values = ListUtils.randomList(10, -20, 20);
    values.sort(Comparator.comparingInt(Math::abs));
    System.out.println(values);

    Result result = findPairSumInAbsSorted(values, 10);
    System.out.println(result);
  }

  private static Result findPairSumInAbsSorted(List<Integer> values, int k) {
    Result result = findPairSumPositiveNegative(values, k);
    if (result.left == -1 && result.right == -1) {
      result = k > 0 ? findPairSumPositive(values, k) : findPairSumNegative(values, k);
    }
    return result;
  }

  private static Result findPairSumPositiveNegative(List<Integer> values, int k) {
    Result result = new Result(values.size() - 1, values.size() - 1);
    //find last positive
    while (result.right >= 0  && values.get(result.right) < 0) {
      result.right--;
    }
    //find last negative
    while (result.left >=0 && values.get(result.left) >= 0) {
      result.left--;
    }

    while (result.left >=0 &&  result.right >=0) {
      if (values.get(result.left) + values.get(result.right) == k) {
        return result;
      } else if (values.get(result.left) + values.get(result.right) > k) {
        do {
          result.right--;
        } while (   result.right >=0 && values.get(result.right) < 0);
      } else {
        do {
          result.left--;
        } while (result.left >=0   && values.get(result.left) >= 0);
      }
    }

    return new Result(-1, -1);
  }

  private static Result findPairSumNegative(List<Integer> values, int k) {
    Result result = new Result(0, values.size() - 1);
    while (result.left < result.right && values.get(result.left) >= 0) {
      result.left++;
    }
    while (result.left < result.right && values.get(result.right) >= 0) {
      result.right--;
    }
    while (result.left < result.right) {
      if (values.get(result.left) + values.get(result.right) == k) {
        return result;
      } else if (values.get(result.left) + values.get(result.right) > k) {
        do {
          result.left++;
        } while (result.left < result.right && values.get(result.left) >= 0);
      } else {
        do {
          result.right--;
        } while (result.left < result.right && values.get(result.right) >= 0);
      }
    }

    return new Result(-1, -1);
  }

  private static Result findPairSumPositive(List<Integer> values, int k) {
    Result result = new Result(0, values.size() - 1);
    while (result.left < result.right && values.get(result.left) < 0) {
      result.left++;
    }
    while (result.left < result.right && values.get(result.right) < 0) {
      result.right--;
    }
    while (result.left < result.right) {
      if (values.get(result.left) + values.get(result.right) == k) {
        return result;
      } else if (values.get(result.left) + values.get(result.right) < k) {
        do {
          result.left++;
        } while (result.left < result.right && values.get(result.left) < 0);
      } else {
        do {
          result.right--;
        } while (result.left < result.right && values.get(result.right) < 0);
      }
    }

    return new Result(-1, -1);
  }

  private static class Result {
    int left;
    int right;

    public Result(int left, int right) {
      this.left = left;
      this.right = right;
    }

    @Override
    public String toString() {
      return new StringJoiner(", ", Result.class.getSimpleName() + "[", "]")
              .add("start=" + left)
              .add("end=" + right)
              .toString();
    }
  }
}
