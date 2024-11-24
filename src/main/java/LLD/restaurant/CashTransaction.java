package LLD.restaurant;

import java.util.Date;

public class CashTransaction extends Payment {
  public CashTransaction(String id, double amount, Date creationDate) {
    super(id, amount, creationDate);
  }
}
