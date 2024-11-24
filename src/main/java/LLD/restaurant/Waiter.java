package LLD.restaurant;

public class Waiter extends Employee {
  public Waiter(
      String name,
      String email,
      String phoneNumber,
      String employeeId,
      String userName,
      String password) {
    super(name, email, phoneNumber, employeeId, userName, password);
  }

  public Order getOrder() {
    return null;
  }
}
