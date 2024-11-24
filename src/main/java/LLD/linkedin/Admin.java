package LLD.linkedin;

public class Admin extends Person {

  public Admin(String firstName, String lastName, String email, String phone, Account account) {
    super(firstName, lastName, email, phone, account);
  }

  public void blockMember(Member member) {}
  public void unblockMember(Member member) {}
  public void enableCompany(Company company) {}
  public void disableCompany(Company company) {}
}
