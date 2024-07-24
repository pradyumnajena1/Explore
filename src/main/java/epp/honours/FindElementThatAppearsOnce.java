package epp.honours;

/**
 * Given an integer array, in which each entry but one appears in triplicate, with the remaining
 * element appearing once, find the element appearing once. For example, if the array is (2,4,
 * 2,5,2,5,5), you should return 4.
 */
public class FindElementThatAppearsOnce {
  public static void main(String[] args) {
    int[] arr = {11, 3, 3, 3, 2, 4, 2, 4, 4, 5, 5, 2, 5};
    int result = findElementThatAppearsOnce(arr);
    System.out.println("Element that appears once: " + result);
  }

  /**
   * Given an integer array, in which each entry but one appears in triplicate, with the remaining
   * element appearing once, find the element appearing once. For example, if the array is (2,4,
   * 2,5,2,5,5), you should return 4.
   *
   * @param arr
   * @return
   */
  private static int findElementThatAppearsOnce(int[] arr) {

    int[] bitCount = new int[32];
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < bitCount.length; j++) {
        if ((arr[i] & (1 << j)) != 0) {
          bitCount[j]++;
        }
      }
    }
    int result = 0;
    for (int i = 0; i < bitCount.length; i++) {
      result |= (bitCount[i] % 3) * (1 << i);
    }
    return result;
  }
}
