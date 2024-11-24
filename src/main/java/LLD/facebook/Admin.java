package LLD.facebook;

public class Admin extends Person{


    public Admin(String name, Account account) {
        super(name, account);
    }

    public void blockUser(Member user) {}
    public void unblockUser(Member user) {}

    public void disablePage(Page page) {}
    public void enablePage(Page page) {}

    public void disableGroup(Group group) {}
    public void enableGroup(Group group) {}
}
