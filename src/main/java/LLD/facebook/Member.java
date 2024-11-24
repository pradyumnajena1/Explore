package LLD.facebook;

import java.util.Date;
import java.util.List;

public class Member extends Person{
    private String name;
    private Date dateOfMembership;

    private Photo profilePic;
    private Photo coverPhoto;
    private Profile profile;
    private List<PrivacyList> privacyList;


    public Member(String name, Account account) {
        super(name, account);
    }

    public Post createPost(){
        return null;
    }
    public Group createGroup(){
        return null;
    }
    public boolean joinGroup(Group group){
        return false;
    }
    public Comment createComment(Post post, String content){
        return null;
    }
    public Like createLike(Post post){
        return null;
    }
    public boolean sendMessage(Member member, String content){
        return false;
    }
    public boolean sendConnectionInvitation(Member member){
        return false;
    }
}
