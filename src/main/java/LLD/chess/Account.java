package LLD.chess;

public class Account {
    private String userName;
    private String password;
    private AccountStatus status;

    public boolean resetPassword(String password){
        if(this.status == AccountStatus.ACTIVE){
            this.password = password;
            return true;
        }
        return false;
    }
}
