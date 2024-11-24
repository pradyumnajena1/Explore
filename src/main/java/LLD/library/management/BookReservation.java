package LLD.library.management;

import LLD.library.management.enums.ReservationStatus;
import LLD.parkingLot.User;

import java.util.Date;

public class BookReservation {
    private Date creationDate;
    private Member member;
    private BookItem book;
    private ReservationStatus status;

    public static BookReservation fetchBookReservationDetails(BookItem item){
    // Implementation to fetch book reservation details
    return new BookReservation();
    }

    public Member getMember() {
        return member;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }
}
