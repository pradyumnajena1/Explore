package LLD.linkedin;

import java.util.ArrayList;
import java.util.List;

public class Member extends Person {

  private Profile profile = new Profile();
  private List<Member> follows = new ArrayList<Member>();
  private List<Member> connections = new ArrayList<Member>();

  public Member(String firstName, String lastName, String email, String phone, Account account) {
    super(firstName, lastName, email, phone, account);
  }

  public Post createPost(String content) {
    return null;
  }

  public void sendConnectionInvitation() {}

  public void createGroup(String group) {}

  public void joinGroup(Group group) {}

  public void applyForJob(JobPosting jobPosting) {}

  public void sendMessage(String message, Member to) {}
}
