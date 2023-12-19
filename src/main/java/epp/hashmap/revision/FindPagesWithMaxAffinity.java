package epp.hashmap.revision;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FindPagesWithMaxAffinity {
    public static void main(String[] args) {
        List<PageView> pageViews = getPageViews();

        Set<String> pages = getMaxAffinity(pageViews);
        System.out.println(pages);
    }

    private static Set<String> getMaxAffinity(List<PageView> pageViews) {
        Map<String, Set<String>> pageUserMapping =
                new HashMap<>();
        for(PageView pageView:pageViews){
            Set<String> users = pageUserMapping.getOrDefault(pageView.page, new HashSet<>());
            users.add(pageView.user);
            pageUserMapping.put(pageView.page, users);
        }
        System.out.println(pageUserMapping);
        List<Map.Entry<String, Set<String>>> list = pageUserMapping.entrySet().stream().collect(Collectors.toList());
        Set<String> maxAffinityPages = new HashSet<>();
        int maxIntersection = 0;
        for(int i=0;i< list.size();i++){
            for(int j=i+1;j< list.size();j++){

                HashSet<String> copy = new HashSet<>(list.get(i).getValue());
                copy.retainAll(list.get(j).getValue());
                Set<String> intersection =new HashSet<>(copy);

                if(maxIntersection<intersection.size()){
                    maxAffinityPages = Set.of(list.get(i).getKey(),list.get(j).getKey()) ;

                    maxIntersection = intersection.size();
                }

            }
        }
        return maxAffinityPages;
    }

    private static List<PageView> getPageViews() {
        List<PageView> pageViews = new ArrayList<>();
        List<String> pages = List.of("p1", "p2", "p3", "p4", "p5", "p6");
        List<String> users = List.of("u1", "u2", "u3", "u4", "u5", "u6");
        for (int i = 0; i < 30; i++) {
            String page = pages.get((int) (pages.size() * Math.random()));
            String user = users.get((int) (users.size() * Math.random()));
            PageView pageView = new PageView(page, user);
            pageViews.add(pageView);
        }
        return pageViews;
    }

    private static class PageView {
        String page;
        String user;

        public PageView(String page, String user) {
            this.page = page;
            this.user = user;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", PageView.class.getSimpleName() + "[", "]")
                    .add("page='" + page + "'")
                    .add("user='" + user + "'")
                    .toString();
        }
    }
}
