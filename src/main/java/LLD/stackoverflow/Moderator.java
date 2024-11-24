package LLD.stackoverflow;

public class Moderator extends Member{
    public Moderator(Account account) {
        super(account);
    }
    public void closeQuestion(Question question) {}
    public void reOpenQuestion(Question question) {}
}
