package epp.binarysearchtree.revision;

import epp.binaryTree.BinaryTreeNode;
import epp.binaryTree.BinaryTreeUtils;
import epp.binarysearchtree.BSTUtils;

import java.util.*;

public class MostVisitedPageTracker {

    protected Map<String, Integer> pageCount;
    protected BinaryTreeNode<PageFrequency> root;

    public MostVisitedPageTracker() {
        pageCount = new HashMap<>();
    }

    public static void main(String[] args) throws InterruptedException {
        MostVisitedPageTracker mostVisitedPageTracker = new MostVisitedPageTracker();
        String[] uniquePages = {"hello5","hello4", "hello3",   "hello", "hello2", "hello1"};
        for (int i = 0; i < 50; i++) {
            String uniquePage = uniquePages[(int) (Math.random() * uniquePages.length)];

            mostVisitedPageTracker.addPage(uniquePage, System.currentTimeMillis());

        }

        List<String> mostVisitedPages = mostVisitedPageTracker.mostVisitedPages(3);
        System.out.println(mostVisitedPages);
        System.out.println(mostVisitedPageTracker.pageCount);
    }



    public void addPage(String page, long timeStamp) {

        if (!pageCount.containsKey(page)) {
            PageFrequency data = new PageFrequency(page, 1);
            root = BSTUtils.insertNode(root, data);
            pageCount.put(page, data.count);
        } else {
            incrementPageCount(page);

        }

    }

    protected void incrementPageCount(String page) {
        incrementPageCount(page, 1);
    }
    protected void decrementPageCount(String page) {
        incrementPageCount(page, -1);
    }

    private void incrementPageCount(String page, int i) {
        int frequency = pageCount.get(page);
        root = BSTUtils.deleteNode(root, new PageFrequency(page, frequency));
        pageCount.remove(page);

        PageFrequency newPageFrequency = new PageFrequency(page, frequency + i);
        if(newPageFrequency.count>0){
            root =BSTUtils.insertNode(root, newPageFrequency);
            pageCount.put(page, newPageFrequency.count);
        }

    }



    public List<String> mostVisitedPages(int max) {
        List<String> pages = reverseInOrderTraversal(root, max);
        return pages;
    }

    private List<String> reverseInOrderTraversal(BinaryTreeNode<PageFrequency> root, int max) {
        List<String> collector = new ArrayList<>();
        reverseInOrderTraversal(root, collector, max);
        return collector;
    }

    private void reverseInOrderTraversal(BinaryTreeNode<PageFrequency> root, List<String> collector, int max) {
        if (root != null) {
            reverseInOrderTraversal(root.right, collector, max);
            if (max > 0 && collector.size() == max) {
                return;
            }
            collector.add(root.data.page);
            reverseInOrderTraversal(root.left, collector, max);
        }
    }

    private static class PageFrequency implements Comparable<PageFrequency> {
        private String page;
        private int count;

        public PageFrequency(String page, int count) {
            this.page = page;
            this.count = count;
        }

        @Override
        public int compareTo(PageFrequency o) {
            return Integer.compare(this.count, o.count) ;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            PageFrequency that = (PageFrequency) o;

            if (count != that.count) return false;
            return Objects.equals(page, that.page);
        }

        @Override
        public int hashCode() {
            int result = page != null ? page.hashCode() : 0;
            result = 31 * result + count;
            return result;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ",   "[", "]").add("p='" + page + "'").add("c=" + count).toString();
        }
    }
}
