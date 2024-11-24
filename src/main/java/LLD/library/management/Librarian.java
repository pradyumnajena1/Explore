package LLD.library.management;

public class Librarian extends Account {

    public boolean blockMember(Member member) {
        return true;
    }
    public boolean unblockMember(Member member) {
        return true;
    }
    public boolean addBookItem(BookItem bookItem) {
        return true;
    }
}
