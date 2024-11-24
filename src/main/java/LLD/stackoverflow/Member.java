package LLD.stackoverflow;

public class Member {
  private Account account;

  public Member(Account account) {
    this.account = account;
  }

  public String getEmail() {
    return account.getEmail();
  }

  public int getReputation() {
    return 0;
  }

  public void createQuestion(String question) {}
}
