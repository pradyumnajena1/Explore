package LLD.linkedin;

public class Person {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Account account;
    private Address address;

    public Person(String firstName, String lastName, String email, String phone, Account account) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.account = account;
    }
}
