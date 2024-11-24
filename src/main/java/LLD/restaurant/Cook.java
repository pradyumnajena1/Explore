package LLD.restaurant;

public class Cook extends Employee{
    public Cook(String name, String email, String phoneNumber, String employeeId, String userName, String password) {
        super(name, email, phoneNumber, employeeId, userName, password);
    }

    public void prepareOrder(Order order) {}
}
