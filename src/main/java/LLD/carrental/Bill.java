package LLD.carrental;

import java.util.ArrayList;
import java.util.List;

public class Bill {
    private List<BillItem> billItems = new ArrayList<BillItem>();
    private Payment payment ;

    public double getTotalAmount() {
        return 0;
    }
    public void addBillItem(BillItem billItem) {
        billItems.add(billItem);
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
