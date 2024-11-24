package LLD.facebook;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private String groupName;
    private String groupId;
    private int totalMembers;
    private List<Member> members = new ArrayList<Member>();
    public Group(String name, String id, int totalMembers) {
        this.groupName = name;
        this.groupId = id;
        this.totalMembers = totalMembers;
    }
    public void addMember(Member member) {
        members.add(member);
    }
}
