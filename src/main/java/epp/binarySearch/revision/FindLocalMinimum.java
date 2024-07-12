package epp.binarySearch.revision;

/**
 * Let A be an unsorted array of n integers, with A[0] > A[l] and A[n - 2] < A[n-1]. Call an index i
 * a local minimum if A[i\ is less than or equal to its neighbors. How would you efficiently find a
 * local minimum, if one exists
 */
public class FindLocalMinimum {

  public static void main(String[] args) {
    int[] values = new int[] {8, 7, 2, 11, 34, 23, 4, 6, 10};
    int localMinIndex = findLocalMinimum(values);
    System.out.println(localMinIndex);
  }

  private static int findLocalMinimum(int[] values) {
    int left = 0;
    int right = values.length - 1;

    while (left < right) {
      int mid = left + (right - left) / 2;
      if ((mid == 0 || values[mid] <= values[mid - 1])
          && (mid == values.length - 1 || values[mid] <= values[mid + 1])) {
        return mid;
      } else if (mid>0 && values[mid] > values[mid - 1]) {
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }
    return left == right ? left : -1;
  }
}
