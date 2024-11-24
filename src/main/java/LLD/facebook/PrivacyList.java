package LLD.facebook;

import LLD.parkingLot.User;

import java.util.Date;
import java.util.List;

public class PrivacyList {
    private Member owner;
    private Date createdDate;
    private List<Member> members;

    public PrivacyList(Member owner, Date createdDate) {
        this.owner = owner;
        this.createdDate = createdDate;
    }
    public  void addMember(Member member) {}
    public  void removeMember(Member member) {}
}
