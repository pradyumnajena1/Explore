package epp.greedy.revision;

import epp.array.ArrayUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class TwoSumCheck {

  public static void main(String[] args) {
    int[] values = {5, 2, 3, 4, 3};
    ArrayUtils.printArray(values);
    List<Integer> hasTwoSum = hasTwoSum(values, 9);
    System.out.println(hasTwoSum);
    List<Integer> hasThreeSum = hasThreeSum(values, 9);
    System.out.println(hasThreeSum);
    SumCounter twoSumCounter = getTwoSumCounter(new int[] {1, 2, 3, 2, 3, 4, 5}, 6);
    System.out.println(twoSumCounter);

    twoSumCounter = countPairs(new int[] {1, 2, 3, 2, 3, 4, 5}, 6,0,6);
    System.out.println(twoSumCounter);

    List<Integer> closestTwoSum = closestTwoSum(new int[] {1, 2, 3, 2, 3, 4, 5}, 6);
    System.out.println(closestTwoSum);

    List<Integer> closestThreeSum = closestThreeSum(new int[] {1, 4, 9, 15, 21}, 34);
    System.out.println(closestThreeSum);
  }

  public static List<Integer> hasTwoSum(int[] values, int sum) {
    Arrays.sort(values);
    return hasTwoSum(values, sum, 0, values.length - 1);
  }

  /**
   * @param values sorted array
   * @param sum
   * @param start
   * @param end
   * @return
   */
  public static List<Integer> hasTwoSum(int[] values, int sum, int start, int end) {
    int left = start;
    int right = end;
    while (left < right) {
      int currentSum = values[left] + values[right];
      if (currentSum == sum) {
        return List.of(values[left], values[right]);
      } else if (currentSum < sum) {
        left++;
      } else {
        right--;
      }
    }
    return null;
  }

  public static List<Integer> closestThreeSum(int[] values, int sum) {
    Arrays.sort(values);
    List<Integer> result = new ArrayList<>();
    int minDistance = Integer.MAX_VALUE;
    for (int i = 0; i < values.length - 2; i++) {
      List<Integer> closestTwoSum =
          closestTwoSum(values, sum - values[i], i + 1, values.length - 1);
      if (closestTwoSum != null) {
        int distance = Math.abs(sum - (values[i] + closestTwoSum.get(0) + closestTwoSum.get(1)));
        if (distance < minDistance) {
          minDistance = distance;
          result = List.of(values[i], closestTwoSum.get(0), closestTwoSum.get(1));
        }
      }
    }
    return result;
  }

  public static List<Integer> closestTwoSum(int[] values, int sum) {
    Arrays.sort(values);
    return closestTwoSum(values, sum, 0, values.length - 1);
  }

  public static List<Integer> closestTwoSum(int[] values, int sum, int start, int end) {
    int left = start;
    int right = end;
    int minDistance = Integer.MAX_VALUE;
    List<Integer> result = null;
    while (left < right) {
      int currentSum = values[left] + values[right];
      int currentDistance = Math.abs(currentSum - sum);
      if (currentDistance < minDistance) {
        minDistance = currentDistance;
        result = List.of(values[left], values[right]);
      }
      if (currentSum == sum) {
        break;
      } else if (currentSum < sum) {
        left++;
      } else {
        right--;
      }
    }
    return result;
  }

  public static ArrayList<Integer> hasThreeSum(int[] values, int sum) {
    Arrays.sort(values);
    // cant use same index twice
    for (int i = 0; i < values.length - 2; i++) {
      List<Integer> twoSum = hasTwoSum(values, sum - values[i], i + 1, values.length - 1);
      if (twoSum != null) {
        ArrayList<Integer> threeSum = new ArrayList<>();
        threeSum.add(values[i]); // add the first number
        threeSum.addAll(twoSum); // add the two numbers from the twoSum result
        return threeSum;
      }
    }
    return null;
  }

  public static SumCounter getTwoSumCounter(int[] values, int sum) {
    Arrays.sort(values);
    return getTwoSumCounter(values, sum, 0, values.length - 1);
  }

  public static SumCounter getTwoSumCounter(int[] values, int sum, int start, int end) {
    int left = start;
    int right = end;
    SumCounter counter = new SumCounter( );
    while (left < right) {
      int currentSum = values[left] + values[right];
      if (currentSum == sum) {

          int leftCount = 1;
          while (left < right - 1 && values[left] == values[left + 1]) {
            left++;
            leftCount++;
          }
          int rightCount = 1;
          while (right > left + 1 && values[right] == values[right - 1]) {
            right--;
            rightCount++;
          }
          counter.equal += leftCount * rightCount;
          left++;
          right--;



      } else if (currentSum < sum) {
       // counter.smaller +=  right -left;
        left++;

      } else {
     //   counter.bigger += right-left;
        right--;
      }
    }

    // Reset pointers for counting pairs less and greater than target
    left = start;
    right = end;

    // Count pairs less than the target
    while (left < right) {
      if (values[left] + values[right] < sum) {
        counter.smaller += right - left;
        left++;
      } else {
        right--;
      }
    }

    // Reset pointers for counting pairs greater than target
    left = start;
    right = end;

    // Count pairs greater than the target
    while (left < right) {
      if (values[left] + values[right] > sum) {
        counter.bigger += right - left;
        right--;
      } else {
        left++;
      }
    }
    return counter;
  }


  public static SumCounter countPairs(int[] nums, int target,int start,int end) {
    Arrays.sort(nums);
    int left = start, right = end;
   SumCounter counter = new SumCounter();

    while (left < right) {
      int currentSum = nums[left] + nums[right];
      if (currentSum == target) {
        if (nums[left] == nums[right]) {
          int n = right - left + 1;
          counter.equal += (n * (n - 1)) / 2;
          break;
        } else {
          int countLeft = 1;
          int countRight = 1;
          while (left < right - 1 && nums[left] == nums[left + 1]) {
            left++;
            countLeft++;
          }
          while (right > left + 1 && nums[right] == nums[right - 1]) {
            right--;
            countRight++;
          }
          counter.equal += countLeft * countRight;
          left++;
          right--;
        }
      } else if (currentSum < target) {
       // counter.smaller += right - left;
        left++;
      } else {
       // counter.bigger += right - left;
        right--;
      }
    }
// Reset pointers for counting pairs less and greater than target
    left = start;
    right = end;

    // Count pairs less than the target
    while (left < right) {
      if (nums[left] + nums[right] < target) {
        counter.smaller += right - left;
        left++;
      } else {
        right--;
      }
    }

    // Reset pointers for counting pairs greater than target
    left = start;
    right = end;

    // Count pairs greater than the target
    while (left < right) {
      if (nums[left] + nums[right] > target) {
        counter.bigger += right - left;
        right--;
      } else {
        left++;
      }
    }
    return counter;
  }


  public static class SumCounter {
    int smaller;
    int equal;
    int bigger;

    public SumCounter(int smaller, int equal, int bigger) {
      this.smaller = smaller;
      this.equal = equal;
      this.bigger = bigger;
    }

    public SumCounter() {
    }

    @Override
    public String toString() {
      return new StringJoiner(", ", SumCounter.class.getSimpleName() + "[", "]")
              .add("less=" + smaller)
              .add("equal=" + equal)
              .add("greater=" + bigger)
              .toString();
    }
  }
}
