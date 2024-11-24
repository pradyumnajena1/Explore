package LLD.carrental;

import LLD.carrental.enums.AccountStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Receptionist extends Account{
    private Date dateJoined;



    public Receptionist(int id, AccountStatus status, Person person, String accountName, String password, Date dateJoined) {
        super(id, status, person, accountName, password);
        this.dateJoined = dateJoined;
    }

    public List<Account> searchMembers(){
        return new ArrayList<Account>();
    }
}
