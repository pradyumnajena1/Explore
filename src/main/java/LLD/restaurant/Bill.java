package LLD.restaurant;

public class Bill {
    private String billId;
    private double totalAmount;
    private Order order;
    private Payment payment;

    private boolean isPaid;

    public Bill(String billId, double totalAmount, Order order, Payment payment, boolean isPaid) {
        this.billId = billId;
        this.totalAmount = totalAmount;
        this.order = order;
        this.payment = payment;
        this.isPaid = isPaid;
    }
}
