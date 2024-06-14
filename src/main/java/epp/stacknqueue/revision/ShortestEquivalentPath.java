package epp.stacknqueue.revision;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class ShortestEquivalentPath {

  public static void main(String[] args) {
    // Create a Deque to be used as a stack
    Deque<Integer> stack = new ArrayDeque<>();

    // Push elements onto the stack
    stack.push(1);
    stack.push(2);
    stack.push(3);
    stack.push(4);
    stack.push(5);

    // Get the descending iterator
    Iterator<Integer> descendingIterator = stack.descendingIterator();

    // Iterate over the elements in reverse order
    System.out.print("Elements in stack from top to bottom: ");
    while (descendingIterator.hasNext()) {
      System.out.print(descendingIterator.next() + " ");
    }
  }
  public static void main2(String[] args) {
    System.out.println(getShortestEquivalentPath("/usr/lib/../bin/gcc"));
    System.out.println(getShortestEquivalentPath("scripts/test/./../script2/awkscripts"));
  }

  private static String getShortestEquivalentPath(String path) {
    if (path == null || path.isEmpty()) {
      throw new IllegalArgumentException("invalid path.Path cant be null or empty");
    }
    Deque<String> pathNames = new ArrayDeque<>();
    if (path.startsWith("/")) {
      pathNames.push("/");
    }
    String[] tokens = path.split("/");
    for (String token : tokens) {
      if (token.equals("..")) {
        if (pathNames.isEmpty() || pathNames.peek().equals("..")) {
          pathNames.push(token);
        } else {
          if (pathNames.peek().equals("/")) {
            throw new IllegalStateException("Path error: trying to go up root " + path);
          }
          pathNames.pop();
        }
      } else if (!token.equals(".") && !token.isEmpty()) {
        pathNames.push(token);
      }
    }

    StringBuilder result = new StringBuilder();
    if (!pathNames.isEmpty()) {
      Iterator<String> it = pathNames.descendingIterator();
      String prev = it.next();
      result.append(prev);
      while (it.hasNext()) {
        if (!prev.equals("/")) {
          result.append("/");
        }
        String current = it.next();
        result.append(current);
        prev = current;
      }
    }
    return result.toString();
  }
}
