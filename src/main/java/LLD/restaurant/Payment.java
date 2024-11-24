package LLD.restaurant;

import java.util.Date;

public class Payment {
    private String id;
    private double amount;
    private Date creationDate;
    public Payment(String id, double amount, Date creationDate) {
        this.id = id;
        this.amount = amount;
        this.creationDate = creationDate;
    }



    public void initTransaction(String id, double amount){

    }
}
