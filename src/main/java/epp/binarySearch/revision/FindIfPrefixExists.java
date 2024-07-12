package epp.binarySearch.revision;

import java.util.Arrays;

/** Write a program which tests if p is a prefix of a string in an array of sorted strings */
public class FindIfPrefixExists {
  public static void main(String[] args) {

        checkPrefixExists(
            new String[] {
              "apple",
              "banana",
              "cherry",
              "date",
              "elderberry",
              "fig",
              "grape",
              "kiwi",
              "lemon",
              "mango",
              "nectarine",
              "orange",
              "papaya",
              "quince",
              "raspberry",
              "strawberry",
              "tangerine",
              "ugli",
              "victoria",
              "watermelon",
              "xigua",
              "yellow",
              "zucchini"
            },
            "app");

        checkPrefixExists(
            new String[] {
              "albatross",
              "bear",
              "cat",
              "dolphin",
              "elephant",
              "frog",
              "giraffe",
              "horse",
              "iguana",
              "jaguar",
              "kangaroo",
              "lion",
              "monkey",
              "newt",
              "octopus",
              "panda",
              "quail",
              "rabbit",
              "snake",
              "tiger",
              "umbrella",
              "vulture",
              "wolf",
              "xenopus",
              "yak",
              "zebra"
            },
            "dol");

        checkPrefixExists(
            new String[] {
              "blueberry", "blackberry", "cranberry", "gooseberry", "raspberry", "strawberry"
            },
            "cran");


        checkPrefixExists(
            new String[] {
              "alpha", "beta", "gamma", "delta", "epsilon", "zeta", "eta", "theta", "iota", "kappa",
              "lambda", "mu", "nu", "xi", "omicron", "pi", "rho", "sigma", "tau", "upsilon", "phi",
              "chi", "psi", "omega"
            },
            "omeg");

    // Test case 5

        checkPrefixExists(
            new String[] {
              "amazon",
              "ebay",
              "facebook",
              "google",
              "instagram",
              "linkedin",
              "microsoft",
              "snapchat",
              "twitter",
              "youtube"
            },
            "goo");
  }

  public static void checkPrefixExists(String[] sortedStrings, String prefix) {
    Arrays.sort(sortedStrings);
    int position = findPrefix(sortedStrings, prefix);
    System.out.println("Array: " + java.util.Arrays.toString(sortedStrings));
    System.out.println("Prefix: \"" + prefix + "\"");
    System.out.println("Is prefix present? " + (position>=0));
    System.out.println();
  }

  public static int findPrefix(String[] values, String prefix) {
    int left = 0;
    int right = values.length - 1;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (values[mid].startsWith(prefix)) {
        return mid;
      } else if (values[mid].compareTo(prefix) < 0) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    return -(left + 1);
  }
}
