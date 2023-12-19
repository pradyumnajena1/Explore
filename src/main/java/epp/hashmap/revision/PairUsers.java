package epp.hashmap.revision;

import java.util.*;

public class PairUsers {
    public static void main(String[] args) {
        List<User> users = List.of(
                new User("u1",Set.of("attr1","attr3","attr11")),
                new User("u11",Set.of("attr1","attr3","attr11")),
                new User("u2",Set.of("attr1","attr13","attr11")),
                new User("u12",Set.of("attr1","attr13","attr11")),
                new User("u3",Set.of("attr11","attr3","attr13")),
                new User("u32",Set.of("attr13","attr3","attr11")),
                new User("u4",Set.of("attr12","attr3","attr11")),
                new User("u43",Set.of("attr12","attr3","attr11"))

        );
     Map<Set<String>, List<Queue<User>>> unpairedUsers =    getUnpairedUsers(users);
        System.out.println(unpairedUsers);
    }

    private static Map<Set<String>, List<Queue<User>>> getUnpairedUsers(List<User> users) {
        Map<Set<String>, List<Queue<User>>> mapping = new HashMap<>();
        for(User user:users){
            List<Queue<User>> userQueues = mapping.getOrDefault(user.attributes,  List.of(new ArrayDeque<>(),new ArrayDeque<>()));

            if(userQueues.get(0).isEmpty()){
                userQueues.get(0).offer(user);
            }else{

                   User polled = userQueues.get(0).poll();
                   polled.paired = user;
                   user.paired = polled;

                   userQueues.get(1).offer(polled);
                   userQueues.get(1).offer(user);

            }
            mapping.put(user.attributes,userQueues);

        }
        return mapping;
    }

    private static class User{
        String name;
        Set<String> attributes;
        User paired;

        public User(String name, Set<String> attributes, User paired) {
            this.name = name;
            this.attributes = attributes;
            this.paired = paired;
        }

        public User(String name, Set<String> attributes) {
            this.name = name;
            this.attributes = attributes;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                    .add("name='" + name + "'")
                    .add("attributes=" + attributes)
                    .add("paired=" + (paired!=null? paired.name : "null"))
                    .toString();
        }
    }
}
