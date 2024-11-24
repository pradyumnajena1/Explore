package LLD.carrental;

import LLD.carrental.Person;
import LLD.carrental.enums.AccountStatus;

public class Account {
    private int id;
    private AccountStatus status;
    private Person person;
    private String accountName;
    private String password;

    public Account(int id, AccountStatus status, Person person, String accountName, String password) {
        this.id = id;
        this.status = status;
        this.person = person;
        this.accountName = accountName;
        this.password = password;
    }
}
