package LLD.moviebooking;

import java.util.List;

public class Booking {
  private String bookingId;
  private List<ShowSeat> seats;
  private int numberOfSeats;
  private Payment payment;
  private BookingStatus status;

  public Booking(String bookingId, List<ShowSeat> seats) {
    this.bookingId = bookingId;
    this.seats = seats;
  }

  public boolean cancel(){
    return true;
  }
}
