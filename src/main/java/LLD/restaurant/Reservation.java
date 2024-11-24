package LLD.restaurant;

import java.util.Date;

public class Reservation {
    private String reservationId;
    private Customer customer;
    private int peopleCount;
    private Table table;
    private Date creationDate;
    private ReservationStatus status;

    public Reservation(String reservationId, Customer customer, Table table, Date creationDate) {
        this.reservationId = reservationId;
        this.customer = customer;
        this.table = table;
        this.creationDate = creationDate;
    }

    public void updateStatus(ReservationStatus status){

    }

    public void setPeopleCount(int peopleCount) {
        this.peopleCount = peopleCount;
    }
}
