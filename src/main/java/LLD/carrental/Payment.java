package LLD.carrental;

import LLD.carrental.enums.PaymentStatus;

import java.util.Date;

public class Payment {
    private Date creationDate;
    private double amount;
    private PaymentStatus status;

    public Payment(Date creationDate, double amount) {
        this.creationDate = creationDate;
        this.amount = amount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }
}
