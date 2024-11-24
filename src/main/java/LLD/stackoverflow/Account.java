package LLD.stackoverflow;

public class Account {
    private String username;
    private String email;
    private String password;
    private AccountStatus status;
    public Account(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public void resetPassword() {}

    public String getEmail() {
        return email;
    }
}
