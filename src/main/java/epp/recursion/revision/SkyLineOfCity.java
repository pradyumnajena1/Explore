package epp.recursion.revision;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicInteger;

public class SkyLineOfCity {
    public static void main(String[] args) {
        List<SkyLine> randomSkylines = getRandomSkylines(4, 1, 50, 20, 20);
        for(SkyLine skyLine:randomSkylines){
            System.out.println(skyLine);
        }
        List<SkyLine> skyLines = getSkyLines(randomSkylines);
        for(SkyLine skyLine:skyLines){
            System.out.println(skyLine);
        }

    }

    private static List<SkyLine> getSkyLines(List<SkyLine> randomSkylines) {

        return getSkyLines(randomSkylines, 0, randomSkylines.size() - 1);
    }

    private static List<SkyLine> getSkyLines(List<SkyLine> randomSkylines, int start, int end) {
        System.out.println(start+" "+end);
        if (start > end) {
            return new ArrayList<>();
        }
        if (start == end) {
            ArrayList<SkyLine> skyLines = new ArrayList<>();
            skyLines.add(randomSkylines.get(start));
            return skyLines;
        }
        int mid =start+ ( end-start) / 2;
        List<SkyLine> left = getSkyLines(randomSkylines, start, mid  );
        List<SkyLine> right = getSkyLines(randomSkylines, mid+1, end);
        List<SkyLine> merged = merge(left, right);


        return merged;
    }

    private static List<SkyLine> merge(List<SkyLine> left, List<SkyLine> right) {
        List<SkyLine> merged = new ArrayList<>();
        AtomicInteger leftIndex = new AtomicInteger(0);
        AtomicInteger rightIndex = new AtomicInteger(0);
        while (leftIndex.get() < left.size() && rightIndex.get() < right.size()) {

            SkyLine a = left.get(leftIndex.get());
            SkyLine b = right.get(rightIndex.get());
            if (a.right <= b.left) {
                merged.add(a);
                leftIndex.getAndIncrement();

            } else if (b.right <= a.left) {
                merged.add(b);
                rightIndex.getAndIncrement();

            } else if (a.left <= b.left) {
                mergeIntersectSkyline(merged, a, leftIndex, b, rightIndex);
            } else {
                mergeIntersectSkyline(merged, b, rightIndex, a, leftIndex);
            }
        }
        while (leftIndex.get() < left.size()) {
            merged.add(left.get(leftIndex.getAndIncrement()));
        }
        while (rightIndex.get() < right.size()) {
            merged.add(right.get(rightIndex.getAndIncrement()));
        }
        return merged;
    }

    private static void mergeIntersectSkyline(List<SkyLine> merged, SkyLine a, AtomicInteger aIndex, SkyLine b, AtomicInteger bIndex) {

        if (a.right <= b.right) {

            if (a.height > b.height) {

                if (a.right != b.right) {

                    b.left = a.right;
                    merged.add(a);
                    aIndex.getAndIncrement();
                } else {
                    bIndex.getAndIncrement();
                }


            } else if (a.height == b.height) {
                b.left = a.left;
                aIndex.getAndIncrement();
            } else {
                if (a.left != b.left) {

                    merged.add(new SkyLine(a.left, b.left, a.height));
                }
                aIndex.getAndIncrement();
            }

        } else {
            //a .right> b.right
            if (a.height >= b.height) {
                bIndex.incrementAndGet();
            } else {
                if (a.left != b.left) {
                    merged.add(new SkyLine(a.left, b.left, a.height));
                }
                a.left = b.right;
                merged.add(b);
                bIndex.incrementAndGet();
            }

        }

    }

    private static List<SkyLine> getRandomSkylines(int size, int minLeft, int maxRight, int maxWidth, int maxHeight) {
        List<SkyLine> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            int width = (int) (1 + Math.random() * maxWidth);
            int wall = (int) (Math.random() * (maxRight - minLeft));
            int height = (int) (Math.random() * maxHeight);
            int left;
            int right;
            if (wall + width <= maxRight) {
                left = wall;
                right = wall + width;
            } else {
                right = wall;
                left = wall - width;
            }
            result.add(new SkyLine(left, right, height));
        }
        return result;
    }

    private static class SkyLine {
        int left;
        int right;
        int height;

        public SkyLine(int left, int right, int height) {
            this.left = left;
            this.right = right;
            this.height = height;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", SkyLine.class.getSimpleName() + "[", "]")
                    .add("left=" + left)
                    .add("right=" + right)
                    .add("height=" + height)
                    .toString();
        }
    }
}
