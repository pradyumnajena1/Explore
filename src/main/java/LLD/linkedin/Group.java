package LLD.linkedin;

import java.util.Date;
import java.util.List;

public class Group {
    private String id;
    private String name;
    private String description;
    private Date creationDate;
    private int totalMembers;
    private Member owner;
    private List<Member> members;


    public void addMember(Member member){}
}
