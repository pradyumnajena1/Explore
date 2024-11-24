package LLD.restaurant;

public class Employee extends Person{
    private String employeeId;
    private String userName;
    private String password;
    private AccountStatus status;

    public Employee(String name, String email, String phoneNumber, String employeeId, String userName, String password) {
        super(name, email, phoneNumber);
        this.employeeId = employeeId;
        this.userName = userName;
        this.password = password;
    }
    public boolean updatePassword(String password) {
        return true;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }
}
