package epp.binarySearch.revision;

/**
 * Design an 0(log n) algorithm for finding the position of an element k in a cyclically sorted
 * array of distinct elements.
 */
public class SearchInRotatedSortedArray {
  public static void main(String[] args) {
    // Test case 1
    testSearchInRotatedArray(new int[] {4, 5, 6, 7, 0, 1, 2}, 0, 4);

    // Test case 2
    testSearchInRotatedArray(new int[] {4, 5, 6, 7, 0, 1, 2}, 3, -1);

    // Test case 3
    testSearchInRotatedArray(new int[] {1}, 0, -1);

    // Test case 4
    testSearchInRotatedArray(new int[] {1, 3}, 3, 1);

    // Test case 5
    testSearchInRotatedArray(new int[] {5, 1, 2, 3, 4}, 1, 1);

    testSearchInRotatedArray(new int[] {0, 1, 2, 3, 4, 5, 6, 7}, 1, 1);
  }

  public static void testSearchInRotatedArray(int[] array, int target, int expected) {
    int result = searchInRotatedArray(array, target);
    System.out.println("Array: " + java.util.Arrays.toString(array));
    System.out.println("Target: " + target);
    System.out.println("Expected: " + expected);
    System.out.println("Result: " + result);
    System.out.println(result == expected ? "Test Passed" : "Test Failed");
    System.out.println();
  }

  public static int searchInRotatedArray(int[] values, int target) {

    int left = 0;
    int right = values.length - 1;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (values[mid] == target) {
        return mid;
      }
      // left part is sorted
      if (values[left] <= values[mid]) {
        if (values[left] <= target && target < values[mid]) {
          right = mid - 1;
        } else {
          left = mid + 1;
        }
      } else { // right part is sorted
        if ( values[mid] < target && target <= values[right]) {
          left = mid + 1;
        } else {
          right = mid - 1;
        }
      }
    }
    return -1;
  }
}
