package LLD.facebook;

public class Account {

  private String password;
  private String userName;
  private AccountStatus status;

  public Account(String password, String userName) {

    this.password = password;
    this.userName = userName;
  }

  public void setStatus(AccountStatus status) {
    this.status = status;
  }
}
