package LLD.restaurant;

import java.util.Date;

public class CreditCardTransaction  extends Payment{
    public CreditCardTransaction(String id, double amount, Date creationDate) {
        super(id, amount, creationDate);
    }
}
