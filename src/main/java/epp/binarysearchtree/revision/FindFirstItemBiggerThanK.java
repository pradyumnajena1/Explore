package epp.binarysearchtree.revision;

import epp.array.ArrayUtils;
import epp.binaryTree.BinaryTreeNode;
import epp.binaryTree.BinaryTreeUtils;
import epp.binarysearchtree.BSTUtils;

public class FindFirstItemBiggerThanK {
  public static void main(String[] args) {
    int[] ages = {2, 2, 8, 8, 9, 9, 9, 9, 9, 10};
    Person[] persons = new Person[ages.length];
    for (int i = 0; i < persons.length; i++) {
      persons[i] = new Person(ages[i], "P" + i);
    }
    ArrayUtils.printArray(persons);
    BinaryTreeNode<Person> root = BSTUtils.buildBSTFromSortedArray(persons);
    System.out.println(root);
    Person searchKey = new Person(ages[3]);
    BinaryTreeNode<Person> next = findFirstBiggerItem(root, searchKey);
    System.out.println(next != null ? next.data : null);

    next = findFirstBiggerItem2(root, searchKey);
    System.out.println(next != null ? next.data : null);
  }

  private static BinaryTreeNode<Person> findFirstBiggerItem(
      BinaryTreeNode<Person> root, Person person) {
    if (root == null) {
      return null;
    }
    if (root.data.age > person.age) {
      BinaryTreeNode<Person> leftResult = findFirstBiggerItem(root.left, person);
      return leftResult != null ? leftResult : root;
    }
    return findFirstBiggerItem(root.right, person);
  }

  private static BinaryTreeNode<Person> findFirstBiggerItem2(
      BinaryTreeNode<Person> root, Person person) {
    BinaryTreeNode<Person> current = root;
    BinaryTreeNode<Person> bigger = null;

    while (current != null) {
      int compare = current.data.compareTo(person);
      if (compare > 0) {
        bigger = current;
        current = current.left;
      } else {
        current = current.right;
      }
    }
    return bigger;
  }
}
